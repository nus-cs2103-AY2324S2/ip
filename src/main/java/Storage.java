import java.io.*;

import java.util.Scanner;

public class Storage {
    File tasksFile;
    FileWriter taskFileWriter;
    public Storage() throws NicoleException {
        new File("./data").mkdirs();
        this.tasksFile = new File("./data/tasks.txt");
        try {
            this.taskFileWriter = new FileWriter("./data/tasks.txt");
        } catch (IOException e) {
            throw new NicoleException("Sorry sorry I couldn't create a file to save your tasks...");
        }
        this.loadTasksFromFile();
    }
    public void saveTasksToFile() throws NicoleException {
        try {
            for (int i = 0; i < TaskList.taskList.size(); i++) {
                this.taskFileWriter.write(TaskList.taskList.get(i) + "\n");
            }
            this.taskFileWriter.close();
        } catch (IOException e) {
            throw new NicoleException("I couldn't save the task >< try again plss");
        }
    }
    public void loadTasksFromFile() throws NicoleException {
        File tasksFile = new File("./data/tasks.txt");
        try {
            Scanner userTaskFileReader = new Scanner(tasksFile);
            int numTasksInFile = 0;
            BufferedReader reader = new BufferedReader(new FileReader(tasksFile));
            while (reader.readLine() != null) {
                numTasksInFile++;
            }
            int i = 1;
            while (userTaskFileReader.hasNextLine()) {
                String task = userTaskFileReader.nextLine();
                if (TaskList.taskList.size() < numTasksInFile) {
                    char taskType = task.charAt(1);
                    char taskCompleted = task.charAt(4);
                    String taskDescription = task.substring(7);
                    Task recreatedTask = Task.taskFactory(taskDescription, taskType);
                    if (taskCompleted == 'C') {
                        recreatedTask.markDone();
                    }
                    TaskList.taskList.add(recreatedTask);
                }
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println(Nicole.botName + ": I have no past data with you, let's start something ;)");
        } catch (IOException e) {
            throw new NicoleException("Sorry sorry I have trouble loading your tasks from storage....");
        }
    }
}
