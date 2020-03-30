package matyk.webtasker;

public class HasherUtils {
    public static String combineHash(String salt, String hash) {
        return "1000" + ":" + salt + ":" + hash;
    }
}
