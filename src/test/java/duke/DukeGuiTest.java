package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DukeGuiTest {
    @Test
    void getInstance_tryGettingMultipleInstances() {
        Duke dukeGui1 = Duke.getInstance();
        Duke dukeGui2 = Duke.getInstance();

        // all instances should be the same instance
        assertEquals(dukeGui1, dukeGui2);
    }
}