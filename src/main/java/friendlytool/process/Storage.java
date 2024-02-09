package friendlytool.process;

import friendlytool.command.Parser;
import friendlytool.task.*;

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
     * @throws FTException
     */
    public static void loadTask(TaskList myList) throws FTException {
        try {
            File save = new File("./data/myTask.txt");
            if (!save.exists()) {
                save.getParentFile().mkdirs();
                boolean isSuccessful = save.createNewFile();
                UI.printCreateSaveMsg(isSuccessful);
            } else {
                UI.printLoadSaveMsg();
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
                    myList.add(new Deadline(parsedSave[2], Parser.parseBool(parsedSave[1]),
                            new Date(parsedSave[3])));
                    break;
                case "E":
                    myList.add(new Event(parsedSave[2], Parser.parseBool(parsedSave[1]), new Date(parsedSave[3]),
                            new Date(parsedSave[4])));
                    break;
                default:
                    throw new FTException("    Warning: The file is corrupted. Please delete the file");
                }
                s.nextLine();
            }
        } catch (IOException e) {
            throw new FTException("File not found");
        }
    }

    /**
     * Updates the save file based on the given list.
     *
     * @param list given list with tasks.
     * @throws FTException
     */
    public static void updateTask(TaskList list) throws FTException {
        try {
            FileWriter fw = new FileWriter("./data/myTask.txt");
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                fw.write(task.toSaveFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new FTException("Error in updating the task");
        }
    }

}
