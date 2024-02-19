package notes;

import task.Task;

import java.util.ArrayList;

// Currently only supports adding and deleting.
public class NoteList {

    private ArrayList<Note> noteList;

    private int count;

    public NoteList() {
        noteList = new ArrayList<Note>();
        count = 0;
    }

    public NoteList(ArrayList<Note> noteList) {
        this.noteList = noteList;
        count = noteList.size();
    }

    public int getSize() {
        return count;
    }

    /**
     * Adds a new note to the end of the list.
     *
     * @param newNote The new note provided by the user.
     * @param loadNote Indicates whether note is being loaded by IO.
     */
    public String addNote(Note newNote, boolean loadNote) {
        noteList.add(newNote);
        count++;

        if (!loadNote) {
            String printMessage = "Cool! Adding new note: \n";
            printMessage += newNote.toString();
            printMessage += "\n Now you have " + count + " notes in your list. \n";

            return printMessage;
        }
        return "";
    }

    /**
     * Deletes a note at the specified index.
     *
     * @param index The specified index given by the user.
     */
    public String deleteNote(int index) {
        assert index > 0 : "Index cannot be less than 1";
        assert index < count : "Index cannot be more than valid notes";

        Note removedNote = noteList.remove(index - 1);
        count--;

        String printMessage = "Okay. Deleting the note: \n";
        printMessage += removedNote.toString() + "\n";
        printMessage += "Now you have " + count + " notes in your list \n";
        return printMessage;
    }

    /**
     * Retrieves a note given the index.
     * The index is 1-based, hence -1 is applied.
     *
     * @param ind The index to retrieve the note.
     * @return The note at the specified index.
     */
    public Note getNote(int ind) {
        return noteList.get(ind - 1);
    }

    /**
     * Sets a note at the specified index.
     * The index is 1-based, hence -1 is applied.
     *
     * @param note The note to set.
     * @param ind  The index to set the note.
     */
    public void setNote(Note note, int ind) {
        noteList.set(ind - 1, note);
    }

    /**
     * Prints all notes currently in the noteList.
     */
    public String printAllNotes() {
        String printMessage = "Here are all your notes so far! ^.^ : \n";
        for (int i = 0; i < count; i++) {
            Note currentNote = noteList.get(i);
            printMessage += ((i + 1) + ". " + currentNote.toString() + "\n");
        }
        return printMessage;
    }
}
