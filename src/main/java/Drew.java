import taskTypes.*;
import java.util.Scanner;

/**
 * Main chatbot class
 * @author cocoanautz
 */
public class Drew {
    /**
     * Checks the identity of the command.
     * @param input String containing full user input.
     * @return Command specified by user input.
     */
    public static Command checkCommandIdentity(String input) throws UnknownCommandException, InsufficientArgumentsException, IllegalArgumentException {
        int inputLength = input.length();
        Command userCommand;
        if(inputLength == 4 && input.substring(0, 4).equalsIgnoreCase("list")) {
            userCommand = Command.LIST;
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("mark")) {
            userCommand = Command.MARK;
        } else if (inputLength >= 6 && input.substring(0, 6).equalsIgnoreCase("unmark")) {
            userCommand = Command.UNMARK;
        } else if (inputLength >= 4 && input.substring(0, 4).equalsIgnoreCase("todo")) {
            userCommand = Command.TODO;
        } else if (inputLength >= 8 && input.substring(0, 8).equalsIgnoreCase("deadline")) {
            userCommand = Command.DEADLINE;
        } else if (inputLength >= 5 && input.substring(0, 5).equalsIgnoreCase("event")) {
            userCommand = Command.EVENT;
        } else {
            userCommand = Command.UNKNOWN;
        }

        String inputNoWhitespace = input.replaceAll("\\s", "");
        int inputNoWhitespaceLength = inputNoWhitespace.length();
        int backslashCount = 0;
        for (int i = 0; i < inputNoWhitespaceLength; i ++) {
            char c = inputNoWhitespace.charAt(i);
            if (c == '/') {
                backslashCount++;
            }
        }
        switch (userCommand) {
            case UNKNOWN:
                throw new UnknownCommandException("Command not recognized");
            case LIST:
                if(inputNoWhitespaceLength != 4) {
                   throw new IllegalArgumentException("No arguments are needed");
                }
                break;
            case TODO:
                if(inputNoWhitespaceLength == 4) {
                    throw new InsufficientArgumentsException("'Todo task' cannot be empty");
                }
                break;
            case MARK:
                if(inputNoWhitespaceLength == 4) {
                    throw new InsufficientArgumentsException("'Mark value' cannot be empty");
                }
                break;
                //valid integer will be checked later
            case UNMARK:
                if(inputNoWhitespaceLength == 6) {
                    throw new InsufficientArgumentsException("'Unmark value' cannot be empty");
                }
                break;
                //valid integer will be checked later
            case DEADLINE:
                if(inputNoWhitespaceLength == 8) {
                    throw new InsufficientArgumentsException("'Deadline task' cannot be empty");
                }
                if(backslashCount != 1){
                    throw new IllegalArgumentException("Number of '/' is invalid");
                }
                break;
            case EVENT:
                if(inputNoWhitespaceLength == 5) {
                    throw new InsufficientArgumentsException("'Event task' cannot be empty");
                }
                if(backslashCount != 2){
                    throw new IllegalArgumentException("Number of '/' is invalid");
                }
                break;
        }
       return userCommand;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String DELIMITER = "______________________________________";

        System.out.println(DELIMITER);
        System.out.println("Hello! I'm Drew");
        System.out.println("What can I do for you?");
        System.out.println(DELIMITER);

        String userInput = sc.nextLine();
        Task[] taskList = new Task[100];
        int listLength = 0;

        while (!userInput.equalsIgnoreCase("bye")){
            String reply = "";

            try {
                Command userCommand;
                userCommand = checkCommandIdentity(userInput);

                switch (userCommand) {
                    case LIST: {
                        reply = reply + "Here are the tasks in your list:" + "\n";
                        for (int i = 0; i < listLength; i++) {
                            reply = reply + Integer.toString(i + 1) + ". " +
                                    taskList[i].statusString() + "\n";
                        }
                        reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                        break;
                    }
                    case MARK: {
                        int taskIndex;
                        taskIndex = Integer.parseInt(userInput.substring(5));
                        if (taskIndex > listLength) {
                            throw new IllegalArgumentException("This task does not exist!");
                        }
                        taskList[taskIndex - 1].setDone();
                        reply = "Well done! I have marked this task as done:\n" +
                                taskList[taskIndex - 1].statusString() + "\n";
                        break;
                    }
                    case UNMARK: {
                        int taskIndex;
                        taskIndex = Integer.parseInt(userInput.substring(7));
                        if (taskIndex > listLength) {
                            throw new IllegalArgumentException("This task does not exist!");
                        }
                        taskList[taskIndex - 1].setNotDone();
                        reply = "Ok. I have marked this task as not done yet:\n" +
                                taskList[taskIndex - 1].statusString() + "\n";
                        break;
                    }
                    case TODO: {
                        String todoDescription = userInput.substring(5);
                        Todo newTask = new Todo(todoDescription);
                        taskList[listLength] = newTask;
                        reply = "Got it. I've added this task:\n";
                        reply = reply + newTask.statusString() + "\n";
                        listLength++;
                        reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                        break;
                    }
                    case DEADLINE: {
                        int firstBackslashIndex = userInput.indexOf("/");
                        String deadlineDescription = userInput.substring(9, firstBackslashIndex);
                        String date = userInput.substring(firstBackslashIndex + 4);
                        Deadline newTask = new Deadline(deadlineDescription, date);
                        taskList[listLength] = newTask;
                        reply = "Got it. I've added this task:\n";
                        reply = reply + newTask.statusString() + "\n";
                        listLength++;
                        reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                        break;
                    }
                    case EVENT: {
                        int firstBackslashIndex = userInput.indexOf("/");
                        int secondBackslashIndex = userInput.indexOf("/", firstBackslashIndex + 1);
                        //known bug: event // does not work
                        String eventDescription = userInput.substring(6, firstBackslashIndex);
                        String startDate = userInput.substring(firstBackslashIndex + 1, secondBackslashIndex);
                        String endDate = userInput.substring(secondBackslashIndex + 1);
                        Event newTask = new Event(eventDescription, startDate, endDate);
                        taskList[listLength] = newTask;
                        reply = "Got it. I've added this task:\n";
                        reply = reply + newTask.statusString() + "\n";
                        listLength++;
                        reply = reply + String.format("Now you have %d task(s) in the list.", listLength) + "\n";
                        break;
                    }
                    case ADD:
                        taskList[listLength] = new Task(userInput);
                        reply = String.format("added: %s\n", userInput);
                        listLength++;
                        break;
                }
            } catch (UnknownCommandException e) {
                reply = "That does not seem to be a valid command. Please try again.\n";
            } catch (InsufficientArgumentsException e) {
                reply = e.getMessage() + "\n";
            } catch (NumberFormatException e) {
                reply = "Please enter a valid number for your Mark/Unmark command.\n";
            } catch (IllegalArgumentException e) {
                reply = e.getMessage() + "\n";
            } finally {
                System.out.println(DELIMITER);
                System.out.print(reply);
                System.out.println(DELIMITER);

                userInput = sc.nextLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
