package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.*;
import duke.ui.Ui;


public class AddCommand extends Command {
    private String[] details;
    private String command;
    private String desc;
    public AddCommand(String... details) {
        this.details = new String[details.length - 2];
        for (int i = 0; i < this.details.length; i++) {
            this.details[i] = details[i + 2];
        }
        this.command = details[0];
        this.desc = details[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = null;
        switch (this.command) {
        case "todo":
            t = new Todo(this.desc);
            tasks.add(t);
            break;
        case "deadline":
            t = new Deadline(this.desc, this.details[0]);
            tasks.add(t);
            break;
        case "event":
            t = new Event(this.desc, this.details[0], this.details[1]);
            tasks.add(t);
            break;
        }
        ui.showResult("Got it. I've added this duke.task:");
        ui.showResult(t.toString());
        ui.showResult("Now you have " + tasks.size() + " task(s) in the list.");

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
