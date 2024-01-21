import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm MichelleBot! \nWhat can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        ArrayList<String> theList = new ArrayList<>();
        do {
            String input = scanner.nextLine();
            
            switch(input.toLowerCase()) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon! \\(^-^)/ ");
                    keepRunning = false;
                    break;

                case "list":
                    for (int i = 0; i < theList.size(); i++) {
                        int itemNumber = i + 1;
                        String listItem = theList.get(i);
                        System.out.println(itemNumber + ". " + listItem);
                    }
                    break;

                default:
                    System.out.println("added: " + input);
                    theList.add(input);
            }
            System.out.println("____________________________________________________________");

        } while (keepRunning);

        scanner.close();
    }
}
