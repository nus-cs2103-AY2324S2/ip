import java.util.Scanner;
public class Bob {
    private static boolean startsWith(String keyword, String input) {
        int len = keyword.length();
        return input.length() >= len && keyword.equals(input.substring(0, len));
    }

    private static Task getTaskFromIndex(String keyword, String input, Task[] list) {
        try {
            int num = Integer.parseInt(input.substring(keyword.length() + 1));
            return list[num - 1];

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException |
                 StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    private static Task createTask(String input) {
        try {
            if (startsWith("todo ", input)) {
                return new Todo(input.substring(5));

            } else if (startsWith("deadline ", input)) {
                int byIdx = input.indexOf(" /by ");
                String description = input.substring(9, byIdx);
                String by = input.substring(byIdx + 5);
                return new Deadline(description, by);

            } else if (startsWith("event ", input)) {
                int fromIdx = input.indexOf(" /from ");
                int toIdx = input.indexOf(" /to ");
                if (fromIdx > toIdx) {
                    return null;
                }

                String description = input.substring(6, fromIdx);
                String from = input.substring(fromIdx + 7, toIdx);
                String to = input.substring(toIdx + 5);
                return new Event(description, from, to);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int nxtIdx = 0;

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
                for (int i = 0; i < nxtIdx; i++) {
                    System.out.println(i + 1 + "." + list[i]);
                }

            // mark Task as done
            } else if (startsWith("mark", input)) {
                Task task = getTaskFromIndex("mark", input, list);
                if (task != null) {
                    task.markAsDone();
                    System.out.println("Did you actually finish this? \uD83E\uDD14:\n" +
                                       "  " + task);
                } else {
                    System.out.println("Incorrect mark format!\nDo: mark <task_number>");
                }

            // mark Task as undone
            } else if (startsWith("unmark", input)) {
                Task task = getTaskFromIndex("unmark", input, list);
                if (task != null) {
                    task.markAsUndone();
                    System.out.println("I knew you didn't finish it \uD83D\uDE0F:\n" +
                                       "  " + task);
                } else {
                    System.out.println("Incorrect unmark format!\nDo: unmark <task_number>");
                }

            // add Task
            } else {
                Task task = createTask(input);
                if (task != null) {
                    list[nxtIdx++] = task;
                    System.out.println("This task has been added:\n  " + task +
                                       "\n" + "Now you have " + nxtIdx +
                                       " task(s) in total.");
                } else {
                    System.out.println("Invalid format. Please try again.");
                }
            }
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
