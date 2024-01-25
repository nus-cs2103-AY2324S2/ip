import java.util.Objects;
import java.util.Scanner;

public class TalkingJohn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] TaskArr = new Task[100];

        System.out.println("Hello, I am TalkingJohn\n"
                + "What can I do for you?\n");

        int j = 0;
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                System.out.println("Bye, hope to see you soon");
                break;
            } else if (Objects.equals(input, "list")) {
                for (int i = 0; i < j; i++) {
                    Task nthElement = TaskArr[i];
                    System.out.println((i + 1) + ". " + nthElement.getStatusIcon() + "\n");
                }
            } else if (input.length() > 4 && Objects.equals(input.substring(0, 4), "mark")) {
                if (input.charAt(4) == ' ') {
                    String[] parts = input.split(" ", 2);
                    String integer = parts[1];
                    try {
                        int i = Integer.parseInt(integer);
                        Task task_to_mark = TaskArr[i - 1];
                        task_to_mark.mark();
                        System.out.println("Nice! I've marked this task as done:\n"
                                + task_to_mark.getStatusIcon());
                    } catch (NumberFormatException e) {
                        TaskArr[j] = new Task(input);
                        System.out.println("added: " + input);
                        j++;
                    }
                } else {
                    TaskArr[j] = new Task(input);
                    System.out.println("added: " + input);
                    j++;
                }
            } else if (input.length() > 6 && Objects.equals(input.substring(0, 6), "unmark")) {
                if (input.charAt(6) == ' ') {
                    String[] parts = input.split(" ", 2);
                    String integer = parts[1];
                    try {
                        int i = Integer.parseInt(integer);
                        Task task_to_unmark = TaskArr[i - 1];
                        task_to_unmark.unMark();
                        System.out.println("OK, I've marked this task as not done yet:\n"
                                + task_to_unmark.getStatusIcon());
                    } catch (NumberFormatException e) {
                        TaskArr[j] = new Task(input);
                        System.out.println("added: " + input);
                        j++;
                    }
                } else {
                    TaskArr[j] = new Task(input);
                    System.out.println("added: " + input);
                    j++;
                }
            } else {
                TaskArr[j] = new Task(input);
                System.out.println("added: " + input);
                j++;
            }
        }
        scanner.close();
    }
}

