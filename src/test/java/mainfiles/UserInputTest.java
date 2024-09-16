package mainfiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class UserInputTest {

    @Test
    public void nullTest() {
        assertEquals(false, new UserInput("help me /from oh /my").arg1Empty());
        assertEquals("", new UserInput("").getInputName());
        assertEquals(true, new UserInput("cheating I /fromnever /to fight").arg3Empty());
        assertEquals("", new UserInput(" lol").getInputName());
        assertEquals(true, new UserInput(" why are /you testing").arg2Empty());
    }
}
