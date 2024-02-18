package hanxiao.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import hanxiao.Storage;
import hanxiao.TaskList;

/**
 * Class for change data source
 */
public class ChangingDataSource implements Command {
    private boolean status = true;
    private String filePath;

    /**
     * Constructor
     *
     * @param filePath file path.
     * @param storage the current storage.
     * @param tasks current task list.
     */
    public ChangingDataSource(String filePath, Storage storage, TaskList tasks) {
        this.filePath = filePath;
        storage.resetStorage(filePath);
        try {
            tasks.resetTaskList(storage.load());
        } catch (FileNotFoundException e) {
            handleFileNotFound(filePath);
            tasks.resetTaskList(new ArrayList<>());
            this.status = false;
        }
    }

    /**
     * In case the new file path is incorrect
     *
     * @param filePath file path.
     */
    private void handleFileNotFound(String filePath) {
        String[] pathStep = filePath.split("/");
        String progressivePath = "";
        for (int i = 0; i < pathStep.length - 1; i++) {
            String dir = pathStep[i];
            progressivePath = String.format("%s%s/", progressivePath, dir);
        }
        File directory = new File(progressivePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File makeupFile = new File(filePath);
        try {
            makeupFile.createNewFile();
        } catch (IOException ex) {
            System.out.println("Logically it won't happen, but who knows?");
            System.exit(-1);
        }
    }

    /**
     * Reply
     *
     * @return reply.
     */
    @Override
    public String reply() {
        if (status) {
            return String.format("Dear sir, your data source has been changed to %s\n.", this.filePath);
        }
        return "Sir a bad news, file not found. But the good news is we create one for you!\n"
                + "Please feel free to add task now\n";
    }
}
