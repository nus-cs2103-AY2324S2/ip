package Storage;

import Actions.Action;
import ExceptionHandling.DukeException;
import TaskList.TaskList;
import Tasks.Task;
import UI.Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static Parser.Parser.parseCommand;

public class Storage {
    private final static String FILE_NAME = "src/main/duke.txt";

    public Storage() {
    }

    public void save(TaskList list){
        try{
            FileWriter dest = new FileWriter(Storage.FILE_NAME);
            for (Task t : list.getList()) {
                dest.write(t.getCommand());
            }
            dest.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(Duke chatbot){
        try {
            File f = new File(Storage.FILE_NAME);
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                String command = reader.nextLine();
                String isDone = reader.nextLine();
                Action a = parseCommand(command);
                a.execute(chatbot);
                if (Objects.equals(isDone, "true")) {
                    chatbot.getTaskList().markTask(chatbot.getTaskList().getListSize());
                }
            }
            System.out.println("Loading Done!");
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
    }
}
