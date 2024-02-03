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


    public String getResponse(String userInput) {
        return botDispatcher.performTaskFromSingleUserInput(userInput);
    }
}
