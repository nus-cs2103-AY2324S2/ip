package me.ruibin.leto.parser;

import me.ruibin.leto.tasklist.TaskList;

import java.util.function.Function;

/** Prints the list of tasks and returns <code>Results.OK</code>*/
public class ListCommand implements Function<String, Results> {
    @Override
    public Results apply(String s) {
        TaskList.printList();
        return Results.OK;
    }
}
