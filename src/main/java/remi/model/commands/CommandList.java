package remi.model.commands;

import java.util.HashMap;
import java.util.List;

import javafx.application.Platform;
import remi.io.Message;
import remi.model.Deadline;
import remi.model.Event;
import remi.model.Task;
import remi.model.TaskList;
import remi.model.ToDo;
import remi.model.Ui;
import remi.utils.RemiError;

/**
 * A list of all the commands. This class's responsibility is to parse commands as strings and run them.
 */
public class CommandList {
    private HashMap<String, Command> commandLookup;
    private TaskList taskList;
    private Ui chatbot;

    /**
     * Constructor that requires a TaskList object and Ui object.
     * Both of these are expected to be given by the Ui class.
     *
     * @param taskList the TaskList object
     * @param chatbot the Ui object, mostly used for exiting
     */

    public CommandList(TaskList taskList, Ui chatbot) {
        commandLookup = new HashMap<>();
        this.taskList = taskList;
        this.chatbot = chatbot;
        loadCommands();
    }

    /**
     * Finds the option of the form ".../option...".
     * Example: "going home /by: friday", should return the string "friday"
     *
     * @param option the option to be found, include the "/" at the start
     * @param input the input line to be scanned
     * @return the string value of the specific option
     */
    private String findOption(String option, String input) throws RemiError {
        assert !option.isEmpty();

        int idx = input.indexOf(option);

        if (idx == -1) {
            throw new RemiError("I couldn't find a " + option + ", please specify it by adding a " + option);
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

    private String getLabel(String input) throws RemiError {
        int idx = input.indexOf("/");
        if (idx == -1) {
            idx = input.length();
        } else {
            idx--;
        }

        if (idx <= 0) {
            throw new RemiError("You didn't put a description for the task.");
        }
        return input.substring(0, idx);
    }

    private void loadCommands() {
        commandLookup.put("exit", (args) -> {
            Platform.exit();
            System.exit(0);
            return new Message("Bye. Please finish some of your tasks.");
        });
        commandLookup.put("list", (args) -> {
            return new Message(taskList.toString());
        });

        commandLookup.put("listsort", (args) -> {
            return new Message(taskList.getSortedTasks().toString());
        });

        commandLookup.put("mark", (args) -> {
            int idx = Integer.parseInt(args);
            taskList.markTask(idx);
            return new Message("Wow you took your time with that one:\n" + taskList.getTask(idx));
        });
        commandLookup.put("unmark", (args) -> {
            int idx = Integer.parseInt(args);
            taskList.unmarkTask(idx);
            return new Message("Why did you say it was finished then?\n" + taskList.getTask(idx));
        });
        commandLookup.put("todo", (args) -> {
            String label = getLabel(args);
            ToDo todo = new ToDo(label);
            taskList.addTask(todo);
            return new Message(String.format(
                    "I've added the task.\n%s\nYou still have %d tasks in the list.",
                    todo, taskList.size())
            );
        });

        commandLookup.put("deadline", (args) -> {
            String label = getLabel(args);
            String by = findOption("/by", args);
            Deadline deadline = new Deadline(label, by);
            taskList.addTask(deadline);
            return new Message(String.format(
                    "I've added the task.\n%s\nYou still have %d tasks in the list.",
                    deadline, taskList.size())
            );
        });

        commandLookup.put("event", (args) -> {
            String label = getLabel(args);
            String from = findOption("/from", args);
            String to = findOption("/to", args);
            Event event = new Event(label, from, to);
            taskList.addTask(event);
            return new Message(String.format("I've added the task.\n%s\nYou still have %d tasks in the list.",
                    event, taskList.size())
            );
        });

        commandLookup.put("delete", (args) -> {
            int idx = Integer.parseInt(args);
            Task task = taskList.getTask(idx);
            taskList.removeTask(idx);
            return new Message(String.format("I've removed the task.\n%s\nYou still have %d tasks in the list.",
                    task, taskList.size())
            );
        });

        commandLookup.put("find", (args) -> {
            List<Task> tasks = taskList.getTask(args);
            StringBuilder res = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 0; tasks.size() > i; i++) {
                res.append(String.format("\n%d. %s", i + 1, tasks.get(i)));
            }
            return new Message(res.toString());
        });
    }

    /**
     * This runs a keyword with the given args.
     * Updates all required state and returns the output message.
     *
     * @param keyword the keyword, first word in the inputted string
     * @param args the rest of the args, expected to be space seperated
     * @return the String response of the keyword and args
     * @throws RemiError when the keyword isn't recognized
     */
    public Message runKeyword(String keyword, String args) throws RemiError {
        if (this.commandLookup.containsKey(keyword)) {
            return commandLookup.get(keyword).run(args);
        } else {
            throw new RemiError(keyword + " is not a real command.");
        }
    }
}
