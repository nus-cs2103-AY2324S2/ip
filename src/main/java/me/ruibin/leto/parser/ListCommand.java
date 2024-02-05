package me.ruibin.leto.parser;

import me.ruibin.leto.tasklist.TaskList;

import java.util.function.Function;

public class ListCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.printList();
        return Results.OK;
    }
}
