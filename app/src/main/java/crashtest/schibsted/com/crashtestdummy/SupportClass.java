package crashtest.schibsted.com.crashtestdummy;

public class SupportClass {
    public static void crash(RuntimeException ex) {
        throw ex;
    }
}
