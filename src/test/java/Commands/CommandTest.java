package Commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {

    @Test
    public void dummyTest(){
        assertEquals(Command.LIST, Command.LIST);
    }

    @Test
    public void anotherDummyTest(){
        assertEquals(Command.GETCOMMANDS, Command.GETCOMMANDS);
    }
}
