package xyz.hyperreal.sprayapp

import akka.actor.{Actor, ActorSystem, Props, ActorRefFactory}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout

import spray.can.Http
import spray.routing._

import concurrent.duration._


trait SprayApp extends App with HttpService {

 @volatile private[this] var _refFactory: Option[ActorRefFactory] = None

  implicit def actorRefFactory = _refFactory getOrElse sys.error(
    "Route creation is not fully supported before `startServer` has been called" )
	
	def startServer( interface: String = "localhost", port: Int = 8080, service: String = "web-service", akka: ActorSystem = ActorSystem("on-spray-can") )( r: Route ) = {
		
		import akka.dispatcher
		
		val serviceActor = akka.actorOf( Props{
        new Actor {
          _refFactory = Some(context)
          def receive = runRoute(r)
				}
		}, service )

		implicit val timeout = Timeout(5 seconds)
		implicit val system = akka
		
		(IO(Http) ? Http.Bind(serviceActor, interface = interface, port = port)).map {
			case _: Http.CommandFailed => akka.shutdown
		}
		
		akka.dispatcher
		
	}
	
}
