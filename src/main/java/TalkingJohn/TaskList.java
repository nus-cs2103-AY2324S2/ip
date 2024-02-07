package TalkingJohn;

import java.util.List;
import java.util.Objects;

/**
 * Provides methods to perform various actions on the tasks.
 * It interacts with the user interface to display messages and handles user inputs.
 */
public class TaskList {
    private final List<Task> taskArr;
    private final Ui ui;

    /**
     * Constructs a TaskList object with the specified list of tasks and user interface.
     *
     * @param taskArr The list of tasks.
     * @param ui      The user interface.
     */
    public TaskList(List<Task> taskArr, Ui ui) {
        this.taskArr = taskArr;
        this.ui = ui;
    }

    /**
     * Converts a string input to an integer.
     *
     * @param input The input string.
     * @return The integer value parsed from the input.
     */
    public int convertInt(String input) {
        return Integer.parseInt(input.split(" ", 2)[1]);
    }

    /**
     * Handles the user input by performing corresponding actions on the task list.
     *
     * @param input The user input.
     */
    public void action(String input) {
        if (Objects.equals(input, "list")) {
            if (!taskArr.isEmpty()) {
                for (int i = 0; i < taskArr.size(); i++) {
                    System.out.println((i + 1) + ". " + taskArr.get(i) + "\n");
                }
            } else {
                ui.emptyInput("list");
            }
        }else if (input.startsWith("delete") && input.length() > 6) {
            try {
                int i = convertInt(input);
                Task deleted = taskArr.remove(i - 1);
                System.out.println("Noted. I've removed this task:\n" + deleted + "\nNow you have " + taskArr.size() + " tasks in the list.");
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! Invalid delete expression.");
            }
        } else if (input.startsWith("mark") && input.length() > 4) {
            try {
                int i = convertInt(input);
                Task taskToMark = taskArr.get(i - 1);
                taskToMark.mark();
                System.out.println("Nice! I've marked this task as done:\n" + taskToMark);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! Invalid mark expression.");
            }
        } else if (input.startsWith("unmark") && input.length() > 6) {
            try {
                int i = convertInt(input);
                Task taskToUnmark = taskArr.get(i - 1);
                taskToUnmark.unMark();
                System.out.println("OK, I've marked this task as not done yet:\n" + taskToUnmark);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! Invalid unmark expression.");
            }
        } else if (input.startsWith("todo") && input.length() > 4 && input.charAt(4) == ' ') {
            String whatToDo = input.split(" ", 2)[1];
            Todo toDo = new Todo(whatToDo);
            taskArr.add(toDo);
            ui.printAddTask(toDo, taskArr.size());
        } else if (input.startsWith("deadline") && input.length() > 8) {
            try {
                String[] parts = input.split(" ", 2);
                String buffer = parts[1];
                String[] secPart = buffer.split("/", 2);
                Deadline deadline = new Deadline(secPart[0], secPart[1]);
                taskArr.add(deadline);
                ui.printAddTask(deadline, taskArr.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OH NO! It seems like the format is wrong. Did you include at least 1 '/' in the description?");
            }
        } else if (input.startsWith("event") && input.length() > 5) {
            try {
                String[] parts = input.split(" ", 2);
                String buffer = parts[1];
                String[] secPart = buffer.split("/", 3);
                Event event = new Event(secPart[0], secPart[1], secPart[2]);
                taskArr.add(event);
                ui.printAddTask(event, taskArr.size());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OH NO! It seems like the format is wrong. Did you include at least 2 '/' in the description?");
            }
        } else if (input.startsWith("find") && input.length() > 4) {
            try {
                String[] parts = input.split(" ", 2);
                String toFind = parts[1];
                int i = 1;
                for (Task task : taskArr) {
                    if (task.toString().contains(toFind)) {
                        System.out.println((i) + ". " + task + "\n");
                        i++;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OH NO! It seems like the format is wrong.");
            }

        } else {
            ui.invalidInput();
        }
    }
}