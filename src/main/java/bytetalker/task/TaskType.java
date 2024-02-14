package bytetalker.task;

/**
 * Represents the types of task supported by the chatbot.
 *
 * @author Junseo Kim
 * @version 1.0
 * @since 2024-02-06
 */
public enum TaskType {
    TODO("T") {},
    DEADLINE("D") {},
    EVENT("E") {};

    TaskType(String icon) {
        this.icon = icon;
    }

    private String icon;

    /**
     * Returns the icon representing each type of task.
     *
     * @return String Icon that represents that type of task.
     */
    public String getIcon() {
        return this.icon;
    }
}
