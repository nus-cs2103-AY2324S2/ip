import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Luke {

    //Logo created using https://patorjk.com/software/taag/#p=display&f=Varsity&t=Luke
    private static String logo = "  _____             __             \n"
            + " |_   _|           [  |  _         \n"
            + "   | |     __   _   | | / ] .---.  \n"
            + "   | |   _[  | | |  | '' < / /__\\\\ \n"
            + "  _| |__/ || \\_/ |, | |`\\ \\| \\__., \n"
            + " |________|'.__.'_/[__|  \\_]'.__.' ";

    public static void main(String[] args) throws LukeException {
        Storage storage;
        UI ui = new UI();
        ui.greet();

        String wd = System.getProperty("user.dir");
        Path directoryPath = Paths.get(wd,  "data");
        Path historyPath = Paths.get(wd, "data", "history.txt");
        //create directory (if it does not already exist)
        try {
            Files.createDirectory(directoryPath);
        } catch (FileAlreadyExistsException e) {
            //directory already exists.
        } catch (IOException e) {
            System.out.println("Failed to create directory");
        }
        //create file (if it does not already exist)
        try {
            Files.createFile(historyPath);
        } catch (FileAlreadyExistsException e) {
            //file already exists.
        } catch (IOException e) {
            System.out.println("Failed to create file");
            return;
        }

        //load the file if there is save data (reference: https://www.baeldung.com/java-serialization)
        //otherwise, create a new History object.
        File historyFile = new File(String.valueOf(historyPath));
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(historyFile));
            storage = (Storage) inputStream.readObject();
        } catch (IOException e) {
            //System.out.println("No save data found, creating new save.");
            storage = new Storage();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            return;
        }

        Parser parser = new Parser(storage, historyFile);
        Scanner sc = new Scanner(System.in);

        boolean isFinished = false;
        while (!isFinished) {
            //task mode
            //first, determine the type of input.
            String input = sc.nextLine().trim(); //trim removes preceding and trailing whitespace.
            isFinished = parser.parseCommand(input);
        }

        ui.bye();

    }
}


