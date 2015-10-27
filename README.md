# spray-app

a simple way to start a Spray application that also allows the JVM to exit in case of a binding failure.

## Example

	import spray.routing._
	import xyz.hyperreal.sprayapp.SprayApp

	object SprayAppMain extends SprayApp {

		startServer( interface = "localhost", port = 8080 ) {

			(get & pathSingleSlash) {
				complete {
					<html>
						<head>
							<title>Spray App Test</title>
						</head>
						<body>
							<h1>Spray App Test</h1>
						</body>
					</html>
				}
			}
		}

	}

The `interface` and `port` parameters default to `localhost` and `8080`, respectively.

## Usage

Just include

	resolvers += "Hyperreal Repository" at "https://dl.bintray.com/edadma/maven"

	libraryDependencies += "xyz.hyperreal" %% "spray-app" % "0.1"

in your build.sbt.

## License

MIT license