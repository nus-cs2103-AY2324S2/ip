package cappy.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cappy.command.CommandType;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParsedInputTest {
    @Test
    public void numberOfPositionalArguments() {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        String[] args = {"arg1", "arg2", "arg3"};
        for (String arg : args) {
            positionalArgs.add(arg);
            ParsedInput parsedInput = new ParsedInput(CommandType.EMPTY, namedArgs, positionalArgs);
            assertEquals(positionalArgs.size(), parsedInput.numberOfPositionalArguments());
        }
    }

    @Test
    public void numberOfNamedArguments() {
        HashMap<String, String> namedArgs = new HashMap<>();
        HashMap<String, String> iterNamedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        namedArgs.put("option1", "value1");
        namedArgs.put("option2", "value2");
        namedArgs.put("option3", "value3");

        for (Map.Entry<String, String> entry : namedArgs.entrySet()) {
            iterNamedArgs.put(entry.getKey(), entry.getValue());
            ParsedInput parsedInput =
                    new ParsedInput(CommandType.EMPTY, iterNamedArgs, positionalArgs);
            assertEquals(iterNamedArgs.size(), parsedInput.numberOfNamedArguments());
        }
    }

    @Test
    public void hasNamedArgument() {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        namedArgs.put("option1", "value1");
        ParsedInput parsedInput = new ParsedInput(CommandType.EMPTY, namedArgs, positionalArgs);
        assertEquals(true, parsedInput.hasNamedArgument("option1"));
        assertEquals(false, parsedInput.hasNamedArgument("option2"));
    }

    @Test
    public void getNamedArgument() {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        namedArgs.put("option1", "value1");
        ParsedInput parsedInput = new ParsedInput(CommandType.EMPTY, namedArgs, positionalArgs);
        assertEquals("value1", parsedInput.getNamedArgument("option1"));
        assertEquals(null, parsedInput.getNamedArgument("option2"));
    }

    @Test
    public void getPositionalArgument() {
        HashMap<String, String> namedArgs = new HashMap<>();
        ArrayList<String> positionalArgs = new ArrayList<>();
        positionalArgs.add("arg1");
        ParsedInput parsedInput = new ParsedInput(CommandType.EMPTY, namedArgs, positionalArgs);
        assertEquals("arg1", parsedInput.getPositionalArgument(0));
        try {
            assertEquals("", parsedInput.getPositionalArgument(1));
            fail();
        } catch (Exception e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }
}
