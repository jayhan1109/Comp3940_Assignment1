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
            if (isConsole) {
                String inputLine, outputLine;
                inputLine = in.readLine();
                File dir = new File(inputLine);
                String[] chld = dir.list();
                if (chld == null) {
                    out.println("Invalid Directory\n");
                } else {
                    for (int i = 0; i < chld.length; i++) {
                        String fileName = chld[i];
                        out.println(fileName + "\n");
                    }
                }
            } else {
                System.out.println(in.readLine());
               
                out.write("HTTP/1.0 200 OK\n");
                out.flush();
                out.write("Content-Type: text/html\n\n");
                out.flush();

                out.write("<h1>Welcome!.</h1>");
                out.flush();
            }

            socket.close();
        } catch (Exception e) {
            System.out.println("Exception in thread: " + Thread.currentThread().getId() + "\nMessage: " + e.getMessage() + "\n");
        }
    }
}

