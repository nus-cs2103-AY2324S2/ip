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
    }

    /**
     * From input to determine which type of command to generate
     * @param input scanner's result
     * @throws DukeException wrong usage
     * @return the generated command for execute
     */
    private Command commandDistributor(String input) throws DukeException{
        String[] inputs = input.split(" ");
        if (input.equals("bye") || input.equals("exit") || input.equals("quit")) {
            return new Bye();
        } else if (input.equals("list") || input.equals("ls")) {
            return new List(tasks);
        } else if (input.equals("current") || input.equals("curr")) {
            return new CurrentTask(tasks);
        } else if (inputs[0].equals("mark") && inputs.length==2) {
            return new Mark(Integer.parseInt(inputs[1])-1,tasks);
        } else if (inputs[0].equals("unmark") && inputs.length==2) {
            return new Unmark(Integer.parseInt(inputs[1])-1,tasks);
        } else if ((inputs[0].equals("delete") || inputs[0].equals("remove")) && inputs.length==2) {
            return new Delete(Integer.parseInt(inputs[1])-1,tasks);
        } else {
            return new Add(input,tasks);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("    Hello! I'm Hanxiao.\n  What can I do for you?");

        while (true) {
            try{
                Command cmd = commandDistributor(sc.nextLine());
                cmd.reply();
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
