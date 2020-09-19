import java.net.*;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * DirServer Class
 */
public class DirServer {
    /**
     * Main method
     */
    public static void main(String[] args) throws IOException {
        boolean listening = true;

        // Thread Pool
        ExecutorService threadPool = Executors.newCachedThreadPool();

        /**
         * Initialize server socket
         * Create DirServerThread when there's request to access the server
         */
        try (ServerSocket serverSocket = new ServerSocket(8086)) {
            while (listening)
                threadPool.execute(new DirServerThread(serverSocket.accept()));
        } catch (Exception e) {
            // Print error message
            System.err.println("Error Creating Socket\n");
            System.exit(-1);
        }
    }
}
