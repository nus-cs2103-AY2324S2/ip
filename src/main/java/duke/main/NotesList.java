package duke.main;

import duke.exception.FileNotFoundException;

import java.util.ArrayList;

/**
 * Represents the list of notes generated from user inputs
 */
public class NotesList {
    private final ArrayList<String> notesList;
    public NotesList() {
        ArrayList<String> notesList1;
        notesList1 = Storage.readNotes();
        this.notesList = notesList1;
    }
    public void add(String note) {
        assert !note.isEmpty();
        this.notesList.add(note);
        Storage.store();
    }
    public String get(int i) {
        return this.notesList.get(i);
    }

    public void remove(int i) {
        this.notesList.remove(i - 1);
        Storage.store();
    }

    public String getAll() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.notesList.size(); i++) {
            out.append(i == 0 ? (i + 1) + ". " + this.notesList.get(i) : "/n" + (i + 1) + ". " + this.notesList.get(i));
        }
        return out.toString();
    }
    public int getSize() {
        return this.notesList.size();
    }
}
