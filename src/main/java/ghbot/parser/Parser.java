package ghbot.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ghbot.Instruction;
import ghbot.executor.ByeExecutor;
import ghbot.executor.DeadlineExecutor;
import ghbot.executor.DeleteExecutor;
import ghbot.executor.EventExecutor;
import ghbot.executor.Executor;
import ghbot.executor.FindExecutor;
import ghbot.executor.ListExecutor;
import ghbot.executor.MarkExecutor;
import ghbot.executor.TodoExecutor;
import ghbot.executor.UnmarkExecutor;

/**
 * Parser Class.
 * It deals with making sense of the user command.
 */
public class Parser {
    private static final DateTimeFormatter IN_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUT_TIME_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy hhmma");

    /**
     * Creates specific executor instance corresponding to the different commands.
     * @param input User input.
     * @return Specific executor instance corresponding to the different commands.
     */
    public static Executor parse(String[] input) {
        String instr = input[0];

        if (instr.equalsIgnoreCase(Instruction.BYE.name())) {
            return createNewByeExecutor();
        } else if (instr.equalsIgnoreCase(Instruction.LIST.name())) {
            return createNewListExecutor();
        } else if (instr.equalsIgnoreCase(Instruction.MARK.name())) {
            return createNewMarkExecutor(input[1].trim());
        } else if (instr.equalsIgnoreCase(Instruction.UNMARK.name())) {
            return createNewUnmarkExecutor(input[1].trim());
        } else if (instr.equalsIgnoreCase(Instruction.TODO.name())) {
            return createNewTodoExecutor(input[1].trim());
        } else if (instr.equalsIgnoreCase(Instruction.DEADLINE.name())) {
            return createNewDeadlineExecutor(input[1].trim());
        } else if (instr.equalsIgnoreCase(Instruction.EVENT.name())) {
            return createNewEventExecutor(input[1].trim());
        } else if (instr.equalsIgnoreCase(Instruction.DELETE.name())) {
            return createNewDeleteExecutor(input[1].trim());
        } else if (instr.equalsIgnoreCase(Instruction.FIND.name())) {
            return createNewFindExecutor(input[1].trim());
        } else {
            assert false : instr + " is a wrong instruction!";
            return null;
        }
    }

    /**
     * Creates a new instance of ByeExecutor.
     * @return A new instance of ByeExecutor.
     */
    public static ByeExecutor createNewByeExecutor() {
        return new ByeExecutor();
    }

    /**
     * Creates a new instance of ListExecutor.
     * @return A new instance of ListExecutor.
     */
    public static ListExecutor createNewListExecutor() {
        return new ListExecutor();
    }

    /**
     * Creates a new instance of MarkExecutor.
     * @param description A string containing the number of the specific task on the list.
     * @return A new instance of MarkExecutor.
     */
    public static MarkExecutor createNewMarkExecutor(String description) {
        int listNo = Integer.parseInt(description);
        return new MarkExecutor(listNo);
    }

    /**
     * Creates a new instance of UnmarkExecutor.
     * @param description A string containing the number of the specific task on the list.
     * @return A new instance of UnmarkExecutor.
     */
    public static UnmarkExecutor createNewUnmarkExecutor(String description) {
        int listNo = Integer.parseInt(description);
        return new UnmarkExecutor(listNo);
    }

    /**
     * Creates a new instance of TodoExecutor.
     * @param description A string containing the description of the task.
     * @return A new instance of TodoExecutor.
     */
    public static TodoExecutor createNewTodoExecutor(String description) {
        return new TodoExecutor(description);
    }

    /**
     * Creates a new instance of DeadlineExecutor.
     * @param description A string containing the description of the task.
     * @return A new instance of DeadlineExecutor.
     */
    public static DeadlineExecutor createNewDeadlineExecutor(String description) {
        String[] subStr = description.split("/by");
        LocalDateTime inputTime = LocalDateTime.parse(subStr[1].trim(), IN_TIME_FORMAT);
        String formattedTime = inputTime.format(OUT_TIME_FORMAT);

        return new DeadlineExecutor(subStr[0], formattedTime);
    }

    /**
     * Creates a new instance of EventExecutor.
     * @param description A string containing the description of the task.
     * @return A new instance of EventExecutor.
     */
    public static EventExecutor createNewEventExecutor(String description) {
        String[] subStr = description.split("/from");
        String[] subStr2 = subStr[1].split("/to");
        LocalDateTime inputFromTime = LocalDateTime.parse(subStr2[0].trim(), IN_TIME_FORMAT);
        String formattedFromTime = inputFromTime.format(OUT_TIME_FORMAT);
        LocalDateTime inputToTime = LocalDateTime.parse(subStr2[1].trim(), IN_TIME_FORMAT);
        String formattedToTime = inputToTime.format(OUT_TIME_FORMAT);
        return new EventExecutor(subStr[0], formattedFromTime, formattedToTime);
    }

    /**
     * Creates a new instance of DeleteExecutor.
     * @param description A string containing the number of the specific task on the list.
     * @return A new instance of DeleteExecutor.
     */
    public static DeleteExecutor createNewDeleteExecutor(String description) {
        int listNo = Integer.parseInt(description);
        return new DeleteExecutor(listNo);
    }

    /**
     * Creates a new instance of FindExecutor.
     * @param description A string containing the description of a task that the user is finding.
     * @return A new instance of FindExecutor.
     */
    public static FindExecutor createNewFindExecutor(String description) {
        return new FindExecutor(description);
    }
}
