import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Numerator {
    public static String line = "____________________________________________________________";


    public static void main(String[] args) {

        String logo =
                "Hello! I'm Numerator\n" +
                        "What can I do for you?";
        System.out.println(logo);

        Scanner sc = new Scanner(System.in);

        TaskList taskList = new TaskList();

        String input;
        while (true) {
            if (sc.hasNext()) {
                input = sc.nextLine();
                System.out.println(line);
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    System.out.println(line);
                    break;
                } else if (input.startsWith("mark ")) {
                    String[] inputArray = input.split(" ");
                    try {
                        int taskNum = Integer.parseInt(inputArray[1]) - 1;
                        taskList.markAsDone(taskNum);
                        System.out.println("Nice! I've marked this task as done:");
                        taskList.printTask(taskNum);
                    } catch (NumberFormatException e) {
                        System.out.print("Please input a valid number");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist");
                    }

                } else if (input.startsWith("unmark ")) {
                    String[] inputArray = input.split(" ");
                    try {
                        int taskNum = Integer.parseInt(inputArray[1]) - 1;
                        taskList.markAsUndone(taskNum);
                        System.out.println("OK, I've marked this task as not done yet:");
                        taskList.printTask(taskNum);
                    } catch (NumberFormatException e) {
                        System.out.print("Please input a valid number");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist");
                    }
                } else if (input.startsWith("todo ")) {
                    String taskDesc = input.substring("todo ".length());
                    Task t = taskList.addToDo(taskDesc);
                    taskList.printAddTask(t);

                } else if (input.startsWith("deadline ")) {
                    Pattern p = Pattern.compile("deadline (.+) /by (.+)");
                    Matcher m = p.matcher(input);
                    if (!m.matches()) {
                        System.out.println("Please use the format: deadline <task> /by <date>");
                    } else {
                        String taskDesc = m.group(1);
                        String by = m.group(2);
                        Task t = taskList.addDeadline(taskDesc, by);
                        taskList.printAddTask(t);
                    }

                } else if (input.startsWith("event ")) {
                    Pattern p = Pattern.compile("event (.+) /from (.+) /to (.+)");
                    Matcher m = p.matcher(input);
                    if (!m.matches()) {
                        System.out.println("Please use the format: event <task> /from <date> /to <date>");
                    } else {
                        String taskDesc = m.group(1);
                        String from = m.group(2);
                        String to = m.group(3);
                        Task t = taskList.addEvent(taskDesc, from, to);
                        taskList.printAddTask(t);
                    }
                } else if (input.equals("list")) {
                    System.out.println(taskList);
                } else {
                    System.out.println("Input not recognised");
                }
                System.out.println(line);
            } else {
                break;
            }
        }
        sc.close();


    }
}

