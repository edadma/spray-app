package xyz.hyperreal.sprayapp

import spray.routing._


object SprayAppMain extends SimpleRoutingApp {
	
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
