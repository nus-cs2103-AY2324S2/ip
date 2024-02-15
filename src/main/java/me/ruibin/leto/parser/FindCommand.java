package me.ruibin.leto.parser;

import static me.ruibin.leto.ui.Ui.letoSpeak;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import me.ruibin.leto.tasklist.Task;
import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.ui.Ui;

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
        Stream<Task> taskStream = TaskList.getTasks().stream();
        StringBuilder outputListMessage = new StringBuilder("Tasks matching \"" + keyword + "\":\n");
        List<Task> filteredTasks = taskStream.filter((task) ->
                task.toCsvString().split(",")[2].contains(keyword)).collect(Collectors.toList());
        for (int index = 0; index < filteredTasks.size(); index++) {
            outputListMessage.append(index + 1).append(". ").append(filteredTasks.get(index)).append("\n");
            index++;
        }
        r.updateMessage(letoSpeak(outputListMessage.toString()));
        return r;
    }
}
