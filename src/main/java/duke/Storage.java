package duke;

import duke.contacts.Contacts;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;


import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a Storage system which saves and loads tasks in a File f.
 */
public class Storage {
    private File f;
    static final String FILE_NOT_FOUND = "file not found! try again xx";

    public Storage (File f) {
        this.f = f;
    }
//    public Storage (File f, ArrayList<Task> tasklist) {
//        this.tasklist = tasklist;
//        this.f = f;
//    }

    /**
     * Gets the File content of f.
     *
     * @throws FileNotFoundException When data file doesn't exist.
     */
    public String getTasksContent() throws FileNotFoundException {
        String str = "";
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                str += "    " + s.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            return "file not found! try again xx";
        }

        return str;
    }

    public String getContactsContent(ArrayList<Contacts> contactList) throws FileNotFoundException {
        String str = "";
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                str += "    " + s.nextLine() + "\n";
            }

            for (int i = 0; i < contactList.size(); i++) {
                str += "    " + contactList.get(i).contacting() + "\n";
            }
        } catch (FileNotFoundException e) {
            return "file not found! try again xx";
        }

        return str;
    }

    /**
     * Getter function that returns the File f.
     *
     * @return File f.
     */
    public File getFile() {
        return f;
    }

    public void getSavedTasks(ArrayList<Task> tasklist) throws FileNotFoundException {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                 String input = s.nextLine();
                 String[] str = input.split("/");
                 if (str.length == 3) {
                     Todo todo = new Todo(str[2]);
                     if (Integer.parseInt(str[1]) == 1) {
                         todo.setDone();
                     }
                     tasklist.add(todo);
                 } else if (str.length == 4) {
                     Deadline deadline = new Deadline(str[3], str[2]);
                     if (Integer.parseInt(str[1]) == 1) {
                         deadline.setDone();
                     }
                     tasklist.add(deadline);
                 } else if (str.length == 5) {
                     Event event = new Event(str[3], str[4], str[2]);
                     if (Integer.parseInt(str[1]) == 1) {
                         event.setDone();
                     }
                     tasklist.add(event);
                 }
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        }
//        String str = "Here are your tasks: \n";
//        for (int i = 0; i < tasklist.size(); i++) {
//            int j = i + 1;
//            str += "    " + j + ". " + tasklist.get(i).add() + "\n";
//        }
//        return str;
//        if(tasklist.isEmpty()) {
//            return "empty";
//        } else {
//            return "not empty";
//        }
    }

}
