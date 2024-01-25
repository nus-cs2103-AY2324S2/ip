import java.util.List;
import java.util.ArrayList;
public class TaskManager {
    private List<Task> taskList;
    public TaskManager() {
        this.taskList = new ArrayList<>();
    }
    public void addTask(String taskDescription, TaskType type) {
        Task task;
        switch (type) {
            case TODO:
                task = new Todo(taskDescription);
                break;
            case EVENT:
                task = new Event(taskDescription);
                break;
            case DEADLINE:
                task = new Deadline(taskDescription);
                break;
            default:
                throw new IllegalArgumentException("Invalid task type");
        }

        task.setTaskDescription(taskDescription);
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
        int count = 1;
        for (Task task : taskList) {
            System.out.println(Commands.INDENTATION + count + ".  [" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.getTaskDescription());
        }
    }

    private void printMarkTask(int index) {
        Task task = taskList.get(index);
        System.out.println(Commands.INDENTATION + "Nice! I've marked this task as done: ");
        System.out.println(Commands.INDENTATION + "  [" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.getTaskDescription());
    }

    private void printUnmarkTask(int index) {
        Task task = taskList.get(index);
        System.out.println(Commands.INDENTATION + "OK, I've marked this task as not done yet: ");
        System.out.println(Commands.INDENTATION + "  [" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.getTaskDescription());
    }

    private void printAddedTask(int index) {
        Task task = taskList.get(index);
        System.out.println(Commands.INDENTATION + "Got it. I've added this task: " + task.getTaskDescription());
        System.out.println(Commands.INDENTATION + "  [" + task.getTaskType() + "][" + task.getStatusIcon() + "] " + task.getTaskDescription());
        System.out.println(Commands.INDENTATION + "Now you have " + taskList.size() + " tasks in the list.");
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
