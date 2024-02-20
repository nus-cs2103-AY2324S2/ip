package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyDescriptionException;
import duke.main.Duke;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

public class NameCommand extends Command {
    private String[] words;
    public NameCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public String executeForString(TaskList tasks, UI ui, Storage storage) throws DukeException {
        boolean hasEmptyDescription = words.length == 1;
        if (hasEmptyDescription) {
            throw new EmptyDescriptionException("name");
        }
        String userName = words[1].trim();
        ui.setName(userName);
        return ui.nameMessage();
    }
}
