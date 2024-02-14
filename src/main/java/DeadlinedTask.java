public class DeadlinedTask extends Task {

    private String deadline;

    public DeadlinedTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {

        String taskString = "[D] ";

        String temp = "[ ] ";

        if(this.isDone()){
            temp = "[X] ";
        }

        return taskString + temp + this.getName() + "(by: " + this.deadline + ")";
    }
}
