import java.io.*;
import java.net.*;

/**
 * DirClient Class
 */
public class DirClient {

    // variable to hold directory name
    private String dirName = null;

    /**
     * Constructor of DirClient Class
     *
     * @param dirName - Path of directory
     */
    public DirClient(String dirName) {
        this.dirName = dirName;
    }

    /**
     * Connect to Server with socket.
     * Send HTTP request with directory path
     * Get the files & folder lists at the directory
     *
     * @return files & folder lists at the directory as JSON
     */
    public String getListing() {
        // variable to store the result from server.
        StringBuilder result = new StringBuilder();

        // Initialize socket, output stream, input stream.
        try (Socket socket = new Socket("localhost", 8086);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // Variable to read from server
            String fromServer;

            // Send HTTP request to server.
            out.println("GET " + dirName + " HTTP/1.0\r\n");
            out.println("User-Agent: Console");

            // Read from server and store to result variable
            while ((fromServer = in.readLine()) != null) {
                result.append(fromServer).append("\n");
            }
        } catch (Exception e) {
            // Print error message
            System.err.println("Error\n" + e);
        }

        // return the files & folder lists at the directory
        return result.toString();
    }
}
