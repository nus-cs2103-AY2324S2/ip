import java.io.*;

public class Duke {
    private Storage storage;
    private MyList myList;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui(System.in);
        storage = new Storage(filePath);
        try {
            myList = new MyList(storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not Found");
            myList = new MyList();
        } catch (DukeException e) {
            System.out.println("Error: " + e.getMsg());
            myList = new MyList();
        } catch (IOException e) {
            System.err.println("Error reading task from file: " + e.getMessage());
            myList = new MyList();
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        runCommandLoopUntilExitCommand();
        storage.save(myList);
        System.exit(0);
    }

    private void runCommandLoopUntilExitCommand() {
        Parser parser = new Parser();
        Parser.Request request;
        do {
            String userInput = ui.getUserRequest();
            request = parser.getRequest(userInput);
            String result = parser.parseCommand(myList, userInput);
            ui.showResultToUser(result);
        } while (request != Parser.Request.BYE);
    }

    public static void main(String[] args) {
        new Duke("src/main/java/data/duke.txt").run();
    }
}
