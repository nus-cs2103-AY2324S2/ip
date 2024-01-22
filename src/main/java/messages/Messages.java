package messages;

public class Messages {
    public static final String BANTER_LOGO = ".______        ___      .__   __. .___________. _______ .______      \n" +
            "|   _  \\      /   \\     |  \\ |  | |           ||   ____||   _  \\     \n" +
            "|  |_)  |    /  ^  \\    |   \\|  | `---|  |----`|  |__   |  |_)  |    \n" +
            "|   _  <    /  /_\\  \\   |  . `  |     |  |     |   __|  |      /     \n" +
            "|  |_)  |  /  _____  \\  |  |\\   |     |  |     |  |____ |  |\\  \\----.\n" +
            "|______/  /__/     \\__\\ |__| \\__|     |__|     |_______|| _| `._____|\n" +
            "                                                                     \n";

    public static final String GREET_MESSAGE_BODY = "Hello! I'm Banter\n" +
            "What can I do for you?";

    public static final String EXIT_MESSAGE_BODY = "Bye. Hope to see you again soon!";


    // Messages
    public static final MessageBox GREET_MESSAGE = new MessageBox(GREET_MESSAGE_BODY);

    public static final MessageBox EXIT_MESSAGE = new MessageBox(EXIT_MESSAGE_BODY);
}
