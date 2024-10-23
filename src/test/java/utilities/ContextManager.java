package utilities;

public class ContextManager {

    private static ThreadLocal<String> message = new ThreadLocal<>();

    public static void setMessage(String msg) {
        message.set(msg);

    }

    public static String getMessage() {
        return message.get();
    }

    public static void clear() {
        message.remove();

    }
}
