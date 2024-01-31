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
    INVALID_DEADLINE_DATE("Please enter a DATE for your Deadline task!"),
    INVALID_EVENT_FROM("Please enter a START DATE for your Event task!"),
    INVALID_EVENT_TO("Please enter a END DATE for your Event task!"),
    INVALID_CMD("I don't understand what you mean by \"%s\"\nPlease request something like: list, mark, delete, todo, deadline, event, etc."),
    BYE("Bye. Hope to see you again soon!");
    private String msg;

    Messages(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n" + 
            this.msg + 
            "\n____________________________________________________________\n";
    }
}
