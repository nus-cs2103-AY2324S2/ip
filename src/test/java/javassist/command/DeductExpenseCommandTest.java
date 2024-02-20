package javassist.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import javassist.exception.JavAssistException;
import javassist.util.ExpenseList;
import javassist.util.StorageStub;
import javassist.util.Ui;

public class DeductExpenseCommandTest {
    @Test
    public void execute_deleteFormat_decremented() {
        ExpenseList list = new ExpenseList(10, 0, 0, 0, 0, 0, 0);
        DeductExpenseCommand dec = new DeductExpenseCommand("deduct Food /amount 5");
        try {
            dec.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            assertEquals((float) 5, list.getFood());
        } catch (JavAssistException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void execute_insufficientAmount_javassistException() {
        ExpenseList list = new ExpenseList(10, 0, 0, 0, 0, 0, 0);
        DeductExpenseCommand dec = new DeductExpenseCommand("deduct Food /amount 15");
        try {
            dec.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            fail();
        } catch (JavAssistException e) {
            assertEquals("Amount to deduct is greater than initial expense!", e.getMessage());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void execute_unknownCategory_javassistException() {
        ExpenseList list = new ExpenseList(30, 0, 0, 0, 0, 0, 0);
        DeductExpenseCommand dec = new DeductExpenseCommand("deduct house /amount 15");
        try {
            dec.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            fail();
        } catch (JavAssistException e) {
            assertEquals("Invalid category. Try: food, grocery, transport, books, clothes, entertainment, others",
                    e.getMessage());
        } catch (IOException e) {
            fail();
        }
    }
}
