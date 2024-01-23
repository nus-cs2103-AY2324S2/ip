import java.util.Scanner;
public class Bob {
    private static boolean startsWith(String keyword, String input) {
        int len = keyword.length();
        return input.length() >= len && keyword.equals(input.substring(0, len));
    }

    private static Task getTaskFromIndex(String keyword, String input, Task[] list) {
        int len = keyword.length();

        try {
            int index = Integer.parseInt(input.substring(len + 1)) - 1;
            return list[index];

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException |
                 StringIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int curr = 0;

        System.out.println(line + "Hello! I'm Bob.\nWhat can I do for you?");

        while (true) {
            System.out.println(line);
            input = sc.nextLine();
            System.out.print(line);

            if ("bye".equals(input)) {
                break;
            }

            // list all Tasks
            if ("list".equals(input)) {
                for (int i = 0; i < curr; i++) {
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
                list[curr++] = new Todo(input);
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
