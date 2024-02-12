package nihao;

import nihao.action.Action;
import nihao.action.ExitAction;
import nihao.handler.InputHandler;
import nihao.handler.PrintHandler;

import java.util.Scanner;

/**
 * Contains the main application logic for the Nihao app.
 */
public final class Nihao {
    public static final Nihao instance = new Nihao();
    private Nihao() {}

    /**
     * Reads use input and executes the main logic.
     */
    public void run() {
        PrintHandler.printInit();

//        File myInput = new File("text-ui-test/input.txt");
//        try {
//            Scanner scanner = new Scanner(myInput);
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
//        } catch (FileNotFoundException e) {
//            PrintHandler.printWithDivider("File not found");
//        }
    }
}
