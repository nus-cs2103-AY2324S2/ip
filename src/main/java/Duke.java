import CustomExceptions.MalformedUserInputException;
import CustomExceptions.NoTaskCreatedYetException;
import CustomExceptions.TooManyTasksException;
import TaskList.Task;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Parser.EventParser.*;

public class Duke {


    public static void main(String[] args) {

        String userInput = "";

        // Outsource the dataStorage.
        DataStorage dataStorage = new DataStorage(100);

        // To read in user input
        Scanner sc = new Scanner(System.in);

        String welcomeMessage = "\t ____________________________________________________________\n" +
                "\t Hello! I'm JeromeGPT \n" +
                "\t What can I do for you?\n" +
                "\t ____________________________________________________________";

        System.out.println(welcomeMessage);


        while (true) {
            // Keep reading user input until they type "bye"
            userInput = sc.nextLine();

            if (userInput.equals("list")) {
                // Print out all the tasks.
                System.out.println("\t ____________________________________________________________");

                for (int i = 0; i < dataStorage.getTaskCount(); i++) {
                    int humanReadableId = i + 1;
                    Task currentTask = dataStorage.getTask(i);
                    System.out.println("\t " + humanReadableId + ". " + currentTask.toString());
                }

                System.out.println("\t ____________________________________________________________");
            } else if (userInput.startsWith("mark")) {
                // TODO: possibly need to handle a task that is called "mark..."


                try {
                    int idToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

                    dataStorage.setTaskStatus(idToMark, true);

                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t  Nice! I've marked this task as done:");
                    System.out.println("\t " + dataStorage.getTask(idToMark).toString());
                    System.out.println("\t ____________________________________________________________");
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t  Please do not enter an invalid index.");
                    System.out.println("\t ____________________________________________________________");
                } catch (NumberFormatException numberFormatException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t  Please enter numbers only.");
                    System.out.println("\t ____________________________________________________________");
                } catch (NoTaskCreatedYetException noTaskCreatedYetException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t  No task is created here yet.");
                    System.out.println("\t ____________________________________________________________");
                }

            } else if (userInput.startsWith("unmark")) {
                // TODO: possibly need to handle a task that is called "mark..."

                try {
                    int idToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;


                    dataStorage.setTaskStatus(idToMark, false);

                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t  Nice! I've marked this task as not completed yet:");
                    System.out.println("\t " + dataStorage.getTask(idToMark).toString());
                    System.out.println("\t ____________________________________________________________");
                } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t  Please do not enter an invalid entry");
                    System.out.println("\t ____________________________________________________________");
                } catch (NoTaskCreatedYetException noTaskCreatedYetException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t  No task is created here yet.");
                    System.out.println("\t ____________________________________________________________");
                }

            } else if (userInput.startsWith("todo")) {
                // We further do another Regex search


                try {
                    Task task = toDoParser(userInput);
                    createNewTask(dataStorage, task);
                } catch (MalformedUserInputException malformedUserInputException) {
                    // TODO: Handle failure in terms of missing variable.

                    System.out.println("You have a malformed input for your todo command. " +
                            "It is likely that you are missing the todo name");
                }


            } else if (userInput.startsWith("deadline")) {
                // We further do another Regex search


                try {
                    Task task = deadlineParser(userInput);
                    createNewTask(dataStorage, task);
                } catch (MalformedUserInputException malformedUserInputException) {
                    // TODO: Handle failure in terms of missing variable.
                    System.out.println("You have a malformed input for your deadline command. " +
                            "It is likely that you are missing the deadline name");
                }


            } else if (userInput.startsWith("event")) {
                // We further do another Regex search

                // Solution below adapted from https://www.w3schools.com/java/java_regex.asp
                Pattern pattern = Pattern.compile("^event (.+) \\/from (.+) \\/to (.+)$");
                Matcher matcher = pattern.matcher(userInput);

                try {
                    Task task = eventParser(userInput);
                    createNewTask(dataStorage, task);
                } catch (MalformedUserInputException malformedUserInputException) {
                    // TODO: Handle failure in terms of missing variable.
                    System.out.println("You have a malformed input for your event command. " +
                            "It is likely that you are missing the event name");
                }

            } else if (userInput.equals("bye")) {
                // Use this construct because we don't want to echo the bye message.
                break;
            } else {
                System.out.println("\t ____________________________________________________________");
                // Emoji of \uD83D\uDE05 is 😅
                System.out.println(" \t I have no idea what you are trying to tell me??? \uD83D\uDE05");
                System.out.println(" \t Please try again.");
                System.out.println("\t ____________________________________________________________");
            }

        }

        System.out.println(
                "\t ____________________________________________________________\n" +
                        "\t Bye. Hope to see you again soon!\n" +
                        "\t ____________________________________________________________"
        );
    }

    private static void createNewTask(DataStorage dataStorage, Task task) {
        // This allows user to add in a new task.
        try {
            dataStorage.addTask(task);
            System.out.println(
                    "\t ____________________________________________________________\n" +
                            "\t Got it. I've added this task:\n" +
                            "\t added: " + task.toString() + "\n" +
                            "\t Now you have " + dataStorage.getTaskCount() + " tasks in the list.\n" +
                            "\t ____________________________________________________________"
            );

        } catch (TooManyTasksException tooManyTaskException) {
            System.out.println("\t ____________________________________________________________");
            System.out.println("\t  Sorry you are too busy .... how come you got so many tasks??");
            System.out.println("\t  See la the array no space already. Delete some stuff or restart the program please.");
            System.out.println("\t ____________________________________________________________");
        }
    }


}
