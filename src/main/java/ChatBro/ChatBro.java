package ChatBro;

/**
 * Main class of the ChatBro chatbot.
 */
public class ChatBro {
    static boolean isRunning;
    public static void main(String[] args) {
        Parser parser = new Parser();
        TaskManager tm = new TaskManager(); // Instantiate ChatBro.TaskManager to create taskList
        isRunning = true;

        String savedTasks = Database.readFromFile();
        String[] savedTasksSplit = savedTasks.split("\n"); // Split savedTasks by newline
        int length = savedTasksSplit.length;
        for (int i = 0; i < 100; i++) { // Fill taskList with null
            tm.getList().add(null);
        }
        if (!savedTasksSplit[0].isEmpty()) { // savedTasks is not empty, load tasks into taskList
            try {
                for (int j = 0; j < length; j++) {
                    String taskString = savedTasksSplit[j];
                    tm.addTask(Database.parseTask(taskString));
                }
            } catch (WrongFileFormatException e) {
                Ui.printMessage(e.getMessage());
                return;
            }
        }
        Ui.printWelcome();

        while (isRunning) {
            Parser.parseCommand();
        }
        Parser.closeScanner();
    }
}
