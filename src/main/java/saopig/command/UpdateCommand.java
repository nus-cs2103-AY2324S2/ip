package saopig.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.Task;
import saopig.task.TaskList;

/**
 * Represents a command to update a task in the task list.
 */
public class UpdateCommand extends Command {
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private String command;
    private int typeIndex;

    /**
     * Constructor for UpdateCommand.
     *
     * @param command   User input command.
     * @param typeIndex Index of the type of task to be updated.
     */
    public UpdateCommand(String command, int typeIndex) {
        this.command = command;
        this.typeIndex = typeIndex;
    }

    /**
     * Updates the task in the task list based on the user input.
     *
     * @param input    User input command.
     * @param taskList Task list to be updated.
     * @param ui       User interface to interact with the user.
     * @param storage  Storage to save the task list.
     * @return Response to the user.
     */
    @SuppressWarnings("checkstyle:SeparatorWrap")
    public String updateTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            StringBuilder response = new StringBuilder("Got it. I've updated this task:\n");
            checkValue(input.length(), 8, Integer.MAX_VALUE);
            String[] splitCommand = input.split(" ");
            int taskIndex = Integer.parseInt(splitCommand[1]) - 1;
            int lengthOfCommand = splitCommand.length;
            String ui1 = updateTaskByType(taskList, ui, lengthOfCommand, splitCommand, taskIndex, response);
            if (ui1 != null) {
                return ui1;
            }
            storage.saveTaskList(taskList);
            if (response.toString().equals("Got it. I've updated this task:\n")) {
                return "Nothing to update.";
            }
            return response.toString();
        } catch (SaopigInvaildSizeException e) {
            return (e.getMessage() + ui.getUpdateMissDetail());
        } catch (NumberFormatException e) {
            return ("Input is not a number.");
        } catch (IndexOutOfBoundsException e) {
            return (ui.getInvalidIndex());
        }
    }

    private String updateTaskByType(
            TaskList taskList, Ui ui, int lengthOfCommand,
            String[] splitCommand, int taskIndex, StringBuilder response) {
        for (int i = 0; i < lengthOfCommand; i += 1) {
            if (isChangeDesription(splitCommand[i])) {
                spliteInput(splitCommand, lengthOfCommand, i);
                String description = spliteInput(splitCommand, lengthOfCommand, i);
                taskList.getTask(taskIndex).changeDescription(description);
                response.append("Change description to ").append(description).append("\n");
            } else if (isChangeDeadLineTime(splitCommand[i])) {
                String ui1 = updateTaskTime(taskList, ui, splitCommand, lengthOfCommand, i, taskIndex, response);
                if (ui1 != null) {
                    return ui1;
                }
            } else if (isChangeStart(splitCommand[i])) {
                String ui1 = updateTaskStartTime(
                        taskList, ui, splitCommand, lengthOfCommand, i, taskIndex, response);
                if (ui1 != null) {
                    return ui1;
                }
            }
        }
        return null;
    }

    private String updateTaskStartTime(
            TaskList taskList, Ui ui, String[] splitCommand,
            int lengthOfCommand, int i, int taskIndex, StringBuilder response) {
        spliteInput(splitCommand, lengthOfCommand, i);
        String time = spliteInput(splitCommand, lengthOfCommand, i);
        LocalDateTime startDateTime;
        try {
            startDateTime = LocalDateTime.parse(time, DATE_TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            return (ui.getInvalidDateFormat());
        }
        Event event = (Event) taskList.getTask(taskIndex);
        event.changeStartTime(startDateTime);
        response.append("Change start time to ").append(time);
        return null;
    }

    private String updateTaskTime(
            TaskList taskList, Ui ui, String[] splitCommand,
            int lengthOfCommand, int i, int taskIndex, StringBuilder response) {
        spliteInput(splitCommand, lengthOfCommand, i);
        Task task = taskList.getTask(taskIndex);
        String time = spliteInput(splitCommand, lengthOfCommand, i);
        LocalDateTime deadlineDateTime;
        try {
            deadlineDateTime = LocalDateTime.parse(time, DATE_TIME_FORMATTER);

        } catch (DateTimeParseException e) {
            return (ui.getInvalidDateFormat());
        }
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.changeDeadline(deadlineDateTime);
            response.append("Change deadline to ").append(time);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            event.changeEndTime(deadlineDateTime);
        }
        return null;
    }

    private String spliteInput(String[] splitCommand, int lengthOfCommand, int i) {
        StringBuilder string = new StringBuilder();
        for (int j = i + 1; j < lengthOfCommand; j += 1) {
            if (isCommand(splitCommand[j])) {
                break;
            }
            if (j == lengthOfCommand - 1) {
                string.append(splitCommand[j]);
            } else {
                string.append(splitCommand[j]).append(" ");
            }
        }
        return string.toString();
    }

    private static boolean isCommand(String splitCommand) {
        return Objects.equals(splitCommand, "/by")
                || Objects.equals(splitCommand, "/at")
                || Objects.equals(splitCommand, "/to")
                || Objects.equals(splitCommand, "/s")
                || Objects.equals(splitCommand, "/start")
                || Objects.equals(splitCommand, "/from")
                || Objects.equals(splitCommand, "/d");
    }

    private static boolean isChangeDesription(String splitCommand) {
        return splitCommand.equals("/d") || splitCommand.equals("/decription");
    }

    private static boolean isChangeStart(String splitCommand) {
        return splitCommand.equals("/s") || splitCommand.equals("/start") || splitCommand.equals("/from");
    }

    private static boolean isChangeDeadLineTime(String splitCommand) {
        return splitCommand.equals("/by") || splitCommand.equals("/at") || splitCommand.equals("/to");
    }


    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return updateTask(command, tasks, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
