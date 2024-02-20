package signal;

import signal.task.*;
import signal.util.Parser;
import signal.util.Storage;
import signal.util.Ui;

import java.io.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Duke {
    private static Storage fileManager = new Storage();
    private static ArrayList<Task> taskList = fileManager.loadTasks();
    private static Ui ui = new Ui(taskList, fileManager);
    public static Parser parser = new Parser(taskList, ui);


    public static void main(String[] args) {
        ui.intro();


        while(true) {
            String userInput = ui.scan();
            String[] inputArray = userInput.split(" ");

            if (userInput.equals("bye")) {
                // Exit program
                break;
            }
            parser.read(userInput);
        }
        ui.leave();
    }
}

