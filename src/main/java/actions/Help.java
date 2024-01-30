package actions;

public class Help extends Command {
    private static final String help = "Active commands:x\n" +
            "\nVIEW\n" +
            "list: lists all tasks saved.\n" +
            "help: lists all available commands.\n" +
            "\nEDIT\n" +
            "mark <index>: marks the task at index to be completed.\n" +
            "unmark <index>: marks the task at index to be not completed.\n" +
            "delete <index>: deletes task at index.\n" +
            "\nADD TASK\n" +
            "todo <name>: adds a todo task with its name.\n" +
            "deadline <name> /by <date>: adds a deadline task with name and date in d-M-yy format." +
            "event <name> /from <date> /to <date>: adds an event task with name and date in d-M-yy format.";

    public Help() {
    }

    @Override
    public String execute() {
        return help;
    }

}
