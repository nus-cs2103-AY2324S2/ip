package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Marks task as completed and returns <code>Results.OK</code>*/
public class MarkCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.markTaskCompleted(s);
        return Results.OK;
    }
}
