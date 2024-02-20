package panda.component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import panda.command.AlterMarkCommand;
import panda.command.Command;
import panda.command.NewTaskCommand;
import panda.command.ModifyTagCommand;
import panda.exception.CorruptedFileException;
import panda.exception.PandaException;
import panda.task.Deadline;
import panda.task.Event;
import panda.task.Todo;

import java.util.ArrayList;

public class Storage {
    private File cacheFile;
    
    /**
     * Constructs a new Storage.
     * 
     * @param filePath the path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        cacheFile = new File(filePath);
        if (!cacheFile.exists()) {
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }   
        }
    }

    /**
     * Loads tasks from the file.
     * 
     * @return a list of commands representing the tasks loaded from the file.
     * @throws PandaException if an error occurs during loading.
     */
    public ArrayList<Command> load() throws PandaException {
        ArrayList<Command> clist = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(cacheFile);
            int idx = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] parts = data.split("\\|");
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                String desc = parts[2].split("#", 2)[0].trim();
                String[] tags = parts[2].split("#", 2).length > 1 
                        ? parts[2].split("#", 2)[1].trim().split(" ")
                        : new String[0];
                if(parts[0].equals("T")) {
                    clist.add(new NewTaskCommand(new Todo(desc)));
                } else if(parts[0].equals("D")) {
                    clist.add(new NewTaskCommand(new Deadline(desc, parts[3])));
                } else if(parts[0].equals("E")) {
                    clist.add(new NewTaskCommand(new Event(desc,  parts[3], parts[4])));
                } else {
                    myReader.close();
                    throw new CorruptedFileException();
                }
                if(parts[1].equals("1")) clist.add(new AlterMarkCommand(idx, true));
                for(String tag : tags) {
                    clist.add(new ModifyTagCommand(idx, tag, true));
                }
                idx = idx + 1;
            }
            myReader.close();
        } catch (IOException e) {
            throw new CorruptedFileException();
        }
        return clist;
    }

    /**
     * Saves tasks to the file.
     * 
     * @param tlist the TaskList to save to the file.
     */
    public void save(TaskList tlist) {
        try (FileWriter writer = new FileWriter(cacheFile.getAbsolutePath(), false)) {
            writer.write(tlist.toSaveString());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
