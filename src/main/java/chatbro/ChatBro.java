package chatbro;

/**
 * Main class of the ChatBro chatbot.
 */
public class ChatBro {
    private TaskManager tm;

    public ChatBro() throws WrongFileFormatException {
        tm = new TaskManager();
        String savedTasks = Database.readFromFile();
        String[] savedTasksSplit = savedTasks.split("\n"); // Split savedTasks by newline
        int length = savedTasksSplit.length;
        for (int i = 0; i < 100; i++) { // Fill taskList with null
            tm.getList().add(null);
        }
        if (!savedTasksSplit[0].isEmpty()) { // savedTasks is not empty, load tasks into taskList
            for (int i = 0; i < length; i++) {
                String taskString = savedTasksSplit[i];
                tm.addTask(Database.parseTask(taskString));
            }
        }
    }
}
