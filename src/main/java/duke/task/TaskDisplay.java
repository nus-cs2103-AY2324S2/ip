package duke.task;

import java.util.List;

public class TaskDisplay {
    private static final String INDENTATION = "    ";
    private static final String LINE = "    -----------------------------------------------------------------------------------------";

    public void displayTasks(List<Task> taskList, String input) {
        String[] tokens = input.split(" ");
        String command = tokens[0].toLowerCase();

        System.out.println(LINE);

        if (!command.equalsIgnoreCase("list") && tokens.length == 1) {
            printErrorMessage(input);
            return;
        }

        int index;
        switch (command) {
            case "list":
                printTaskList(taskList);
                break;
            case "mark":
                index = Integer.parseInt(tokens[1]) - 1;
                printMarkTask(taskList, index);
                break;
            case "unmark":
                index = Integer.parseInt(tokens[1]) - 1;
                printUnmarkTask(taskList, index);
                break;
            case "delete":
                index = Integer.parseInt(tokens[1]) - 1;
                printDeletedTask(taskList, index);
                break;
            default:
                index = taskList.size() - 1;
                printAddedTask(taskList, index);
                break;
        }

        System.out.println(LINE);
    }

    private void printTaskList(List<Task> taskList) {
        System.out.println(INDENTATION + " Here are the tasks in your list:");
        int count = 1;
        for (Task task : taskList) {
            System.out.println(INDENTATION + "    " + count + ". " + task.getTaskIcon()  + task.getStatusIcon() + " " + task.getTaskDescription());
            count++;
        }
    }

    private void printMarkTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            return;
        }
        Task task = taskList.get(index);
        System.out.println(INDENTATION + "  Nice! I've marked this task as done: ");
        System.out.println(INDENTATION  + "    " + task.getTaskIcon() + task.getStatusIcon() + task.getTaskDescription());
    }

    private void printUnmarkTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            return;
        }
        Task task = taskList.get(index);
        System.out.println(INDENTATION + "  OK, I've marked this task as not done yet: ");
        System.out.println(INDENTATION  + "    " + task.getTaskIcon() + task.getStatusIcon() + task.getTaskDescription());
    }

    private void printDeletedTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            System.out.println(INDENTATION + "  Sorry, I believe the TASK NUMBER you specified doesn't exist.");
            return;
        }
        Task deletedTask = taskList.get(index);
        System.out.println(INDENTATION + "  Noted. I've removed this task:");
        System.out.println(INDENTATION + "     " + deletedTask.getTaskIcon() + deletedTask.getStatusIcon() + deletedTask.getTaskDescription());
        System.out.println(INDENTATION + "  Now you have " + (taskList.size() - 1) + " tasks left in this list.");
    }

    private void printAddedTask(List<Task> taskList, int index) {
        if (index < 0 || index >= taskList.size()) {
            return;
        }
        Task task = taskList.get(index);
        System.out.println(INDENTATION + "  Got it. I've added this task: " + task.getTaskDescription());
        System.out.println(INDENTATION + "    " + task.getTaskIcon() + task.getStatusIcon() + task.getTaskDescription());
        System.out.println(INDENTATION + "  Now you have " + taskList.size() + " tasks in the list.");
    }

    public void printErrorMessage(String input) {
        System.out.println(INDENTATION + "  Sorry, the description of " + input.toUpperCase() + " cannot be empty. Please add details, so that I can");
        System.out.println(INDENTATION + "  assist you better!");
        System.out.println(LINE);
    }
}

