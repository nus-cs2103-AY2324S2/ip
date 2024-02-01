import java.util.Scanner;

public final class Nihao {
    public static final Nihao instance = new Nihao();
//    private static final String LOGO =
//            " ____        _        \n"
//            + "|  _ \\ _   _| | _____ \n"
//            + "| | | | | | | |/ / _ \\\n"
//            + "| |_| | |_| |   <  __/\n"
//            + "|____/ \\__,_|_|\\_\\___|\n";
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
    private Nihao() {}

    public void run() {
        printHandler.printWithDivider(LOGO);
        printHandler.printWithDivider(GREETINGS);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printHandler.printWithDivider(GOODBYE);
                break;
            }
            if (input.equals("list")) {
                printHandler.printNumberedDivider(dataHandler.getData());
            } else {
                dataHandler.handleData(input);
                printHandler.printWithDivider("added: " + input);
            }
        }

    }
}
