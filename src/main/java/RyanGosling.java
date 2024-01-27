import exceptions.RyanGoslingException;
import utilities.MessagePrinter;
import utilities.Ui;

import java.io.FileNotFoundException;

public class RyanGosling {
    public static String chatBotName = "RyanGosling";

    public static void main(String[] args) throws RyanGoslingException, FileNotFoundException {
        MessagePrinter.greeting(chatBotName);
        //We begin listening
        Ui botDispatcher = new Ui();
        botDispatcher.chatListener();
    }
}
