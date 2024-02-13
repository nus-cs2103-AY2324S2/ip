package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Read input for new task to add to list and returns <code>ResultTypes.OK</code>*/
public class NewTaskCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        return TaskList.addToList(s);
    }
}
