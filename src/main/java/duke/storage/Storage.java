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

    /**
     * Create a StringBuilder from data inside TaskList for Local Storage.
     * @param tasks TaskList from the ChatBot.
     * @return StringBuilder the converted tasks' data.
     */
    public StringBuilder createSaveDataFromTaskList(TaskList tasks) {
        StringBuilder saveData = new StringBuilder();
        for (Task task : tasks) {
            saveData.append(task.createSaveData());
        }
        return saveData;
    }

    /**
     * Save data inside TaskList into a SaveFile for Local Storage.
     * @param tasks TaskList tasks to be saved.
     */
    public void saveTaskListToFile(TaskList tasks) {
        try {
            Files.createDirectories(Paths.get(DATA_FOLDER));
            Files.writeString(this.getFileLocationPath(), this.createSaveDataFromTaskList(tasks),
                    StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("Folder Does not Exists");
        }
    }

    public String saveTaskListToFile(String fileName, TaskList tasks) throws ChatBotParameterException, IOException {
        String fullFileName = Parser.parseArchiveFileName(fileName);
        Path path = Paths.get(DATA_FOLDER, fullFileName);
        Files.createDirectories(Paths.get(DATA_FOLDER));
        Files.writeString(path, this.createSaveDataFromTaskList(tasks),
                StandardOpenOption.CREATE);
        return fullFileName;
    }

    /**
     * Get Location of the SaveFile.
     * @return Path Location of the SaveFile.
     */
    public Path getFileLocationPath() {
        return Paths.get(DATA_FOLDER, this.fileName);
    }


    /**
     * Load data from SaveFile in Local Storage into TaskList.
     * @param taskList TaskList to for local data to be added in.
     */

    public void loadTasksFromFileToTaskList(TaskList taskList) throws ChatBotParameterException, IOException {
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
    }


}
