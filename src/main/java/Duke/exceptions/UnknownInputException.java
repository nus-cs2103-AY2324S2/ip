package duke.exceptions;

/**
 * Class for UnknownInputException
 */
public class UnknownInputException extends DukeException {
    private static final String[] listOfCommands = new String[]{
        "  1. bye (exit program)\n",
        "  2. date DD/MM/YYYY HHmm (search relevant task with timeStamp)\n",
        "  3. deadline [task description] /by dd/mm/yyyy HHmm\n",
        "  4. delete [integer]\n",
        "  5. event [task description] /from dd/mm/yyyy HHmm /to dd/mm/yyyy HHmm\n",
        "  6. find [task description] (search for relevant task with desciption\n",
        "  7. list (show current tasks)\n",
        "  8. mark [integer] (mark task at index to be done)\n",
        "  9. todo [task description]\n",
        " 10. unmark [integer]\n",
        " 11. name [your name]\n"
    };
    public UnknownInputException() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("JUST WHAT ARE YOU SAYING BRUUUHH,\n"
                + "if you wish to continue talking to me\n\n"
                + "Here is the list of VALID Commands\n");
        for (int i = 0; i < listOfCommands.length; i++) {
            result.append(listOfCommands[i]);
        }
        return result.toString();
    }
}