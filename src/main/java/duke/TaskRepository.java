package duke;

import java.io.*;

/**
 * TaskRepository class to handle the storage of tasks
 */
public class TaskRepository {
    // Relative file path to store tasks
    private final String FILE_PATH = "./data/taskStorage.txt";
    private TaskList taskList;

    /**
     * Constructor for the TaskRepository class
     * It checks if a file at FILE_PATH exists, and if not, creates it
     * It also initializes an empty TaskList
     */
    public TaskRepository() {
        try {
            // Create the file if it does not exist
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            // Initialize an empty TaskList
            this.taskList = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file at FILE_PATH into the TaskList.
     * Process the string from file and delegates the task creation
     * to respective methods based on the task type.
     * 
     * @return The TaskList containing the loaded tasks.
     * @throws BotException If there is an error while processing the tasks.
     */
    public TaskList loadTasksFromFile() throws BotException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(FILE_PATH));
            assert reader != null : "Reader should be initialized";
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split("\\|");
                assert taskDetails.length >= 3 : "Task details are incomplete";
                String isDone = taskDetails[1].trim();
                Boolean isTaskDone = isDone.equals("X");
                String taskType = taskDetails[0].trim();
                String description = taskDetails[2].trim();
                processTask(taskDetails, isTaskDone, taskType, description);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return taskList;
    }

    /**
     * Processes a task's details and adds it to the TaskList.
     * The task type determines how the task is processed.
     * 
     * @param taskDetails The details of the task.
     * @param isTaskDone  Whether the task is done.
     * @param taskType    The type of the task.
     * @param description The description of the task.
     * @throws BotException If there is an error while processing the task.
     */
    private void processTask(String[] taskDetails, Boolean isTaskDone, String taskType, String description)
            throws BotException {
        switch (taskType) {
            case "T":
                processTodoTask(isTaskDone, description);
                break;
            case "D":
                processDeadlineTask(taskDetails, isTaskDone, description);
                break;
            case "E":
                processEventTask(taskDetails, isTaskDone, description);
                break;
        }
    }

    /**
     * Processes a Todo task's details and adds it to the TaskList.
     * 
     * @param isTaskDone  Whether the task is done.
     * @param description The description of the task.
     */
    private void processTodoTask(Boolean isTaskDone, String description) {
        taskList.addTodo(description);
        if (isTaskDone) {
            taskList.getTaskByNum(taskList.getTaskCount()).markAsDone();
        }
    }

    /**
     * Processes a Deadline task's details and adds it to the TaskList.
     * 
     * @param taskDetails The details of the task.
     * @param isTaskDone  Whether the task is done.
     * @param description The description of the task.
     * @throws BotException If there is an error while processing the task.
     */
    private void processDeadlineTask(String[] taskDetails, Boolean isTaskDone, String description) throws BotException {
        assert taskDetails.length >= 4 : "Deadline task details are incomplete";
        String dueDate = taskDetails[3].trim();
        dueDate = dueDate.substring(3).trim();
        taskList.addDeadline(description, dueDate);
        if (isTaskDone) {
            taskList.getTaskByNum(taskList.getTaskCount()).markAsDone();
        }
    }

    /**
     * Processes an Event task's details and adds it to the TaskList.
     * 
     * @param taskDetails The details of the task.
     * @param isTaskDone  Whether the task is done.
     * @param description The description of the task.
     * @throws BotException If there is an error while processing the task.
     */
    private void processEventTask(String[] taskDetails, Boolean isTaskDone, String description) throws BotException {
        assert taskDetails.length >= 4 : "Event task details are incomplete";
        String timeBlock = taskDetails[3].trim();
        String[] parts = timeBlock.split("to:");
        assert parts.length == 2 : "Time block is incomplete";
        String fromPart = parts[0];
        String toPart = parts[1];
        String startTime = fromPart.replace("from:", "").trim();
        String endTime = toPart.trim();
        taskList.addEvent(description, startTime, endTime);
        if (isTaskDone) {
            taskList.getTaskByNum(taskList.getTaskCount()).markAsDone();
        }
    }

    /**
     * This method saves the tasks from the provided TaskList to a text file
     * It writes each task as a new line in the file
     * The task number is removed before writing to the file as it is not needed
     *
     * @param taskList The TaskList containing the tasks to be saved to the file
     *
     */
    public void saveTasksToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (String task : taskList.getTasksAsList()) {
                String taskWithoutNumber = task.substring(task.indexOf(" ") + 1);
                fileWriter.write(taskWithoutNumber + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}