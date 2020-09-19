import com.google.gson.Gson;

import java.io.*;

/**
 * DirUtils Class
 */
public class DirUtils {

    /**
     * Get files & folder lists at the directory
     * return as JSON if the request is from console
     * return as html if the request is from browser
     *
     * @param path      - Directory path
     * @param isConsole - check if the request is from console
     * @return files & folder lists at the directory
     */
    public String getListing(String path, Boolean isConsole) {
        // Variable to store files & folder lists at the directory
        String dirList = "";

        // Search at the directory
        File dir = new File(path);
        String[] chld = dir.list();

        // If there's nothing inside return error message.
        if (chld == null) {
            return "Invalid Directory";
        }

        // If the request is from console return as JSON
        // Or return as html format.
        if (isConsole) {
            for (String s : chld) {
                dirList += s + "\n";
            }

            Gson gson = new Gson();
            dirList = gson.toJson(dirList);

        } else {
            for (String s : chld) {
                dirList += "<li style=\"margin-bottom:30px\">" +
                        "<button style=\"\n" +
                        "            color: white;\n" +
                        "            background-color: #444;\n" +
                        "            outline: none;\n" +
                        "            border: none;\n" +
                        "            border-radius: 5px;\n" +
                        "            padding: 7px 15px; \n" +
                        "            font-family: sans-serif;\n" +
                        "            font-size: 20px;" +
                        "\">"
                        + s
                        + "</button>" +
                        "</li>";
            }
        }

        // Return the lists.
        return dirList;
    }

    /**
     * Parse path from the request
     *
     * @param request - HTTP request
     * @return path
     */
    public String getPath(String request) {
        int start = request.indexOf("GET ") + 4;
        int end = request.indexOf(" HTTP/");
        request = request.substring(start, end);
        return request;
    }

    /**
     * Parse User-Agent from the request
     *
     * @param request - HTTP request
     * @return User-Agent
     */
    public String getUserAgent(String request) {
        int start = request.indexOf("User-Agent: ") + 12;
        request = request.substring(start, start + 7);
        return request;
    }
} 