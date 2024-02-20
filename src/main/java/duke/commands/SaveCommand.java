package duke.commands;

import static duke.constants.Constant.DATE_TIME_FORMATTER;
import static duke.constants.Constant.RELATIVE_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to save all the tasks to a file.
 */
public class SaveCommand extends Command {
    /**
     * Constructs a new SaveCommand object.
     */
    public SaveCommand() {

    }

    @Override
    public String execute(Ui ui, TaskList tasks) {
        Path path = Paths.get(RELATIVE_PATH);
        List<String> taskStrList = new ArrayList<String>();
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            for (int i = 0; i < tasks.size(); i++) {
                String str = tasks.get(i).convertToFileFormat(DATE_TIME_FORMATTER);
                taskStrList.add(str);
            }
            String tasksStr = String.join("\n", taskStrList);
            Files.writeString(path, tasksStr);
            return "It is successfully saved!";
        } catch (IOException e) {
            return "There are some error in saving. Try again";
        }
    }
}
