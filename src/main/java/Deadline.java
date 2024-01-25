public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static void addTask(String description) {
        String[] tokens = description.split("/");
        String[] tokens2 = tokens[0].split(" ");
        String taskName = "";
        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        Deadline curr = new Deadline(taskName.strip(), tokens[1].split(" ")[1]);
        Duke.taskList.add(curr);
        System.out.println("Yer task has been added: \n  " + curr);
        System.out.println("Now you have " + Duke.taskList.size() + " task(s) in the list.");
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

