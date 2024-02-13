package duke.util;

import org.junit.jupiter.api.Test;

public class UITest {
    @Test
    public void testIntro() {
        System.out.println(new UI().introMessage());
    }
    @Test
    public void testName() {
        UI ui = new UI();
        ui.setName("Ding Hao");
        System.out.println(new UI().getName());
    }
}
