package saopig.command;

import saopig.Saopig;
import saopig.SaopigInvaildSizeException;
import saopig.Storage;
import saopig.Ui;
import saopig.task.Deadline;
import saopig.task.Event;
import saopig.task.TaskList;
import saopig.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private static final String TIME_PATTERN = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final String FILE_PATH = "./data/saopigTaskList.txt";
    private static final String FILE_DIRECTORY = "./data";
    private String command;
    private int typeIndex; //0 for todo, 1 for deadline, 2 for event
    private static void checkValue(int value, int lowerBound, int upperBound) throws SaopigInvaildSizeException {
        if (value < lowerBound || value > upperBound) {
            throw new SaopigInvaildSizeException("Error");
        }
    }

    public void addTodoTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 6, Integer.MAX_VALUE);
            String processedInput = input.substring(5);
            Todo task = new Todo(processedInput);
            taskList.addTodoTask(task);
            storage.saveTaskList(taskList);
            ui.printMessage("\n" +
                    "Oh, splendid! Your Todo task: {" + task.toString() + "} has been added successfully.\n " +
                    "Now you have " + taskList.getTasks().size() + " tasks in the list.");
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage(e.getMessage() +
                    "\n" +
                    "Oh, it looks like the 'todo' command is missing some details for the task.\n " +
                    "No problem at all!\n " +
                    "Just add a bit more information about what you'd like to do, " +
                    "and it will be as perfect as a sunny day.\n " +
                    "You're doing wonderfully! ");
        }
    }

    public void addDeadlineTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 10, Integer.MAX_VALUE);
            String splitInput = input.substring(9);
            String[] splitArguments = splitInput.split(" /by ");
            if (splitArguments.length != 2) {
                ui.printMessage("\n" +
                        "Whoopsie!\n " +
                        "It seems like you may have forgotten to write the deadline time.");
            }
            LocalDateTime deadlineDateTime = null;
            try {
                deadlineDateTime = LocalDateTime.parse(splitArguments[1], DATE_TIME_FORMATTER);

            } catch (DateTimeParseException e) {
                ui.printMessage("\n" +
                        "Whoopsie!\n " +
                        "It seems like you may have given an invalid date time format.\n " +
                        "Please use the format: yyyy-MM-dd HH:mm");
                return;
            }
            Deadline task = new Deadline(splitArguments[0], deadlineDateTime);
            taskList.addDeadlineTask(task);
            storage.saveTaskList(taskList);
            ui.printMessage("\n" +
                    "Oh, splendid! Your Deadline task: {" + task.toString() + "} has been added successfully.\n " +
                    "Now you have " + taskList.getTasks().size() + " tasks in the list.");
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage(e.getMessage() +
                    "\n" +
                    "Oh, it looks like the 'deadline' command is missing some details for the task.\n " +
                    "No problem at all!\n " +
                    "Just add a bit more information about what you'd like to do, " +
                    "and it will be as perfect as a sunny day.\n " +
                    "You're doing wonderfully! ");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printMessage("\n" +
                    "Whoopsie! " +
                    "It seems like you may have forgotten to write the deadline time " +
                    "or didn't use ' /by ' in your command.\n " +
                    "Remember there is a space before and after '/by'.\n " +
                    "It's a tiny detail, " +
                    "but oh so important! Just add the deadline after '/by ', " +
                    "and you'll be as organized as a library on a quiet morning.\n " +
                    "You're doing an amazing job! ");
        }
    }

    public void addEventTask(String input, TaskList taskList, Ui ui, Storage storage) {
        try {
            checkValue(input.length(), 7, Integer.MAX_VALUE);
            String splitInput = input.substring(6);
            String[] splitArguments = splitInput.split("/");
            if (splitArguments.length != 3) {
                ui.printMessage("\n" +
                        "Whoopsie!\n " +
                        "It seems like you may have forgotten to write the event start or end time ");
            }
            String description = splitArguments[0].trim();
            String fromTime = splitArguments[1].trim().substring(5); // Remove "from " prefix
            String toTime = splitArguments[2].trim().substring(3); // Remove "to " prefix
            LocalDateTime fromDateTime = null;
            LocalDateTime toDateTime = null;
            try {
                fromDateTime = LocalDateTime.parse(fromTime, DATE_TIME_FORMATTER);
                toDateTime = LocalDateTime.parse(toTime, DATE_TIME_FORMATTER);
            } catch (DateTimeParseException e) {
                ui.printMessage("\n" +
                        "Whoopsie!\n " +
                        "It seems like you may have given an invalid date time format.\n " +
                        "Please use the format: yyyy-MM-dd HH:mm");
                return;
            }
            Event task = new Event(description, fromDateTime, toDateTime);
            taskList.addEventTask(task);
            storage.saveTaskList(taskList);
            ui.printMessage("\n" +
                    "Oh, splendid! " +
                    "Your Event task: {" + task.toString() + "} has been added successfully.\n " +
                    "Isn't it just wonderful when things go exactly as planned?\n " +
                    "I'm so proud of you for getting it done!\n " +
                    "Now you have " + taskList.getTasks().size() + " tasks in the list.");
        } catch (SaopigInvaildSizeException e) {
            ui.printMessage(e.getMessage() +
                    "\n" +
                    "Oh, it looks like the 'event' command is missing some details for the task.\n " +
                    "No problem at all!\n " +
                    "Just add a bit more information about what you'd like to do, " +
                    "and it will be as perfect as a sunny day.\n " +
                    "You're doing wonderfully! ");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printMessage("\n" +
                    "Whoopsie!\n " +
                    "It seems like you may have forgotten to write the event start and end time\n " +
                    "or didn't use ' /from ' or ' /to ' in your command.\n " +
                    "Remember there is a space before and after '/from' and ' /to '.\n " +
                    "It's a tiny detail, " +
                    "but oh so important! Just add the deadline after '/by ', " +
                    "and you'll be as organized as a library on a quiet morning.\n " +
                    "You're doing an amazing job! ");
        }
    }

    public AddCommand(String command, int typeIndex) {
        this.command = command;
        this.typeIndex = typeIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch (typeIndex) {
        case 0:
            addTodoTask(command, tasks, ui, storage);
            break;
        case 1:
            addDeadlineTask(command, tasks, ui, storage);
            break;
        case 2:
            addEventTask(command, tasks, ui, storage);
            break;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
