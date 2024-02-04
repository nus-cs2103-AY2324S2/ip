package enums;

/**
 * Enum representing different types of tasks in the application.
 */
public enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String symbol;

    TaskType(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Gets the symbol associated with the task type.
     *
     * @return The symbol representing the task type.
     */
    public String getSymbol() {
        return symbol;
    }
}
