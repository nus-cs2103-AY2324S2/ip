import java.util.ArrayList;
import java.util.Scanner;
public class MissA {
    // greet, exit sentences
    private String greeting = "What can I do for you?\n"
            + "______________________\n";
    private String goodBye = "Bye. Have a nice day!\n"
            + "______________________\n";
    private String emptyLine = "______________________\n";
    // store existing tasks in list
    private ArrayList<String> taskList = new ArrayList<>(100);

    public static void main(String[] args) {
        MissA missA = new MissA();

        // greet users when first enter the program
        System.out.println("Hello from MissA ^_^\n" + missA.greeting);

        //obtain user input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println(missA.emptyLine);
                missA.taskList.forEach(System.out::println);
                System.out.println(missA.emptyLine);
                userInput = scanner.nextLine();
            } else {
                missA.taskList.add(userInput);
                System.out.println(missA.emptyLine
                        + "added: "
                        + userInput
                        + "\n"
                        + missA.emptyLine);
                userInput = scanner.nextLine();
            }
        }

        // exit program
        System.out.println(missA.emptyLine + missA.goodBye);
    }
}
