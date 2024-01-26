import java.io.*;
import java.sql.Array;
import java.util.ArrayList;

public class BobStorage {

    private static final String HOME_BASE_PATH = System.getProperty("user.home");
    private static final File saveData = new File(HOME_BASE_PATH + "/save.txt");
    private static final String NEW_LINE = System.lineSeparator();

    /**
     * Add items to storage.
     */
    public Task addItem(Task t, ArrayList<Task> list) throws BobException {
        list.add(t);
        this.updateTaskList(list);
        return t;
    }

    public void updateTaskList(ArrayList<Task> list) {
        try {
            if (!this.saveData.exists()) {
                this.instantiateDirectory();
            }
            FileWriter fileWriter = new FileWriter(this.saveData, false);
            for (Task t : list) {
                fileWriter.write(t.toSavableFormat() + this.NEW_LINE);
            }
            fileWriter.close();

        } catch (Exception e) {
            System.out.println("An error occurred when trying to access the save file. "
                    + "Please ensure that the application has permissions to write and read from your HOME directory.");
        }
    }

    public ArrayList<Task> loadSavedTasks() throws BobException.FileAccessError, BobException.CorruptedSaveData {

        ArrayList<Task> savedTasks = new ArrayList<>();

        try {

            if (!this.saveData.exists()) {
                this.instantiateDirectory();
                return new ArrayList<Task>();
            }

            try (BufferedReader br = new BufferedReader(new FileReader(this.saveData))) {

                String line;
                boolean invalidFile = false;

                while ((line = br.readLine()) != null) {

                    String[] properties = line.split("\\|");

                    if (properties.length < 4) {
                        invalidFile = true;
                    }

                    String taskType = properties[1];

                    if (!invalidFile
                            && !(taskType.equals("T")
                            || taskType.equals("E")
                            || taskType.equals("D"))) {
                        invalidFile = true;
                    }

                    if (!invalidFile && !(properties[3].equals("false")
                            || properties[3].equals("true"))) {
                        invalidFile = true;
                    }

                    if (invalidFile) {
                        throw new BobException
                                .CorruptedSaveData("Save file is corrupt. "
                                + "The application will create a new save file.");
                    }

                    if (taskType.equals("T") && properties.length < 4
                            || taskType.equals("E") && properties.length < 6
                            || taskType.equals("D") && properties.length < 5) {
                        throw new BobException
                                .CorruptedSaveData("Tasks are corrupt. The application will create a new save file.");
                    }

                    try {

                        switch (taskType) {
                        case "E":
                            this.addItem(new Event(properties[2], properties[4], properties[5])
                                    .setUuid(properties[0])
                                    .updateStatus(properties[3].equals("true")), savedTasks);
                            break;
                        case "D":
                            this.addItem(new Deadline(properties[2], properties[4])
                                    .setUuid(properties[0])
                                    .updateStatus(properties[3].equals("true")), savedTasks);
                            break;
                        default:
                            this.addItem(new Task(properties[2])
                                    .setUuid(properties[0])
                                    .updateStatus(properties[3].equals("true")), savedTasks);
                            break;
                        }

                    } catch (BobException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (BobException.FileAccessError e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new BobException
                    .FileAccessError("An error occurred when trying to access the save file. "
                    + "Please ensure that the application has permissions to write and read from your HOME directory.");
        }

        return savedTasks;
    }

    private void instantiateDirectory() throws BobException.FileAccessError {
        try {
            this.saveData.createNewFile();
        } catch (IOException e) {
            throw new BobException.FileAccessError("An error occurred when trying to access the save file. "
                    + "Please ensure that the application has permissions to write and read from your HOME directory.");
        }
    }
}
