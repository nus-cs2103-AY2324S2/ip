import commands.ByeCommand;
import commands.Command;
import commands.CommandResult;
import common.DataStorage;
import exception.MalformedUserInputException;
import parser.EventParser;
import parser.Parser;
import tasklist.Task;

public class Duke {

    private Ui ui;
    private DataStorage dataStorage;

    public Duke() {

    }

    public void start() {
        // TODO: There could be a try catch here
        this.ui = new Ui();
        this.dataStorage = new DataStorage(Integer.MAX_VALUE, "database.txt");
        ui.showWelcome();
    }

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodbye();
        System.exit(0);
    }


    public void run() {
        start();
        runCommandLoopUntilExitCommand();
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }


    public CommandResult executeCommand(Command command) {
        command.setData(dataStorage);
        CommandResult commandResult = command.execute();
        return commandResult;
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {

        Command command;

        do {
            // Keep reading user input until they type "bye"
            String userInput = ui.readCommand();
            command = new Parser().parseCommand(userInput);

            CommandResult commandResult = executeCommand(command);

            ui.showLine();
            System.out.println(commandResult.feedbackToUser);
            ui.showLine();
//            else if (userInput.startsWith("mark")) {
//                handleCommandWithIndex(dataStorage, userInput, TypeOfActions.MARK);
//            } else if (userInput.startsWith("unmark")) {
//                handleCommandWithIndex(dataStorage, userInput, TypeOfActions.UNMARK);
//            } else if (userInput.startsWith("delete")) {
//                handleCommandWithIndex(dataStorage, userInput, TypeOfActions.DELETE);
//            } else if (userInput.startsWith("todo")) {
//

        } while (!ByeCommand.isExit(command));
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
        } catch (NumberFormatException | MalformedUserInputException numberFormatException) {
            System.out.println("\t ____________________________________________________________");
            System.out.println("\t  Please enter positive integers 1, 2, 3, ... etc only.");
            System.out.println("\t ____________________________________________________________");
        }
    }


}

