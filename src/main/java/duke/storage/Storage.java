package duke.storage;

import duke.exception.ChatBotParameterException;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    private final String fileName;
    private static final String DATA_FOLDER = "data";

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public StringBuilder createSaveDataFromTaskList(TaskList tasks) {
        StringBuilder saveData = new StringBuilder();
        for (Task task: tasks) {
            saveData.append(task.createSaveData());
        }
        return saveData;
    }

    public void saveTaskListToFile(TaskList tasks) {
        try {
            Files.createDirectories(Paths.get(DATA_FOLDER));
            Files.writeString(this.getFileLocationPath(), this.createSaveDataFromTaskList(tasks),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Folder Does not Exists");
        }
    }

    public Path getFileLocationPath() {
        return Paths.get(DATA_FOLDER, this.fileName);
    }



    public void loadTasksFromFileToTaskList(TaskList taskList) {
        try {
            Path filePath = this.getFileLocationPath();
            if (!Files.exists(filePath)) {
                return;
            }

            List<String> tasks = Files.readAllLines(filePath);
            for (String taskString : tasks) {
                String[] parameters = Parser.parseSavedTask(taskString);
                switch (parameters[0]) {
                case ("T"):
                    taskList.addToDo(parameters[2], parameters[1].equals("1"));
                    break;
                case ("D"):
                    taskList.addDeadline(parameters[2], parameters[3], parameters[1].equals("1"));
                    break;
                case ("E"):
                    taskList.addEvent(parameters[2], parameters[3], parameters[4], parameters[1].equals("1"));
                    break;
                }
            }
        } catch (IOException | ChatBotParameterException e) {
            throw new RuntimeException(e);
        }
    }

}
