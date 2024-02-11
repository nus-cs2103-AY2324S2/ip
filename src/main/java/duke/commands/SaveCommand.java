package duke.commands;

import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static duke.constants.Constant.RELATIVE_PATH;
import static duke.constants.Constant.DATE_TIME_FORMATTER;
public class SaveCommand extends Command{
    public SaveCommand() {

    }

    @Override
    public boolean execute(Ui ui, TaskList tasks) {
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
            System.out.println("It is successfully saved!");
        } catch (IOException e) {
            System.err.println("There are some error in saving. Try again");
        }
        return true;
    }
}
