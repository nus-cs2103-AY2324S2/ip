import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Numerator {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String logo = line +
                "    Hello! I'm Numerator\n" +
                "    What can I do for you?\n" + line;
        String bye = line +
                "     Bye. Hope to see you again soon!\n" +
                line;

        String added = line +
                "     added: %s\n" +
                line;

        Scanner scanner = new Scanner(System.in);
        System.out.println(logo);
        ArrayList<Task> store = new ArrayList<>();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println(bye);
                break;
            } else if (input.startsWith("mark ")) {
                String[] inputArray = input.split(" ");
                try {
                    int taskNum = Integer.parseInt(inputArray[1]) - 1;
                    Task t = store.get(taskNum);
                    t.setIsDone();
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("   " + t);
                    System.out.println(line);
                } catch (NumberFormatException e) {
                    System.out.print("Please input a valid number");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist");
                }

            } else if (input.startsWith("unmark ")) {
                String[] inputArray = input.split(" ");
                try {
                    int taskNum = Integer.parseInt(inputArray[1]) - 1;
                    Task t = store.get(taskNum);
                    t.setIsNotDone();
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("   " + t);
                    System.out.println(line);
                } catch (NumberFormatException e) {
                    System.out.print("Please input a valid number");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist");
                }

            } else if (input.equals("list")) {
                System.out.print(line);
                if (store.isEmpty()) {
                    System.out.println("Nothing in list");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    IntStream.iterate(1, x -> x + 1)
                            .limit(store.size())
                            .forEachOrdered(i -> {
                                String s = String.format("    %d. %s", i, store.get(i - 1));
                                System.out.println(s);
                            });
                }
                System.out.print(line);
            } else {
                Task t = new Task(input);
                store.add(t);
                System.out.println(t);
            }


        }
    }
}
