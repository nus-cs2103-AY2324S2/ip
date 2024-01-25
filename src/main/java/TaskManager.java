import java.util.List;
import java.util.ArrayList;
public class TaskManager {
    private List<Task> taskList;
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }
    public void addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        taskList.add(task);
    }

    public void markAsComplete(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.markAsComplete();
        }
    }

    public void markAsIncomplete(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            task.markAsIncomplete();
        }
    }

    public void displayTask(String input) {
        String[] tokens = input.split(" ");
        String command = tokens[0].toLowerCase();

        System.out.println(Commands.LINE);

        switch (command) {
            case "list":
                printTaskList();
                break;
            case "mark":
                if (tokens.length > 1 && isNumeric(tokens[1])) {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    printMarkTask(index);
                } else {
                    System.out.println("Sorry, please define the task number so that I can assist you better.");
                }
                break;
            case "unmark":
                if (tokens.length > 1 && isNumeric(tokens[1])) {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    printUnmarkTask(index);
                } else {
                    System.out.println("Sorry, please define the task number so that I can assist you better.");
                }
                break;
            default:
                int index = taskList.size() - 1;
                printAddedTask(index);
                break;
        }

        System.out.println(Commands.LINE);
    }

    public void printTaskList() {
        System.out.println(Commands.INDENTATION + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(Commands.INDENTATION + "  " + (i + 1) + ". [" + task.getStatusIcon() + "] " + task.getTaskDescription());
        }
    }

    private void printMarkTask(int index) {
        Task task = taskList.get(index);
        System.out.println(Commands.INDENTATION + "Nice! I've marked this task as done: ");
        System.out.println(Commands.INDENTATION + "  [" + task.getStatusIcon() + "]" + " " + task.getTaskDescription());
    }

    private void printUnmarkTask(int index) {
        Task task = taskList.get(index);
        System.out.println(Commands.INDENTATION + "OK, I've marked this task as not done yet: ");
        System.out.println(Commands.INDENTATION + "  [" + task.getStatusIcon() + "]" + " " + task.getTaskDescription());
    }

    private void printAddedTask(int index) {
        Task task = taskList.get(index);
        System.out.println(Commands.INDENTATION + "added: " + task.getTaskDescription());
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
