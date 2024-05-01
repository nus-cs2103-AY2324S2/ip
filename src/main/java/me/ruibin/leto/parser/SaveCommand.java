package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;

/** Save tasks and returns <code>ResultTypes.OK</code>*/
public class SaveCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        return TaskList.saveTasks();
    }
}
