import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveFile {
    private final File saveFile;
    private final String path;

    public SaveFile(String path) throws DukeException {
        this.saveFile = new File(path);
        this.path = path;
        if (!this.saveFile.exists()) {
            try {
                this.saveFile.createNewFile();
            } catch (IOException e) {
                throw new DukeException("ERROR! IOException occurred!");
            }
        }
    }

    public SaveFile() throws DukeException {
        this("./data/duke.txt");
    }

    public TaskList getTasksFromFile() throws DukeException {
        try {
            Scanner fileScanner = new Scanner(this.saveFile);
            TaskList taskList = new TaskList();
            while (fileScanner.hasNextLine()) {
                String curLine = fileScanner.nextLine();
                taskList.addTask(Task.generateTaskFromFile(curLine));
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("ERROR! File not found!");
        }
    }

    public void saveTasksToTile(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.saveFile);
            StringBuilder strBuild = new StringBuilder();
            for (int i = 0; i < taskList.getSize(); i++) {
                Task curTask = taskList.getTask(i);
                boolean isDone = curTask.getDone();
                int mark = isDone ? 1 : 0;
                if (curTask.getTypeEquals(TaskType.TODO)) {
                    strBuild.append(String.format("T | %d | %s", mark,
                            curTask.getDescription()));
                } else if (curTask.getTypeEquals(TaskType.DEADLINE)) {
                    strBuild.append(String.format("D | %d | %s | %s", mark,
                            curTask.getDescription(), curTask.getEndTime()));
                } else {
                    strBuild.append(String.format("E | %d | %s | %s | %s", mark,
                            curTask.getDescription(), curTask.getStartTime(),
                            curTask.getEndTime()));
                }
            }
            fileWriter.write(strBuild.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("ERROR! IOException occurred!");
        }
    }
}
