package model;

import io.Inputter;
import io.Message;
import io.Outputter;

public class Chatbot {

    private static boolean exitLoop = false;
    public static void ioLoop() {
        Outputter.outputMessage(new Message("Hello! I'm Remi\n" + "What can I do for you?\n");

        while (!exitLoop) {
            Message msg = Inputter.inputMessage();

            if (exitLoop)
                break;

        }
    }

    public static void
}
