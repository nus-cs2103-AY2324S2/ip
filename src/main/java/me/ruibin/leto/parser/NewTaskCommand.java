package me.ruibin.leto.parser;

import me.ruibin.leto.tasklist.TaskList;

import java.util.function.Function;

/** Read input for new task to add to list and returns <code>Results.OK</code>*/
public class NewTaskCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.addToList(s);
        return Results.OK;
    }
}
