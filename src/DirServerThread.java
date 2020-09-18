import java.net.*;
import java.io.*;

public class DirServerThread extends Thread {
    private Socket socket = null;
    private boolean isConsole = true;

    public DirServerThread(Socket socket) {
        super("DirServerThread");
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     socket.getInputStream()));
        ) {

            String inputLine, outputLine;
            String request = "";
            String path = "";
            String userAgent = "";

            while ((inputLine = in.readLine()) != null) {

                request += inputLine + "\n";

                if (path.equals("") && request.contains("GET")) {
                    path = DirUtils.getPath(request);
                    System.out.println(path);
                }

                if (request.contains("User-Agent")) {
                    userAgent = DirUtils.getUserAgent(request);
                    System.out.println(userAgent);

                    isConsole = userAgent.equals("Console");
                    System.out.println(isConsole);

                    break;
                }
            }


            if (isConsole) {
                String body = DirUtils.getListing(path, isConsole);
                if (body.equals("Invalid Directory"))
                    throw new Exception("Invalid Directory");
                out.println(body);
            } else {
                System.out.println(in.readLine());

                String topPart = "<!DOCTYPE html><html><body><ul>";
                String bottomPart = "</ul></body></html>";
                path = path.replace("/", "\\");
                String body = DirUtils.getListing("C:" + path, isConsole);


                if (body.equals("Invalid Directory"))
                    throw new Exception("Invalid Directory");

                out.write("HTTP/1.0 200 OK\n");
                out.flush();
                out.write("Content-Type: text/html\n\n");
                out.flush();

                out.write("" + topPart + body + bottomPart);
                out.flush();
            }

            socket.close();
        } catch (Exception e) {
            System.out.println("Exception in thread: " + Thread.currentThread().getId() + "\nMessage: " + e.getMessage() + "\n");
        }
    }
}

