package javassist.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ExpenseListTest {
    @Test
    public void calculateSum_allOnes_seven() {
        ExpenseList list = new ExpenseList(1, 1, 1, 1, 1, 1, 1);
        assertEquals("7.00", list.calculateSum());
    }

    @Test
    public void calculateSum_oddFigures_seven() {
        ExpenseList list = new ExpenseList(1, 3, 7, 2.3f, 8, 13.5f, 13);
        assertEquals("47.80", list.calculateSum());
    }
}
