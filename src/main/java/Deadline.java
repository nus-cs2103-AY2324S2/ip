class Deadline extends Task {
    private String deadline;

    public String getDeadline() {
        return deadline;
    }

    public Deadline(String description, boolean hasDone, String deadline) {
        this.deadline = deadline;
        super.setDescription(description);
        super.setHasDone(hasDone);
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public static Deadline buildDeadLine(String input) {
        String[] splitedInput = input.split("/");
        String taskDescription = splitedInput[0];
        taskDescription = taskDescription.substring(8).trim();
        String[] tmp = splitedInput[1].split(" ");
        return new Deadline(taskDescription, tmp[1]);
    }

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;

    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
