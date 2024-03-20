package saopig.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.Task;
import saopig.task.TaskList;


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
        assert typeIndex == 0 || typeIndex == 1 : "typeIndex should be 0 or 1";
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
    public String listTasks(TaskList taskList, Ui ui) {
        if (taskList.getTasks().isEmpty()) {
            return (ui.getListIsEmpty());
        }
        assert !taskList.getTasks().isEmpty() : "Task list should not be empty";
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < taskList.getTasks().size(); i++) {
            Task task = taskList.getTasks().get(i);
            response.append((i + 1)).append(". ").append(task.toString()).append("\n");
        }
        return response.toString();
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
     * @return Response to the user.
     */
    public String listTasksOnDate(String input, TaskList taskList, Ui ui) {
        try {
            StringBuilder response = new StringBuilder();
            checkValue(input.length(), 12, Integer.MAX_VALUE);
            assert input.length() >= 12 : "Input length should be at least 12";
            String date = input.substring(11);
            response.append("\n" + "Oh, splendid! Let me check my calendar for tasks on ").append(date).append("...");
            LocalDateTime dateTime = LocalDateTime.parse(date + " 00:00", DATE_TIME_FORMATTER);
            ArrayList<Task> tasksOnDate = new ArrayList<>();
            getTaskOnDate(taskList, dateTime, tasksOnDate);
            if (tasksOnDate.isEmpty()) {
                addNoTaskMessage(response, date);
                return response.toString();
            }
            response.append("\n" + "Oh, splendid! Here are the tasks on ")
                    .append(date).append(":");
            for (int i = 0; i < tasksOnDate.size(); i++) {
                Task task = tasksOnDate.get(i);
                response.append((i + 1)).append(". ").append(task.toString());
            }
            return response.toString();
        } catch (SaopigInvaildSizeException e) {
            return (e.getMessage() + ui.getListOnDateNoArgument());
        } catch (DateTimeParseException e) {
            return (ui.getInvalidDateFormat());
        }
    }

    private static void addNoTaskMessage(StringBuilder response, String date) {
        response.append("\n" + "Oh dear, it looks like there are no tasks on ")
                .append(date).append("!\n ")
                .append("But that's alright.\n ")
                .append("It gives us a chance to start fresh and dream up some new plans.\n ")
                .append("Whenever you're ready to add tasks, I'll be right here to assist you.\n ")
                .append("Let's make it a magical journey together!");
    }

    private static void getTaskOnDate(TaskList taskList, LocalDateTime dateTime, ArrayList<Task> tasksOnDate) {
        for (Task task : taskList.getTasks()) {
            if (task instanceof Deadline) {
                deadlineDateChecker(dateTime, tasksOnDate, task);
            } else if (task instanceof Event) {
                eventDateChecker(dateTime, tasksOnDate, task);
            }
        }
    }

    private static void deadlineDateChecker(LocalDateTime dateTime, ArrayList<Task> tasksOnDate, Task task) {
        if (((Deadline) task).getBy().toLocalDate().isEqual(dateTime.toLocalDate())) {
            tasksOnDate.add(task);
        }
    }

    private static void eventDateChecker(LocalDateTime dateTime, ArrayList<Task> tasksOnDate, Task task) {
        if (((Event) task).getStartTime().toLocalDate().isEqual(dateTime.toLocalDate())
                || ((Event) task).getEndTime().toLocalDate().isEqual(dateTime.toLocalDate())) {
            tasksOnDate.add(task);
        }
    }

    /**
     * Executes the command to list tasks.
     *
     * @param tasks   The task list.
     * @param ui      The user interface.
     * @param storage The storage.
     * @return response to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (typeIndex == 0) {
            return listTasks(tasks, ui);
        } else if (typeIndex == 1) {
            return listTasksOnDate(command, tasks, ui);
        } else {
            return null;
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
