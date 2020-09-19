import java.lang.reflect.Method;

/**
 * Activity Class
 */
public class Activity {

    // variable to hold directory name
    private String dirName = null;

    /**
     * Constructor of Activity Class
     *
     * @param dirName - Path of directory
     */
    public Activity(String dirName) {
        this.dirName = dirName;
    }

    /**
     * Main method
     */
    public static void main(String[] args) {
        new Activity(args[0]).onCreate();
    }

    /**
     * Reflection - Print DirClient method lists
     * Create DirClient instance with passing dirName as a parameter
     * and run getListing method to print the files and folder lists
     * at the directory path.
     */
    public void onCreate() {
        try {
            // Reflection - Print DirClient method lists
            Class cls = Class.forName("DirClient");
            Method[] methodList = cls.getDeclaredMethods();
            System.out.println("===============================================");
            System.out.println("DirClient Class Method List");
            for (Method m : methodList) {
                System.out.println(m);
            }
            System.out.println("===============================================");

            // Create DirClient instance
            System.out.println(new DirClient(this.dirName).getListing());

        } catch (ClassNotFoundException e) {
            // If DirClient is not exist, print error message.
            System.out.println("Can't find the Class");
        }
    }
}
