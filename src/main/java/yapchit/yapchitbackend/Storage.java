package yapchit.yapchitbackend;

import yapchit.yapchitbackend.tasks.Deadline;
import yapchit.yapchitbackend.tasks.Event;
import yapchit.yapchitbackend.tasks.Task;
import yapchit.yapchitbackend.tasks.ToDo;
import yapchit.yapchitexceptions.FileListParseException;
import yapchit.yapchitexceptions.YapchitException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that handles interactions with the external storage file.
 * File maintains the tasks outside the Yapchit program.
 */
public class Storage {

    /**
     * Creates new storage object.
     */
    public Storage() {
    }

    /**
     * Imports existing tasks from file at specified filepath and returns list as a TaskList.
     *
     * @param filePath Path where data is stored.
     * @param ui User interface object to handle outputs
     * @param handler Handler object to handle tasks.
     * @param parser Parser object to parse input.
     * @return TaskList containing all tasks in file.
     * @throws YapchitException if file is not found or corrupted.
     */
    public TaskList importFromFile(
            String filePath, Ui ui, Handler handler, Parser parser) throws YapchitException {
        assert filePath != "" : "FilePath cannot be empty";

        File f = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new FileListParseException("Could not locate existing file list");
        }

        TaskList tasks = new TaskList();

        while (s.hasNext()) {
            String input = s.nextLine();
            String[] parts = parser.parseInputParts(input);
            try {
                YapchitBackend.Operations k = YapchitBackend.Operations.valueOf(parts[0].toUpperCase());
                handler.handleOperation(input, k, tasks, ui, parser, false);
            } catch (Exception e) {
                throw new FileListParseException("Error in parsing file. Some of the contents may be corrupted");
            }
        }

        return tasks;
    }

    /**
     * Updates file at specified path with list of tasks for permanent storage.
     *
     * @param filePath Path of file to update.
     * @param tasks List of tasks to update the file with.
     */
    public void updateFile(String filePath, TaskList tasks) {
        String toWrite = "";
        for (int i = 0; i < tasks.getListSize(); i++) {
            Task t = tasks.getItem(i);
            toWrite = toWrite + getTaskWriteString(t);
        }
        File f = new File(filePath);

        assert toWrite != "";

        File dirCheck = f.getParentFile();
        if (!dirCheck.exists()) {
            dirCheck.mkdirs();
        }

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            Ui.print("Error in creating file. " + e.getMessage());
        }

        try{
            this.writeToFile(filePath, toWrite);
        } catch (IOException e) {
            Ui.print(e.getMessage());
        }

    }

    private String getTaskWriteString(Task t) {
        String toWrite = "";
        if (t instanceof ToDo) {
            toWrite = "todo "+ t.getName() + (t.getDone() ? "1" : "0") + "\n";
        }

        if (t instanceof Event) {
            toWrite = "event "+ t.getName()
                    + " /from " + ((Event) t).getFrom()
                    + " /to " + ((Event) t).getTo()
                    +(t.getDone() ? "1" : "0") + "\n";
        }

        if (t instanceof Deadline) {
            toWrite = "deadline "+ t.getName()
                    + " /by " + ((Deadline) t).getBy()
                    +(t.getDone() ? "1" : "0") + "\n";
        }

        return toWrite;
    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
