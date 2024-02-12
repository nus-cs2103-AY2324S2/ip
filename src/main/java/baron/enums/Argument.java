package baron.enums;

/**
 * Enum for possible argument types that a user can input.
 */
public enum Argument {
    BY("/by"),
    FROM("/from"),
    TO("/to");

    private final String arg;

    Argument(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }
}
