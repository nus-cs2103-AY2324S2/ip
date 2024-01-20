import java.util.Scanner;
public class MissA {
    // greet, exit sentences
    private String greeting = "What can I do for you?\n"
            + "______________________\n";
    private String goodBye = "Bye. Have a nice day!\n"
            + "______________________\n";
    private String emptyLine = "______________________\n";

    public static void main(String[] args) {
        MissA missA = new MissA();

        // greet users when first enter the program
        System.out.println("Hello from MissA ^_^\n" + missA.greeting);

        //obtain user input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(missA.emptyLine + userInput + "\n" + missA.emptyLine);
            userInput = scanner.nextLine();
        }
        System.out.println(missA.emptyLine + missA.goodBye);
    }
}
