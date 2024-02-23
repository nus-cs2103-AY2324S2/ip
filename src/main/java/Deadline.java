public class Deadline extends Task {
    private static String TASK_TYPE = "[D]";
    private static String DEADLINE = "deadline";
    
    // Deadline class needs to be able to parse dates
    public Deadline(String name) {
        super(name);
    }

    public Deadline(String name, String isDone) {
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

        return DEADLINE + " " + isDoneString + " " + this.name;
    }
}