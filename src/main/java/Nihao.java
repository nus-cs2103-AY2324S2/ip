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

    private final String GREETINGS = "Hello! I'm Nihao.\nWhat can I do for you?";
    private final String GOODBYE = "Hope to see you again soon. Goodbye!";
    private final PrintHandler printer = PrintHandler.instance;
    private Nihao() {}

    public void run() {
        printer.printWithDivider(LOGO);
        printer.printWithDivider(GREETINGS);
        printer.printWithDivider(GOODBYE);
    }
}
