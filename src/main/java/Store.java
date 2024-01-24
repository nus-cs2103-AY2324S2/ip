import java.util.ArrayList;
import java.util.HashSet;

public class Store {
    private ArrayList<Task> storage = new ArrayList<>();
    private int count = 0;
    private HashSet<String> taskType = new HashSet<>();

    public Store(String[] types) {
        for (String type : types) {
            taskType.add(type);
        }
    }

    public String addText(String text) throws DukeException{
        String[] tokens = text.split("/");
        String type_and_description = tokens[0];
        String type = type_and_description.split(" ")[0];
        if (!taskType.contains(type)) {
            throw new DukeException("Oh no! There is no such task :(");
        }
        String description = type_and_description.substring(type.length());
        if (description.equals("")) {
            throw new DukeException("Oh no! We need a description for your task:(");
        }
        Task task;
        if (type.equals("todo")) {
            task = new ToDo(description);
        } else if (type.equals("deadline")) {
            try {
                String by = tokens[1];
                task = new Deadline(description, by);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Oh no! Please state the deadline of the task :D");
            }
        } else  {
            try {
                String from = tokens[1];
                String to = tokens[2];
                task = new Event(description, from, to);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Oh no! Please state the start and end of the task :D");
            }
        }

        storage.add(task);
        count++;
        return "Got it. I've added this task: \n" + task.toString() + "\n" +
                "Now you have " + this.count + (count > 1 ? " tasks " : " task ") +
                "in the list. \n";
    }
    public void displayStore() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < count; ++i) {
            System.out.println((i + 1) + "." + storage.get(i).toString());
        }
        System.out.println();
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
        System.out.println("Noted. I'm removing this task: \n" + storage.get(i - 1).toString() +
                "Now you have " + --count + (count > 1 ? " tasks " : " task ") + "in the list.");
        storage.remove(i - 1);
    }



}