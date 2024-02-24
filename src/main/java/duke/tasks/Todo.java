package duke.tasks;
public class Todo extends Task {
    private static String TASK_TYPE = "[T] ";
    private static String COMPLETED_MESSAGE_END = " is complete!";
    private static String INCOMPLETE_MESSAGE_END = " has yet to be completed.";
    private static String TODO = "todo";

    public Todo(String name) {
        super(name);
    }

    public Todo(String name, String isDone) {
        super(name, isDone);
    }    

    @Override
    public String checkStatus() {
        if (this.checkDone()) {
            return TASK_TYPE + this.getName() + COMPLETED_MESSAGE_END;
        } else {
            return TASK_TYPE + this.getName() + INCOMPLETE_MESSAGE_END;
        }
    }

    public String getAttributes() {
        String isDoneString = "";
        if (this.isDone) {
            isDoneString = "true";
        } else {
            isDoneString = "false";
        }

        return TODO + " " + isDoneString + " " + this.name;
    }
}
