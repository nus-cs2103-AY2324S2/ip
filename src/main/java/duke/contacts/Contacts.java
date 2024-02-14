package duke.contacts;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Contacts {

    private String name;
    private int number;

    private String notes = "";

    public Contacts(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public Contacts(String name, int number, String notes) {
        this.name = name;
        this.number = number;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getNotes() {
        if (notes.isEmpty()) {
            return "bb sowwie there's no tea about this person...";
        } else {
            return notes;
        }
    }

    public String contacting () {
        if (this instanceof Family) {
            return this.toString();
        }

        return notes.isEmpty()
                ? name + " | " + number + "\n"
                : name + " | " + number + "\n" + notes + "\n";
    }

    public void writeToFile(File filePath) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            fw.write(this.contacting());
            fw.close();
        } catch (IOException e) {
            System.out.println("file not found! try again bb");
        }
    }
}
