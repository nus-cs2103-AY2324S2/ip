package eueu.contacts;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

public class Contacts {

    private String name;
    private int number;

    private String notes = "";
    static final String NO_NOTES = "bb sowwie there's no tea about this person...";
    static final String FILE_NOT_FOUND = "file not found! try again bb";

    public Contacts(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public Contacts(String name, int number, String notes) {
        this.name = name;
        this.number = number;
        this.notes = notes;
    }

    /**
     * Getter function to retrieve the name of the contact.
     *
     * @return The name of the contact.
     */

    public String getName() {
        return name;
    }


    /**
     * Getter function to retrieve the number of the contact.
     *
     * @return The number of the contact.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Getter function to retrieve the notes of the contact.
     *
     * @return The notes of the contact. If the notes are empty, returns a default message indicating no notes.
     */
    public String getNotes() {
        if (notes.isEmpty()) {
            return NO_NOTES;
        } else {
            return notes;
        }
    }

    /**
     * Formats the contact information for display.
     *
     * @return A string containing the formatted contact information.
     */
    public String contacting () {
        if (this instanceof Family) {
            return this.toString();
        }

        return notes.isEmpty()
                ? name + " | " + number + "\n"
                : name + " | " + number + "\n" + notes + "\n";
    }

    /**
     * Writes the contact information to a file.
     *
     * @param filePath The file path where the contact information will be written.
     * @throws IOException if an I/O error occurs while writing to the file.
     */
    public void writeToFile(File filePath) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            fw.write(this.contacting());
            fw.close();
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }
}
