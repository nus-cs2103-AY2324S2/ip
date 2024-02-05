import action.Action;
import handler.DataHandler;
import handler.InputHandler;
import handler.PrintHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class Nihao {
    public static final Nihao instance = new Nihao();
    private static final String LOGO = "::::    ::: ::::::::::: :::    :::     :::      ::::::::  \n" +
        ":+:+:   :+:     :+:     :+:    :+:   :+: :+:   :+:    :+: \n" +
        ":+:+:+  +:+     +:+     +:+    +:+  +:+   +:+  +:+    +:+ \n" +
        "+#+ +:+ +#+     +#+     +#++:++#++ +#++:++#++: +#+    +:+ \n" +
        "+#+  +#+#+#     +#+     +#+    +#+ +#+     +#+ +#+    +#+ \n" +
        "#+#   #+#+#     #+#     #+#    #+# #+#     #+# #+#    #+# \n" +
        "###    #### ########### ###    ### ###     ###  ########  ";

    private final String GREETINGS = "Hello! I'm Nihao.\nI'm lazy and I don't want to do anything for you.";
    private final String GOODBYE = "Hope to never see you again. Goodbye!";
    private final PrintHandler printHandler = PrintHandler.instance;
    private final DataHandler dataHandler = DataHandler.instance;
    private final InputHandler inputHandler = InputHandler.instance;
    private Nihao() {}

    public void run() {
        printHandler.printWithDivider(LOGO);
        printHandler.printWithDivider(GREETINGS);

        File myInput = new File("text-ui-test/input.txt");
//        try {
//            Scanner scanner = new Scanner(myInput);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("bye")) {
                    printHandler.printWithDivider(GOODBYE);
                    break;
                }

                try {
                    Action action = inputHandler.handleInput(input);
                    action.execute();
                } catch (Exception e) {
                    printHandler.printException(e);
                }
            }
            scanner.close();
//        } catch (FileNotFoundException e) {
//            printHandler.printWithDivider("File not found");
//        }
    }
}
