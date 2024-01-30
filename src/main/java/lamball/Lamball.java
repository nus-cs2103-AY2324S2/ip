package lamball;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Lamball {
    public static String indent = "    ____________________________________________________________\n";
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Lamball chatbot.
     *
     */
    public Lamball() {
        tasks = new TaskList();
        ui = new Ui();
    }

    private void initialize() {
        ui.greetingMessage();
        Storage.obtainSavedFile(this);
    }

    /**
     * Constructor for Event task.
     *
     * @param msg Command to parse.
     * @param isInit If the parse is during initialization phase
     * @throws LamballParseException if invalid command is provided.
     */
    public void initParse(String msg, boolean isInit) throws LamballParseException {
        String[] comd = Parser.parse(msg);
        tasks.runComd(comd, isInit);
    }

    /**
     * Runs Lamball chatbot.
     *
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isActive = true;

        this.initialize();

        while (isActive) {
            System.out.print("    You:");
            String userInput = scanner.nextLine();

            // Echo the user's command
            try {
                String[] comd = Parser.parse(userInput);
                isActive = tasks.runComd(comd, false);
                if (!isActive) {
                    ui.goodbyeMessage();
                }
            } catch (LamballParseException e) {
                ui.displayError(e);
            }

        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Lamball().run();
    }
}


