package me.ruibin.leto.parser;

import me.ruibin.leto.tasklist.TaskList;

import java.util.function.Function;

/** Save tasks and exit by returning <code>Results.EXIT</code>*/
public class ByeCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.saveTasks();
        return Results.EXIT;
    }
}
