package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Save tasks and returns <code>Results.OK</code>*/
public class SaveCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.saveTasks();
        return Results.OK;
    }
}
