package ghbot.ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import ghbot.Instruction;
import ghbot.exception.GhBotException;

/**
 * Ui Class.
 * It deals with the interaction with the user.
 */
public class Ui {
    private String input;
    private final DateTimeFormatter inTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Ui Constructor.
     * @param input User input.
     */
    public Ui(String input) {
        this.input = input;
    }

    /**
     * Checks whether the user have key in the input correctly.
     * @return A string array containing instruction and descriptions.
     * @throws GhBotException When the format of the input is wrong.
     */
    public String[] validateInput() throws GhBotException {
        checkEmptyInstruction();
        checkInvalidInstruction();
        checkMissingDescription();
        additionalCheckForDeadlineTask();
        additionalCheckForEventTask();

        return this.input.split(" ", 2);
    }

    /**
     * Checks for empty input.
     * @throws GhBotException When the user send without any input.
     */
    public void checkEmptyInstruction() throws GhBotException {
        if (this.input.isEmpty()) {
            throw new GhBotException("Helloooo!! please type something and follow the format below!!\n"
                    + "todo 'task description'\n"
                    + "deadline 'task description' /by 'time'\n"
                    + "event 'task description' /from 'start time' /to 'end time'");
        }
    }

    /**
     * Checks for invalid instruction.
     * @throws GhBotException When the user key in an unknown instruction.
     */
    public void checkInvalidInstruction() throws GhBotException {
        String[] subStr = this.input.split(" ", 2);
        if (!subStr[0].equalsIgnoreCase(Instruction.TODO.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.EVENT.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.LIST.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.MARK.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.UNMARK.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.BYE.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.DELETE.toString())
                && !subStr[0].equalsIgnoreCase(Instruction.FIND.toString())) {
            throw new GhBotException("Sorry! I don't get the instruction!\n"
                    + "please use either todo, deadline, event, list, mark, unmark, bye, find or delete!");
        }
    }

    /**
     * Checks for missing description.
     * @throws GhBotException When user did not provide any description for an instruction.
     */
    public void checkMissingDescription() throws GhBotException {
        String[] subStr = this.input.split(" ", 2);
        if (subStr[0].equalsIgnoreCase(Instruction.TODO.toString()) && subStr.length < 2) {
            throw new GhBotException("Sorry! You need to include a description for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString()) && subStr.length < 2) {
            throw new GhBotException("Sorry! You need to include a description and deadline for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.EVENT.toString()) && subStr.length < 2) {
            throw new GhBotException("Sorry! You need to include a description, "
                    + "start time and end time for your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.MARK.toString()) && subStr.length < 2) {
            throw new GhBotException("Sorry! You need to include a number to mark your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.UNMARK.toString()) && subStr.length < 2) {
            throw new GhBotException("Sorry! You need to include a number to unmark your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.DELETE.toString()) && subStr.length < 2) {
            throw new GhBotException("Sorry! You need to include a number to delete your task!");
        } else if (subStr[0].equalsIgnoreCase(Instruction.FIND.toString()) && subStr.length < 2) {
            throw new GhBotException("Sorry! You need to include a keyword that you want to find!");
        }
    }

    /**
     * Checks the date and time format for deadline task.
     */
    public void additionalCheckForDeadlineTask() {
        String[] subStr = this.input.split(" ", 2);
        if (!subStr[0].equalsIgnoreCase(Instruction.DEADLINE.toString())) {
            return;
        }
        String[] ss = subStr[1].split("/by");
        try {
            LocalDateTime.parse(ss[1].trim(), this.inTimeFormat);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Your date/time format is wrong.\n"
                    + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", ss[1].trim(), 0);
        }
    }

    /**
     * Checks the date and time format for event task.
     */
    public void additionalCheckForEventTask() {
        String[] subStr = this.input.split(" ", 2);
        if (!subStr[0].equalsIgnoreCase(Instruction.EVENT.toString())) {
            return;
        }
        String[] ss = subStr[1].split("/from");
        String[] ss2 = ss[1].split("/to");
        try {
            LocalDateTime.parse(ss2[0].trim(), this.inTimeFormat);
            LocalDateTime.parse(ss2[1].trim(), this.inTimeFormat);
        } catch (DateTimeParseException e) {
            if (e.getParsedString().equals(ss2[0].trim())) {
                throw new DateTimeParseException("Your date/time format is wrong.\n"
                        + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", ss2[0].trim(), 0);
            } else if (e.getParsedString().equals(ss2[1].trim())) {
                throw new DateTimeParseException("Your date/time format is wrong.\n"
                        + "Please use yyyy-MM-dd HHmm format (e.g. 2024-01-01 1500).", ss2[1].trim(), 0);
            }
        }

    }
}
