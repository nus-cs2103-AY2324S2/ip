package shon.command.note;

import shon.exception.ParameterException;
import shon.note.NoteList;

/**
 * Represents a command to delete a note.
 */
public class DeleteNoteCommand extends NoteCommand {
    private int idx;

    /**
     * Creates a DeleteNoteCommand with the associated NoteList and the index of the note to delete.
     *
     * @param notes The associated NoteList.
     * @param idx The index of the note to delete.
     */
    public DeleteNoteCommand(NoteList notes, int idx) {
        super(notes);
        this.idx = idx;
    }

    @Override
    public String execute() throws ParameterException {
        return String.join("\n", this.notes.deleteNote(idx));
    }
}
