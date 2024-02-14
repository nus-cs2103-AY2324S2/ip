package chatbro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Main class of the ChatBro chatbot.
 */
public class ChatBro extends Application {
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
                for (int i = 0; i < length; i++) {
                    String taskString = savedTasksSplit[i];
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

        @Override
        public void start(Stage stage) {
            Label helloWorld = new Label("Hello World!"); // Creating a new Label control
            Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

            stage.setScene(scene); // Setting the stage to show our screen
            stage.show(); // Render the stage.
        }
    }
}
