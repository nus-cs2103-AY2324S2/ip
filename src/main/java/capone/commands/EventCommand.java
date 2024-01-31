package capone.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import capone.Parser;
import capone.Storage;
import capone.TaskList;
import capone.Ui;
import capone.exceptions.CaponeException;
import capone.tasks.Event;

public class EventCommand extends Command {
    private final ArrayList<String> inputList;

    public EventCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Insufficient arguments!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        int fromNdx = inputList.indexOf("/from");
        int toNdx = inputList.indexOf("/to");

        // If /to is specified before /from, throw error.
        if (toNdx < fromNdx) {
            throw new CaponeException("Please input from date followed by to date!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        // Catch potential errors from date entry.
        if (fromNdx == -1 || toNdx == -1 || toNdx - fromNdx == 1 || fromNdx - toNdx == 1
                || fromNdx == inputList.size() - 1 || toNdx == inputList.size() - 1) {
            throw new CaponeException("Please enter a start and end date!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        // Combine the task description into a single string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < fromNdx; i++) {
            if (i == fromNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new CaponeException("Insufficient arguments!\n"
                    + "Usage: event [description] /from [date] /to [date]");
        }

        LocalDate fromDate = null;
        LocalTime fromTime = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder fromDateString = new StringBuilder();
        for (int i = fromNdx + 1; i < toNdx; i++) {
            if (Parser.isDateFormat(inputList.get(i))) {
                fromDate = Parser.parseDate(inputList.get(i));
                continue;
            }

            if (Parser.isTimeFormat(inputList.get(i))) {
                fromTime = Parser.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                fromDateString.append(inputList.get(i));
            } else {
                fromDateString.append(inputList.get(i)).append(" ");
            }
        }

        LocalDate toDate = null;
        LocalTime toTime = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder toDateString = new StringBuilder();
        for (int i = toNdx + 1; i < inputList.size(); i++) {
            if (Parser.isDateFormat(inputList.get(i))) {
                toDate = Parser.parseDate(inputList.get(i));
                continue;
            }

            if (Parser.isTimeFormat(inputList.get(i))) {
                toTime = Parser.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                toDateString.append(inputList.get(i));
            } else {
                toDateString.append(inputList.get(i)).append(" ");
            }
        }

        LocalDateTime fromDateTime = Parser.processDateTime(fromDate, fromTime);
        LocalDateTime toDateTime = Parser.processDateTime(toDate, toTime);

        if (fromDateTime != null && toDateTime != null) {
            taskList.addTask(new Event(description.toString(), false, fromDateTime, toDateTime));
        } else if (fromDateTime != null || toDateTime != null) {
            // If either fromDateTime or toDateTime is null but the other is not.
            throw new CaponeException("Oops! It seems like there is a format mismatch between"
                    + "your start and dates and end dates.\nMake sure you enter both of them in the accepted "
                    + "date format!\nAlternatively, you can specify a string for both your start and end dates.\n"
                    + "Use the 'help' command for more information.");
        } else {
            taskList.addTask(new Event(description.toString(), false,
                    fromDateString.toString(), toDateString.toString()));
        }

        storage.writeTasksToJsonFile(taskList);

        ui.sendMessage(String.format("Got it. I've added this task:\n%s\n"
                + "Now you have %d task(s) in the list.\n", taskList.getLastTask().toString(), taskList.getSize()));

    }

}
