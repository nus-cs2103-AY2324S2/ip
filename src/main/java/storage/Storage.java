package storage;
import static parser.Parser.parseCommand;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import actions.Action;
import exceptionhandling.DukeException;
import tasklist.TaskList;
import tasks.Task;
import ui.Duke;


/**
 * The `Storage` class handles the saving and loading of task data to and from a file.
 * It is responsible for writing tasks to a file when saving and reading tasks from a file when loading.
 */
public class Storage {
    private static final String FILE_NAME = "duke.txt";

    /**
     * Constructs a `Storage` object.
     */
    public Storage() {
        File file = new File(Storage.FILE_NAME);
        if (!file.exists()) {
            createFile(file);
        }

    }

    private static void createFile(File file) {
        try {
            if (file.createNewFile()) {
                System.out.println("Save File created successfully.");
            } else {
                System.out.println("Unable to create the Save file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the tasks from the specified task list to a file.
     *
     * @param list The task list containing tasks to be saved.
     */
    public void save(TaskList list) {
        try {
            FileWriter dest = new FileWriter(Storage.FILE_NAME);
            for (Task t : list.getList()) {
                dest.write(t.getCommand());
            }
            dest.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from a file and updates the Duke chatbot with the loaded tasks.
     *
     * @param chatbot The Duke chatbot to be updated with the loaded tasks.
     */
    public void load(Duke chatbot) {
        try {
            File f = new File(Storage.FILE_NAME);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String command = reader.nextLine();
                String isDone = reader.nextLine();
                Action a = parseCommand(command);
                a.execute(chatbot);
                if (Objects.equals(isDone, "true")) {
                    chatbot.getTaskList().markTask(chatbot.getTaskList().getListSize());
                }
            }
            System.out.println("Loading Done!");
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
    }
}
