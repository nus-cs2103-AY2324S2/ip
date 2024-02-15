package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.Note;

public class NoteList {
    protected ArrayList<Note> notes = new ArrayList<>();

    public void add(Note note) {
        notes.add(note);
    }

    public void delete(Integer ind) {
        notes.remove(notes.get(ind));
    }

    public Note get(Integer ind) {
        return notes.get(ind);
    }

    public Integer size() {
        return notes.size();
    }

    /**
     * Lists out all the notes in the list.
     *
     * @return String of all of the notes.
     */
    public String list() {
        if (notes.size() == 0) {
            return "You don't have any notes!";
        }
        String output = "";
        for (int i = 0; i < notes.size(); i++) {
            output += (i + 1) + ". " + notes.get(i);
            output += "\n";
        }
        return output;
    }


    /**
     * Formats all the notes into the correct format to save.
     *
     * @return ArrayList of notes in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < notes.size(); i++) {
            toSave.add(notes.get(i).formatToSave());
        }
        return toSave;
    }       
}
