public class Duke {
    private static UI ui;
    private static TaskList taskList;
    private static CommandCreator commandCreator;

    public static void main(String[] args) {
        ui = new UI();
        taskList = new TaskList();
        commandCreator = new CommandCreator(taskList);
        ui.printWelcomeMessage();
        ui.processCommands(commandCreator);
    }
}