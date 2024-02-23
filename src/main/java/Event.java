public class Event extends Task {
    private static String TASK_TYPE = "[E]";
    private static String EVENT = "event";


    public Event(String name) {
        super(name);
    }

    public Event(String name, String isDone) {
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

        return EVENT + " " + isDoneString + " " + this.name;
    }
}
