package model.commands;

import io.Message;
import model.Chatbot;
import model.Task;
import model.TaskList;

import java.util.HashMap;

public class CommandList {
    private HashMap<String, Command> commandLookup;
    private TaskList taskList;
    private Chatbot chatbot;
    private void loadCommands() {
        commandLookup.put("exit", (args) -> {
            chatbot.exitIoLoop();
            return new Message("Bye. Hope to see you again soon!");
        });
        commandLookup.put("list", (args) -> {
            return new Message(taskList.toString());
        });
        commandLookup.put("mark", (args) -> {
            int idx = Integer.parseInt(args);
            taskList.markTask(idx);
            return new Message("Wow you took your time with that one:\n" + taskList.getTask(idx));
        });
        commandLookup.put("unmark", (args) -> {
            int idx = Integer.parseInt(args);
            taskList.unmarkTask(idx);
            return new Message("Why did you say it was finished then? \uD83D\uDE44\n" + taskList.getTask(idx));
        });
    }

    /**
     *
     * @param taskList TaskList object from Chatbot
     */
    public CommandList(TaskList taskList, Chatbot chatbot) {
        this.commandLookup = new HashMap<>();
        this.taskList = taskList;
        this.chatbot = chatbot;
        loadCommands();
    }

    public Message runKeyword(String keyword, String args) {
        if (this.commandLookup.containsKey(keyword)) {
            return commandLookup.get(keyword).run(args);
        } else {
            String input = keyword + " " + args;
            taskList.addTask(new Task(input));
            return new Message("added: " + input);
        }
    }
}
