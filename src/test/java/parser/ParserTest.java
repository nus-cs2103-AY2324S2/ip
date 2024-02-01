package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processor.Processor;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    private Processor processor;
    private Parser parser;

    // Stub class for Processor
    private class ProcessorStub extends Processor {
        public ProcessorStub(TaskList taskList, Ui chatbotUi) {
            super(taskList, chatbotUi);
        }

        List<String> methodsCalled = new ArrayList<>();

        @Override
        public void userInputDeleteTask(String userInput) {
            methodsCalled.add("userInputDeleteTask");
        }

        @Override
        public void userInputProcessMarkUnmark(String userInput) {
            methodsCalled.add("userInputProcessMarkUnmark");
        }

        @Override
        public void userInputListTasks() {
            methodsCalled.add("userInputListTasks");
        }

        @Override
        public void userInputAddTask(String userInput) {
            methodsCalled.add("userInputAddTask");
        }
    }

    @BeforeEach
    public void setUp() {
        processor = new ProcessorStub(new TaskList(), new Ui());
        parser = new Parser(processor);
    }

    @Test
    public void test_processCommand_delete() throws IOException {
        String userInput = "delete 1";
        parser.processCommand(userInput);
        assert(((ProcessorStub) processor).methodsCalled.contains("userInputDeleteTask"));
    }

    // ... repeat for other test methods
}