package reacher.command;


import reacher.Parser;
import reacher.ReacherException;
import reacher.Storage;
import reacher.TaskList;

import static reacher.Parser.Variable.KEYWORD;
import static reacher.Parser.getInfo;

public class FindCommand extends Command {
    @Override
    public String execute(String input, TaskList tasks, Storage storage) throws ReacherException {
        String keyword = getInfo(input, KEYWORD);
        TaskList relevantTasks = tasks.findTasks(keyword);
        return relevantTasks.toString();
    }
}
