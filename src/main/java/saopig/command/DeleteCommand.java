package saopig.command;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.TaskList;

public class DeleteCommand extends Command {

    private String command;
    private int typeIndex;

    public DeleteCommand(String command, int typeIndex) {
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    public void deleteTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            int index = Integer.parseInt(input.substring(7)) - 1;
            ui.printMessage("\n"
                    + "Oh, splendid! Your task: {" + taskList.getTask(index).toString()
                    + "} has been deleted successfully.\n "
                    + "Now you have " + (taskList.getSize() - 1) + " tasks in the list.");
            taskList.deleteTask(index);
            storage.saveTaskList(taskList);
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have forgotten to give an argument for the delete command.\n "
                    + "Don't worry, it happens to most of us.\n "
                    + "Just add the index for the task you'd like to delete, and you'll be all set.\n "
                    + "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            ui.printMessage("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid index for the task list "
                    + "or your input is not a number.");
        } catch (NullPointerException e) {
            ui.printMessage("\n"
                    + "Oopses daisy!\n "
                    + "It seems that taskList do not have anything inside it (return null).");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        deleteTask(command, tasks, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
