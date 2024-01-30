import CustomExceptions.BlankEventException;
import CustomExceptions.MalformedUserInputException;
import CustomExceptions.NoTaskCreatedYetException;
import CustomExceptions.TooManyTasksException;
import Parser.EventParser;
import TaskList.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {

    private Ui ui;

    public Duke() {
        ui = new Ui();
    }

    public void run(){
        ui.showWelcome();

        String userInput = "";

        // Outsource the dataStorage.
        DataStorage dataStorage = new DataStorage(Integer.MAX_VALUE, "database.txt");

        // To read in user input
        Scanner sc = new Scanner(System.in);


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
                handleCommandWithIndex(dataStorage, userInput, TypeOfActions.MARK);
            } else if (userInput.startsWith("unmark")) {
                handleCommandWithIndex(dataStorage, userInput, TypeOfActions.UNMARK);
            } else if (userInput.startsWith("delete")) {
                handleCommandWithIndex(dataStorage, userInput, TypeOfActions.DELETE);
            } else if (userInput.startsWith("todo")) {
                // We further do another Regex search

                try {
                    // In this format: todo borrow book.
                    Task task = EventParser.toDoParser(userInput);
                    createNewTask(dataStorage, task);

                } catch (MalformedUserInputException malformedUserInputException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t You have a malformed input for your todo command. \n" +
                            "\t It is likely that you are missing the todo name\n" +
                            "\t Your command should be in this format: todo event_name"
                    );
                    System.out.println("\t ____________________________________________________________");
                } catch (BlankEventException e) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t " + e.getMessage());
                    System.out.println("\t ____________________________________________________________");
                }


            } else if (userInput.startsWith("deadline")) {
                // We further do another Regex search
                try {
                    Task task = EventParser.deadlineParser(userInput);
                    createNewTask(dataStorage, task);
                } catch (MalformedUserInputException malformedUserInputException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t You have a malformed input for your deadline command. \n" +
                            "\t It is likely that you are missing the event name and or a by date.\n" +
                            "\t Your command should be in this format: deadline return book /by Sunday \n" +
                            "\t " + malformedUserInputException.getMessage()
                    );
                    System.out.println("\t ____________________________________________________________");

                } catch (BlankEventException e) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t " + e.getMessage());
                    System.out.println("\t ____________________________________________________________");
                }


            } else if (userInput.startsWith("event")) {
                // We further do another Regex search
                // In this format: event project meeting /from Mon 2pm /to 4pm

                try {
                    Task task = EventParser.eventParser(userInput);
                    createNewTask(dataStorage, task);
                } catch (MalformedUserInputException malformedUserInputException) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t You have a malformed input for your event command.\n" +
                            "\t It is likely that you are missing the event name" +
                            "\t Your command should be in this format: event project meeting /from Mon 2pm /to 4pm");
                    System.out.println("\t ____________________________________________________________");

                } catch (BlankEventException e) {
                    System.out.println("\t ____________________________________________________________");
                    System.out.println("\t " + e.getMessage());
                    System.out.println("\t ____________________________________________________________");
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
                            "\t Now you have " + dataStorage.getTaskCount() + " task(s) in the list.\n" +
                            "\t ____________________________________________________________"
            );

        } catch (TooManyTasksException tooManyTaskException) {
            System.out.println("\t ____________________________________________________________");
            System.out.println("\t You are too busy .... how come you got so many tasks??");
            System.out.println("\t See la the array no space already. Delete some stuff or restart the program please.");
        }
    }

    public static void main(String[] args) {

        new Duke().run();

    }

    public static void handleCommandWithIndex(DataStorage dataStorage, String userInput, TypeOfActions typeOfActions) {
        try {
            int idToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (typeOfActions == TypeOfActions.UNMARK) {
                dataStorage.setTaskStatus(idToMark, false);

                System.out.println("\t ____________________________________________________________");
                System.out.println("\t  Nice! I've marked this task as not completed yet:");
                System.out.println("\t " + dataStorage.getTask(idToMark).toString());
                System.out.println("\t ____________________________________________________________");
            } else if (typeOfActions == TypeOfActions.MARK) {
                dataStorage.setTaskStatus(idToMark, true);

                System.out.println("\t ____________________________________________________________");
                System.out.println("\t Nice! I've marked this task as done:");
                System.out.println("\t " + dataStorage.getTask(idToMark).toString());
                System.out.println("\t ____________________________________________________________");

            } else if (typeOfActions == TypeOfActions.DELETE) {
                Task task = dataStorage.getTask(idToMark);
                dataStorage.deleteTask(idToMark);

                System.out.println("\t ____________________________________________________________");
                System.out.println("\t Noted. I've removed this task:");
                System.out.println("\t " + task.toString());
                System.out.println("\t Now you have " + dataStorage.getTaskCount() + " tasks in the list.");
                System.out.println("\t ____________________________________________________________");
            }


        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.out.println("\t ____________________________________________________________");
            System.out.println("\t Please do not enter an invalid index. There are " + dataStorage.getTaskCount() + " task(s) currently.");
            System.out.println("\t ____________________________________________________________");
        } catch (NoTaskCreatedYetException noTaskCreatedYetException) {
            System.out.println("\t ____________________________________________________________");
            System.out.println("\t No task is created here yet.");
            System.out.println("\t ____________________________________________________________");
        } catch (NumberFormatException numberFormatException) {
            System.out.println("\t ____________________________________________________________");
            System.out.println("\t  Please enter positive integers 1, 2, 3, ... etc only.");
            System.out.println("\t ____________________________________________________________");
        }
    }





}

