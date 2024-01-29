package taskList;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //enum to represent different commands
    public enum Command {
        MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, LIST, BYE, HELPG, ADD, UNKNOWN
    }

    private static Command getCommand(String commandString) {
        try {
            return Command.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    public static void main(String[] args) {
        //introduction text
        System.out.println("Hello! I'm MichelleBot! What can I do for you? (helpg for guide)" );
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        @SuppressWarnings("unchecked")
        ArrayList<Task> theList = new ArrayList<>();

        String dataDirectoryPath = "./data";
        String dataFilePath = "./data/duke.ser";
        
        if (!Files.exists(Paths.get(dataDirectoryPath))) {
            try {
                Files.createDirectories((Paths.get(dataDirectoryPath)));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An unexpected error has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
            }
        }

        if (!Files.exists(Paths.get(dataFilePath))) {
            try {
                Files.createFile(Paths.get(dataFilePath));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An unexpected error has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
            }
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(dataFilePath))) {
            Object obj = objectInputStream.readObject();
            if (obj instanceof ArrayList) {
                theList = (ArrayList<Task>) obj;
            }
        } catch (EOFException e) {
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }


        do { //continue the program until 'bye' command is inputted. 
            String input = scanner.nextLine().trim();
            String[] inputList = input.split(" ",2);

            if (inputList.length != 1) { //indicates one of the other commands
                switch(getCommand(inputList[0])) {
                    //both mark and unmark handle the same exceptions. 
                    case MARK: //mark the task
                    try {        
                        int number = Integer.parseInt(inputList[1])-1;
                        theList.get(number).markItem();
                        System.out.println("I've marked this task as done: \n" + theList.get(number));
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Error: Index is out of bounds. The list currently has " + theList.size() + " item(s)");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Input is not a valid numeric value.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("An unexpected errormark has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
                    }
                    break;

                    case UNMARK: //unmark the task
                        try {
                            int number = Integer.parseInt(inputList[1])-1;
                            theList.get(number).unmarkItem();
                            System.out.println("I've marked this task as not done yet: \n" + theList.get(number));
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: Index is out of bounds. The list currently has " + theList.size() + " item(s)");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Input is not a valid numeric value.");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("An unexpected error has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
                        }
                        break;
                    
                    // add in three types of tasks
                    case TODO :
                        ToDo newToDo = new ToDo(inputList[1]);
                        theList.add(newToDo);
                        System.out.println("Roger that! I've added in this task:\n " + newToDo  +"\nNow you have "+ theList.size() + " tasks in the list.");
                        break;

                    case DEADLINE: 
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
                                    e.printStackTrace();
                                    System.out.println("Error creating Deadline." + e.getMessage());
                                    System.out.println("Please contact the adminstrator.");
                                }
                            } else {
                                System.out.println("Error: /by cannot be found. Please try again");
                            }
                            
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Error creating Deadline: Please enter a deadline.");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Error creating Deadline." + e.getMessage());
                            System.out.println("Please contact the adminstrator.");
                        }

                        break;

                    case EVENT:
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
                                        e.printStackTrace();
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
                            e.printStackTrace();
                            System.out.println("Error creating Event." + e.getMessage());
                            System.out.println("Please contact the adminstrator.");
                        }                      
                        break;                  
                        
                    //delete task from task list
                    case DELETE:
                        try {
                            int number = Integer.parseInt(inputList[1])-1;
                            Task removedTask = theList.remove(number);
                            System.out.println("Roger that! I've removed this task:\n " + removedTask  +"\nNow you have "+ theList.size() + " tasks in the list.");
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Error: Index is out of bounds. The list currently has " + theList.size() + " item(s)");
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Input is not a valid numeric value.");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("An unexpected error has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
                        }
                        break;
                    
                    // default - add in the task as according to the text input
                    case ADD:
                        System.out.println("I've added in this task: " + inputList[1]);
                        theList.add(new Task(inputList[1]));
                        System.out.println("\nNow you have "+ theList.size() + " tasks in the list.");
                        break;

                    case UNKNOWN:
                        System.out.println("Unknown command: " + inputList[0].toLowerCase());
                        break;

                    case HELPG:
                    case BYE:
                    case LIST:
                        System.out.println("Unexpected addition text after command");
                        System.out.println("Did you mean just " + getCommand(inputList[0]) + "?");
                        break;

                } 
            } else { //one word command - either list or bye 
                switch(getCommand(input)) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon! \\(^-^)/ ");
                        isRunning = false; 
                        break;

                    case LIST:
                        for (int i = 0; i < theList.size(); i++) {
                            int itemNumber = i + 1;
                            Task listItem = theList.get(i);
                            System.out.println(itemNumber + ". " + listItem);
                        }
                        break;
                    
                    // incorrect inputs by user - other commands
                    case TODO:
                    case EVENT:
                    case DEADLINE:
                    case ADD:
                        System.out.println("You have to enter more information :( refer to helpg for more details. ");
                        break;
                    
                    case MARK:
                    case UNMARK:
                    case DELETE:
                        System.out.println("Let me know the index number!");
                        break;

                    case HELPG:
                        System.out.println("Type in text to add in a task to your list\n"+
                        "Other commands:\n" +
                        "add [task] - adds a task to the task list\n" +
                        "mark [input number] - mark a task as done\n" +
                        "unmark [input number] - unmark a task as undone\n" +
                        "todo [task] - add a TODO task to your list\n" +
                        "deadline [task] /by [deadline] - add a DEADLINE to your list\n" + 
                        "event [task] /from [date] /to [date] - add an EVENT to your list\n" + 
                        "delete [input number] - delete a task from task list\n" +
                        "list - list out the current tasks you have\n" +
                        "bye - exit the program ");
                        break;

                    case UNKNOWN:
                        System.out.println("Unknown command: " + inputList[0].toLowerCase() + "\nPlease try again.");
                        break;

                }
            }
            System.out.println("____________________________________________________________");

            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(dataFilePath))) {
                objectOutputStream.writeObject(theList);
            } catch (IOException e) {
                e.printStackTrace();
            }
    
        } while (isRunning);

        scanner.close();
    }
}
