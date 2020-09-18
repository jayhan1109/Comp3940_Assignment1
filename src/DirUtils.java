import com.google.gson.Gson;
import java.io.*;

public class DirUtils {

    public String getListing(String path, Boolean isConsole) {
        String dirList = "";
        File dir = new File(path);
        String[] chld = dir.list();

        if (chld == null) {
            return "Invalid Directory";
        }

        if (isConsole) {
            for (int i = 0; i < chld.length; i++) {
                dirList += chld[i] + "\n";
            }

            // Return as JSON
            Gson gsonObj = new Gson();
            dirList = gsonObj.toJson(dirList);

        } else {
            for (int i = 0; i < chld.length; i++) {
                dirList += "<li><button>" + chld[i] + "</button></li>";
            }
        }

        return dirList;
    }


    public String getPath(String request) {
        int start = request.indexOf("GET ") + 4;
        int end = request.indexOf(" HTTP/");
        request = request.substring(start, end);
        return request;
    }

    public String getUserAgent(String request) {
        int start = request.indexOf("User-Agent: ") + 12;
        request = request.substring(start, start + 7);
        return request;
    }
} 