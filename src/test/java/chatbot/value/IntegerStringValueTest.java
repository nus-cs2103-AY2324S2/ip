package chatbot.value;

import chatbot.value.exception.InvalidValueTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class IntegerStringValueTest {
    @Test
    public void of_integer_expectedBehaviour() {
        try {
            IntegerStringValue i = IntegerStringValue.of(new StringValue("1"));
            assertEquals(1, i.tryGetIntegerValue());

            i = IntegerStringValue.of(new StringValue("0"));
            assertEquals(0, i.tryGetIntegerValue());

            i = IntegerStringValue.of(new StringValue("-1"));
            assertEquals(-1, i.tryGetIntegerValue());

            i = IntegerStringValue.of(new StringValue("01"));
            assertEquals(1, i.tryGetIntegerValue());

            i = IntegerStringValue.of(new StringValue(" 1 "));
            assertEquals(1, i.tryGetIntegerValue());
        } catch (InvalidValueTypeException e) {
            fail();
        }
    }

    @Test
    public void of_nonInteger_exceptionThrown() {
        assertThrows(
                InvalidValueTypeException.class,
                () -> IntegerStringValue.of(new StringValue("1.0"))
        );

        assertThrows(
                InvalidValueTypeException.class,
                () -> IntegerStringValue.of(new StringValue("1.5"))
        );

        assertThrows(
                InvalidValueTypeException.class,
                () -> IntegerStringValue.of(new StringValue("1 1"))
        );

        assertThrows(
                InvalidValueTypeException.class,
                () -> IntegerStringValue.of(new StringValue(".1"))
        );

        assertThrows(
                InvalidValueTypeException.class,
                () -> IntegerStringValue.of(new StringValue("one"))
        );

        assertThrows(
                InvalidValueTypeException.class,
                () -> IntegerStringValue.of(new StringValue(""))
        );
    }
}
