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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            Files.writeString(this.getFileLocationPath(), this.createSaveDataFromTaskList(tasks));
        } catch (IOException e) {
            System.out.println("Folder Does not Exists");
        }
    }

    public String saveTaskListToFile(String fileName, TaskList tasks) throws ChatBotParameterException, IOException {
        String fullFileName = Parser.parseArchiveFileName(fileName);
        Path path = Paths.get(DATA_FOLDER, fullFileName);
        Files.createDirectories(Paths.get(DATA_FOLDER));
        Files.writeString(path, this.createSaveDataFromTaskList(tasks));
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

    public void loadTasksFromFileToTaskList(TaskList taskList) throws ChatBotParameterException {
        try {
            Path filePath = this.getFileLocationPath();
            if (!Files.exists(filePath)) {
                // This is because new user do not have tasks.txt file, hence cannot load data
                return;
            }
            loadTaskListToFilePath(taskList, filePath);
        } catch (IOException e) {
            // This is because new user do not have tasks.txt file, hence cannot load data
        }
    }

    public void loadTasksFromFileToTaskList(TaskList taskList, String fileName) throws ChatBotParameterException {
        try {
            Path filePath = Paths.get(DATA_FOLDER, fileName);
            if (!Files.exists(filePath)) {
                throw new ChatBotParameterException("Archive does not exist.\n" +
                        "Use archive_list to show full archive list.");
            }
            loadTaskListToFilePath(taskList, filePath);
        } catch (IOException e) {
            throw new ChatBotParameterException("Error while loading archive, data possibly corrupted");
        }
    }

    private void loadTaskListToFilePath(TaskList taskList, Path filePath) throws ChatBotParameterException, IOException {
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


    public List<String> loadArchiveList() throws IOException {
        Path pathToDataFolder = Paths.get(DATA_FOLDER);
        if (!Files.exists(pathToDataFolder)) {
            return List.<String>of();
        }
        //@@author baeldung
        //Reused from https://www.baeldung.com/java-list-directory-files
        // with minor modifications
        try (Stream<Path> stream = Files.list(pathToDataFolder)) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .filter(fileNameString -> !fileNameString.equals(this.fileName))
                    .collect(Collectors.toList());
        }
        //@@author
    }
}
