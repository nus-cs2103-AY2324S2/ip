import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;


public class Duke {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Taro\n"
                + "What can I do for you?\n";

        System.out.println(greeting);

        Scanner s = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String input = s.nextLine();
            String[] words = input.split("\\s+");
            String command = words[0];

            if (command.equals("bye")) {
                break;

            } else if (command.equals("list")) {
                int num = 1;
                Iterator<Task> it = list.iterator();
                System.out.println("Here are the tasks in the list:");
                while (it.hasNext()) {
                    System.out.println(num + "." + it.next().toString());
                    num++;
                }

            } else if (command.equals("mark")) {
                int index = Integer.parseInt(words[1]);
                if (index < 0 || index > list.size()) {
                    System.out.println("Please enter index ranging from 1 to " + String.valueOf(list.size()));
                } else {
                    list.get(index - 1).markAsDone();
                }

            } else if (command.equals("unmark")) {
                int index = Integer.parseInt(words[1]);
                if (index < 0 || index > list.size()) {
                    System.out.println("Please enter index ranging from 1 to " + String.valueOf(list.size()));
                } else {
                    list.get(index - 1).markAsUndone();
                }
            } else if (command.equals("delete")) {
                int index = Integer.parseInt(words[1]);
                if (index < 0 || index > list.size()) {
                    System.out.println("Please enter index ranging from 1 to " + String.valueOf(list.size()));
                } else {
                    System.out.println("Ok. I'll be removing this task:\n "
                            + list.get(index - 1).toString()
                            + "\n"
                            + "Now you have " + String.valueOf(list.size() - 1) + " task(s) left");
                    list.remove(index-1);
                }

            } else if (command.equals("todo")) {
                String description = input.trim().substring("todo".length()).trim();
                if (description.isEmpty()) {
                    System.out.println("Please add a description for todo");
                } else {
                    list.add(new ToDo(description));
                    String task = list.get(list.size() - 1).toString();
                    String numberOfTasks = "Now you have "
                            + String.valueOf(list.size())
                            + " task(s) left";
                    System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
                }

            } else if (command.equals("deadline")) {
                if (!input.contains("/by")) {
                    System.out.println("Ensure that the format is 'deadline [task] /by [deadline]'");
                } else {
                    String[] parts = input.split("/by");
                    String description = parts[0].trim().substring("deadline".length()).trim();
                    String deadline = parts[1].trim();
                    if (description.isEmpty() || deadline.isEmpty()) {
                        System.out.println("Ensure that the format is 'deadline [task] /by [deadline]'");
                    } else {
                        list.add(new Deadline(description, deadline));
                        String task = list.get(list.size() - 1).toString();
                        String numberOfTasks = "Now you have "
                                + String.valueOf(list.size())
                                + " task(s) left";
                        System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
                    }
                }
            } else if (command.equals("event")) {
                if (!input.contains("/from") || !input.contains("/to")) {
                    System.out.println("Ensure that the format is 'event [task] /from [start] /to end'");
                } else {
                    String[] parts = input.split("/from| /to");
                    String description = parts[0].trim().substring("event".length()).trim();
                    String start = parts[1].trim();
                    String end = parts[2].trim();
                    if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                        System.out.println("Ensure that the format is 'event [task] /from [start] /to end'");
                    } else {
                        list.add(new Event(description, start, end));
                        String task = list.get(list.size() - 1).toString();
                        String numberOfTasks = "Now you have "
                                + String.valueOf(list.size())
                                + " task(s) left";
                        System.out.println("Ok. I added this task:\n" + task + "\n" + numberOfTasks);
                    }
                }
            } else {
                System.out.println("Please type a valid input:\n"
                        + "1. todo [task]\n"
                        + "2. deadline [task] /by[deadline]\n"
                        + "3. event [task] /from [start] /to end\n"
                        + "4. list");
            }
        }
        s.close();
        System.out.println("BYE BYE");
    }
}
