package duke.utility;

import duke.task.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {
    /** File Object that stores Tasks from previous sessions. */
    private File storageFile;
    /** Boolean Value stating whether the storage file contains Tasks. */
    public boolean isOccupied;

    /**
     * Constructs a Storage Object with storage based on FilePath.
     *
     * @param FilePath FilePath where Tasks will be stored and loaded.
     * @throws DukeException
     * @throws IOException
     */
    public Storage(String FilePath) throws DukeException, IOException{
        storageFile = new File(FilePath);
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
        }
        if(!storageFile.exists()) {
            storageFile.createNewFile();
        }
        if(storageFile.length() == 0) {
            isOccupied = false;
        } else {
            isOccupied = true;
        }
    }

    /**
     * Returns an ArrayList of Tasks based on Tasks stored in File from previous sessions.
     *
     * @return Arraylist of Tasks stored in file.
     * @throws DukeException
     * @throws IOException
     */
    public ArrayList<Task> loadStorage() throws DukeException,IOException {
        Scanner s = new Scanner(storageFile);
        ArrayList<Task> loadedList = new ArrayList<>();
        while (s.hasNextLine()) {
            String storedInput = s.nextLine();
            Task currTask = Parser.parseFileLine(storedInput);
            loadedList.add(currTask);
        }
        return loadedList;
    }

    /**
     * Saves Tasks in the ArrayList into the file.
     *
     * @param taskList Arraylist of Tasks to be stored.
     * @throws IOException
     */
    public void saveStorage(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(storageFile, false);
        for (Task t : taskList) {
            fw.append(t.toString() + "\n");
        }
        fw.close();
    }
}
