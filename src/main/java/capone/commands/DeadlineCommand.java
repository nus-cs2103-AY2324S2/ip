package capone.commands;

import capone.*;
import capone.exceptions.CaponeException;
import capone.tasks.Deadline;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final ArrayList<String> inputList;

    public DeadlineCommand(ArrayList<String> inputList) {
        this.inputList = inputList;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CaponeException {
        // If the inputList has only one string, throw error (insufficient args).
        if (inputList.size() == 1) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        // Find the index of the /by command.
        int byNdx = inputList.indexOf("/by");

        // Catch potential errors from date entry.
        if (byNdx == inputList.size() - 1 || byNdx == -1) {
            throw new CaponeException("Please enter a date for this deadline task!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        // Combine description of task into one string.
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < byNdx; i++) {
            if (i == byNdx - 1) {
                description.append(inputList.get(i));
                break;
            }
            description.append(inputList.get(i)).append(" ");
        }

        if (description.toString().equalsIgnoreCase("")) {
            throw new CaponeException("Insufficient arguments!\n" +
                    "Usage: deadline [description] /by [date]");
        }

        LocalDate date = null;
        LocalTime time = null;
        // Process input for the deadline (i.e. after the /by command).
        StringBuilder byDate = new StringBuilder();
        for (int i = byNdx + 1; i < inputList.size(); i++) {
            if (Parser.isDateFormat(inputList.get(i))) {
                date = Parser.parseDate(inputList.get(i));
                continue;
            }

            if (Parser.isTimeFormat(inputList.get(i))) {
                time = Parser.parseTime(inputList.get(i));
                continue;
            }

            // If this is the last word to be added.
            if (i == inputList.size() - 1) {
                byDate.append(inputList.get(i));
            } else {
                byDate.append(inputList.get(i)).append(" ");
            }
        }


        if (date != null) {
            if (time != null) {
                taskList.addTask(new Deadline(description.toString(), false, date.atTime(time)));
            } else {
                taskList.addTask(new Deadline(description.toString(), false, date.atStartOfDay()));
            }
        } else {
            // If only the time is specified, the deadline will be the time at the next day.
            if (time != null) {
                taskList.addTask(new Deadline(description.toString(), false,
                        LocalDate.now().plusDays(1).atTime(time)));
            } else {
                taskList.addTask(new Deadline(description.toString(), false, byDate.toString()));
            }
        }

        storage.writeTasksToJsonFile(taskList);

        ui.sendMessage(String.format("Got it. I've added this task:\n%s\n" +
                "Now you have %d task(s) in the list.\n", taskList.getLastTask().toString(), taskList.getSize()));
    }

}
