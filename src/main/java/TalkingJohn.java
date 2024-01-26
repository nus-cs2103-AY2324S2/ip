import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class TalkingJohn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> TaskArr = new ArrayList<>();

        System.out.println("Hello, I am TalkingJohn\n"
                + "What can I do for you?\n");

        while (true) {
            String input = null;
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            } else {
                break;
            }

            if (Objects.equals(input, "todo") || Objects.equals(input, "deadline") || Objects.equals(input, "event")) {
                System.out.println("OOPS!!! The description of " + input + " cannot be empty.");
                continue;
            } else if (Objects.equals(input, "mark") || Objects.equals(input, "unmark") || Objects.equals(input, "delete")) {
                System.out.println("OOPS!!! " + input + " cannot be empty.");
                continue;
            }

            if (Objects.equals(input, "bye")) {
                System.out.println("Bye, hope to see you soon");
                break;
            } else if (Objects.equals(input, "list")) {
                int len = TaskArr.size();
                for (int i = 0; i < len; i++) {
                    Task nthElement = TaskArr.get(i);
                    System.out.println((i + 1) + ". " + nthElement + "\n");
                }
            } else if (input.length() > 6 && Objects.equals(input.substring(0, 6), "delete") && input.charAt(6) == ' ') {
                String[] parts = input.split(" ", 2);
                String integer = parts[1];
                try {
                    int i = Integer.parseInt(integer);
                    Task deleted = TaskArr.remove(i - 1);
                    int len = TaskArr.size();
                    System.out.println("Noted. I've removed this task:\n"
                            + deleted + "\n"
                            + "Now you have " + len + " tasks in the list.");
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Invalid delete expression.");
                }
            } else if (input.length() > 4 && Objects.equals(input.substring(0, 4), "mark") && input.charAt(4) == ' ') {
                String[] parts = input.split(" ", 2);
                String integer = parts[1];
                try {
                    int i = Integer.parseInt(integer);
                    Task task_to_mark = TaskArr.get(i - 1);
                    task_to_mark.mark();
                    System.out.println("Nice! I've marked this task as done:\n"
                            + task_to_mark);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Invalid mark expression.");
                }
            } else if (input.length() > 6 && Objects.equals(input.substring(0, 6), "unmark") && input.charAt(6) == ' ') {
                String[] parts = input.split(" ", 2);
                String integer = parts[1];
                try {
                    int i = Integer.parseInt(integer);
                    Task task_to_unmark = TaskArr.get(i - 1);
                    task_to_unmark.unMark();
                    System.out.println("OK, I've marked this task as not done yet:\n"
                            + task_to_unmark);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println("OOPS!!! Invalid unmark expression.");
                }
            } else if (input.length() > 4 && Objects.equals(input.substring(0, 4), "todo") && input.charAt(4) == ' ') {
                String[] parts = input.split(" ", 2);
                String what_to_do = parts[1];
                Todo to_do = new Todo(what_to_do);
                TaskArr.add(to_do);
                int len = TaskArr.size();
                System.out.println("Got it. I've added this task:\n"
                        + to_do + "\n"
                        + "Now you have " + len + " tasks in the list.");
            } else if (input.length() > 8 && Objects.equals(input.substring(0, 8), "deadline") && input.charAt(8) == ' ') {
                try {
                    String[] parts = input.split(" ", 2);
                    String buffer = parts[1];
                    String[] secPart = buffer.split("/", 2);
                    String description = secPart[0];
                    String by = secPart[1];
                    Deadline deadline = new Deadline(description, by);
                    TaskArr.add(deadline);
                    int len = TaskArr.size();
                    System.out.println("Got it. I've added this task:\n"
                            + deadline + "\n"
                            + "Now you have " + len + " tasks in the list.");
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
                    TaskArr.add(event);
                    int len = TaskArr.size();
                    System.out.println("Got it. I've added this task:\n"
                            + event + "\n"
                            + "Now you have " + len + " tasks in the list.");
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

