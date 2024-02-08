package me.ruibin.leto.parser;

import me.ruibin.leto.ui.Ui;
import me.ruibin.leto.tasklist.Task;
import me.ruibin.leto.tasklist.TaskList;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static me.ruibin.leto.ui.Ui.letoSpeak;

/** Command for printing tasks found by a keyword search. */
public class FindCommand implements Function<String, Results> {
    /**
     * Run a keyword search in task message from the keyword extracted from the command.
     * Prints output as a list
     *
     * @param command the command from the user
     * @return <code>Results.OK</code>
     */
    @Override
    public Results apply(String command) {
        String[] parts = command.split(" ");
        if (parts.length != 2) {
            Ui.shortSay("find command: find <keyword>, please ensure no space in between");
            return Results.OK;
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
        letoSpeak(outputListMessage.toString());
        return Results.OK;
    }
}
