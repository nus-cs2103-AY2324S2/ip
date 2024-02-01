package duke.commands;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandsEnumTest {
    
    @Test
    public void getCommandEnum_validInput_returnCorrectEnum() {
        String input = "todo";
        CommandsEnum result = CommandsEnum.getCommandEnum(input);
        assertEquals(CommandsEnum.INSERT_TODO, result);
    }

    @Test
    public void getCommandEnum_invalidInput_returnDefaultEnum() {
        String input = "invalid";
        CommandsEnum result = CommandsEnum.getCommandEnum(input);
        assertEquals(CommandsEnum.DEFAULT, result);
    }

    @Test
    public void getCommandEnum_inputWithDifferentCases_returnCorrectEnum() {
        String input = "DeADline";
        CommandsEnum result = CommandsEnum.getCommandEnum(input);
        assertEquals(CommandsEnum.INSERT_DEADLINE, result);
    }

}
