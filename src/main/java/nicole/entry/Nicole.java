package nicole.entry;

import java.io.File;

import javafx.application.Application;

import nicole.nicoleexceptions.NicoleException;
import nicole.taskstorage.Storage;
public class Nicole {
    public Nicole() {
    }

    public static void main(String[] args) {
        try {
            new File("tasks.txt");
            new Storage().loadTasksFromFile();
            Application.launch(Main.class, args);
        } catch (NicoleException e) {
            System.out.println(e);
        }
    }
}
