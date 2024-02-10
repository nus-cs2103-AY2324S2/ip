package nicole.taskstorage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

import nicole.nicoleexceptions.NicoleException;
import nicole.task.Task;

public class Storage {
    public Storage() {
    }

    /**
     * Saves user tasks to "./data/tasks.txt"
     *
     * @throws NicoleException if there are write issues to tasks.txt
     */
    protected void saveTasksToFile() throws NicoleException {
        try {
            FileWriter taskFileWriter = new FileWriter("tasks.txt");
            for (int i = 0; i < TaskList.TASKS.size(); i++) {
                taskFileWriter.write(TaskList.TASKS.get(i) + "\n");
            }
            taskFileWriter.close();
        } catch (IOException e) {
            throw new NicoleException("I couldn't save the task >< try again plss");
        }
    }

    public void loadTasksFromFile() throws NicoleException {
        File tasksFile = new File("tasks.txt");
        try {
            int numTasksInFile = 0;
            BufferedReader numTasksReader = new BufferedReader(new FileReader(tasksFile));
            while (numTasksReader.readLine() != null) {
                numTasksInFile++;
            }
            numTasksReader.close();
            Scanner userTaskFileReader = new Scanner(tasksFile);
            while (userTaskFileReader.hasNextLine()) {
                String task = userTaskFileReader.nextLine();
                if (TaskList.TASKS.size() < numTasksInFile) {
                    char taskType = task.charAt(1);
                    char taskCompleted = task.charAt(4);
                    String taskDescription = task.substring(7);
                    Task recreatedTask = Task.taskFactory(taskDescription, taskType);
                    if (taskCompleted == 'C') {
                        recreatedTask.markDone();
                    }
                    TaskList.TASKS.add(recreatedTask);
                }
            }
            userTaskFileReader.close();
        } catch (FileNotFoundException e) {
            throw new NicoleException("I have no past data with you, let's start something ;)");
        } catch (IOException e) {
            throw new NicoleException("Sorry sorry I have trouble loading your tasks from storage....");
        }
    }
}
