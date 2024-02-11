package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DukeGUITest {
    @Test
    void getInstance_tryGettingMultipleInstances() {
        Duke dukeGUI1 = Duke.getInstance();
        Duke dukeGUI2 = Duke.getInstance();

        // all instances should be the same instance
        assertEquals(dukeGUI1, dukeGUI2);
    }
}