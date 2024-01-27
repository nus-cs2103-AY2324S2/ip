package duke.task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

class TaskTest {
    @Test
    public void validateComponentKeys_noDescription_invalidComponentsThrown() {
        Task.InvalidComponents e = Assertions.assertThrows(Task.InvalidComponents.class, () -> {
            Task.validateComponentKeys(new HashSet<>(), new HashSet<>());
        });

        Assertions.assertEquals("No description given", e.getMessage());
    }

    @Test
    public void validateComponentKeys_tooManyActual_invalidComponentsThrown() {
        HashSet<String> expected = new HashSet<>(), actual = new HashSet<>(List.of("DESCRIPTION", "/by", "/from"));
        Task.InvalidComponents e = Assertions.assertThrows(Task.InvalidComponents.class, () -> {
            Task.validateComponentKeys(expected, actual);
        });

        Assertions.assertEquals("Invalid task components: " + actual + "; expected: " + expected, e.getMessage());
    }
}