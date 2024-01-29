package saopig.command;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Task;
import saopig.task.TaskList;

public class MarkCommand extends Command {

    private String command;
    private int typeIndex; //0 for mark done, 1 for mark undone

    public MarkCommand(String command, int typeIndex) {
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    public void markTaskAsDone(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            int index = Integer.parseInt(input.substring(5)) - 1;
            Task task = taskList.getTask(index);
            task.markAsDone();
            storage.saveTaskList(taskList);
            ui.printMessage("\n" +
                    "Oh, splendid! Your task: {" + task.toString() + "} has been marked as done successfully.\n " +
                    "Isn't it just wonderful when things go exactly as planned?\n " +
                    "I'm so proud of you for getting it done!");
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage(e.getMessage() +
                    "\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have forgotten to give an argument for the mark command.\n " +
                    "Don't worry, it happens to most of us.\n " +
                    "Just add the index for the task you'd like to mark, and you'll be all set.\n " +
                    "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list " +
                    "or your input is not a number.");
        }
    }

    public void unmarkTaskAsDone(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            int index = Integer.parseInt(input.substring(7)) - 1;
            Task task = taskList.getTask(index);
            task.unmarkAsDone();
            storage.saveTaskList(taskList);
            ui.printMessage("\n" +
                    "Oh, you've unmarked task: {" + task.toString() + "}?\n " +
                    "No worries at all! It's always okay to reevaluate and adjust your plans.\n " +
                    "Flexibility is a sign of strength, you know. Keep up the great work!");
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage(e.getMessage() +
                    "\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have forgotten to give an argument for the unmark command.\n " +
                    "Don't worry, it happens to most of us.\n " +
                    "Just add the index for the task you'd like to unmark, and you'll be all set.\n " +
                    "Please try again, or type 'bye' to exit.");
        } catch (IndexOutOfBoundsException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list.");
        } catch (NumberFormatException e) {
            ui.printMessage("\n" +
                    "Oopses daisy!\n " +
                    "It seems like you might have given an invalid index for the task list " +
                    "or your input is not a number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (typeIndex == 0) {
            markTaskAsDone(command, tasks, ui, storage);
        } else {
            unmarkTaskAsDone(command, tasks, ui, storage);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
