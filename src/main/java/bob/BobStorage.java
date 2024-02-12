package bob;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles the saving of user tasks.
 */
public class BobStorage {

    private static final String HOME_BASE_PATH = System.getProperty("user.home");
    private static final File SAVE_DATA = new File(HOME_BASE_PATH + "/save.txt");
    private static final String NEW_LINE = System.lineSeparator();

    private BobUI ui;

    public BobStorage(BobUI ui) {
        this.ui = ui;
    }

    /**
     * Add items to storage.
     *
     * @param t A Task object.
     * @param list An ArrayList that contains the tasks.
     * @return a task object that can be further modified.
     */
    public Task addItem(Task t, ArrayList<Task> list) throws BobException {
        list.add(t);
        updateTaskList(list);
        return t;
    }

    /**
     * Updates task list and saves them to a file.
     *
     * @param list An ArrayList that contains the tasks.
     */
    public void updateTaskList(ArrayList<Task> list) {
        try {
            if (!SAVE_DATA.exists()) {
                instantiateDirectory();
            }

            FileWriter fileWriter = new FileWriter(SAVE_DATA, false);
            for (Task t : list) {
                fileWriter.write(t.toSavableFormat() + NEW_LINE);
            }

            fileWriter.close();

        } catch (Exception e) {
            ui.getErrorText(new BobException
                    .FileAccessError(BobErrorMessages.INACCESSIBLE_SAVE_DATA));
        }
    }

    /**
     * Load tasks from saved file.
     *
     * @return List of tasks from the save file.
     * @throws BobException.FileAccessError If file is not accessible.
     * @throws BobException.CorruptedSaveData If save file contains invalid task format.
     */
    public ArrayList<Task> loadSavedTasks() throws BobException.FileAccessError, BobException.CorruptedSaveData {

        ArrayList<Task> savedTasks = new ArrayList<>();

        if (!SAVE_DATA.exists()) {
            instantiateDirectory();
            return savedTasks;
        }

        try {

            BufferedReader br = new BufferedReader(new FileReader(SAVE_DATA));

            String line;

            boolean isInvalidFile;

            boolean isValidTaskType;
            boolean isValidTaskStatus;

            boolean isInvalidTodoFormat;
            boolean isInvalidEventFormat;
            boolean isInvalidDeadlineFormat;

            while ((line = br.readLine()) != null) {

                String[] properties = line.split("\\|");
                String taskType = properties[1];

                isInvalidFile = properties.length < 4;

                isValidTaskType = taskType.equals("T") || taskType.equals("E") || taskType.equals("D");
                isValidTaskStatus = properties[3].equals("false") || properties[3].equals("true");

                isInvalidTodoFormat = taskType.equals("T") && properties.length < 4;
                isInvalidEventFormat = taskType.equals("E") && properties.length < 6;
                isInvalidDeadlineFormat = taskType.equals("D") && properties.length < 5;

                boolean isInvalidTaskFormat = isInvalidTodoFormat
                        || isInvalidEventFormat
                        || isInvalidDeadlineFormat;

                if (!isValidTaskType || !isValidTaskStatus || isInvalidTaskFormat) {
                    isInvalidFile = true;
                }

                if (isInvalidFile) {
                    throw new BobException
                            .CorruptedSaveData(BobErrorMessages.CORRUPT_SAVE_DATA);
                }

                switch (taskType) {
                case "E":
                    addItem(new Event(properties[2], properties[4], properties[5])
                            .setUuid(properties[0])
                            .updateStatus(properties[3].equals("true")), savedTasks);
                    break;
                case "D":
                    addItem(new Deadline(properties[2], properties[4])
                            .setUuid(properties[0])
                            .updateStatus(properties[3].equals("true")), savedTasks);
                    break;
                default:
                    addItem(new Task(properties[2])
                            .setUuid(properties[0])
                            .updateStatus(properties[3].equals("true")), savedTasks);
                    break;
                }
            }
        } catch (IOException e) {
            throw new BobException
                    .FileAccessError(BobErrorMessages.INACCESSIBLE_SAVE_DATA);
        } catch (BobException e) {
            ui.getErrorText(e);
        }

        return savedTasks;
    }

    private void instantiateDirectory() throws BobException.FileAccessError {
        try {
            SAVE_DATA.createNewFile();
        } catch (IOException e) {
            throw new BobException
                    .FileAccessError(BobErrorMessages.INACCESSIBLE_SAVE_DATA);
        }
    }
}
