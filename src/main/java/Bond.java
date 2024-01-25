import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class Bond {

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

    public static void main(String[] args) {

        String line = "____________________________________________________________";

        String logo = "Bond";
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

            if (components.length == 1) {

                if (userInput.equalsIgnoreCase("bye")) {

                    System.out.println(String.format("%s\n\nBye. Hope to see you again soon! \n%s", line, line));
                    sc.close();
                    System.exit(0);

                } else {

                    if (userInput.equalsIgnoreCase("list")) {

                        ListIterator<Task> toprintln = taskList.listIterator();

                        System.out.println(String.format("%s\n\n    Here are the tasks in your list:\n", line));

                        while (toprintln.hasNext()) {
                            System.out.println(String.format("    %d. %s",
                                    toprintln.nextIndex() + 1, toprintln.next().toString()));
                        }

                        System.out.println(line + "\n");

                    } else {
                        System.out.println(String.format("\n    Invalid query syntax detected! \n%s", line));
                    }
                }
            } else if (components.length == 2 && components[0].contains("mark")) {

                if (components[0].equalsIgnoreCase("mark")) {

                    if (isNumber(components[1])) {
                        int index = Integer.parseInt(components[1]) - 1;

                        if (index < taskList.size()) {
                            taskList.get(index).markAsComplete();
                            System.out
                                    .println(String.format(
                                            "%s\n\n    Nice! I've marked this task as done:\n      %s \n%s\n",
                                            line, taskList.get(index).toString(), line));
                        } else {
                            System.out.println(String.format("\n    Invalid index! \n%s", line));
                        }
                    } else {
                        System.out.println(
                                String.format("\n    An integer is expected for the index of a task! \n%s\n", line));
                    }
                } else if (components[0].equalsIgnoreCase("unmark")) {

                    if (isNumber(components[1])) {
                        int index = Integer.parseInt(components[1]) - 1;

                        if (index < taskList.size()) {
                            taskList.get(index).markAsIncomplete();
                            System.out
                                    .println(String.format(
                                            "%s\n\n    OK, I've marked this task as not done yet:\n      %s \n%s\n",
                                            line, taskList.get(index).toString(), line));
                        } else {
                            System.out.println(String.format("\n    Invalid index! \n%s", line));
                        }
                    } else {
                        System.out.println(
                                String.format("\n    An integer is expected for the index of a task! \n%s", line));
                    }
                } else {
                    System.out.println(String.format("\n    Invalid query syntax detected! \n%s", line));
                }
            } else if (components[0].equalsIgnoreCase("todo") || components[0].equalsIgnoreCase("deadline")
                    || components[0].equalsIgnoreCase("event")) {

                Task newTask = Task.makeTask(userInput);
                taskList.add(newTask);
                System.out.println(String.format(
                        "%s\n\n    Got it. I've added this task:\n      %s \n    Now you have %d tasks in the list.\n%s\n",
                        line, newTask.toString(), taskList.size(), line));
            } else {
                System.out.println(String.format("\n    Invalid query syntax detected! \n%s", line));
            }
        }
    }
}
