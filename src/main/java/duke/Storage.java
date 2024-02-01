package duke;

import duke.task.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Storage {

    private final String file;
    private static final String FILE_PATH = "./data/jamie.txt";

    public Storage(String file) {
        this.file = file;
    }


    public ArrayList<Task> load() throws FileNotFoundException, JamieException {
        File file = new File(this.file);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        if (!file.exists()) {
            return loadedTasks; // Return an empty list if the file doesn't exist
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String taskString = scanner.nextLine();
            String[] splits = taskString.split(" \\| "); // Splitting each part of the task
            switch (splits[0]) {
                case "T": {
                    Task toAdd = new ToDo(splits[2], splits[1].equals("1"));
                    loadedTasks.add(toAdd);
                    break;
                }
                case "E": {
                    // Assuming splits[2] is the description, splits[3] is the fromDateTime, and splits[4] is the toDateTime
                    Task toAdd = new Event(splits[2], splits[3], splits[4], splits[1].equals("1"));
                    loadedTasks.add(toAdd);
                    break;
                }
                case "D": {
                    // Assuming splits[2] is the description and splits[3] is the byDateTime
                    Task toAdd = new Deadline(splits[2], splits[3], splits[1].equals("1"));
                    loadedTasks.add(toAdd);
                    break;
                }
                default: {
                    throw new JamieException("Error occurred when reading data from storage file.\n"
                            + "Therefore, creating a new task list.");
                }
            }
        }
        scanner.close();

        return loadedTasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        String textToAdd = convertToString((tasks.getTasks()));
        writer.write(textToAdd);
        writer.close();
    }

    public String convertToString(ArrayList<Task> taskList) {
        StringBuilder textToAdd = new StringBuilder();
        for (Task curr : taskList) {
            if (curr instanceof ToDo) {
                textToAdd.append(curr.toFileString()).append("\n");
            }
        }
        return textToAdd.toString();
    }

    public String caseTodo(ToDo todo) {
        String toReturn = "";
        toReturn += "T ";
        toReturn += todo.getIsDone() ? "1 " : "0 ";
        toReturn += todo.getDescription() + "\n";
        return toReturn;
    }

    public String caseDeadline(Deadline deadline) {
        String toReturn = "";
        toReturn += "D ";
        toReturn += deadline.getIsDone() ? "1 " : "0 ";
        toReturn += deadline.getDescription() + "\n";
        toReturn += deadline.getBy() + "\n";
        return toReturn;
    }

    public String caseEvent(Event event) {
        String toReturn = "";
        toReturn += "E ";
        toReturn += event.getIsDone() ? "1 " : "0 ";
        toReturn += event.getIsDone() + "\n";
        toReturn += event.getTo() + "\n";
        toReturn += event.getFrom() + "\n";
        return toReturn;
    }

}
