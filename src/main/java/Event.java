public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static void addTask(String description) {
        String[] tokens = description.split("/");
        String[] tokens2 = tokens[0].split(" ");
        String taskName = "";
        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        Event curr = new Event(taskName.strip(), tokens[1], tokens[2]);
        Duke.taskList.add(curr);
        System.out.println("Yer task has been added: \n  " + curr);
        System.out.println("Now you have " + Duke.taskList.size() + " task(s) in the list.");
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + from + to + ")" ;
    }
}
