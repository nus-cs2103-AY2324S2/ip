package shon.command.note;

import shon.command.Command;
import shon.note.NoteList;

/**
 * Represents a command that can be executed to carry out an action for notes.
 */
abstract class NoteCommand extends Command {
    protected NoteList notes;

    /**
     * Creates a NoteCommand with the associated NoteList.
     * @param notes The NoteList associated with this command.
     */
    public NoteCommand(NoteList notes) {
        this.notes = notes;
    }
}
