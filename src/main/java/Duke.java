import java.util.*;
public class Duke {
    public static void main(String[] args) {

        //name and introduction
        String name = "Bearducky";
        System.out.println("Quack! My name is " + name + ". I would like to earn some bread. How may I " +
                "help?");

        //Set up scanner, store user's inputs, structure to store tasks
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        label:
        while (true) {
            String userInput = scanner.nextLine();
            int x = tasks.size();

            //marking or not
            if (userInput.toLowerCase().startsWith("mark ") || userInput.toLowerCase().startsWith("unmark ")) {
                String[] inputs = userInput.split(" ");
                try {
                    int num = Integer.parseInt(inputs[1]);
                    if (inputs[0].equals("mark")) {
                        tasks.get(num - 1).Mark();
                    } else {
                        tasks.get(num - 1).unMark();
                    }
                    System.out.println("[busy quacking]");
                } catch (NumberFormatException e) {
                    System.out.println("[angry quacking] I can only mark numbers!");
                } catch (IndexOutOfBoundsException a) {
                    System.out.println("[exasperated quacking] You're not that busy - numbers from 1 to " + x + " only, please.");
                }
                continue;
            }

            //other hard commands
            switch (userInput.toLowerCase()) {
                case "bye":
                    System.out.println("[sad quacking] Can I have my bread now?\n");
                    break label;
                case "list":
                    for (Task a : tasks) {
                        System.out.println(a);
                    }
                    System.out.println("\n");
                    break;
                default:
                    System.out.println("added: " + userInput + "\n");
                    tasks.add(new Task(userInput));
                    break;
            }
        }
    }
}
