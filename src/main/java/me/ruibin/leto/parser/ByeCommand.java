package me.ruibin.leto.parser;

import java.util.function.Function;

import me.ruibin.leto.tasklist.TaskList;
import me.ruibin.leto.ui.Ui;

/** Save tasks and exit by returning <code>Results.EXIT</code>*/
public class ByeCommand implements Function<String, Result> {
    @Override
    public Result apply(String s) {
        Result r = TaskList.saveTasks();
        r.updateType(ResultTypes.EXIT);
        r.updateMessage(Ui.letoSpeak("Bye\nHere I am, Here I remain"));
        return r;
    }
}
