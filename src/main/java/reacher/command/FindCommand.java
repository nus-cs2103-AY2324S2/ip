package reacher.command;

import reacher.*;
import reacher.ui.MainWindow;

public class FindCommand extends Command {
    @Override
    public String execute(String input, TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        String keyword = Parser.getInfo(input, Parser.Variable.KEYWORD);
        String list = ui.listToString(tasks.findTasks(keyword));
        return list;
    }
}
