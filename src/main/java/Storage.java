import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;
import YapchitExceptions.UnavailableListException;
import YapchitExceptions.YapchitException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }

    public TaskList importFromFile(String filePath) throws YapchitException {
        File f = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e){
            throw new UnavailableListException("Could not locate existing file list");
        }

        while (s.hasNext()) {
            String input = s.nextLine();
            String[] parts = input.split(" ");

            try {
                Yapchit.Operations k = Yapchit.Operations.valueOf(parts[0].toUpperCase());
                switch (k) {
                    case EVENT:
                        this.handleEvent(input, false);
                        break;
                    case DEADLINE:
                        this.handleDeadline(input, false);
                        break;
                    case TODO:
                        this.handleTodo(input, false);
                        break;
                }
            } catch (Exception e){
                Ui.print("Error in parsing file. Some of the contents may be corrupted");
                break;
            }
        }

    }

    private void updateFile(String filePath){
        String toWrite = "";
        for(Task t : list){
            if(t instanceof ToDo){
                toWrite = toWrite + "todo "+ t.getName() + (t.getTag() == true ? "1" : "0") + "\n";
            }

            if(t instanceof Event){
                toWrite = toWrite
                        + "event "+ t.getName()
                        + " /from " + ((Event) t).getFrom()
                        + " /to " + ((Event) t).getTo()
                        +(t.getTag() == true ? "1" : "0") + "\n";
            }

            if(t instanceof Deadline){
                toWrite = toWrite
                        + "event "+ t.getName()
                        + " /by " + ((Deadline) t).getBy()
                        +(t.getTag() == true ? "1" : "0") + "\n";
            }
        }
        File f = new File(filePath);


        File dirCheck = f.getParentFile();
        if(!dirCheck.exists()){
            dirCheck.mkdirs();
        }

        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e){
            Ui.print("Error in creating file. " + e.getMessage());
        }

        try{
            this.writeToFile(filePath, toWrite);
        } catch (IOException e){
            Ui.print(e.getMessage());
        }

    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }


}
