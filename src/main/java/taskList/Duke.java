package taskList;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm MichelleBot! What can I do for you?\n" +
        "Type in text to add in a task to your list\n"+
        "Other commands:\n" +
        "mark [input number] - mark a task as done\n" +
        "unmark [input number] - unmark a task as undone\n" +
        "list - list out the current tasks you have\n" +
        "bye - exit the program ");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        ArrayList<Task> theList = new ArrayList<>();
        do {
            String input = scanner.nextLine();
            String[] inputList = input.split(" ",2);

            if (inputList.length != 1) {
                switch(inputList[0].toLowerCase()) {
                    case "mark":
                        int number = Integer.parseInt(inputList[1])-1;
                        theList.get(number).markItem();
                        System.out.println("I've marked this task as done: \n" + theList.get(number));
                        break;

                    case "unmark":
                        number = Integer.parseInt(inputList[1])-1;
                        theList.get(number).unmarkItem();
                        System.out.println("I've marked this task as not done yet: \n" + theList.get(number));
                        break;
                    
                    case "todo" :
                        ToDo newToDo = new ToDo(inputList[1]);
                        theList.add(newToDo);
                        System.out.println("Roger that! I've added in this task:\n " + newToDo  +"\nNow you have "+ theList.size() + " tasks in the list.");
                        break;

                    case "deadline": 
                        String[] theParts = inputList[1].split("/",2);
                        Deadline newDeadline = new Deadline(theParts[0].trim(), theParts[1].trim());
                        theList.add(newDeadline);
                        System.out.println("Roger that! I've added in this task:\n " + newDeadline  +"\nNow you have "+ theList.size() + " tasks in the list.");
                        break;

                    case "event":
                        theParts = inputList[1].split("/",3);
                        Event newEvent = new Event(theParts[0].trim(), theParts[1].trim(), theParts[2].trim());
                        theList.add(newEvent);
                        System.out.println("Roger that! I've added in this task:\n " + newEvent  +"\nNow you have "+ theList.size() + " tasks in the list.");
                        break;

                    default:
                        System.out.println("I've added in this task: " + input);
                        theList.add(new Task(input));
                        System.out.println("\nNow you have "+ theList.size() + " tasks in the list.");
                } 
            } else {
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

                    default:
                        System.out.println("I've added in this task: " + input);
                        theList.add(new Task(input));
                        System.out.println("\nNow you have "+ theList.size() + " tasks in the list.");
                }
            }
            System.out.println("____________________________________________________________");

        } while (keepRunning);

        scanner.close();
    }
}
