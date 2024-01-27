import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A chatbot class named as MissA.
 * Records 3 types of tasks for users.
 */
public class MissA {
    private TaskList tasks;
    private Ui ui = new Ui();
    private Storage storage;
    private Parser parser = new Parser();
    private boolean isBye = false;

    public MissA(String filePath) {
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (WrongTaskDataException | FileNotFoundException e) {
            System.out.println(ui.showError(e));
            tasks = new TaskList();
        }
    }

    /**
     * Starts the MissA chatbot.
     */
    public void run() {
        // Greets users.
        System.out.println(ui.sayHi());

        while (!isBye) {
            try {
                // Collects user input.
                String userInput = ui.readInput();
                Command command = parser.parse(userInput, tasks);
                tasks = command.execute(ui);
                isBye = command.isBye();
            } catch (IncorrectTaskTypeException
                     | NoSuchTaskException
                     | NoTimingException
                     | NoContentException e) {
                System.out.println(ui.showError(e));
            }
        }

        // Writes back to data file.
        try {
            String newData = tasks.getUpdatedData();
            storage.writeBackData(newData);
        } catch (IOException e) {
            System.out.println("Sorry, I am unable to update data file.");
        }

        // Exits program.
        System.out.println(ui.goodBye());
    }

    /**
     * Starts communication with chatbot MissA.
     */
    public static void main(String[] args) {
        MissA missA = new MissA("src/main/java/data/tasks.txt");
        missA.run();
    }
}
