package ghbot.parser;

import java.io.IOException;
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
     * Prints corresponding string for different commands.
     * @param input User input.
     * @throws IOException Throws an IOException when there is an issue updating the file.
     */
    public static Executor parse(String[] input) throws IOException {
        String instr = input[0];

        if (instr.equalsIgnoreCase(Instruction.BYE.toString())) {
            return new ByeExecutor();

        } else if (instr.equalsIgnoreCase(Instruction.LIST.toString())) {
            return new ListExecutor();

        } else if (instr.equalsIgnoreCase(Instruction.MARK.toString())) {
            int lstNo = Integer.parseInt(input[1]);
            return new MarkExecutor(lstNo);

        } else if (instr.equalsIgnoreCase(Instruction.UNMARK.toString())) {
            int lstNo = Integer.parseInt(input[1]);

            return new UnmarkExecutor(lstNo);

        } else if (instr.equalsIgnoreCase(Instruction.TODO.toString())) {
            return new TodoExecutor(input[1]);

        } else if (instr.equalsIgnoreCase(Instruction.DEADLINE.toString())) {
            String[] subStr = input[1].split("/by");
            LocalDateTime inputTime = LocalDateTime.parse(subStr[1].trim(), IN_TIME_FORMAT);
            String formattedTime = inputTime.format(OUT_TIME_FORMAT);

            return new DeadlineExecutor(subStr[0], formattedTime);

        } else if (instr.equalsIgnoreCase(Instruction.EVENT.toString())) {
            String[] subStr = input[1].split("/from");
            String[] subStr2 = subStr[1].split("/to");
            LocalDateTime inputFromTime = LocalDateTime.parse(subStr2[0].trim(), IN_TIME_FORMAT);
            String formattedFromTime = inputFromTime.format(OUT_TIME_FORMAT);
            LocalDateTime inputToTime = LocalDateTime.parse(subStr2[1].trim(), IN_TIME_FORMAT);
            String formattedToTime = inputToTime.format(OUT_TIME_FORMAT);

            return new EventExecutor(subStr[0], formattedFromTime, formattedToTime);

        } else if (instr.equalsIgnoreCase(Instruction.DELETE.toString())) {
            int lstNo = Integer.parseInt(input[1]);

            return new DeleteExecutor(lstNo);

        } else if (instr.equalsIgnoreCase(Instruction.FIND.toString())) {
            return new FindExecutor(input[1]);

        } else {
            // Unreachable code as ui.validateInput already checks for all possible instructions
            assert false : instr;
        }
        return null;
    }
}
