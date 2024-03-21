package shon.command.note;

import shon.exception.ParameterException;
import shon.note.NoteList;

/**
 * Represents a command to add a note to the NoteList.
 */
public class AddNoteCommand extends NoteCommand {
    private String text;

    /**
     * Creates a AddNoteCommand with the associated NoteList and text to add.
     * @param notes The associated NoteList.
     * @param text The text on the note to add.
     */
    public AddNoteCommand(NoteList notes, String text) {
        super(notes);
        this.text = text;
    }

    @Override
    public String execute() throws ParameterException {
        return String.join("\n", this.notes.addNote(this.text));
    }
}
