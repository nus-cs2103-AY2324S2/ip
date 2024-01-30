package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.DateTimeManager;
import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDateTime;

public class SearchCommand extends Command {

    private String input;

    public SearchCommand(String userInput) {
        this.input = userInput;
    }

    @Override
    public void excuteCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length <= 1) {
            throw new DukeException("Missing the date!");
        }

        LocalDateTime dateInput = DateTimeManager.convertStringToLocalDateTime(splitInput[1].trim() +"T00:00");

        ui.printAnyStatement("Here are the tasks on " + input +":");
        int index = 1;
        for (Task i : tasks.getTasks()) {
            if (i instanceof Deadline) {
                if (((Deadline) i).getBy().toLocalDate().isEqual(dateInput.toLocalDate())) {
                    ui.printAnyStatement((index++) + "." + i.toString());
                }
            } else if (i instanceof Event) {
                if (((Event) i).getStart().toLocalDate().isEqual(dateInput.toLocalDate())) {
                    ui.printAnyStatement((index++) + "." + i.toString());
                }
            }
        }
        if (index == 1) {
            ui.printAnyStatement("There are no task on this date");
        }
    }
}
