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
            if (Objects.equals(input, "todo") || Objects.equals(input, "deadline") || Objects.equals(input, "event")) {
                System.out.println("OOPS!!! The description of " + input + " cannot be empty.");
                continue;
            } else if (Objects.equals(input, "mark")) {
                System.out.println("OOPS!!! mark cannot be empty.");
                continue;
            } else if (Objects.equals(input, "unmark")) {
                System.out.println("OOPS!!! unmark cannot be empty.");
                continue;
            }

            if (Objects.equals(input, "bye")) {
                System.out.println("Bye, hope to see you soon");
                break;
            } else if (Objects.equals(input, "list")) {
                for (int i = 0; i < j; i++) {
                    Task nthElement = TaskArr[i];
                    System.out.println((i + 1) + ". " + nthElement + "\n");
                }
            } else if (input.length() > 4 && Objects.equals(input.substring(0, 4), "mark") && input.charAt(4) == ' ') {
                String[] parts = input.split(" ", 2);
                String integer = parts[1];
                try {
                    int i = Integer.parseInt(integer);
                    Task task_to_mark = TaskArr[i - 1];
                    task_to_mark.mark();
                    System.out.println("Nice! I've marked this task as done:\n"
                            + task_to_mark);
                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println("OOPS!!! Invalid mark expression.");
                }
            } else if (input.length() > 6 && Objects.equals(input.substring(0, 6), "unmark") && input.charAt(6) == ' ') {
                String[] parts = input.split(" ", 2);
                String integer = parts[1];
                try {
                    int i = Integer.parseInt(integer);
                    Task task_to_unmark = TaskArr[i - 1];
                    task_to_unmark.unMark();
                    System.out.println("OK, I've marked this task as not done yet:\n"
                            + task_to_unmark);
                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println("OOPS!!! Invalid unmark expression.");
                }
            } else if (input.length() > 4 && Objects.equals(input.substring(0, 4), "todo") && input.charAt(4) == ' ') {
                String[] parts = input.split(" ", 2);
                String what_to_do = parts[1];
                Todo to_do = new Todo(what_to_do);
                TaskArr[j] = to_do;
                j++;
                System.out.println("Got it. I've added this task:\n"
                        + to_do + "\n"
                        + "Now you have " + j + " tasks in the list.");
            } else if (input.length() > 8 && Objects.equals(input.substring(0, 8), "deadline") && input.charAt(8) == ' ') {
                try {
                    String[] parts = input.split(" ", 2);
                    String buffer = parts[1];
                    String[] secPart = buffer.split("/", 2);
                    String description = secPart[0];
                    String by = secPart[1];
                    Deadline deadline = new Deadline(description, by);
                    TaskArr[j] = deadline;
                    j++;
                    System.out.println("Got it. I've added this task:\n"
                            + deadline + "\n"
                            + "Now you have " + j + " tasks in the list.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OH NO! It seems like the format is wrong. Did you include at least 1 '/' in the description?");
                }
            } else if (input.length() > 5 && Objects.equals(input.substring(0, 5), "event") && input.charAt(5) == ' ') {
                try {
                    String[] parts = input.split(" ", 2);
                    String buffer = parts[1];
                    String[] secPart = buffer.split("/", 3);
                    String description = secPart[0];
                    String by1 = secPart[1];
                    String by2 = secPart[2];
                    Event event = new Event(description, by1, by2);
                    TaskArr[j] = event;
                    j++;
                    System.out.println("Got it. I've added this task:\n"
                            + event + "\n"
                            + "Now you have " + j + " tasks in the list.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("OH NO! It seems like the format is wrong. Did you include at least 2 '/' in the description?");
                }
            } else {
                System.out.println("HMM? This is an activity planner and you are not making sense. Please repeat \uD83E\uDD72");
            }
        }
        scanner.close();
    }
}

