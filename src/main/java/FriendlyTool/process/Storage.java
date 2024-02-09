package FriendlyTool.process;

import FriendlyTool.command.Parser;
import FriendlyTool.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class that manages storing data for future use,
 */
public class Storage {
    /**
     * Loads Tasks to the list from the saved file. If not exists, create a new file in the directory.
     *
     * @param myList lists that will receive tasks.
     * @throws ftException
     */
    public static void loadTask(TaskList myList) throws ftException {
        try {
            File save = new File("./data/myTask.txt");
            if (!save.exists()) {
                save.getParentFile().mkdirs();
                boolean isSuccessful = save.createNewFile();
                UI.createSaveMsg(isSuccessful);
            } else {
                UI.loadSaveMsg();
            }
            Scanner s = new Scanner(save);
            while (s.hasNext()) {
                String[] parsedSave = Parser.parseSave(s.nextLine());
                String taskType = parsedSave[0];
                switch (taskType) {
                    case "T":
                        myList.add(new ToDo(parsedSave[2], Parser.parseBool(parsedSave[1])));
                        break;
                    case "D":
                        myList.add(new Deadline(parsedSave[2], Parser.parseBool(parsedSave[1]), new Date(parsedSave[3])));
                        break;
                    case "E":
                        myList.add(new Event(parsedSave[2], Parser.parseBool(parsedSave[1]), new Date(parsedSave[3]), new Date(parsedSave[4])));
                        break;
                    default:
                        throw new ftException("    Warning: The file is corrupted. Please delete the file");
                }
                s.nextLine();
            }
        } catch (IOException e) {
            throw new ftException("File not found");
        }
    }

    /**
     * Updates the save file based on the given list.
     *
     * @param list given list with tasks.
     * @throws ftException
     */
    public static void updateTask(TaskList list) throws ftException {
        try {
            FileWriter fw = new FileWriter("./data/myTask.txt");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                fw.write(task.toSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new ftException("Error in updating the task");
        }
    }

}
