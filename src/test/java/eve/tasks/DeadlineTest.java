package eve.tasks;

import java.time.LocalDateTime;
import eve.parser.Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {    
    //3 test to test if event is created correctly or not

    @Test
    public void test1(){
        Deadline deadline = new Deadline("return book", "2/12/2019 1800");
        assertEquals("return book", deadline.getTask());

    }

    @Test
    public void test2(){
        Deadline deadline = new Deadline("return book", "2/12/2019 1800");
        LocalDateTime temp = Parser.stringToDateTime("2/12/2019 1800");
        assertEquals(temp, deadline.getDeadline());
    }


    
}
