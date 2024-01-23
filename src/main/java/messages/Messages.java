package messages;

import commands.CommandType;

public class Messages {
    public static final String BANTER_LOGO = ".______        ___      .__   __. .___________. _______ .______\n" +
            "|   _  \\      /   \\     |  \\ |  | |           ||   ____||   _  \\\n" +
            "|  |_)  |    /  ^  \\    |   \\|  | `---|  |----`|  |__   |  |_)  |\n" +
            "|   _  <    /  /_\\  \\   |  . `  |     |  |     |   __|  |      /\n" +
            "|  |_)  |  /  _____  \\  |  |\\   |     |  |     |  |____ |  |\\  \\----.\n" +
            "|______/  /__/     \\__\\ |__| \\__|     |__|     |_______||__| `._____|\n";
    public static final String EXIT_MESSAGE_BODY = "Bye. Hope to see you again soon!";
    public static final String TODO_USAGE = "todo <description>";
    public static final String DEADLINE_USAGE = "deadline <description> /by <due date>";
    public static final String EVENT_USAGE = "event <description> /from <start date> /to <end date>";
    public static final String MARK_USAGE = "mark <task number>";
    public static final String UNMARK_USAGE = "unmark <task number>";
    public static final String DELETE_USAGE = "delete <task number>";
    public static final String COMMAND_USAGE = "This is what Banter can do:\n" +
            CommandType.BYE + "\n" +
            CommandType.LIST + "\n" +
            MARK_USAGE + "\n" +
            UNMARK_USAGE + "\n" +
            TODO_USAGE + "\n" +
            DEADLINE_USAGE + "\n" +
            EVENT_USAGE + "\n" +
            DELETE_USAGE;
    public static final String GREET_MESSAGE_BODY = "Hello! I'm Banter\n" +
            "What can I do for you?\n\n" +
            COMMAND_USAGE;


    // Messages
    public static final MessageBox GREET_MESSAGE = new MessageBox(GREET_MESSAGE_BODY);
    public static final MessageBox EXIT_MESSAGE = new MessageBox(EXIT_MESSAGE_BODY);
}
