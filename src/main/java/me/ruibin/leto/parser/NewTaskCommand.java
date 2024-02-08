package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Read input for new task to add to list and returns <code>Results.OK</code>*/
public class NewTaskCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.addToList(s);
        return Results.OK;
    }
}
