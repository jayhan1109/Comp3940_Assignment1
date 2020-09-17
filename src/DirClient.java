import java.io.*;
import java.net.*;

public class DirClient {
    public static void main(String[] args) {
        String result = "";
        try (Socket socket = new Socket("localhost", 8086);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            String fromServer;
            out.println("GET " + args[0] + " HTTP/1.0\r\n");
            out.println("User-Agent: Console");

            while ((fromServer = in.readLine()) != null) {
                result += fromServer + "\n";
            }
        } catch (Exception e) {
            System.err.println("Error\n");
        }
        System.out.println(result);
    }
}
