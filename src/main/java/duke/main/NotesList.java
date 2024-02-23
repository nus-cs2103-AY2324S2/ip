package duke.main;

import java.util.ArrayList;

public class NotesList {
    private final ArrayList<String> notesList;
    public NotesList() {
        this.notesList = new ArrayList<>();
    }
    public void add(String note) {
        this.notesList.add(note);
    }
    public String get(int i) {
        return this.notesList.get(i - 1);
    }

    public void remove(int i) {
        this.notesList.remove(i - 1);
    }

    public String getAll() {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < this.notesList.size(); i++) {
            out.append(i == 0 ? (i + 1) + ". " + this.notesList.get(i) : "/n" + (i + 1) + ". " + this.notesList.get(i));
        }
        return out.toString();
    }
}
