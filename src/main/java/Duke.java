import java.util.*;
public class Duke {
    public static void main(String[] args) {

        //name and introduction
        String name = "Bearducky";
        System.out.println("Quack! My name is " + name + ". I would be glad to help you in exchange for some bread.");

        //Set up scanner, store user's inputs, structure to store tasks
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("[sad quacking]\n");
                break;
            } else if (userInput.equals("list")) {
                for (Task a : tasks) {
                    System.out.println("[" + a.getStatusIcon() + "] " + a.getName());
                }
                System.out.println("\n");
            } else if (userInput.equals("mark")) {
                System.out.println("Please provide the task number to mark\n");
                int markTask = scanner.nextInt();
                scanner.nextLine();
                tasks.get(markTask - 1).Mark();
            } else if (userInput.equals("unmark")) {
                System.out.println("Please provide the task number to unmark\n");
                int unmarkTask = scanner.nextInt();
                scanner.nextLine();
                tasks.get(unmarkTask - 1).unMark();
            } else {
                System.out.println("added: " +userInput + "\n");
                tasks.add(new Task(userInput));
            }
        }
    }
}
