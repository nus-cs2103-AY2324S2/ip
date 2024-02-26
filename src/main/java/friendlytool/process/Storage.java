package friendlytool.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import friendlytool.command.Parser;
import friendlytool.task.Deadline;
import friendlytool.task.Event;
import friendlytool.task.Task;
import friendlytool.task.ToDo;

/**
 * A class that manages storing data for future use,
 */
public class Storage {

    /**
     * Loads Tasks to the list from the saved file. If not exists, create a new file in the directory.
     *
     * @param myList lists that will receive tasks.
     * @throws FtException if file is corrupted or missing
     */
    public static void loadTask(TaskList myList) throws FtException {
        Scanner s;
        try {
            File save = new File("./data/myTask.txt");
            if (!save.exists()) {
                save.getParentFile().mkdirs();
                boolean isSuccessful = save.createNewFile();
                Ui.getCreateSaveMsg(isSuccessful);
            } else {
                Ui.getLoadSaveMsg();
            }
            s = new Scanner(save);
        } catch (IOException e) {
            throw new FtException("File not found");
        }
        while (s.hasNext()) {
            String[] parsedSave = Parser.parseSave(s.nextLine());
            String taskType = parsedSave[0];
            switch (taskType) {
            case "T":
                myList.add(new ToDo(parsedSave[2], Parser.parseBool(parsedSave[1])));
                break;
            case "D":
                myList.add(new Deadline(parsedSave[2], Parser.parseBool(parsedSave[1]),
                        new Date(parsedSave[3])));
                break;
            case "E":
                myList.add(new Event(parsedSave[2], Parser.parseBool(parsedSave[1]), new Date(parsedSave[3]),
                        new Date(parsedSave[4])));
                break;
            default:
                throw new FtException("    Warning: The file is corrupted. Please delete the file");
            }
            s.nextLine();
        }
    }

    /**
     * Updates the save file based on the given list.
     *
     * @param list given list with tasks.
     * @throws FtException if file is not updated
     */
    public static void updateTask(TaskList list) throws FtException {
        try {
            FileWriter fw = new FileWriter("./data/myTask.txt");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                fw.write(task.toSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new FtException("Error in updating the task");
        }
    }

}
