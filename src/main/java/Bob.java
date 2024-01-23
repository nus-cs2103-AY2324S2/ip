import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static boolean startsWith(String keyword, String input) {
        return input.length() >= keyword.length() &&
                keyword.equals(input.substring(0, keyword.length()));
    }

    private static Task getTaskFromIndex(String keyword, String input, ArrayList<Task> list) {
        try {
            int num = Integer.parseInt(input.substring(keyword.length() + 1));
            return list.get(num - 1);

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OH NOSE! Task number cannot be empty..");
        } catch (NumberFormatException e) {
            System.out.println("OH NOSE! Task number has to be an integer..");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("OH NOSE! Task number must be in valid range..");
        }
        return null;
    }

    private static void addTask(ArrayList<Task> list, Task task) {
        if (task != null) {
            list.add(task);
            System.out.println("This task has been added:\n  " + task +
                               "\n" + "Now you have " + list.size() +
                               " task(s) in total.");
        }
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String input;

        System.out.println(line + "Hello, I'm Bob.\nI might help if I feel like it.");

        while (true) {
            System.out.println(line);
            input = sc.nextLine();
            System.out.print(line);

            if ("bye".equals(input)) {
                break;
            }

            // list all Tasks
            if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }

            // mark Task as done
            } else if (startsWith("mark", input)) {
                Task task = getTaskFromIndex("mark", input, list);
                if (task != null) {
                    task.markAsDone();
                    System.out.println("Did you actually finish this? \uD83E\uDD14:\n" +
                                       "  " + task);
                }

            // mark Task as undone
            } else if (startsWith("unmark", input)) {
                Task task = getTaskFromIndex("unmark", input, list);
                if (task != null) {
                    task.markAsUndone();
                    System.out.println("I knew you didn't finish it! \uD83D\uDE0F:\n" +
                                       "  " + task);
                }

            } else if (startsWith("delete", input)) {
                Task task = getTaskFromIndex("delete", input, list);
                if (task != null) {
                    list.remove(task);
                    System.out.println("This task has been removed:\n  " + task +
                                       "\n" + "Now you have " + list.size() +
                                       " task(s) in total.");
                }

            } else if (startsWith(Todo.keyword, input)) {
                addTask(list, Todo.of(input));

            } else if (startsWith(Deadline.keyword, input)) {
                addTask(list, Deadline.of(input));

            } else if (startsWith(Event.keyword, input)) {
                addTask(list, Event.of(input));

            } else {
                System.out.println("OH NOSE! Wakaranai... :(");
            }
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
