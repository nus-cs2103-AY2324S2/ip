package dino.commands;

import dino.tasks.TaskList;

import java.util.ArrayList;
import java.util.List;

public class ByeCommand extends Command{
    @Override
    public List<String> execute(TaskList tasks) {
        List<String> messages = new ArrayList<>();
        messages.add("Bye. Hope to see you again soon!");
        return messages;
    }
}
