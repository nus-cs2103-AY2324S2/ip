package command;

import cro.CroException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void convertDateDeadline_emptyInput_exceptionThrown(){
        Parser parser = new Parser();
        List<String> res = null;
        try {
            res = parser.convertDateDeadline(new ArrayList<>());
        } catch (CroException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(res, null);
    }

    @Test
    public void convertDateDeadline_incorrectFormat_exceptionThrown(){
        Parser parser = new Parser();
        List<String> res = null;
        try {
            res = parser.convertDateDeadline(new ArrayList<>(Arrays.asList("test deadline", "2")));
        } catch (CroException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(res, null);
    }

    @Test
    public void convertDateEvent_emptyInput_exceptionThrown(){
        Parser parser = new Parser();
        List<String> res = null;
        try {
            res = parser.convertDateEvent(new ArrayList<>());
        } catch (CroException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(res, null);
    }

    @Test
    public void convertDateEvent_incorrectFormat_exceptionThrown(){
        Parser parser = new Parser();
        List<String> res = null;
        try {
            res = parser.convertDateEvent(new ArrayList<>(Arrays.asList("test deadline", "2")));
        } catch (CroException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(res, null);
    }

}