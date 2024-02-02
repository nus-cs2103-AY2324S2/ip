import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static TaskList readFromFile() {
        TaskList tasks = new TaskList();
        List<String> lines;

        try {
            lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("scrolls_of_tasks.txt"));
        } catch (IOException e) {
            return new TaskList();
        }

        try {
            for (String line : lines) {
                Command command = CommandParser.parseCommand(line);
                if (!command.isTaskInitialisation()) {
                    Ui.speak("Unexpected or invalid content in thy scroll of tasks. Thy scroll is cleared.");
                    return new TaskList();
                }
                tasks.executeCommandSilently(command, line);
            }
        } catch (NonstandardCommandException e) {
            Ui.speak("Unexpected or invalid content in thy scroll of tasks. Thy scroll is cleared.");
            return new TaskList();
        }

        return tasks;
    }
    public static void writeToFile(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter("scrolls_of_tasks.txt");
            writer.write(tasks.getCommands());
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
