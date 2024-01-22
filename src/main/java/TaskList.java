import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    protected static int tasksCount = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String input) {
        tasks.add(convertTask(input));
        tasksCount ++;

    }

    public Task convertTask(String input) {
        String[] inputs = input.split("\\s+", 2);
        String command = inputs[0];
        String description = inputs[1];

        if (command.startsWith("todo")) {
            return new ToDo(description);
        } else if (command.startsWith("deadline")) {
            String[] parts = description.split("/by", 2);
            return new Deadline(parts[0], parts[1]);
        } else {
            String[] parts = description.split("\\s+/from\\s+|\\s+/to\\s+");
            return new Event(parts[0], parts[1], parts[2]);
        }
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1)+ ". " + tasks.get(i).getDescription());
        }
    }

    public String getTaskDescription(int num) {
        return tasks.get(num).getDescription();
    }

    public void markTask(int num, boolean status) {
        tasks.get(num).mark(status);
    }

}
