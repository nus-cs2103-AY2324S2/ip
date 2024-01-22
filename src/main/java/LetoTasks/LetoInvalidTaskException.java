package LetoTasks;

public class LetoInvalidTaskException extends Exception {
    private final String message;

    public LetoInvalidTaskException(String message) {
        this.message = message;
    }
    public void printException() {
        System.out.println("  <<Duke Leto>>\n   >> We have a problem! " + this.message);
    }
}
