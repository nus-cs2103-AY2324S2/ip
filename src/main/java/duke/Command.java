package duke;

interface Command {

    void execute(TaskList tasks, Ui ui) throws DukeException;

    boolean isExit();
}
