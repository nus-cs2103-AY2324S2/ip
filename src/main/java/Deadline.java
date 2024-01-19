class Deadline extends Task {
    private final String deadLine;

    public static Deadline buildDeadLine(String input) {
        String[] splitedInput = input.split("/");
        String taskDescription = splitedInput[0];
        taskDescription = taskDescription.substring(8).trim();
        String[] tmp = splitedInput[1].split(" ");
        return new Deadline(taskDescription, tmp[1]);
    }

    public Deadline(String description, String deadLine) {
        super(description);
        this.deadLine = deadLine;

    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadLine + ")";
    }
}
