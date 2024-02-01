public enum Messages {
    GREET("Hello! I'm %s.\nWhat can I do for you?"),
    LIST("Here are the tasks in your list:%s"),
    INVALID_TASK_NUMBER("Please enter a valid task number!"),
    OUT_OF_RANGE_TASK_NUMBER("Task selected does not exist.\nTask number must be between 1 to %d"),
    MARKED("Nice! I've marked this task as done:\n  %s"),
    UNMARKED("OK, I've marked this task as not done yet:\n  %s"),
    REMOVE("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list."),
    ADD("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list."),
    MISSING_TASK_NAME("Please enter a NAME for your %s task!"),
    INVALID_DEADLINE_DATE("Please enter a valid DATE and TIME (%s)\nfor your Deadline task!"),
    INVALID_EVENT_FROM("Please enter a valid START DATE and TIME (%s)\nfor your Event task!"),
    INVALID_EVENT_TO("Please enter a valid END DATE and TIME (%s)\nfor your Event task!"),
    INVALID_CMD("I don't understand what you mean by \"%s\"\nPlease request something like:\n" + 
            "  bye, list, mark, delete, todo, deadline, event."),
    BYE("Bye. Hope to see you again soon!");
    private String msg;
    private final String DIVIDER = "_".repeat(100) + "\n";

    private Messages(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return DIVIDER + this.msg + "\n" + DIVIDER;
    }
}
