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
                "Here is the list of available commands:\n"
                + "   1. todo <todo title>\n"
                + "   2. event <event title> /from <dd-mm-yyyy HHmm>\n"
                + "      /to <HHmm>\n"
                + "   3. deadline <deadline title> /by <dd-mm-yyyy HHmm>\n"
                + "   4. list\n"
                + "   5. mark <task number>\n"
                + "   6. unmark <task number>\n"
                + "   7. delete <task number>\n"
                + "   8. find <keyword>\n"
                + "   9. bye\n");
    }
}
