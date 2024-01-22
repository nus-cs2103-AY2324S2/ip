package taskList;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        //introduction text
        System.out.println("Hello! I'm MichelleBot! What can I do for you?\n" +
        "Type in text to add in a task to your list\n"+
        "Other commands:\n" +
        "mark [input number] - mark a task as done\n" +
        "unmark [input number] - unmark a task as undone\n" +
        "todo [task] - add a TODO task to your list\n" +
        "deadline [task] /by [deadline] - add a DEADLINE to your list\n" + 
        "Event [task] /from [date] /to [date] - add an EVENT to your list\n" + 
        "list - list out the current tasks you have\n" +
        "bye - exit the program ");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;
        ArrayList<Task> theList = new ArrayList<>();
        do { //continue the program until 'bye' command is inputted. 
            String input = scanner.nextLine().trim();
            String[] inputList = input.split(" ",2);

            if (inputList.length != 1) { //indicates one of the other commands
                switch(inputList[0].toLowerCase()) {
                    //both mark and unmark handle the same exceptions. 
                    case "mark": //mark the task
                    try {        
                        int number = Integer.parseInt(inputList[1])-1;
                        theList.get(number).markItem();
                        System.out.println("I've marked this task as done: \n" + theList.get(number));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: Index is out of bounds. The list currently has " + theList.size() + " item(s)");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Input is not a valid numeric value.");
                    } catch (Exception e) {
                        System.out.println("An unexpected errormark has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
                    }
                    break;

                    case "unmark": //unmark the task
                        try {
                            int number = Integer.parseInt(inputList[1])-1;
                            theList.get(number).unmarkItem();
                            System.out.println("I've marked this task as not done yet: \n" + theList.get(number));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: Index is out of bounds. The list currently has " + theList.size() + " item(s)");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Input is not a valid numeric value.");
                        } catch (Exception e) {
                            System.out.println("An unexpected error has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
                        }
                        break;
                    
                    // add in three types of tasks
                    case "todo" :
                        ToDo newToDo = new ToDo(inputList[1]);
                        theList.add(newToDo);
                        System.out.println("Roger that! I've added in this task:\n " + newToDo  +"\nNow you have "+ theList.size() + " tasks in the list.");
                        break;

                    case "deadline": 
                        try{
                            String[] theParts = inputList[1].split("/",2);
                            if (theParts[1].trim().startsWith("by")) {
                                try {
                                    Deadline newDeadline = new Deadline(theParts[0].trim(), theParts[1].trim());
                                    theList.add(newDeadline);
                                    System.out.println("Roger that! I've added in this task:\n " + newDeadline  +"\nNow you have "+ theList.size() + " tasks in the list.");
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Error creating Deadline: " + e.getMessage());
                                } catch (Exception e) {
                                    System.out.println("Error creating Deadline." + e.getMessage());
                                    System.out.println("Please contact the adminstrator.");
                                }
                            } else {
                                System.out.println("Error: /by cannot be found. Please try again");
                            }
                            
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error creating Deadline: Please enter a deadline.");
                        } catch (Exception e) {
                            System.out.println("Error creating Deadline." + e.getMessage());
                            System.out.println("Please contact the adminstrator.");
                        }

                        break;

                    case "event":
                        try{
                            String[] theParts = inputList[1].split("/",3);
                            if (theParts[1].trim().startsWith("from")) {
                                if (theParts[2].trim().startsWith("to")) {
                                    try {
                                        Event newEvent = new Event(theParts[0].trim(), theParts[1].trim(), theParts[2].trim());
                                        theList.add(newEvent);
                                        System.out.println("Roger that! I've added in this task:\n " + newEvent  +"\nNow you have "+ theList.size() + " tasks in the list.");
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        System.out.println("Error creating Event: " + e.getMessage());
                                    } catch (Exception e) {
                                        System.out.println("Error creating Event." + e.getMessage());
                                        System.out.println("Please contact the adminstrator.");
                                    }
                                } else {
                                    System.out.println("Error: /to cannot be found. Please try again");
                                }
                            } else {
                                System.out.println("Error: /from cannot be found. Please try again");
                            }
                            
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error creating Event: Please format the input properly.");
                        } catch (Exception e) {
                            System.out.println("Error creating Event." + e.getMessage());
                            System.out.println("Please contact the adminstrator.");
                        }                      
                        break;                  
                        
                    //delete task from task list
                    case "delete":
                        try {
                            int number = Integer.parseInt(inputList[1])-1;
                            Task removedTask = theList.remove(number);
                            System.out.println("Roger that! I've removed this task:\n " + removedTask  +"\nNow you have "+ theList.size() + " tasks in the list.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: Index is out of bounds. The list currently has " + theList.size() + " item(s)");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Input is not a valid numeric value.");
                        } catch (Exception e) {
                            System.out.println("An unexpected error has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
                        }
                        break;

                    // default - add in the task as according to the text input
                    default:
                        System.out.println("I've added in this task: " + input);
                        theList.add(new Task(input));
                        System.out.println("\nNow you have "+ theList.size() + " tasks in the list.");
                } 
            } else { //one word command - either list or bye 
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
                    
                    // incorrect inputs by user - other commands
                    case "todo":
                    case "event":
                    case "deadline":
                        System.out.println("You have to enter more information :( refer to the introduction text for more details. ");
                        break;
                    
                    case "mark":
                    case "unmark":
                    case "delete":
                        System.out.println("Let me know the index number!");
                        break;

                    // default - add in the task as according to the text input
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
