package duke;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static duke.Parser.parseFromInput;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ddMMyy HHmm");
    LocalDateTime ldt = LocalDateTime.parse("220224 2359", dtf);
    @Test
    public void multipleInputDeadlineSuccess() throws Exception {

        // ddMM
        assertEquals(new Deadline("iP ", ldt).toString(), parseFromInput("d iP /by 2202").toString());
        // ddMMyy HHmm
        assertEquals(new Deadline("iP ", ldt).toString(), parseFromInput("d iP /by 220224 2359").toString());
        // ddMM HHmm
        assertEquals(new Deadline("iP ", ldt).toString(), parseFromInput("d iP /by 2202 2359").toString());

    }


}
