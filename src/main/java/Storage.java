import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

public class Storage {
    Path filepath;
    TaskList taskList;

    public Storage(Path filepath, TaskList taskList) {
        this.filepath = filepath;
        this.taskList = taskList;
    }


    public void load() {
        try {
            // Solution below adapted from https://stackoverflow.com/a/41514348
            if (!Files.exists(this.filepath)) {
                Files.createDirectories(this.filepath.getParent());
                Files.createFile(this.filepath);
                return;
            }

            // Solution below adapted from https://www.baeldung.com/reading-file-in-java
            Stream<String> lines = Files.lines(this.filepath);
            lines.forEach(line -> {
                        String[] s = line.split(" \\| ");
                        String taskType = s[0];
                        boolean isDone = s[1].equals("1");
                        String taskDesc = s[2];
                        switch (taskType) {
                        case "T":
                            this.taskList.addToDo(taskDesc);
                            break;
                        case "D":
                            this.taskList.addDeadline(taskDesc, s[3]);
                            break;
                        case "E":
                            this.taskList.addEvent(taskDesc, s[3], s[4]);
                            break;
                        default:
                            System.out.println("Error loading file");
                            break;
                        }
                        if (isDone) {
                            this.taskList.markLastAsDone();
                        }
                    }
            );

            lines.close();
        } catch (IndexOutOfBoundsException | IllegalStateException | IOException | DateTimeParseException e) {
            System.out.println(e.getMessage());
            System.out.println("Error loading file");
        }

    }

    public void save() {
        try (BufferedWriter bw = Files.newBufferedWriter(this.filepath)) {
            bw.write(this.taskList.getSavedTasksString());
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

}

