package duke;
import duke.Parsers.FileParser;
import duke.Tasks.*;
import duke.Parsers.*;
import duke.Ui;
import duke.Parser;
import duke.Storage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

/**
 * Main class of duke chatbot project. Upon running, creates a new ui to handle interactions with user and a parser to parse instructions
 * from the user. Stores a txt file containing consequences of the instructions using Storage and parses the txt file for future
 * use using the FileParser class.
 */
public class Toothless {

    public static void main(String[] args) {
        String filePath = "data/toothless.txt";
        Ui ui = new Ui();
        Parser parser = new Parser();
        File f = new File(filePath);
        try {
            boolean fileCreated = f.createNewFile();

        } catch (IOException e) {
            System.err.println("Error creating the file: " + e.getMessage());
            e.printStackTrace();
        }
        FileParser fileParser = new FileParser(f);
        try {
            fileParser.parseFile(f);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        TaskList currTaskList = fileParser.getTaskList();
        currTaskList = ui.run(parser, currTaskList);
        Storage storage = new Storage(currTaskList);
        try {
            storage.store();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
