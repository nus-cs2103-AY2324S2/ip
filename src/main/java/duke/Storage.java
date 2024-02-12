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

        if (!file.exists()) {
            file.createNewFile();
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String inputLine = scanner.nextLine();
            String[] inputComponents = inputLine.split("\\|", 5);

            Task currentTask = null;
            String tasktype = inputComponents[0];
            String markStatus = inputComponents[1];
            String taskdesc = inputComponents[2];

            if (tasktype.equals("T")) {
                currentTask = new ToDo(taskdesc, tasktype);
            } else if (tasktype.equals("D")) {
                String dateTime = inputComponents[3];
                currentTask = new Deadline(taskdesc, tasktype, dateTime);
            } else if (tasktype.equals("E")) {
                String startDateTime = inputComponents[3];
                String endDateTime = inputComponents[4];
                currentTask = new Event(taskdesc, tasktype,
                        startDateTime, endDateTime);
            } else {
                System.out.println("What is this nonsense. Failure to load object.");
            }

            if (markStatus.equals("1")) {
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
