package lilybot.command;

import java.util.concurrent.atomic.AtomicInteger;

import lilybot.gui.Ui;
import lilybot.parser.Parser;
import lilybot.task.TaskList;


/**
 * Command for finding tasks that contain the keywords.
 */
public class FindCommand implements Command {

    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs FindCommand with the following constructor.
     *
     * @param ui To be displayed for users.
     * @param taskList For tracking the list of tasks.
     */
    public FindCommand(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Find the tasks that contain the keywords.
     *
     * @param command Command entered by users.
     * @return The messages to be displayed after execution.
     */
    @Override
    public String exceute(String command) {
        try {
            String keyWord = (Parser.parseCommand(command))[1];
            StringBuilder s = new StringBuilder("Here're the matching tasks in ur list: \n");
            AtomicInteger counter = new AtomicInteger(1);

            taskList.getLs()
                    .stream()
                    .filter(t -> t.getDescription().contains(keyWord))
                    .forEach(tk -> {
                        s.append(counter + ". " + tk.toString() + "\n");
                        counter.getAndIncrement();
                    });

            return s.toString();

        } catch (Exception exc) {
            return ui.invalidKeyWord();

        }
    }
}
