import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Storage {

    protected static String FILE_PATH;
    protected static ArrayList<Task> tasks;

    /**
     * Constructs a new Storage object with the specified filePath value.
     *
     * @param filePath a String representing the filepath where data should be read and written to
     */
    public Storage(String filePath) {
        FILE_PATH = filePath;
        tasks = new ArrayList<Task>();
    }

    /**
     * Method to read existing data from file and populate task list.
     * Creates missing directory and file as required using relative filepath.
     *
     * @return an ArrayList object containing the read tasks from the filepath
     */
    public ArrayList<Task> loadData() {
        try(Scanner dataScanner = new Scanner(new File(FILE_PATH))) {
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
                            //Check correct number of fields
                            if (data.length != 3) {
                                throw new ParseException("Todo does not have 3 data fields.", lineNum);
                            }

                            Todo todo = new Todo(taskDescription);

                            //Check completion status is a string "1" or "0"
                            if (taskComplete.equals("0")) {
                                todo.markAsNotDone();
                            } else if (taskComplete.equals("1")) {
                                todo.markAsDone();
                            } else {
                                throw new ParseException("Task completion data is corrupted.", lineNum);
                            }

                            tasks.add(todo);
                            break;
                        case "D":
                            //Check correct number of fields
                            if (data.length != 4) {
                                throw new ParseException("Deadline does not have 4 data fields.", lineNum);
                            }

                            LocalDateTime deadlineBy = Parser.parseDate(data[3].trim());

                            Deadline deadline = new Deadline(taskDescription, deadlineBy);

                            //Check completion status is a string "1" or "0"
                            if (taskComplete.equals("0")) {
                                deadline.markAsNotDone();
                            } else if (taskComplete.equals("1")) {
                                deadline.markAsDone();
                            } else {
                                throw new ParseException("Task completion data is corrupted.", lineNum);
                            }

                            tasks.add(deadline);
                            break;
                        case "E":
                            //check correct number of fields
                            if (data.length != 5) {
                                throw new ParseException("Event does not have 5 data fields.", lineNum);
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
                                throw new ParseException("Task completion data is corrupted.", lineNum);
                            }

                            tasks.add(event);
                            break;
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
            System.out.println("File not found: " + FILE_PATH);
            File file = new File(FILE_PATH);
            Path directoryPath = Paths.get(file.getParent());
            if (!Files.exists(directoryPath)) {
                try {
                    Files.createDirectories(directoryPath);
                    System.out.println("Created missing directory: " + directoryPath);
                } catch (IOException err) {
                    System.out.println("Error creating directory: " + directoryPath);
                    err.printStackTrace();
                }

            }
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
        return tasks;
    }

    /**
     * Helper function for loadData() to delete corrupted data lines from the .txt data file by line number.
     *
     * @param indexes an ArrayList of Integer objects signifying corrupted file lines (1-based indexing) to be deleted
     */
    private static void removeCorruptedData(ArrayList<Integer> indexes) {
        ArrayList<String> lines = new ArrayList<>();
        Collections.sort(indexes);
        int offset = 0;
        try (Scanner dataScanner = new Scanner(new File(FILE_PATH))) {
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
            System.out.println("File not found: " + FILE_PATH);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + FILE_PATH);
        }
    }


    /**
     * Solution below adapted from https://www.baeldung.com/java-write-to-file
     * Helper function that appends the input Task data as a string to the file at the FILE_PATH and starts a new line.
     *
     * @param task a Task object signifying the Task to be appended to the file
     */
    public void appendTaskToFile(Task task) {
        String line = task.getData();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + FILE_PATH);
        }
        tasks.add(task);
    }

    /**
     * Helper function that overwrites the original file data using the updated ArrayList data.
     * Called after changes to the list are made by marking, unmarking and deleting tasks.
     *
     * @param newTasks an ArrayList object containing Task objects from the current task list
     */
    public void saveTaskListToFile(ArrayList<Task> newTasks) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : newTasks) {
                bw.write(task.getData());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + FILE_PATH);
        }
        tasks = newTasks;
    }
}
