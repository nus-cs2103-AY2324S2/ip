package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Marks task as uncompleted and returns <code>Results.OK</code>*/
public class UnmarkCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.markTaskUncompleted(s);
        return Results.OK;
    }
}
