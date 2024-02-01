import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Main code for running Steven
 */
public class Steven {

    private Storage storage;
    private TaskList tasks;
    private static final String line = "========\n";
    private static final String bootMsg = ("This is Steven!\nHow can I advise?\n");

    /**
     * Constructor class for Steven
     */
    public Steven() {
        this.storage = new Storage();
        this.tasks = new TaskList(storage);
        try {
            new File("./data").mkdirs();
            File newFile = new File("./data/Steven.txt");
            if (!newFile.createNewFile()) {
                tasks.readFile(newFile);
            }
        } catch (IOException e) {
            System.out.println("Whoops, looks like something went wrong, it really shouldn't!");
            e.printStackTrace();
        }
        System.out.print(line + bootMsg + line);
        System.out.println("Steven's advice: Don't know what commands I understand? Use \"help\"!");
        System.out.print(line);
    }

    /**
     * Driver code for Steven. It will run the entire program
     */
    public void run() {
        boolean exit = false;
        while (!exit) {
            Scanner Input = new Scanner(System.in);
            while (Input.hasNextLine()) {
                UserInput command = new UserInput(Input.nextLine());
                exit = new Parser(command, this.tasks).processInput();
                if (exit) {
                    System.out.print(line);
                    break;
                }
            }
        }
        System.out.println("I'll see you soon then!\n" + line);
    }
    public static void main(String[] args) {
        new Steven().run();
    }
}
