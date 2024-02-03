package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    @Test
    void toggleActiveState_tryTogglingState() {
        Duke duke = Duke.getInstance();
        boolean originalState = duke.isActive();
        duke.ToggleActiveState();
        assertEquals(duke.isActive(), !originalState);
    }

    @Test
    void getInstance_tryGettingMultipleInstances() {
        Duke duke1 = Duke.getInstance();
        Duke duke2 = Duke.getInstance();

        // all instances should be the same instance
        assertEquals(duke1, duke2);
    }
}