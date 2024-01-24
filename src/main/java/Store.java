public class Store {
    private Task[] storage = new Task[100];
    private int count = 0;

    public String addText(String text) {
        String[] tokens = text.split("/");
        String type_and_description = tokens[0];
        String type = type_and_description.split(" ")[0];
        String description = type_and_description.substring(type.length());
        Task task;
        if (type.equals("todo")) {
            task = new ToDo(description);
        } else if (type.equals("deadline")) {
            String by = tokens[1];
            task = new Deadline(description, by);
        } else {
            String from = tokens[1];
            String to = tokens[2];
            task = new Event(description, from, to);
        }

        storage[count++] = task;
        return "Got it. I've added this task: \n" + task.toString() + "\n" +
                "Now you have " + this.count + (count > 1 ? " tasks " : " task ") +
                "in the list. \n";
    }
    public void displayStore() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; ++i) {
            System.out.println((i + 1) + "." + storage[i].toString());
        }
        System.out.println();
    }

    public void markTask(int i) {
        Task task = storage[i - 1];
        task.markAsDone();
    }

    public void unmarkTask(int i) {
        Task task = storage[i - 1];
        task.markAsNotDone();
    }



}