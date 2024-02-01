import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String FILE_PATH = "./duke.txt";

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        storage.load();
    }

    public void run() {
        ui.showWelcomeMessage();
        userInput();
    }

    private void userInput() {
        //Scanner to scan what the user is inputting
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                String input = scanner.nextLine();
                try {
                    handleInput(input);
                } catch (DukeException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                Storage.saveTaskToHardDisk(tasks.getTasks());
            }
        } finally {
            scanner.close();
        }
    }

    //Method to handle inputs
    private void handleInput(String input) throws DukeException {
        Command command = Parser.getCommand(input.split(" ")[0]);
        switch (command) {
            case BYE:
                ui.showGoodbyeMessage();
                System.exit(0);
                break;
            case LIST:
                tasks.listTasks();
                break;
            case MARK:
                tasks.markTask(input);
                break;
            case UNMARK:
                tasks.unmarkTask(input);
                break;
            case EVENT:
                tasks.addEventTask(input);
                break;
            case DEADLINE:
                tasks.addDeadlineTask(input);
                break;
            case TODO:
                tasks.addTodoTask(input);
                break;
            case DELETE:
                tasks.deleteTask(input);
                break;
            case UNKNOWN:
                throw new DukeException("Sorry, I do not understand that command. Please try again.");
        }
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }
}