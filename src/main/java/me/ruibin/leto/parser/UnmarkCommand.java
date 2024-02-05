package me.ruibin.leto.parser;

import me.ruibin.leto.tasklist.TaskList;

import java.util.function.Function;

public class UnmarkCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.markTaskUncompleted(s);
        return Results.OK;
    }
}
