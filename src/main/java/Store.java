import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Store {
    private final ArrayList<Task> storage;
    private int count;
    private final HashSet<String> taskTypes;

    public Store(String[] types) {
        this.storage = new ArrayList<>();
        this.count = 0;
        this.taskTypes = new HashSet<>();
        this.taskTypes.addAll(List.of(types));

    }

    public String addTask(String text) throws DiboException {
        String[] tokens = text.split("/");
        String type_and_description = tokens[0];
        String type = type_and_description.split(" ")[0];

        if (!taskTypes.contains(type)) {
            throw new DiboException("Oh no sir! There is no such task type :(");
        }

        String description = type_and_description.substring(type.length());
        if (description.equals("")) {
            throw new DiboException("Oh no sir! We need a description for your task. "
                    + "This will enable us to better keep track of your tasks.");
        }

        Task task;
        switch (type) {
        case "todo":
            task = new ToDo(description);
            break;
        case "deadline":
            try {
                String by = tokens[1];
                task = new Deadline(description, by);
            } catch (IndexOutOfBoundsException e) {
                throw new DiboException("Oh no sir! Please state the deadline of the task :D");
            }
            break;
        default:
            try {
                String from = tokens[1];
                String to = tokens[2];
                task = new Event(description, from, to);
            } catch (IndexOutOfBoundsException e) {
                throw new DiboException("Oh no sir! Please state the start and end of the task :D");
            }
        }

        this.addTask(task);

        return "Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + this.count + (count > 1 ? " tasks " : " task ")
                + "in the list.\n";
    }

    public void addTask(Task t) {
        this.storage.add(t);
        this.count++;
    }
    public void displayStore() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; ++i) {
            System.out.println((i + 1) + "." + storage.get(i).toString());
        }
    }

    public String getFullList() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < count; ++i) {
            Task task = storage.get(i);
            list.append(task.getDetails());
            list.append("\n");
        }
        return list.toString();
    }

    public void markTask(int i) {
        Task task = storage.get(i - 1);
        task.markAsDone();
    }

    public void unmarkTask(int i) {
        Task task = storage.get(i - 1);
        task.markAsNotDone();
    }

    public void deleteTask(int i) {
        System.out.println("Noted. I'm removing this task: \n" + storage.get(i - 1).toString()
                + "Now you have " + --count + (count > 1 ? " tasks " : " task ")
                + "in the list.");
        storage.remove(i - 1);
    }



}