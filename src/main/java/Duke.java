import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String EXITKEY = "bye";
    private static String LISTKEY = "list";
    public static void main(String[] args) {
        String userInput = "";
        ArrayList<String> inputArr = new ArrayList<>();
        System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while(!userInput.equals(EXITKEY)) {
            userInput = scanner.nextLine();
            if (LISTKEY.equals(userInput)) {
                for (int i = 0; i < inputArr.size(); i++) {
                    System.out.println((i+1)+". " + inputArr.get(i));
                }
            } else {
                inputArr.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
