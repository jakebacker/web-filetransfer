package jakebacker.webfiletransfer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Server {
	private HttpServer server;

	public Server(File file) throws IOException{
		int port =  ThreadLocalRandom.current().nextInt(0, 9999 + 1);
		String suffix = "";

		for (int i=0;i<5;i++) {
			if (ThreadLocalRandom.current().nextInt(0, 1 + 1) == 0) {
				// Rand int
				suffix += ThreadLocalRandom.current().nextInt(0, 9 + 1);
			} else {
				// Rand char
				suffix += (char)ThreadLocalRandom.current().nextInt(97,  122 + 1);
			}
		}

		InetSocketAddress address = new InetSocketAddress(port);

		server = HttpServer.create(address, 0);
		server.createContext("/" + suffix, httpExchange -> {
			/*ArrayList<String> types = new ArrayList<>();
			types.add("application/octet-stream");

			httpExchange.getResponseHeaders().put("Content-Type", types);*/

			ArrayList<String> types2 = new ArrayList<>();
			types2.add("attachment; filename=\""+ file.getName() + "\"");
			httpExchange.getRequestHeaders().put("Content-Disposition", types2);

			for(List<String> l : httpExchange.getRequestHeaders().values()) {
				for (String s : l) {
					System.out.print(s);
				}
				System.out.println();
			}

			httpExchange.sendResponseHeaders(200, file.length());

			System.out.println("Connection");

			OutputStream out = httpExchange.getResponseBody();
			FileInputStream in = new FileInputStream(file);
			byte[] buffer = new byte[4096];
			int length;
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
			in.close();
			out.flush();

			server.stop(0);
		});

		server.setExecutor(null);
		server.start();

		System.out.println("Server Started at " + InetAddress.getLocalHost().getHostAddress()+ ":" + port + "/" + suffix);
	}
}
