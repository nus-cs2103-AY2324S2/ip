package saopig.command;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.Task;
import saopig.task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a command to list tasks.
 */
public class ListCommand extends Command {
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final String FILE_PATH = "./data/saopigTaskList.txt";
    private static final String FILE_DIRECTORY = "./data";

    private String command;
    private int typeIndex; //0 for list all, 1 for listtaskondate

    /**
     * Constructs a list command.
     *
     * @param command   The command.
     * @param typeIndex The type index.
     */
    public ListCommand(String command, int typeIndex) {
        this.command = command;
        this.typeIndex = typeIndex;
    }

    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    /**
     * Lists all tasks.
     * If there are no tasks, prints a message.
     *
     * @param taskList The task list.
     * @param ui       The user interface.
     */
    public void listTasks(TaskList taskList, Ui ui) {
        if (taskList.getTasks().isEmpty()) {
            ui.printMessage("\n"
                    + "Oh dear, it looks like there are no tasks yet!\n "
                    + "But that's alright.\n "
                    + "It gives us a chance to start fresh and dream up some new plans.\n "
                    + "Whenever you're ready to add tasks, I'll be right here to assist you.\n "
                    + "Let's make it a magical journey together!");
            return;
        }
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            Task task = taskList.getTasks().get(i);
            ui.printMessage((i + 1) + ". " + task.toString());
        }
    }

    /**
     * Lists tasks on a date.
     * If there are no tasks on the date, prints a message.
     * If the date is invalid, prints a message.
     * If the command is invalid, prints a message.
     * If the command is incomplete, prints a message.
     * If the command is empty, prints a message.
     *
     * @param input    The input.
     * @param taskList The task list.
     * @param ui       The user interface.
     */
    public void listTasksOnDate(String input, TaskList taskList, Ui ui) {
        try {
            checkValue(input.length(), 16, Integer.MAX_VALUE);
            String date = input.substring(15);
            ui.printMessage("\n" +
                    "Oh, splendid! Let me check my calendar for tasks on " + date + "...");
            LocalDateTime dateTime = LocalDateTime.parse(date + " 00:00", DATE_TIME_FORMATTER);
            ArrayList<Task> tasksOnDate = new ArrayList<>();
            for (Task task : taskList.getTasks()) {
                if (task instanceof Deadline) {
                    if (((Deadline) task).getBy().toLocalDate().isEqual(dateTime.toLocalDate())) {
                        tasksOnDate.add(task);
                    }
                } else if (task instanceof Event) {
                    if (((Event) task).getStartTime().toLocalDate().isEqual(dateTime.toLocalDate()) ||
                            ((Event) task).getEndTime().toLocalDate().isEqual(dateTime.toLocalDate())) {
                        tasksOnDate.add(task);
                    }
                }
            }
            if (tasksOnDate.isEmpty()) {
                ui.printMessage("\n"
                        + "Oh dear, it looks like there are no tasks on " + date + "!\n "
                        + "But that's alright.\n "
                        + "It gives us a chance to start fresh and dream up some new plans.\n "
                        + "Whenever you're ready to add tasks, I'll be right here to assist you.\n "
                        + "Let's make it a magical journey together!");
                return;
            }
            ui.printMessage("\n" +
                    "Oh, splendid! Here are the tasks on " + date + ":");
            for (int i = 0; i < tasksOnDate.size(); i++) {
                Task task = tasksOnDate.get(i);
                ui.printMessage((i + 1) + ". " + task.toString());
            }
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage(e.getMessage()
                    + "\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have forgotten to give an argument for the listtaskondate command.\n "
                    + "Don't worry, it happens to most of us.\n "
                    + "Just add the date for the task you'd like to list, and you'll be all set.\n "
                    + "Please try again, or type 'bye' to exit.");
        } catch (DateTimeParseException e) {
            ui.printMessage("\n"
                    + "Oopses daisy!\n "
                    + "It seems like you might have given an invalid date time format.\n "
                    + "Please use the format: yyyy-MM-dd");
        }
    }

    /**
     * Executes the command to list tasks.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (typeIndex == 0) {
            listTasks(tasks, ui);
        } else if (typeIndex == 1) {
            listTasksOnDate(command, tasks, ui);
        }
    }

    /**
     * Returns false to indicate that the program should continue running.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
