package jakebacker.webfiletransfer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
	private HttpServer server;

	private Server(int port, String suffix) throws IOException{
		server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/" + suffix, httpExchange -> {

		});
		server.setExecutor(null);
	}

	public Server() throws IOException{
		int port =  ThreadLocalRandom.current().nextInt(0, 9999 + 1);

		for (int i=0;i<5;i++) {
			if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 0) {
				// Rand int
			} else {
				// Rand char
			}
		}
	}
}
