package commands;

import tasks.TaskList;
import ui.Ui;
import storage.Storage;
public class HelpCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String helpMessage =
                "Hi! Here are the list of commands I recognise: \n\n" +
                "'list' - lists the tasks registered in the list\n" +
                "'todo' - adds an item that has no due date\n" +
                "'deadline' - adds an item with a due date\n" +
                "'event' - adds an item that has a duration\n" +
                "'mark' - marks the task as completed\n" +
                "'unmark' - marks the task as not completed\n" +
                "'help' - opens the list of commands available\n" +
                "'bye' - exits the chatbot";
        ui.echo(helpMessage);
    }
}
