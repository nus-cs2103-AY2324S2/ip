package hanxiao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import hanxiao.command.Command;
import hanxiao.exception.HanxiaoException;

/**
 * Main Class for our Chat bot
 */
public class Hanxiao {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for chatbot.
     *
     * @param filePath file to load.
     */
    public Hanxiao(String filePath) {
        assert filePath.length() > 0 : "file path should not be null";
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            handleFileNotFound(filePath);
            tasks = new TaskList(new ArrayList<>());
        }
        parser = new Parser(tasks, storage);
    }

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

    protected String getResponse(String input) {
        try {
            String fullCommand = input;
            Command cmd = parser.parse(fullCommand);
            String tmp = cmd.reply();
            storage.writeToFile(tasks.getTaskList());
            return tmp;
        } catch (HanxiaoException | IOException e) {
            return String.format("%s\n", e.getMessage());
        }
    }
}
