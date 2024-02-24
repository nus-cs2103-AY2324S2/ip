package duke;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Reads and writes task list data to file on the hard disk.
 */
public class Storage {

    //CHECKSTYLE.OFF:
    protected final String filePath;
    //CHECKSTYLE.ON:
    private ArrayList<Task> tasks;

    /**
     * Constructs a new Storage object with the specified filePath value.
     *
     * @param filePath a String representing the filepath where data should be read and written to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.tasks = new ArrayList<>();
    }

    /**
     * Reads existing data from file and populates task list.
     * Creates missing directory and file as required using relative filepath.
     *
     * @return an ArrayList object containing the read tasks from the filepath
     */
    public ArrayList<Task> loadData() {
        try (Scanner dataScanner = new Scanner(new File(filePath))) {
            int lineNum = 1;
            ArrayList<Integer> corruptedLines = new ArrayList<>();
            while (dataScanner.hasNextLine()) {
                try {
                    String line = dataScanner.nextLine();
                    String[] data = line.split("\\|", 0);
                    String taskType = data[0].trim();
                    String taskComplete = data[1].trim();
                    String taskDescription = data[2].trim();
                    switch (taskType) {
                    case "T":
                        tasks.add(readTodoFromFile(data, taskComplete, taskDescription, lineNum));
                        break;
                    case "D":
                        tasks.add(readDeadlineFromFile(data, taskComplete, taskDescription, lineNum));
                        break;
                    case "E":
                        tasks.add(readEventFromFile(data, taskComplete, taskDescription, lineNum));
                        break;
                    default:
                        throw new ParseException("Task type data is corrupted.",
                                lineNum);
                    }
                } catch (ParseException e) {
                    corruptedLines.add(lineNum);
                    System.out.println(e.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    corruptedLines.add(lineNum);
                    System.out.println("Line " + lineNum + ": " + e.getMessage());
                } finally {
                    lineNum += 1;
                }
            }
            if (corruptedLines.size() > 0) {
                removeCorruptedData(corruptedLines);
            }
        } catch (FileNotFoundException e) {
            handleMissingDirAndFile();
        }
        return tasks;
    }

    /**
     * Helper function for loadData() to read and parse todo data from the .txt data file.
     *
     * @param data an array of Strings containing the data fields of the todo
     * @param taskComplete a String containing the completion status of the todo
     * @param taskDescription a String containing the description of the todo
     * @param lineNum an int representing the line number of the todo data in the file
     * @return a Todo object containing the parsed todo data
     * @throws ParseException if the todo data is corrupted or incomplete
     */
    private Todo readTodoFromFile(String[] data, String taskComplete, String taskDescription, int lineNum) throws ParseException {
        //Check correct number of fields
        if (data.length != 3) {
            throw new ParseException("Todo does not have 3 data fields.",
                    lineNum);
        }

        Todo todo = new Todo(taskDescription);

        //Check completion status is a string "1" or "0"
        if (taskComplete.equals("0")) {
            todo.markAsNotDone();
        } else if (taskComplete.equals("1")) {
            todo.markAsDone();
        } else {
            throw new ParseException("Task completion data is corrupted.",
                    lineNum);
        }

        return todo;
    }

    /**
     * Helper function for loadData() to read and parse deadline data from the .txt data file.
     *
     * @param data an array of Strings containing the data fields of the deadline
     * @param taskComplete a String containing the completion status of the deadline
     * @param taskDescription a String containing the description of the deadline
     * @param lineNum an int representing the line number of the deadline data in the file
     * @return a Deadline object containing the parsed deadline data
     * @throws ParseException if the deadline data is corrupted or incomplete
     */
    private Deadline readDeadlineFromFile(String[] data, String taskComplete, String taskDescription, int lineNum) throws ParseException {
        //Check correct number of fields
        if (data.length != 4) {
            throw new ParseException("Deadline does not have 4 data fields.",
                    lineNum);
        }

        LocalDateTime deadlineBy = Parser.parseDate(data[3].trim());

        Deadline deadline = new Deadline(taskDescription, deadlineBy);

        //Check completion status is a string "1" or "0"
        if (taskComplete.equals("0")) {
            deadline.markAsNotDone();
        } else if (taskComplete.equals("1")) {
            deadline.markAsDone();
        } else {
            throw new ParseException("Task completion data is corrupted.",
                    lineNum);
        }

        return deadline;
    }

    /**
     * Helper function for loadData() to read and parse event data from the .txt data file.
     *
     * @param data an array of Strings containing the data fields of the event
     * @param taskComplete a String containing the completion status of the event
     * @param taskDescription a String containing the description of the event
     * @param lineNum an int representing the line number of the event data in the file
     * @return an Event object containing the parsed event data
     * @throws ParseException if the event data is corrupted or incomplete
     */
    private Event readEventFromFile(String[] data, String taskComplete, String taskDescription, int lineNum) throws ParseException {
        //Check correct number of fields
        if (data.length != 5) {
            throw new ParseException("Event does not have 5 data fields.",
                    lineNum);
        }

        LocalDateTime eventFrom = Parser.parseDate(data[3].trim());
        LocalDateTime eventTo = Parser.parseDate(data[4].trim());

        Event event = new Event(taskDescription, eventFrom, eventTo);

        //Check completion status is a string "1" or "0"
        if (taskComplete.equals("0")) {
            event.markAsNotDone();
        } else if (taskComplete.equals("1")) {
            event.markAsDone();
        } else {
            throw new ParseException("Task completion data is corrupted.",
                    lineNum);
        }

        return event;
    }

    /**
     * Helper function for loadData() to handle missing directory and file.
     */
    private void handleMissingDirAndFile() {
        System.out.println("File not found: " + filePath);
        File file = new File(filePath);
        Path directoryPath = Paths.get(file.getParent());
        //Create missing directory
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
                System.out.println("Created missing directory: " + directoryPath);
            } catch (IOException err) {
                System.out.println("Error creating directory: " + directoryPath);
                err.printStackTrace();
            }

        }
        //Create missing file
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created missing file: " + file);
            } catch (IOException err) {
                System.out.println("Error creating file: " + file);
                err.printStackTrace();
            }
        }
    }

    /**
     * Helper function for loadData() to delete corrupted data lines from the
     * .txt data file by line number.
     *
     * @param indexes an ArrayList of Integer objects signifying corrupted
     *                file lines (1-based indexing) to be deleted
     */
    private void removeCorruptedData(ArrayList<Integer> indexes) {
        ArrayList<String> lines = new ArrayList<>();
        Collections.sort(indexes);
        int offset = 0;
        try (Scanner dataScanner = new Scanner(new File(filePath))) {
            int lineNum = 1;
            while (dataScanner.hasNextLine()) {
                String line = dataScanner.nextLine();
                if (lineNum != indexes.get(offset)) {
                    lines.add(line);
                } else {
                    System.out.println("Removing entry: " + line);
                    if (offset < indexes.size() - 1) {
                        offset += 1;
                    }
                }
                lineNum += 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }


    /**
     * Solution below adapted from https://www.baeldung.com/java-write-to-file
     * Helper function that appends the input Task data as a string to the file at the
     * FILE_PATH and starts a new line.
     *
     * @param task a Task object signifying the Task to be appended to the file
     */
    public void appendTaskToFile(Task task) {
        String line = task.getData();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(line);
            bw.newLine();
            this.tasks.add(task);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }

    /**
     * Helper function that overwrites the original file data using the updated ArrayList data.
     * Called after changes to the list are made by marking, unmarking and deleting tasks.
     *
     * @param newTasks an ArrayList object containing Task objects from the current task list
     */
    public void saveTaskListToFile(ArrayList<Task> newTasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : newTasks) {
                bw.write(task.getData());
                bw.newLine();
            }
            ArrayList<Task> temp = new ArrayList<Task>(newTasks);
            tasks = temp;
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }
}
