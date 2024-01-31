package duke.storage;

import duke.task.*;
import duke.utils.DukeException;
import duke.utils.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileStorage implements Storage {
    private final File file;

    public FileStorage(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        File directory = path.getParent().toFile();
        directory.mkdirs();
        this.file = path.toFile();
        this.file.createNewFile();
    }

    @Override
    public void save(TaskList taskList) throws DukeException {
        file.delete();
        try {

            Writer fileWriter = new FileWriter(file);
            for (Task task : taskList.getTasks()) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error saving file: " + e.getMessage());
        }
    }

    @Override
    public List<Task> load() throws IOException, DukeException {
        Scanner fileScanner = new Scanner(this.file);
        List<Task> tasks = new ArrayList<>();
        try {
            while (fileScanner.hasNext()) {
                String[] split = fileScanner.nextLine().split(" \\| ");
                TodoState state = split[1].equals("1") ? TodoState.DONE : TodoState.UNDONE;
                switch (split[0]) {
                    case "T": {
                        tasks.add(new Todo(split[2], state));
                        break;
                    }
                    case "D": {
                        tasks.add(new Deadline(split[2], Util.parseDate(split[3]), state));
                        break;
                    }
                    case "E": {
                        tasks.add(new Event(split[2], Util.parseDate(split[3]), Util.parseDate(split[4]), state));
                        break;
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("File is corrupted");
        } finally {
            fileScanner.close();
        }
        return tasks;
    }
}
