package storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import exceptions.KewgyException;
import tasks.Deadline;
import ui.Ui;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import tasks.Task.TaskType;

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
     * @throws KewgyException 
     **/
    public void updateTask(int taskInt, Boolean isDone) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if (!saveFile.exists() || saveFile.isDirectory()) {
                saveFile.createNewFile();

                return;
            }

            if (!saveFile.canWrite()) {
                throw new KewgyException("Cannot write to save file!");
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

            for (String taskstring : tasks) {
                saveFileBufferedWriter.write(taskstring + "\n");
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            ui.printKewgyText("An error occurred.");
            e.printStackTrace();
        } catch (KewgyException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Delete task in save file
     * 
     * @param taskInt index of task
     * @throws KewgyException 
     **/
    public void deleteTask(int taskInt) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if (!saveFile.exists() || saveFile.isDirectory()) {
                saveFile.createNewFile();

                return;
            }

            if (!saveFile.canWrite()) {
                throw new KewgyException("Cannot write to save file!");
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

            for (String taskstring : tasks) {
                saveFileBufferedWriter.write(taskstring + "\n");
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            ui.printKewgyText("An error occurred.");
            e.printStackTrace();
        } catch (KewgyException e) {
            ui.printError(e.toString());
        }
    }

    /**
     * Save task in save file when task is created by user
     * 
     * @param userMsg Task description
     * @param taskType Type of task
     * @throws KewgyException 
     **/
    public void saveTask(String userMsg, TaskType taskType) {
        try {
            File saveFile = new File(SAVE_FILE);

            // Check if file exists, if not, create new file
            if (!saveFile.exists() || saveFile.isDirectory()) {
                saveFile.createNewFile();
            }

            if (!saveFile.canWrite()) {
                throw new KewgyException("Cannot write to save file!");
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
                    ui.printKewgyText("Unkown Task Type: " + taskType);
                    break;
            }

            saveFileBufferedWriter.close();
        } catch (IOException e) {
            ui.printKewgyText("An error occurred.");
            e.printStackTrace();
        } catch (KewgyException e) {
            ui.printError(e.toString());
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

            if (!saveFile.canRead()) {
                throw new KewgyException("Cannot read save file!");
            }

            Scanner fileReader = new Scanner(saveFile);
            List<Task> savedtasks = new ArrayList<>();

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
                savedtasks.add(savedTask);
            }

            fileReader.close();

            return savedtasks;
        } catch (IOException e) {
            ui.printKewgyText("An error occurred.");
            e.printStackTrace();
        } catch (KewgyException e) {
            ui.printError(e.toString());
        }

        return new ArrayList<>();
    }
}
