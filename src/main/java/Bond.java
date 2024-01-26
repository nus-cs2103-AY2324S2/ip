import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public final class Bond {

    public static final String line = "____________________________________________________________";

    private static final String logo = "Bond";

    private static final ArrayList<String> commands = new ArrayList<>() {
        {
            add("todo");
            add("deadline");
            add("event");
            add("list");
            add("mark");
            add("unmark");
            add("bye");
            add("delete");
        }
    };

    private static Boolean isValidCommand(String input) {
        return commands.contains(input.toLowerCase());
    }

    private static Boolean isNumber(String input) {
        char[] digits = input.toCharArray();
        Boolean isNumber = true;

        for (char c : digits) {
            if (!Character.isDigit(c)) {
                isNumber = false;
                break;
            }
        }

        return isNumber;
    }

    private static void addTask(String taskName, ArrayList<Task> taskList) {
        Task newTask = Task.makeTask(taskName);
        taskList.add(newTask);
        System.out.println(String.format(
                "%s\n\n    Got it. I've added this task:\n      %s \n    Now you have %d tasks in the list.\n%s\n",
                line, newTask.toString(), taskList.size(), line));
    }

    private static void deleteTask(int taskIndex, ArrayList<Task> taskList) {
        Task deletedTask = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        System.out.println(String.format(
                "%s\n\n    Got it. I've removed this task:\n      %s \n    Now you have %d tasks in the list.\n%s\n",
                line, deletedTask.toString(), taskList.size(), line));
    }

    public static void main(String[] args) {

        System.out.println(String.format("Hello! I'm %s. \nWhat can I do for you? \n%s\n", logo, line));

        Scanner sc = new Scanner(System.in);

        ArrayList<Task> taskList = new ArrayList<Task>();

        String userInput = "";

        while (true) {

            if (sc.hasNextLine()) {
                userInput = sc.nextLine();
            } else {
                break;
            }

            String[] components = userInput.split(" ");

            try {

                // Invalid Command syntax
                if (!Bond.isValidCommand(components[0])) {
                    BondException.raiseException("NA",
                            "INVALID_COMMAND_TYPE");
                }

                if (components[0].equalsIgnoreCase("todo")) {

                    // No valid task name specified for a todo task
                    if (components.length == 1) {
                        BondException.raiseException("todo", "EMPTY_DESCRIPTION");
                    }

                    Bond.addTask(userInput, taskList);

                } else if (components[0].equalsIgnoreCase("deadline")) {

                    if (components.length == 1) {
                        BondException.raiseException("deadline", "EMPTY_DESCRIPTION");
                    }

                    Bond.addTask(userInput, taskList);

                } else if (components[0].equalsIgnoreCase("event")) {

                    if (components.length == 1) {
                        BondException.raiseException("event", "EMPTY_DESCRIPTION");
                    }

                    Bond.addTask(userInput, taskList);

                } else if (components[0].equalsIgnoreCase("list")) {

                    if (components.length != 1) {
                        BondException.raiseException("list", "EXTRA_DETAILS");
                    }

                    ListIterator<Task> toprintln = taskList.listIterator();

                    System.out.println(String.format("%s\n\n    Here are the tasks in your list:", line));

                    while (toprintln.hasNext()) {
                        System.out.println(String.format("    %d. %s",
                                toprintln.nextIndex() + 1, toprintln.next().toString()));
                    }

                    System.out.println(line + "\n");

                } else if (components[0].equalsIgnoreCase("mark")) {

                    if (components.length == 1) {
                        BondException.raiseException("mark", "MISSING_INDEX");
                    } else if (!isNumber(components[1])) {
                        BondException.raiseException("mark", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.size()) {
                        BondException.raiseException("mark", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    taskList.get(index).markAsComplete();

                    System.out
                            .println(String.format(
                                    "%s\n\n    Nice! I've marked this task as done:\n      %s \n%s\n",
                                    line, taskList.get(index).toString(), line));

                } else if (components[0].equalsIgnoreCase("unmark")) {

                    if (components.length == 1) {
                        BondException.raiseException("unmark", "MISSING_INDEX");
                    } else if (!isNumber(components[1])) {
                        BondException.raiseException("unmark", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.size()) {
                        BondException.raiseException("unmark", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    taskList.get(index).markAsIncomplete();

                    System.out
                            .println(String.format(
                                    "%s\n\n    OK, I've marked this task as not done yet:\n      %s \n%s\n",
                                    line, taskList.get(index).toString(), line));

                } else if (components[0].equalsIgnoreCase("bye")) {

                    if (components.length != 1) {
                        BondException.raiseException("bye", "EXTRA_DETAILS");
                    }

                    System.out.println(String.format("%s\n\nBye. Hope to see you again soon! \n%s", line, line));
                    sc.close();
                    System.exit(0);
                } else if (components[0].equalsIgnoreCase("delete")) {

                    if (taskList.isEmpty()) {
                        BondException.raiseException("delete", "EMPTY_LIST");
                    } else if (components.length == 1) {
                        BondException.raiseException("delete", "MISSING_INDEX");
                    } else if (!isNumber(components[1])) {
                        BondException.raiseException("delete", "INVALID_INDEX");
                    } else if (Integer.parseInt(components[1]) - 1 >= taskList.size()) {
                        BondException.raiseException("delete", "INVALID_INDEX");
                    }

                    int index = Integer.parseInt(components[1]) - 1;

                    Bond.deleteTask(index, taskList);
                }

            } catch (BondException e) {
                System.out.println(String.format("%s\n\n    %s\n%s\n", line, e.getMessage(), line));
                continue;
            }
        }
    }
}
