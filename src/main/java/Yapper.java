import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Yapper {
    public static void main(String[] args) {
        String chatbotName = "Yapper";
        List<String> userString = new ArrayList<>();
        Scanner userScanner = new Scanner(System.in);
        System.out.println(" Hello! I'm " + chatbotName + ". I talk a lot, hence my name, but I will be sure to keep you company.");
        System.out.println(" What can I do for you?");

        int userInputCount = 0;

        while (true) {
            System.out.println("User: ");
            String userInput = userScanner.nextLine();
            if (userInput.equalsIgnoreCase("list")){
                for(int i = 0; i < userString.size(); i++){
                    System.out.println("" + (i+1) + ". " + userString.get(i));
                }
            } else {
                userInputCount++;
                userString.add(userInput);
                System.out.println("Added: " + userInput);
            }

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
        }
        userScanner.close();
    }
}

