import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class HughJazz {
    private static Ui ui = new Ui();
    private static Storage storage = new Storage("." + File.separator + "data" + File.separator + "duke.txt");
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        ui.showGreeting();
        try {
            ArrayList<Task> loadedTasks = storage.load();
            taskList.loadTasks(loadedTasks);
        } catch (FileNotFoundException e ){
            ui.showError("No existing txt file found");
        }

        String userInput;

        while (true) {
            userInput = ui.readCommand();
            if("bye".equalsIgnoreCase(userInput)) {
                break;
            } else{
                try {
                    Parser.parse(userInput, taskList, storage);
                } catch (DateTimeParseException e) {
                    System.out.println("Please use the format dd/MM/yyyy HHmm for dates.");
                    System.out.println("Please try again");
                } catch (ChatbotException e) {
                    ui.showError(e.getMessage());
                }
            }

        }
        ui.showGoodbye();
    }
}
