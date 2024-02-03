package main;

import exceptions.RyanGoslingException;
import utilities.ResponseHandler;
import utilities.Ui;

import java.io.FileNotFoundException;

public class RyanGosling {
    Ui botDispatcher;
    public static String chatBotName = "main.RyanGosling";

    public RyanGosling() {
        botDispatcher = new Ui();
        botDispatcher.oneTimeLoadAllTasks();
    }

    public static void main(String[] args) throws RyanGoslingException, FileNotFoundException {
        ResponseHandler.greeting(chatBotName);
        //We begin listening
        Ui botDispatcher = new Ui();
        botDispatcher.chatListener();
    }

    public String getResponse(String userInput) {
        return botDispatcher.performTaskFromSingleUserInput(userInput);
    }
}
