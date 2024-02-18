package nihao;

import java.util.Scanner;

import nihao.action.Action;
import nihao.action.ExitAction;
import nihao.handler.InputHandler;
import nihao.handler.PrintHandler;

/**
 * Contains the main application logic for the Nihao app.
 */
public final class Nihao {
    public static final Nihao INSTANCE = new Nihao();
    private Nihao() {}

    /**
     * Reads use input and executes the main logic.
     */
    public void run() {
        PrintHandler.printInit();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                Action action = InputHandler.handleInput(input);
                action.execute();
                if (action instanceof ExitAction) {
                    break;
                }
            } catch (Exception e) {
                PrintHandler.printException(e);
            }
        }
        scanner.close();
    }
}
