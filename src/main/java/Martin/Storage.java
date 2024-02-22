package Martin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Handles the storage of tasks in a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Starts up the storage sequence.
     * If the file does not exist, a new file is created.
     * The tasks are then loaded from the file.
     *
     * @return the list of tasks loaded from the file
     */
    public ArrayList<Task> startUpSequence() {
        File martinFile;
        try {
            martinFile = new File(filePath);
            if (!martinFile.exists()) {
                System.out.println("File does not exist. Creating a new file.");
                if (martinFile.getParentFile().mkdirs()) {
                    System.out.println("Directory created: " + martinFile.getParentFile().getName());
                }
                if (martinFile.createNewFile()) {
                    System.out.println("File created: " + martinFile.getName());
                }
                FileWriter fw = new FileWriter(martinFile);
                fw.write("T | 1 | dummy offset\n");
                fw.close();
            }
            ArrayList<Task> todoList = loadFromFile(martinFile);
            assert todoList.size() > 0 : "todoList should not be empty";
            return todoList;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Loads the tasks from the specified file.
     *
     * @param martinTxt the file
     * @return the list of tasks loaded from the file
     */
    private ArrayList<Task> loadFromFile(File martinTxt) {
        ArrayList<Task> todoList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(martinTxt));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("line: " + line);
                String[] lineArray = line.split(" \\| ", 3);
                String taskType = lineArray[0];
                boolean isDone = lineArray[1].equals("1");
                String taskDescription = lineArray[2];
                switch (taskType) {
                    case "T":
                        Todo todo = new Todo(taskDescription);
                        if (isDone) {
                            todo.markAsDone();
                        }
                        todoList.add(todo);
                        break;
                    case "E":
                        String[] eventArray = taskDescription.split(" \\| ");
                        String eventDescription = eventArray[0];
                        String[] eventTime = eventArray[1].split("-");
                        String startTime = eventTime[0];
                        String endTime = eventTime[1];
                        Event event = new Event(eventDescription, startTime, endTime);
                        if (isDone) {
                            event.markAsDone();
                        }
                        todoList.add(event);
                        break;
                    case "D":
                        String[] deadlineArray = taskDescription.split(" \\| ");
                        String deadlineDescription = deadlineArray[0];
                        LocalDate deadlineTime = LocalDate.parse(deadlineArray[1]);
                        Deadline deadline = new Deadline(deadlineDescription, deadlineTime);
                        if (isDone) {
                            deadline.markAsDone();
                        }
                        todoList.add(deadline);
                        break;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error reading file");
        } 

        return todoList;
    }

    /**
     * Rewrites the file with the tasks from the given task list.
     *
     * @param taskList The task list containing the tasks to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void rewriteFile(TaskList taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            ArrayList<Task> todoList = taskList.getTodoList();
            for (int i = 0; i < todoList.size(); i++) {
                fw.write(todoList.get(i).toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Appends a line of text to the file.
     *
     * @param line the line of text to be appended
     */
    public void appendToFile(String line) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(line + "\r\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
