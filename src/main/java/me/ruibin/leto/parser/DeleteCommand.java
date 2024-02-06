package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

public class DeleteCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.deleteTask(s);
        return Results.OK;
    }
}
