package jelly;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private String path;

    /**
     * @param filePath Path of file to read from/write to
     */
    public Storage(String filePath) {

        this.path = filePath;
    }

    /**
     * @return TaskList created from reading existing storage file
     * @throws JellyException Exception thrown when file cannot be correctly parsed/read
     */
    public TaskList load() throws JellyException {

        TaskList tasks = new TaskList();

        File file;
        Scanner scanner;

        try {

            file = new File(path);

            file.createNewFile(); //if file exists, does nothing
            scanner = new Scanner(file);

        } catch (IOException e) {

            System.out.println("An exception was thrown!");
            throw new JellyException("An exception was thrown!");
        }

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            if (line.length() < 3) {

                System.out.println("File format error! May have been corrupted");
                throw new JellyException("File format error! May have been corrupted");
            }

            String taskName;
            boolean isDone;

            if (line.charAt(1) == '1') {

                isDone = true;
            } else {

                isDone = false;
            }

            switch (line.charAt(0)) {

            case 'T':

                taskName = line.substring(2);
                tasks.addTodo(taskName, isDone);

                break;

            case 'D':

                int index = line.indexOf("/");
                taskName = line.substring(2, index);
                String deadline = line.substring(index + 1);

                tasks.addDeadline(taskName, deadline, isDone);

                break;

            case 'E':

                int firstIndex = line.indexOf("/");
                taskName = line.substring(2, firstIndex);
                String interval = line.substring(firstIndex + 1);
                int secondIndex = interval.indexOf("/");
                String startTime = interval.substring(0, secondIndex);
                String endTime = interval.substring(secondIndex + 1);

                tasks.addEvent(taskName, startTime, endTime, isDone);

                break;

            default:

                System.out.println("File corrupted");
                throw new JellyException("File corrupted");

            }
        }

        return tasks;
    }

    /**
     * @param taskList TaskList to write to storage file
     */
    public void save(TaskList taskList) {

        try {

            FileWriter writer = new FileWriter(path);

            for (int i = 0; i < taskList.size(); ++i) {

                writer.write(taskList.get(i).header() + taskList.get(i).getName());

                writer.write(taskList.get(i).additionalInfo());

                writer.write("\n");
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("File format error! May have been corrupted");
            return;
        }
    }
}
