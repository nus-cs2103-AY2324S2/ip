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
    private final PrintHandler printer = PrintHandler.instance;
    private Nihao() {}

    public void run() {
        printer.printWithDivider(LOGO);
        printer.printWithDivider(GREETINGS);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                printer.printWithDivider(GOODBYE);
                break;
            }
            printer.printWithDivider(input);
        }

    }
}
