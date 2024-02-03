package data;

import core.Ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;


public class Storage {
    private String filePath;
    private Ui ui;

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();

            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            ui.showErrorCreatingFile();
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void save(List<Task> tasks) {
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            ui.showSavingError();
            e.printStackTrace();
        }
    }

    public List<Task> load() {
        List<Task> loadedTasks = new ArrayList<>();
        Path path = Paths.get(filePath);

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    Task task = null;
                    switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], LocalDate.parse(parts[3]));
                        break;
                    case "E":
                        task = new Event(parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                        break;
                    }
                    if (task != null) {
                        if (parts[1].equals("1")) {
                            task.mark();
                        }
                        loadedTasks.add(task);
                    }
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    ui.showLoadingError("corrupted");
                }
            }
        } catch (FileNotFoundException e) {
            ui.showLoadingError("file not found");
        } catch (IOException e) {
            ui.showLoadingError("cannot read the task file");
            e.printStackTrace();
        }
        return loadedTasks;
    }
}
