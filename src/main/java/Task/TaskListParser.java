package task;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TaskListParser {
    public static TaskList parse(File file) throws IOException, InvalidDataFormatException {
        TaskList list = new TaskList();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            list.addTask(parseTask(line));
        }
        reader.close();
        return list;
    }

    private static Task parseTask(String line) throws InvalidDataFormatException {
        String[] words = line.split(" \\| ");
        if (words.length < 3)
            throw new InvalidDataFormatException("Invalid task format: " + line, "Invalid task format: " + line);
        boolean isDone = words[1].equals("1");
        switch (words[0]) {
        case "T":
            return new Todo(words[2], isDone);
        case "D":
            if (words.length < 4)
                throw new InvalidDataFormatException("Invalid task format: " + line, "Invalid task format: " + line);
            return new Deadline(words[2], words[3], isDone);
        case "E":
            if (words.length < 5)
                throw new InvalidDataFormatException("Invalid task format: " + line, "Invalid task format: " + line);
            return new Event(words[2], words[3], words[4], isDone);
        default:
            throw new InvalidDataFormatException("Unknown task type: " + words[0], "Unknown task type: " + words[0]);
        }
    }

    public static String serialize(TaskList list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(serializeTask(list.get(i)) + "\n");
        }
        return sb.toString();
    }

    private static String serializeTask(Task task) {
        return task.serialize();
    }

    // TODO: add a method to write to file
    public static void writeToFile(TaskList list, File file) throws IOException {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        FileWriter writer = new FileWriter(file, false);
        writer.write(serialize(list));
        writer.close();
    }
}
