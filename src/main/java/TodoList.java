import tasks.Task;
import tasks.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class TodoList {
    List<Task> list = new ArrayList<>();

    public void add(Task item) {
        list.add(item);
        int numberOfTasks = list.size();
        line();
        System.out.println("Got it dawg. I've added this task: \n");
        System.out.println(item.toString() + "\n");
        System.out.println("Now you got "+ numberOfTasks + " tasks fam \n");
        line();
    }

    public void mark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsDone();
            line();
            System.out.println("Marked item " + index + " as done dawg.");
            line();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            line();
            System.out.println("Invalid input for 'mark' command dawg.");
            line();
        }
    }

    public void unmark(String input) {
        try {
            int index = Integer.parseInt(input);
            list.get(index - 1).markAsUndone();
            line();
            System.out.println("Unmarked item " + index + " as done.");
            line();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            line();
            System.out.println("Invalid input for 'unmark' command.");
            line();
        }
    }

    public void delete(String input) {
        try {
            int index = Integer.parseInt(input);
            String task = list.get(index - 1).toString();
            list.remove(index - 1);
            line();
            System.out.println("Deleted item: \n" + task + "\n");
            System.out.println("Now you have " + list.size() + " tasks left in the list.");
            line();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            line();
            System.out.println("Invalid input for 'delete' command.");
            line();
        }
    }

    public void print() {
        line();
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString() + "\n");
        }
        line();
    }

    public String printTasksToString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).formattedString()).append("\n");
        }
        return sb.toString();
    }

    public void saveToFile(String filename) {
        String tasksText = printTasksToString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(tasksText);
            System.out.println("Tasks have been saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void loadData(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            list.clear(); // Clear the existing list before loading from file
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                list.add(task);
            }

            System.out.println("Tasks have been loaded from " + filename);
        } catch (IOException e) {
            list.clear();
        }
    }

    // Example of parsing task lines and creating Task objects
    private Task createTaskFromLine(String line) {
        String[] parts = line.split("\\|");

        if (parts.length >= 3) {
            String taskType = parts[0];
            int completionStatus = Integer.parseInt(parts[1]);
            String taskDetails = parts[2];

            // Depending on the task type, you may need to parse additional details
            if (taskType.equals("T")) {
                Task todo = new TaskFactory().createTask(TaskType.TODO, taskDetails);
                if (completionStatus == 1) {
                    todo.markAsDone();
                }
                return todo;
            } else if (taskType.equals("D")) {
                // Extract deadline date if available
                String deadlineDate = (parts.length > 3) ? parts[3] : null;
                Task deadline = new TaskFactory().createTask(TaskType.DEADLINE, taskDetails, deadlineDate);
                if (completionStatus == 1) {
                    deadline.markAsDone();
                }
                return deadline;
            } else if (taskType.equals("E")) {
                // Extract event time if available

                Task event = new TaskFactory().createTask(TaskType.EVENT, taskDetails, parts[3], parts[4]);
                if (completionStatus == 1) {
                    event.markAsDone();
                }
                return event;
            }
        }

        return null; // Handle invalid lines if needed
    }

    private static void line() {
        System.out.println("_______________________\n");
    }
}