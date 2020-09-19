import java.lang.reflect.Method;

public class Activity {

    private String dirName = null;

    public static void main(String[] args) {

        new Activity(args[0]).onCreate();
    }

    public Activity(String dirName) {
        this.dirName = dirName;
    }

    public void onCreate() {
        try {
            Class cls = Class.forName("DirClient");
            Method[] methodList = cls.getDeclaredMethods();
            System.out.println("===============================================");
            System.out.println("DirClient Class Method List");
            for (Method m : methodList) {
                System.out.println(m);
            }
            System.out.println("===============================================");

            
            System.out.println(new DirClient(this.dirName).getListing());
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find the Class");
        }
    }
}
