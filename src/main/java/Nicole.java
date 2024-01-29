import nicoleexceptions.NicoleException;
import taskstorage.Storage;
import userrequests.Ui;

import java.io.File;
import java.io.IOException;

public class Nicole {
    public static void main(String[] args) {
        try {
            new File("tasks.txt");
            new Storage().loadTasksFromFile();
            new Ui();
        } catch (NicoleException | IOException e) {
            System.out.println(e);
        }
    }
}
