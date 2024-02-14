package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {

    @Test
    public void getRequest_bye_success() {
        Parser parser = new Parser();
        assertEquals(Parser.Request.BYE, parser.getRequest("bye"));
    }

    @Test
    public void getRequest_todoTask_success() {
        Parser parser = new Parser();
        assertEquals(Parser.Request.TODO, parser.getRequest("todo Buy groceries"));
    }
}
