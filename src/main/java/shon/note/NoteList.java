package shon.note;

import java.util.ArrayList;
import java.util.List;

import shon.exception.ParameterException;

/**
 * Represents the list containing all the notes of the user.
 */
public class NoteList {
    private List<Note> notes;

    /**
     * Creates a NoteList with an initial capacity of 100.
     */
    public NoteList() {
        this.notes = new ArrayList<>(100);
    }

    /**
     * Adds a new note to the NoteList.
     * @param text The text of the note to be added.
     * @return The result of the command.
     */
    public String[] addNote(String text) {
        this.notes.add(new Note(text));
        return new String[] {"Got it. I've added this note:", "  " + text};
    }

    /**
     * Deletes the note at the given index.
     * @param idx The index of the note to be deleted.
     * @return The result of the delete command.
     */
    public String[] deleteNote(int idx) throws ParameterException {
        if (this.notes.size() == 0) {
            throw new ParameterException("Your list of notes is empty. Nothing to delete.");
        }

        // check invalid index
        if (idx > this.notes.size() || idx < 1) {
            throw new ParameterException("Please select a valid note number to delete from the list.");
        }

        Note note = this.notes.get(idx - 1);
        this.notes.remove(idx - 1);
        return new String[]{"This note has been deleted:", "  " + note};
    }

    /**
     * Formats and outputs the notes in the NoteList.
     * @return String array of the text of notes in the NoteList.
     */
    public String[] showNotes() {
        // check for empty notelist
        if (this.notes.size() == 0) {
            return new String[]{"Your list of notes is currently empty."};
        }

        List<String> outputs = new ArrayList<>();
        outputs.add("Here are your notes:");
        for (int i = 0; i < this.notes.size(); i++) {
            outputs.add(i + 1 + ". " + this.notes.get(i));
        }
        return outputs.toArray(new String[0]);
    }

    /**
     * Returns the notes in a storage-appropriate format.
     * @return An array of String representing the notes in the list, in a storage-appropriate format.
     */
    public String[] formatData() {
        return this.notes.stream().map(Note::toString).toArray(String[]::new);
    }
}
