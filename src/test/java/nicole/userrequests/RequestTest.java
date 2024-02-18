package nicole.userrequests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import nicole.nicoleexceptions.NicoleException;
public class RequestTest {

    @Test
    public void request_helpRequestGiven_noExceptionThrown() {
        try {
            Request testRequest = new Request("help");
            assertEquals(
                    "I'm your task/deadline/event manager! I'm down with these requests,\n"
                            + "1. todo [task]\n"
                            + "2. deadline [task] by YYYY-MM-DD\n"
                            + "3. event [description] from YYYY-MM-DD at HH-MM-SS to YYY-MM-DD at HH-MM-SS\n"
                            + "4. list\n"
                            + "5. mark [tasknumber]\n"
                            + "6. unmark [tasknumber]\n"
                            + "7. delete [tasknumber]\n"
                            + "8. sort by date\n"
                            + "9. find [keyword]\n"
                            + "10. update [tasknumber] [new task name]\n"
                            + "11. bye\n"
                            + "12. help",
                    testRequest.handleRequest("help"));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void request_incompleteUnMarkCommandGiven_nicoleExceptionThrown() {
        try {
            Request testRequest = new Request("unmark");
            testRequest.handleRequest("unmark");
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(), "ERROR. unmark [task number] pls...");
        }
    }

    @Test
    public void request_incompleteMarkCommandGiven_nicoleExceptionThrown() {
        try {
            Request testRequest = new Request("mark");
            testRequest.handleRequest("mark");
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(), "ERROR. mark [task number] pls...");
        }
    }

    @Test
    public void request_incompleteDeleteCommandGiven_nicoleExceptionThrown() {
        try {
            Request testRequest = new Request("delete");
            testRequest.handleRequest("delete");
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(), "ERROR. delete [task number] pls...");
        }
    }

    @Test
    public void request_incompleteFindCommandGiven_nicoleExceptionThrown() {
        try {
            Request testRequest = new Request("find");
            testRequest.handleRequest("find");
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(), "ERROR. find [keyword] pls...");
        }
    }

    @Test
    public void request_incompleteUpdateCommandGiven_nicoleExceptionThrown() {
        try {
            Request testRequest = new Request("update");
            testRequest.handleRequest("update");
            fail();
        } catch (NicoleException e) {
            assertEquals(e.toString(), "ERROR. update [task number] [name] pls...");
        }
    }
}
