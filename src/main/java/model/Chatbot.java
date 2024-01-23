package model;

import io.Message;
import io.Outputter;

public class Chatbot {
    public static void IoLoop() {
        Outputter.outputMessage(new Message("Hello! I'm Remi\n" + "What can I do for you?\n"));
        Outputter.outputMessage(new Message("Bye. Hope to see you again soon!\n"));
    }
}
