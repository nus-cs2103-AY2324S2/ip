import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String name = "Bearducky";
        System.out.println("Quack! My name is " + name + ". I would be glad to help you in exchange for some bread.");
        Scanner scanner = new Scanner(System.in);
        String userInput;
        List<String> tasks = new ArrayList<>();
        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("[sad quacking]\n");
                break;
            } else if (userInput.equals("list")) {
                for (String a: tasks) {
                    System.out.println(a);
                }
                System.out.println("\n");
            } else {
                System.out.println("added: " +userInput + "\n");
                tasks.add(userInput);
            }
        }
    }
}
