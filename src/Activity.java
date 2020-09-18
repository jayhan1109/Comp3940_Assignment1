public class Activity {

    private String dirName = null;

    public static void main(String[] args) {
        new Activity(args[0]).onCreate();
    }

    public Activity(String dirName) {
        this.dirName = dirName;
    }

    public void onCreate() {
        System.out.println(new DirClient(this.dirName).getListing());
    }
}
