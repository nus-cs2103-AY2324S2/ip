package Gops;

import java.io.*;

public class Storage {
    /**
     * Writes the list of tasks to a file in the hard disk
     * @param taskList task list
     * @param file file to write tasks to
     */
    public static void writeToHardDisk(TaskList taskList, File file) {
        int messageCount = taskList.size();
        BufferedWriter taskWriter = null;
        try {
            taskWriter = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < messageCount; i++) {
                taskWriter.write(taskList.getTask(i).stringPrinter());
                taskWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (taskWriter != null) {
                try {
                    taskWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Reads from a file in the hard disk and creates an ArrayList of tasks
     * @param taskFile list of tasks
     * @return task list
     */
    public static TaskList readFromHardDisk(File taskFile) {
        TaskList taskList = new TaskList();
        BufferedReader taskReader;
        try {
            taskReader = new BufferedReader(new FileReader(taskFile));
            String taskString;
            while ((taskString = taskReader.readLine()) != null) {
                String[] taskData = taskString.split("\\|");
                String taskType = taskData[0].trim();
                boolean status = taskData[1].trim().equals("1");
                String taskDescription = taskData[2].trim();

                if (taskType.equals("T")) {
                    taskList.addNewTodo(taskDescription, status);
                } else if (taskType.equals("D")) {
                    String endDate = taskData[3].trim().substring(3);
                    taskList.addNewDeadline(taskDescription, endDate, status);
                } else if (taskType.equals("E")) {
                    String startBy = taskData[3].trim().substring(6);
                    String endBy = taskData[4].trim().substring(4);
                    taskList.addNewEvent(taskDescription, startBy, endBy, status);
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return taskList;
    }
}
