import java.net.*;
import java.io.*;

/**
 * DirServerThread Class
 */
public class DirServerThread extends Thread {
    // Variables
    private Socket socket = null;
    private boolean isConsole;
    DirUtils dirUtils = new DirUtils();

    /**
     * Constructor of DirServerThread Class
     *
     * @param socket - Server socket
     */
    public DirServerThread(Socket socket) {
        super("DirServerThread");
        this.socket = socket;
    }

    /**
     * Get request from Client with directory path
     * Parse the flies and folder lists at the directory path
     * Return the lists to Client
     */
    public void run() {
        // Initialize socket, output stream, input stream
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     socket.getInputStream()));
        ) {

            // Variables
            String inputLine;
            String request = "";
            String path = "";
            String userAgent = "";

            // Read from Client
            while ((inputLine = in.readLine()) != null) {

                request += inputLine + "\n";

                // Parse path from request
                if (path.equals("") && request.contains("GET")) {
                    path = dirUtils.getPath(request);
                }

                // Parse User-Agent from request
                if (request.contains("User-Agent")) {
                    userAgent = dirUtils.getUserAgent(request);

                    // If User-Agent is from console
                    // Set isConsole variable to ture
                    isConsole = userAgent.equals("Console");

                    // Break while loop after parsing User-Agent
                    break;
                }
            }

            // Check if the
            if (isConsole) {
                String body = dirUtils.getListing(path, isConsole);
                out.println(body);
            } else {
                String topPart = "<!DOCTYPE html><html><body><h2 style=\"\n" +
                        "        color: #444;\n" +
                        "        width: 300px;\n" +
                        "        text-align: center;\n" +
                        "        font-family: sans-serif;\n" +
                        "        font-size: 40px;\n" +
                        "      \">Here's The List</h2><ul style=\"list-style-type: none\">";
                String bottomPart = "</ul></body></html>";
                String body;

                // Check the user's Operating System.
                String OS;
                OS = System.getProperty("os.name");
                if (OS.startsWith("Windows")) {
                    path = path.replace("/", "\\");
                    body = dirUtils.getListing("C:" + path, isConsole);
                } else {
                    body = dirUtils.getListing("/usr" + path, isConsole);
                }

                // Throw error if directory path is invalid
                if (body.equals("Invalid Directory")) {
                    out.write("HTTP/1.0 200 OK\n");
                    out.write("Content-Type: text/html\n\n");
                    out.write("<!DOCTYPE html><html><body><h2 style=\"\n" +
                            "        color: #444;\n" +
                            "        width: 300px;\n" +
                            "        text-align: center;\n" +
                            "        font-family: sans-serif;\n" +
                            "        font-size: 40px;\n" +
                            "      \">Invalid Directory</h2>" + bottomPart);
                    out.flush();
                } else {
                    // Return to Client with files and folder lists
                    out.write("HTTP/1.0 200 OK\n");
                    out.flush();
                    out.write("Content-Type: text/html\n\n");
                    out.flush();

                    out.write("" + topPart + body + bottomPart);
                    out.flush();
                }
            }

            // Close socket
            socket.close();
        } catch (Exception e) {
            // Print error message.
            System.out.println("Exception in thread: " + Thread.currentThread().getId() + "\nMessage: " + e.getMessage() + "\n");
            Thread.currentThread().interrupt();
        }
    }
}

