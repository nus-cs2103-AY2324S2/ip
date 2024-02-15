package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Prints the list of tasks and returns <code>ResultTypes.OK</code>*/
public class ListCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        return TaskList.printList();
    }
}
