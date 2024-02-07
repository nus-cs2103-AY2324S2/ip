import action.Action;
import action.ExitAction;
import handler.DataHandler;
import handler.InputHandler;
import handler.PrintHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Nihao {
    public static final Nihao instance = new Nihao();
    private Nihao() {}

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
