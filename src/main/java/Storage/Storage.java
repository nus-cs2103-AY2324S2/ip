package Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Exceptions.DukeException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Task.TaskType;
import Ui.Ui;
import Tasks.ToDo;

/**
 * Storage class to handle File IO actions
 * 
 * @author Tang Yetong
 **/
public class Storage {

    private final String SAVE_FILE = "src/main/java/Data/Kewqgy.txt";
    private Ui ui;

    public Storage(Ui ui) {
        this.ui = ui;
    }

    /**
     * Update task in save file when task is marked as done or undone.
     * 
     * @param taskInt index of task
     * @param isDone whether task is done
     **/
    public void updateTask(int taskInt, Boolean isDone) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if (!saveFile.exists() || saveFile.isDirectory()) {
                saveFile.createNewFile();

                return;
            }

            List<String> tasks = new ArrayList<>();
            Scanner fileReader = new Scanner(saveFile);

            while (fileReader.hasNextLine()) {
                tasks.add(fileReader.nextLine());
            }

            String taskUpdate = tasks.get(taskInt);
            String[] taskUpdateParsed = taskUpdate.split(" ", 3);
            taskUpdateParsed[1] = isDone.toString();
            tasks.set(taskInt, String.join(" ", taskUpdateParsed));

            fileReader.close();

            FileWriter saveFileWriter = new FileWriter(saveFile, false);
            BufferedWriter saveFileBufferedWriter = new BufferedWriter(saveFileWriter);

            for (String taskString : tasks) {
                saveFileBufferedWriter.write(taskString + "\n");
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            ui.printDukeText("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Delete task in save file
     * 
     * @param taskInt index of task
     **/
    public void deleteTask(int taskInt) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if (!saveFile.exists() || saveFile.isDirectory()) {
                saveFile.createNewFile();

                return;
            }

            List<String> tasks = new ArrayList<>();
            Scanner fileReader = new Scanner(saveFile);

            while (fileReader.hasNextLine()) {
                tasks.add(fileReader.nextLine());
            }

            tasks.remove(taskInt);
            fileReader.close();

            FileWriter saveFileWriter = new FileWriter(saveFile, false);
            BufferedWriter saveFileBufferedWriter = new BufferedWriter(saveFileWriter);

            for (String taskString : tasks) {
                saveFileBufferedWriter.write(taskString + "\n");
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            ui.printDukeText("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Save task in save file when task is created by user
     * 
     * @param userMsg Task description
     * @param taskType Type of task
     **/
    public void saveTask(String userMsg, TaskType taskType) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if (!saveFile.exists() || saveFile.isDirectory()) {
                saveFile.createNewFile();
            }

            FileWriter saveFileWriter = new FileWriter(saveFile, true);
            BufferedWriter saveFileBufferedWriter = new BufferedWriter(saveFileWriter);

            switch (taskType) {
                case T:
                    saveFileBufferedWriter.write(TaskType.T.toString() + " false " + userMsg + "\n");
                    break;
                case D:
                    saveFileBufferedWriter.write(TaskType.D.toString() + " false " + userMsg + "\n");
                    break;
                case E:
                    saveFileBufferedWriter.write(TaskType.E.toString() + " false " + userMsg + "\n");
                    break;
                default:
                    ui.printDukeText("Unkown Task Type: " + taskType);
                    break;
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            ui.printDukeText("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Load task in save file when chatbot is launched
     * 
     * @return list of tasks loaded from save file
     **/
    public List<Task> loadTasks() {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if (!saveFile.exists() || saveFile.isDirectory()) {
                saveFile.createNewFile();

                return new ArrayList<>();
            }

            Scanner fileReader = new Scanner(saveFile);
            List<Task> savedTasks = new ArrayList<>();

            while (fileReader.hasNextLine()) {
                Task savedTask;
                String data = fileReader.nextLine();
                // Type, isDone, Description
                String[] dataParsed = data.split(" ", 3);

                switch (TaskType.getType(dataParsed[0])) {
                    case T:
                        savedTask = new ToDo(dataParsed[2]);
                        break;
                    case D:
                        savedTask = new Deadline(dataParsed[2]);
                        break;
                    case E:
                        savedTask = new Event(dataParsed[2]);
                        break;
                    default:
                        System.out.println("Unknown Task Type: " + dataParsed[0]);
                        continue;
                }

                savedTask.setDone(Boolean.parseBoolean(dataParsed[1]));
                savedTasks.add(savedTask);
            }

            fileReader.close();

            return savedTasks;
        } catch (IOException e) {
            ui.printDukeText("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) {
            ui.printDukeText("DukeException occurred: " + e.toString());
        }

        return new ArrayList<>();
    }
}
