package chatbot.value;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatbot.value.exception.InvalidValueTypeException;

public class IntegerStringValueTest {
    @Test
    public void constructor_integer_expectedBehaviour() {
        try {
            IntegerStringValue i = new IntegerStringValue("1");
            assertEquals(1, i.tryGetIntegerValue());

            i = new IntegerStringValue("0");
            assertEquals(0, i.tryGetIntegerValue());

            i = new IntegerStringValue("-1");
            assertEquals(-1, i.tryGetIntegerValue());

            i = new IntegerStringValue("01");
            assertEquals(1, i.tryGetIntegerValue());

            i = new IntegerStringValue(" 1 ");
            assertEquals(1, i.tryGetIntegerValue());
        } catch (InvalidValueTypeException e) {
            fail();
        }
    }

    @Test
    public void constructor_nonInteger_exceptionThrown() {
        assertThrows(InvalidValueTypeException.class, () ->
                new IntegerStringValue("1.0"));

        assertThrows(InvalidValueTypeException.class, () ->
                new IntegerStringValue("1.5"));

        assertThrows(InvalidValueTypeException.class, () ->
                new IntegerStringValue("1 1"));

        assertThrows(InvalidValueTypeException.class, () ->
                new IntegerStringValue(".1"));

        assertThrows(InvalidValueTypeException.class, () ->
                new IntegerStringValue("one"));

        assertThrows(InvalidValueTypeException.class, () ->
                new IntegerStringValue(""));
    }
}
