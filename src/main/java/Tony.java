import tasks.Task;
import tasks.TaskType;
import java.util.Scanner;
import java.util.Arrays;

public class Tony {
    public static void main(String[] args) {
        TodoList lst = new TodoList();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        greeting();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            String firstWord = words[0];
            try {
                if (firstWord.equals("list")) {
                    lst.print();
                } else if (firstWord.equals("mark")) {
                    String secondWord = words[1];
                    lst.mark(secondWord);
                } else if (firstWord.equals("unmark")) {
                    String secondWord = words[1];
                    lst.unmark(secondWord);
                } else if (firstWord.equals("todo")) {
                    String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    Task toDo = new TaskFactory().createTask(TaskType.TODO, result);
                    lst.add(toDo);
                } else if (firstWord.equals("deadline")) {
                    String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    String[] parts = result.split("/");
                    Task deadline = new TaskFactory().createTask(TaskType.DEADLINE, parts);
                    lst.add(deadline);
                } else if (firstWord.equals("event")) {
                    String result = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    String[] parts = result.split("/");
                    Task event = new TaskFactory().createTask(TaskType.EVENT, parts);
                    lst.add(event);
                } else if (firstWord.equals("delete")) {
                    String secondWord = words[1];
                    lst.delete(secondWord);
                } else {
                    throw new IllegalArgumentException("Invalid command: " + firstWord);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            input = scanner.nextLine();
        }
        goodbye();
    }

    private static void greeting() {
        String greeting = "_______________________\n"
                + "what is up dawg! I'm Tony!\n"
                + "What can I do for you mate?\n"
                + "_________________________\n";
        System.out.println(greeting);
    }
    private static void goodbye() {
        String goodbye = "See ya later dawg!\n";
        line();
        System.out.println(goodbye);
        line();
        System.exit(0);
    }
    private static void line() {
        System.out.println("_______________________\n");
    }

}

