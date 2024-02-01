package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage Class is responsible for the loading and saving of the data.txt file upon exiting the program.
 */
public class Storage {
    private String path;

    /**
     * Constructor for the Storage Class
     * @param path
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Method is responsible for loading the contents in the txt for the Duke program to use.
     * Returns the TaskList for commands to interact with the tasks inside.
     * @return TaskList
     * @throws IOException
     */
    public TaskList loadFile() throws IOException {
        TaskList taskListInput = new TaskList();
        File file = new File(this.path);

        file.getParentFile().mkdirs();

        if(!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()) {
            String inputLine = scanner.nextLine();
            String[] inputComponents = inputLine.split("\\|", 5);

            Task currentTask = null;
            if(inputComponents[0].equals("T")) {
                currentTask = new ToDo(inputComponents[2],inputComponents[0]);
            } else if(inputComponents[0].equals("D")) {
                currentTask = new Deadline(inputComponents[2],inputComponents[0],inputComponents[3]);
            } else if(inputComponents[0].equals("E")) {
                currentTask = new Event(inputComponents[2],inputComponents[0],inputComponents[3],inputComponents[4]);
            } else {
                System.out.println("What is this nonsense. Failure to load object.");
            }

            if(inputComponents[1].equals("1")) {
                currentTask.mark();
            }
            taskListInput.getTaskList().add(currentTask);
        }
        return taskListInput;
    }

    /**
     * Method is responsible for saving the updated TaskList after the user has finished inputting commands.
     * The data.txt will be updated upon the termination of Duke program.
     * @param tasklist
     */
    public void saveFile(TaskList tasklist) {
        try {
            FileWriter writer = new FileWriter(this.path);
            String tasks = "";

            for (int i = 0; i < tasklist.getTaskList().size(); i++) {
                Task currentTask = tasklist.getTask(i);
                tasks += currentTask.saveString() + "\n";
            }
            writer.write(tasks);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving: " + e.getMessage());
        }
    }
}
