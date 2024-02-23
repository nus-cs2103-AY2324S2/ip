package lex.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lex.tasks.TaskList;

/**
 * Represents a storage to save and load tasks.
 */
public class Storage {
    private final String filePath;
    private final ObjectMapper mapper;

    /**
     * Constructor for the Storage class.
     *
     * @param filePath The file path.
     */
    public Storage(String filePath) {
        this(new ObjectMapper(), filePath);
    }

    /**
     * Constructor for the Storage class.
     *
     * @param mapper The jackson object mapper.
     * @param filePath The file path.
     */
    public Storage(ObjectMapper mapper, String filePath) {
        mapper.registerModule(new JavaTimeModule());

        this.mapper = mapper;
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     *
     * @return The list of tasks.
     * @throws Exception If there is an error loading the file.
     */
    public TaskList load() throws Exception {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            return new TaskList();
        }

        try {
            return mapper.readValue(Files.readAllBytes(path), new TypeReference<>(){});
        } catch (Exception e) {
            throw new Exception("Error parsing file.");
        }
    }

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks.
     * @throws Exception If there is an error saving the file.
     */
    public void save(TaskList tasks) throws Exception {
        Path path = Paths.get(filePath);

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
            }

            byte[] content = mapper.writeValueAsBytes(tasks);
            Files.write(path, content);
        } catch (Exception e) {
            throw new Exception("Error saving file.");
        }
    }
}
