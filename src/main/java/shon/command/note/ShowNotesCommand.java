package shon.command.note;

import shon.note.NoteList;

/**
 * Represents a command to show the list of notes.
 */
public class ShowNotesCommand extends NoteCommand {
    /**
     * Creates a ShowNotesCommand with the associated NoteList.
     * @param notes The NoteList containing the notes to show.
     */
    public ShowNotesCommand(NoteList notes) {
        super(notes);
    }

    @Override
    public String execute() {
        return String.join("\n", this.notes.showNotes());
    }
}
