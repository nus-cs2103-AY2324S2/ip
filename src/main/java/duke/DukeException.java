package duke;

/**
 * The `DukeException` class represents any exception related to the Duke program.
 */
public class DukeException extends Exception {

    public static final String BYE_ERROR = "Error: Syntax of bye: bye";
    public static final String LIST_ERROR = "Error: Syntax of list: list";
    public static final String TODO_ERROR = "Error: Syntax of todo: todo {task description} \n"
            + "E.g. todo breakfast";
    public static final String DEADLINE_ERROR = "Error: Syntax of deadline: deadline {task description} "
            + "/by ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
            + "E.g. deadline breakfast /by 2022-12-31 15:00";
    public static final String EVENT_ERROR = "Error: Syntax of event: deadline {task description} "
            + "/from ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd}) "
            + "/to ({yyyy-MM-dd HH:mm} or {yyyy-MM-dd})\n"
            + "E.g. event exam /from 2022-12-31 15:00 /to 17:00";
    public static final String UNKNOWN_ERROR = "Error: Unknown command.";
    public static final String MARK_ERROR = "Error: Syntax of mark: mark {index of task (integer)}\n"
            + "E.g. mark 1";
    public static final String UNMARK_ERROR = "Error: Syntax of unmark: unmark {index of task (integer)}\n"
            + "E.g. unmark 1";
    public static final String DELETE_ERROR = "Error: Syntax of delete: delete {index of task (integer)}\n"
            + "E.g. delete 1";
    public static final String FIND_ERROR = "Error: Syntax of find: find {keyword}\n"
            + "E.g. find book";
    public static final String TAG_ERROR = "Error: Syntax of tag: tag {index of task (integer)} {tag (string without space)}\n"
            + "E.g. tag 1 urgent";



    public static final String EMPTY_ERROR = "Error: Sorry, we are not sync enough to communicate through empty command.";
    public static final String SMALL_INDEX_ERROR = "Error: The index of task cannot be larger than number of task.";
    public static final String NON_POSITIVE_INDEX_ERROR = "Error: The index of task must be positive integer.";

    /**
     * Empty constructor of a `DukeException` object.
     */
    public DukeException() {
        super();
    }

    /**
     * Constructor with error message of a `DukeException` object.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

}
