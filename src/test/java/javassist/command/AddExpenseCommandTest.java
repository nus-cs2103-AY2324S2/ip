package javassist.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import javassist.exception.JavAssistException;
import javassist.util.ExpenseList;
import javassist.util.StorageStub;
import javassist.util.Ui;

public class AddExpenseCommandTest {
    @Test
    public void execute_addFormat_incremented() {
        ExpenseList list = new ExpenseList();
        AddExpenseCommand aec = new AddExpenseCommand("add food /amount 30.9");
        try {
            aec.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
        } catch (JavAssistException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            fail();
        }
        assertEquals((float) 30.9, list.getFood());
    }

    @Test
    public void execute_mixedCaseCategory_incremented() {
        ExpenseList list = new ExpenseList();
        AddExpenseCommand aec = new AddExpenseCommand("add FoOd /amount 30.11");
        try {
            aec.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
        } catch (JavAssistException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            fail();
        }
        assertEquals((float) 30.11, list.getFood());
    }

    @Test
    public void execute_unknownCategory_incremented() {
        ExpenseList list = new ExpenseList();
        AddExpenseCommand aec = new AddExpenseCommand("add fruit /amount 15");
        try {
            aec.execute(list, new Ui("JavAssist", "", System.in), new StorageStub(""));
            fail();
        } catch (JavAssistException e) {
            assertEquals("Invalid category. Try: food, grocery, transport, books, clothes, entertainment, others",
                    e.getMessage());
        } catch (IOException e) {
            fail();
        }
    }
}
