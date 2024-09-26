package chaterpillar.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.parser.Parser;
import chaterpillar.tasks.Task;
import chaterpillar.tasks.TaskList;

/**
 * Custom <code>Storage</code> for file reading/writing pero=sona.
 *
 * @author marclamp
 */
public class Storage {
    public static final String CHATBOT_PATH_NAME = "./data";
    public static final String CHATBOT_FILE_NAME = "history.txt";

    /**
     * Gets the path of the file that stores the list of tasks.
     * Creates the directory if it is not found.
     * Creates the file if it does not exist.
     *
     * @return <code>Path</code> of the file
     * @throws ChaterpillarException if the path string cannot be converted
     *     to a path, or if the parent directory does not exist, or if an
     *     I/O error occurred.
     */
    public static Path getHistoryFilePath() throws ChaterpillarException {
        try {
            Path chatbotDataFilePath = Paths.get(CHATBOT_PATH_NAME);
            if (!Files.exists(chatbotDataFilePath)) {
                Files.createDirectory(chatbotDataFilePath);
            }

            Path chatbotFilePath = chatbotDataFilePath.resolve(CHATBOT_FILE_NAME);
            if (!Files.exists(chatbotFilePath)) {
                Files.createFile(chatbotFilePath);
            }
            return chatbotFilePath;
        } catch (InvalidPathException e) {
            throw new ChaterpillarException("Error: path string cannot be converted to a Path");
        } catch (IOException e) {
            throw new ChaterpillarException("Error in I/O or the parent directory does not exist.");
        }
    }

    /**
     * Loads the contents of the file and inputs it into the listOfTasks.
     *
     * @return An <code>ArrayList</code> of <code>tasks.Task</code> objects.
     * @throws ChaterpillarException if there is an error opening the file,
     *     or when the formatting of a line is in the wrong format.
     */
    public TaskList loadFromFile() throws ChaterpillarException {
        Path path = getHistoryFilePath();
        ArrayList<Task> newList = new ArrayList<Task>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String str;
            while ((str = reader.readLine()) != null) {
                Task task = Parser.parseFromFile(str);
                newList.add(task);
            }
        } catch (IOException e) {
            throw new ChaterpillarException("Error in opening the file.");
        }
        return new TaskList(newList);
    }

    /**
     * Saves the entire list of Tasks into the file, by first clearing its contents.
     *
     * @throws ChaterpillarException if there are errors writing to the file.
     */
    public void saveAllToFile(TaskList tasks) throws ChaterpillarException {
        Path path = getHistoryFilePath();
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.defaultCharset())) {
            writer.write(""); // clears the file
            StringBuilder strBdr = new StringBuilder();
            for (Task task : tasks.getTasks()) {
                String str = task.formatStringForSaving();
                strBdr.append(str).append("\n");
            }
            writer.write(strBdr.toString());
        } catch (IOException e) {
            throw new ChaterpillarException("Error in writing to the file.");
        }
    }
}
