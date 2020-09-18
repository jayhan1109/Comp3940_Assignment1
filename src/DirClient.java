import java.io.*;
import java.net.*;

public class DirClient {

    private String dirName = null;

    public DirClient(String dirName) {
        this.dirName = dirName;
    }

    public String getListing() {
        String result = "";
        try (Socket socket = new Socket("localhost", 8086);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String fromServer;
            out.println("GET " + dirName + " HTTP/1.0\r\n");
            out.println("User-Agent: Console");

            while ((fromServer = in.readLine()) != null) {
                result += fromServer + "\n";
            }
        } catch (Exception e) {
            System.err.println("Error\n");
        }
        return result;
    }
}
