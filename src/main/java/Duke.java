import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String name = "Bearducky";
        System.out.println("Quack! My name is " + name + ". I would be glad to help you in exchange for some bread.");
        String terminate = "";
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("[sad quacking]\n");
                break;
            } else {
                System.out.println(userInput + "\n");
            }
        }
    }
}
