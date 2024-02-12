package luke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    Parser parser = new Parser(new Storage(), null);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate date1 = LocalDate.parse("27-08-2001", formatter);
    LocalDate date2 = LocalDate.parse("28-08-2001", formatter);

    Task[] correctSamples = {
            new Todo("stuff"),
            new Deadline("homework", date1),
            new Event("holiday", date1, date2)
    };

    String[] missingParams = {"todo", "deadline", "event"};

//    @Test
//    public void makeTask_correctParams_returnTask() {
//        assertEquals(correctSamples[0], parser.makeTask("todo stuff"));
//        assertEquals(correctSamples[1], parser.makeTask("deadline homework / by 27-08-2001"));
//        assertEquals(correctSamples[2], parser.makeTask("event holiday / from 27-08-2001 / to 28-08-2001"));
//    }
//
//    @Test
//    public void makeTask_weirdSpacing_returnTask() {
//        assertEquals(correctSamples[0], parser.makeTask("todo          stuff"));
//        assertEquals(correctSamples[1], parser.makeTask("deadline  homework   /   by    27-08-2001"  ));
//        assertEquals(correctSamples[2], parser.makeTask("  event holiday / from    27-08-2001 /  to 28-08-2001"  ));
//    }
//
//    @Test
//    public void makeTask_missingParams_returnNull() {
//        assertEquals(null, parser.makeTask("todo"));
//        assertEquals(null, parser.makeTask("deadline homework"));
//        assertEquals(null, parser.makeTask("event holiday"));
//        assertEquals(null, parser.makeTask("event holiday / from 27-08-2001"));
//    }
//
//    @Test
//    public void makeTask_incorrectDateFormat_returnNull() {
//        assertEquals(null, parser.makeTask("deadline homework / by 082-12-12398"));
//        assertEquals(null, parser.makeTask("event holiday / from Singapore / to London"));
//    }
//
//    @Test
//    public void makeTask_unknownCommand_returnNull() {
//        assertEquals(null, parser.makeTask("akjdijbakf"));
//    }
}
