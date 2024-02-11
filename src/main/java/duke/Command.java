package duke;

interface Command {

    String execute(TaskList tasks, Ui ui) throws DukeException;

    boolean isExit();
}
