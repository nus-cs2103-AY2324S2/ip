package me.ruibin.leto.parser;

import me.ruibin.leto.ui.Ui;
import me.ruibin.leto.tasklist.Task;
import me.ruibin.leto.tasklist.TaskList;

import java.util.List;
import java.util.function.Function;

import static me.ruibin.leto.ui.Ui.letoSpeak;

/** Command for printing tasks found by a keyword search. */
public class FindCommand implements Function<String, Result> {
    /**
     * Run a keyword search in task message from the keyword extracted from the command.
     * Prints output as a list
     *
     * @param command the command from the user
     * @return <code>Result</code>
     */
    @Override
    public Result apply(String command) {
        String[] parts = command.split(" ");
        Result r = Result.makeResultOk("");
        if (parts.length != 2) {
            r.updateMessage(Ui.shortSay("find command: find <keyword>, please ensure no space in between"));
            return r;
        }
        String keyword = parts[1];
        List<Task> tasks = TaskList.getTasks();
        StringBuilder outputListMessage = new StringBuilder("Tasks matching \"" + keyword + "\":\n");
        int index = 1;
        for (Task t: tasks) {
            if (t.toCsvString().split(",")[2].contains(keyword)) {
                outputListMessage.append(index).append(". ").append(t).append("\n");
                index++;
            }
        }
        r.updateMessage(letoSpeak(outputListMessage.toString()));
        return r;
    }
}
