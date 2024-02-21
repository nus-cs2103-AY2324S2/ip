package eueu;

import eueu.contacts.Contacts;
import eueu.task.Deadline;
import eueu.task.Event;
import eueu.task.Task;
import eueu.task.Todo;

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

    /**
     * Retrieves the content of contacts from a file and appends it to a string.
     *
     * @param contactList An ArrayList containing Contacts to be appended to the string.
     * @return A string containing the content retrieved from the file and the content
     * of the Contact objects in the list.
     * @throws FileNotFoundException if the file containing contacts is not found.
     */
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

    /**
     * Saves the Tasks content of f to tasklist.
     *
     * @param tasklist ArrayList<Task> to save file content of tasks to.
     * @throws FileNotFoundException When data file doesn't exist.
     */

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
    }

}
