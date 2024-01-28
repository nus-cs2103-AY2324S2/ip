package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private String filePath; 

    public Storage(String filePath) {
        this.filePath = filePath; 

        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                new DukeException(e.getMessage());
            }
        }
    }

    // save task list into the file again
    public void saveTaskList(ArrayList<Task> instrList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath); 
            for (Task tsk : instrList) {
                fw.write(tsk.toSave()); 
                fw.write("\n"); 
            }
            fw.close(); 
        } catch (IOException e) {
            throw new DukeException("OOOPS! Invalid Item in Task List, please rectify!!!"); 
        }
    } 

    // load out task list 
    public ArrayList<Task> loadTaskList() throws DukeException {
        ArrayList<Task> tskList = new ArrayList<>(); 
        File f = new File(this.filePath);
        try {
            if (f.exists()) {
                try (Scanner sc = new Scanner(f)) {
                    while (sc.hasNext()) {
                        Task tsk = deconstruct(sc.nextLine()); 
                        tskList.add(tsk); 
                    }
                }
            } else {
                throw new DukeException("OOOPS!! File '" + this.filePath + "' not found in local drive. Please check your working folder!");  
            }
        } catch (IOException e) {
            throw new DukeException("An error occurred while reading the file: " + e.getMessage());
        }
        
        return tskList; 
    }    

    public Task deconstruct(String fileStr) {
        String[] arr = fileStr.split("|"); 
        Boolean isDone = arr[1].equals("1") ? true : false;
        if (arr[0].equals("T")) {
            return new Todo(arr[2], isDone); 
        } else if (arr[0].equals("D")) {
            return new Deadline(arr[2], arr[3], isDone);
        } else if (arr[0].equals("E")) {
            return new Event(arr[2], arr[3], arr[4], isDone); 
        }
        return null; 
    }
}