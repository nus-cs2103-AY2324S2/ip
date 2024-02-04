package controller;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Hashtable;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseInput_deadlineTaskInput_success() {
        Hashtable<String, String> dict = Parser.parseInput("deadline testinput /by 21/2/23 1300");
        assertAll("test",
            () -> assertEquals("deadline", dict.get("cmd")),
            () -> assertEquals("testinput", dict.get("name")),
            () -> assertEquals("21/2/23 1300", dict.get("/by"))
        );
    }

    @Test
    public void parseInput_randomArgsInput_success() {
        Hashtable<String, String> dict = Parser.parseInput("notACommand qwerty /hi 1237654 /bye /weeee 9ifef");
        assertAll("test",
            () -> assertEquals("notACommand", dict.get("cmd")),
            () -> assertEquals("qwerty", dict.get("name")),
            () -> assertEquals("1237654", dict.get("/hi")),
            () -> assertEquals("", dict.get("/bye")),
            () -> assertEquals("9ifef", dict.get("/weeee"))
        );
    }
}
