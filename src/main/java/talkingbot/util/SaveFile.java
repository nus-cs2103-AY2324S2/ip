package talkingbot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import talkingbot.exception.TalkingBotException;
import talkingbot.task.Task;

public class SaveFile {
    private final File saveFile;
    private final String path;

    public SaveFile(String path)  {
        this.saveFile = new File(path);
        this.path = path;
        if (!this.saveFile.exists()) {
            try {
                this.saveFile.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public TaskList getTasksFromFile() throws TalkingBotException {
        try {
            Scanner fileScanner = new Scanner(this.saveFile);
            TaskList taskList = new TaskList();
            while (fileScanner.hasNextLine()) {
                String curLine = fileScanner.nextLine();
                taskList.addTask(Task.generateTaskFromFile(curLine));
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new TalkingBotException("ERROR! File not found!");
        } catch (Exception e) {
            throw new TalkingBotException("ERROR! Cannot read from file!");
        }
    }

    public void saveTasksToFile(TaskList taskList) throws TalkingBotException {
        try {
            FileWriter fileWriter = new FileWriter(this.saveFile);
            StringBuilder strBuild = new StringBuilder();
            for (int i = 0; i < taskList.getSize(); i++) {
                Task curTask = taskList.getTask(i);
                strBuild.append(curTask.getSaveFileString());
                if (i < taskList.getSize() - 1) {
                    strBuild.append("\n");
                }
            }
            fileWriter.write(strBuild.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new TalkingBotException("ERROR! IOException occurred!");
        }
    }

    public String getFileName() {
        return this.path;
    }
}
