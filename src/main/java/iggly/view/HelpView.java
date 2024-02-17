package iggly.view;

/**
 * The {@link HelpView} class represents a view for list of commands supported by Iggly.
 */
public class HelpView extends Ui {

    /**
     * Displays a list of commands supported by Iggly.
     */
    @Override
    public String display() {
        return (
                "Here is the list of available commands:\n\n"
                + "   1. todo <todo title> (e.g. todo Todo)\n\n"
                + "   2. event <event title> /from <dd-mm-yyyy HHmm>\n"
                + "      /to <HHmm>\n"
                + "      (e.g. event Event /from 05-05-2024 1300 /to 2359)\n\n"
                + "   3. deadline <deadline title> /by <dd-mm-yyyy HHmm>\n"
                + "      (e.g. deadline Deadline /by 05-05-2024 2359)\n\n"
                + "   4. schedule <schedule title> /from <dd-mm-yyyy>\n"
                + "      /to <dd-mm-yyyy>\n"
                + "      (e.g. schedule Schedule /from 05-05-2024 /to 06-05-2024)\n\n"
                + "   5. list\n\n"
                + "   6. mark <task number> (e.g. mark 1)\n\n"
                + "   7. unmark <task number> (e.g. unmark 1)\n\n"
                + "   8. delete <task number> (e.g. delete 1)\n\n"
                + "   9. find <keyword> (e.g. find homework)\n\n"
                + "   10. bye");
    }
}
