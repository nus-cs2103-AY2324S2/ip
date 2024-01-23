package model.commands;

import io.Message;
import model.*;

import java.util.HashMap;

public class CommandList {
    private HashMap<String, Command> commandLookup;
    private TaskList taskList;
    private Chatbot chatbot;

    /**
     * Finds the option of the form ".../option...".
     * Example: "going home /by: friday", should return the string "friday"
     *
     * @param option the option to be found, include the "/" at the start
     * @param input the input line to be scanned
     * @return
     */
    private String findOption(String option, String input) {
        int ctr = 0;
        int idx = input.indexOf(option);

        /// TODO: handle this
        if (idx == -1) {
            return "";
        } else {
            int endIdx = input.indexOf("/", idx + 1);
            if (endIdx == -1) {
                endIdx = input.length();
            } else {
                // decrement to get the space before the /
                endIdx = endIdx - 1;
            }
            return input.substring(idx + option.length() + 1, endIdx);
        }
    }

    private String getLabel(String input) {
        int idx = input.indexOf("/");
        return input.substring(0, idx - 1);
    }

    private void loadCommands() {
        commandLookup.put("exit", (args) -> {
            chatbot.exitIoLoop();
            return new Message("Bye. Please finish some of your tasks.");
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
        commandLookup.put("todo", (args) -> {
            ToDo todo = new ToDo(args);
            taskList.addTask(todo);
            return new Message(String.format("I've added the task.\n%s\nYou still have %d tasks in the list.", todo, taskList.size()));
        });

        commandLookup.put("deadline", (args) -> {
            String label = getLabel(args);
            String by = findOption("/by", args);
            Deadline deadline = new Deadline(label, by);
            taskList.addTask(deadline);
            return new Message(String.format("I've added the task.\n%s\nYou still have %d tasks in the list.", deadline, taskList.size()));
        });

        commandLookup.put("event", (args) -> {
            String label = getLabel(args);
            String from = findOption("/from", args);
            String to = findOption("/to", args);
            Event event = new Event(label, from, to);
            taskList.addTask(event);
            return new Message(String.format("I've added the task.\n%s\nYou still have %d tasks in the list.", event, taskList.size()));
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
