import java.util.Scanner;
import java.io.IOException;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static TaskList TASKS;
    private static final String STORAGE_PATH = "./cappy.csv";
    private static final Ui UI = new Ui();
    private static Storage storage;

    public static void main(String[] args) {
        UI.showBanner();
        UI.showGreetings();
        try {
            storage = new Storage(STORAGE_PATH);
            TASKS = TaskList.load(storage);
            inputLoop();
            storage.close();
        } catch (IOException e) {
            UI.showError(e.getMessage());
            Duke.SCANNER.close();
            System.exit(1);
        } catch(DukeException e) {
            UI.showError(e.getMessage());
            Duke.SCANNER.close();
            System.exit(1);
        } finally {
            Duke.SCANNER.close();
        }
    }

    private static void inputLoop() {
        String input = "";
        while (true) {
            input = SCANNER.nextLine();
            try {
                ParsedInput parsedInput = Parser.parse(input);
                parsedInput.executeCommand(TASKS, UI, storage);
                if (parsedInput.getCommandType() == CommandType.BYE) {
                    break;
                }
            } catch (DukeException e) {
                UI.showError(e.getMessage());
            } catch (IOException e) {
                UI.showError(e.getMessage());
            }
        }
    }
}
