

import java.util.Scanner;
public class HASSTNT {
    private final Scanner scanner;
    private final ToDoList l;


    private static final String welcome_message = "____________________________________________________________\n" +
            "Hello! I'm HASSTNT\n" +
            "What can I do for you?\n" +
            "____________________________________________________________\n";
    private static final String goodbye_message = "____________________________________________________________\n" +
            "Bye. Hope to see you again soon!\n" +
            "____________________________________________________________\n";

    public HASSTNT() {
        // initialize a scanner
        scanner = new Scanner(System.in);
        l = new ToDoList();
    }

    public void startConversation() {
        // Method containing the conversation logic
        System.out.println(welcome_message);
        String input;
        while (true) {
            input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "bye":
                    handleByeCommand();
                    return;
                case "list":
                    l.showLists();
                    break;
                default:
                    if (input.startsWith("mark")) {
                        handleMarkCommand(input);
                    }
                    else if (input.startsWith("remove")){
                        handleRemoveCommand(input);
                    } else if (input.startsWith("unmark")) {
                        handleUnmarkCommand(input);

                    } else {
                        l.addToList(input);
                    }
                    break;
            }
        }
    }

    private void handleByeCommand() {
        System.out.println(goodbye_message);
    }

    private void handleMarkCommand(String input) {
        String numberString = input.substring("mark".length());
        try {
            int index = Integer.parseInt(numberString.trim());
            l.showMark(index);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter mark followed by an integer");
        }
    }

    private void handleUnmarkCommand(String input) {
        String numberString = input.substring("unmark".length());
        try {
            int index = Integer.parseInt(numberString.trim());
            l.showUnmark(index);
        } catch (NumberFormatException e) {
            System.out.println("\"Invalid input. Please enter unmark followed by an integer\"");
        }
    }
    private void handleRemoveCommand(String input){
        String numberString = input.substring("remove".length());
        try {
            int index = Integer.parseInt(numberString.trim());
            l.removeTask(index);
        } catch (NumberFormatException e) {
            System.out.println("\"Invalid input. Please enter remove followed by an integer\"");
        }
    }
}
