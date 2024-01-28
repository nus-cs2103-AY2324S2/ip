import task.Task;

import java.nio.file.Path;
import java.time.format.DateTimeParseException;
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

        Storage storage = new Storage(Path.of("data", "storage.txt"), taskList);
        storage.load();

        String input;
        while (true) {
            if (sc.hasNext()) {
                input = sc.nextLine();
                System.out.println(line);
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    System.out.println(line);
                    break;
                } else if (input.startsWith("mark")) {
                    try {
                        Pattern p = Pattern.compile("mark (\\d+)");
                        Matcher m = p.matcher(input);
                        if (!m.find()) {
                            System.out.println("Please use the format: mark <task number>");
                        } else {
                            int taskNum = Integer.parseInt(m.group(1)) - 1;
                            taskList.markAsDone(taskNum);
                            System.out.println("Nice! I've marked this task as done:");
                            taskList.printTask(taskNum);
                        }
                        storage.save();
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist");
                    }

                } else if (input.startsWith("unmark")) {
                    try {
                        Pattern p = Pattern.compile("unmark (\\d+)");
                        Matcher m = p.matcher(input);
                        if (!m.find()) {
                            System.out.println("Please use the format: unmark <task number>");
                        } else {
                            int taskNum = Integer.parseInt(m.group(1)) - 1;
                            taskList.markAsUndone(taskNum);
                            System.out.println("OK, I've marked this task as not done yet:");
                            taskList.printTask(taskNum);
                            storage.save();
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist");
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        Pattern p = Pattern.compile("delete (\\d+)");
                        Matcher m = p.matcher(input);
                        if (!m.find()) {
                            System.out.println("Please use the format: delete <task number>");
                        } else {
                            int taskNum = Integer.parseInt(m.group(1)) - 1;
                            Task t = taskList.removeTask(taskNum);
                            System.out.println("Noted. I've removed this task:");
                            System.out.printf("%s\n", t);
                            System.out.printf("Now you have %d tasks in the list\n", taskList.taskList.size());
                            storage.save();
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task does not exist");
                    }
                } else if (input.startsWith("todo")) {
                    Pattern p = Pattern.compile("todo (\\S+.*)");
                    Matcher m = p.matcher(input);
                    if (!m.find()) {
                        System.out.println("Please use the format: todo <task>");
                    } else {
                        String taskDesc = m.group(1);
                        Task t = taskList.addToDo(taskDesc);
                        taskList.printAddTask(t);
                        storage.save();
                    }

                } else if (input.startsWith("deadline")) {
                    Pattern p = Pattern.compile("deadline (\\S+.*) /by (\\S+.*)");
                    Matcher m = p.matcher(input);
                    if (!m.find()) {
                        System.out.println("Please use the format: deadline <task> /by <date>");
                    } else {
                        String taskDesc = m.group(1);
                        String by = m.group(2);
                        try {
                            Task t = taskList.addDeadline(taskDesc, by);
                            taskList.printAddTask(t);
                            storage.save();
                        } catch (DateTimeParseException e) {
                            System.out.println("The date should be in the format: yyyy/MM/dd or yyyy/MM/dd HH:mm");
                        }
                    }

                } else if (input.startsWith("event")) {
                    Pattern p = Pattern.compile("event (\\S+.*) /from (\\S+.*) /to (\\S+.*)");
                    Matcher m = p.matcher(input);
                    if (!m.find()) {
                        System.out.println("Please use the format: event <task> /from <date> /to <date>");
                    } else {
                        String taskDesc = m.group(1);
                        String from = m.group(2);
                        String to = m.group(3);
                        Task t = taskList.addEvent(taskDesc, from, to);
                        taskList.printAddTask(t);
                        storage.save();
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
        storage.save();
        sc.close();


    }
}

