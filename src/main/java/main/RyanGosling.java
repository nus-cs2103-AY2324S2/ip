package main;

import exceptions.RyanGoslingException;
import utilities.ResponseHandler;
import utilities.Ui;

import java.io.FileNotFoundException;

public class RyanGosling {
    Ui botDispatcher;
    public static String chatBotName = "RyanGosling";

    public RyanGosling() {
        botDispatcher = new Ui();
        assert botDispatcher != null : "Dispatcher object should not be null!";
        botDispatcher.oneTimeLoadAllTasks();
    }


    public String getResponse(String userInput) {
        return botDispatcher.performTaskFromSingleUserInput(userInput);
    }
}
