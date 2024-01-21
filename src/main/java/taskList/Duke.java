package taskList;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm MichelleBot! What can I do for you?\n" +
        "Type in text to add in a task to your list\n"+
        "Other commands:\n" +
        "list - list out the current tasks you have\n" +
        "mark - mark a task as done\n" +
        "unmark - unmark a task as undone\n" +
        "bye - exit the program ");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        ArrayList<Task> theList = new ArrayList<>();
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
                        Task listItem = theList.get(i);
                        System.out.println(itemNumber + ". " + listItem);
                    }
                    break;

                case "mark":
                    System.out.println("Which task would you like me to mark? (input number)");
                    int number = Integer.parseInt(scanner.nextLine())-1;
                    theList.get(number).markItem();
                    System.out.println("I've marked this task as done: \n" + theList.get(number));
                    break;

                case "unmark":
                    System.out.println("Which task would you like me to unmark? (input number)");
                    number = Integer.parseInt(scanner.nextLine())-1;
                    theList.get(number).unmarkItem();
                    System.out.println("I've marked this task as not done yet: \n" + theList.get(number));
                    break;

                default:
                    System.out.println("added: " + input);
                    theList.add(new Task(input));
            }
            System.out.println("____________________________________________________________");

        } while (keepRunning);

        scanner.close();
    }
}
