import java.util.Scanner;
public class Bob {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        String input = "";
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
            } else if (input.length() >= 4 && "mark".equals(input.substring(0, 4))) {
                try {
                    int index = Integer.parseInt(input.substring(5));
                    list[index - 1].markAsDone();
                    System.out.println("Did you actually finish this? \uD83E\uDD14:\n" +
                                       "  " + list[index - 1]);

                } catch (NumberFormatException | NullPointerException |
                         StringIndexOutOfBoundsException e) {
                    System.out.println("Incorrect mark format!\nDo: mark <task_number>");
                }

            // mark Task as undone
            } else if (input.length() >= 6 && "unmark".equals(input.substring(0, 6))) {
                try {
                    int index = Integer.parseInt(input.substring(7));
                    list[index - 1].markAsUndone();
                    System.out.println("I knew you didn't finish it \uD83D\uDE0F:\n" +
                                       "  " + list[index - 1]);

                } catch (NumberFormatException | NullPointerException |
                         StringIndexOutOfBoundsException e) {
                    System.out.println("Incorrect unmark format!\nDo: unmark <task_number>");
                }

            // add Task
            } else {
                list[curr++] = new Task(input);
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
