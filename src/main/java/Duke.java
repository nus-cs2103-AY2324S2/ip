import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
/**
 * Main Class for our Chat bot
 */
public class Duke {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            String[] pathStep = filePath.split("/");
            String progressivePath = "";
            for (int i=0; i<pathStep.length-1;i++) {
                String dir = pathStep[i];
                progressivePath = String.format("%s%s/",progressivePath,dir);
            }
            File directory = new File(progressivePath);
            if (!directory.exists()){
                directory.mkdirs();
            }
            File makeupFile = new File(filePath);
            try {
                makeupFile.createNewFile();
            } catch (IOException ex) {
                System.out.println("Logically it won't happen, but who knows?");
                System.exit(-1);
            }
            tasks = new TaskList(new ArrayList<>());
        }
        parser = new Parser(tasks);
    }

    public void run() {
        ui.showWelcome();
        while (true) {
            try{
                String fullCommand = ui.readCommand();
                Command cmd = parser.parse(fullCommand);
                if (cmd instanceof Bye) {
                    ui.sayGoodbye();
                }
                ui.printReply(cmd.reply());
            } catch (DukeException e) {
                System.out.printf("    %s\n",e.getMessage());
            }
            try{
                storage.writeToFile(tasks.getTaskList());
            } catch (IOException e) {
                System.out.println("Why delete the file when program running?");
                System.exit(-1);
            }

        }
    }
    /**
     * Main method
     * @param args command line arguments
     */
    public static void main(String[] args) {
        new Duke("data/tasklist.txt").run();
    }


}
