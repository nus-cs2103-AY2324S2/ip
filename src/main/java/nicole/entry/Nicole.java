package nicole.entry;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;

import nicole.nicoleexceptions.NicoleException;
import nicole.taskstorage.Storage;
public class Nicole {
    public Nicole() {
    }

    public static void main(String[] args) {
        try {
            File tasksFile = new File("tasks.txt");
            tasksFile.createNewFile();
            new Storage().loadTasksFromFile(tasksFile);
            Application.launch(Main.class, args);
        } catch (NicoleException e) {
            System.out.println(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
