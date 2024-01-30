package commands;

import exception.MalformedUserInputException;
import tasklist.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "\t " + COMMAND_WORD + ": Delete a selected event when it exists. \n"
            + "\t Example: " + COMMAND_WORD
            + " 1 ";

    public static final String MESSAGE_SUCCESS = "\t Noted I have removed this task: \n" +
            "\t  %s \n" +
            "\t Now you have %d task(s) in the list.";
    public static final String MESSAGE_INVALID_ID = "\t Please enter a integer that is 1 or larger.";
    public static final String MESSAGE_TASK_NOT_CREATED_YET = " \t This is an invalid index \n" +
            "\t There are %d tasks available.\n";

    private int targetIndex;

    public DeleteCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Task task = dataStorage.getTask(targetIndex);
            dataStorage.deleteTask(targetIndex);
            return new CommandResult(String.format(MESSAGE_SUCCESS, task, dataStorage.getTaskCount()));
        } catch (MalformedUserInputException e) {
            return new CommandResult(e.getMessage());
        } catch (IndexOutOfBoundsException iobe) {
            return new CommandResult(String.format(MESSAGE_TASK_NOT_CREATED_YET, dataStorage.getTaskCount()));
        }
    }
}
