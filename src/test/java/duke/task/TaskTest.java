package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

class TaskTest {
    @Test
    public void validateComponentKeys_noDescription_invalidComponentsThrown() {
        Task.InvalidComponents e = Assertions.assertThrows(Task.InvalidComponents.class, () -> {
            Task.validateComponentKeys(new HashSet<>(), new HashSet<>());
        });

        Assertions.assertEquals("No description given", e.getMessage());
    }
}