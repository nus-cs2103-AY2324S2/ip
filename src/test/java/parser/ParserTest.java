package parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import processor.AddTaskProcessor;
import processor.DeleteTaskProcessor;
import processor.Factory;
import processor.MarkTaskProcessor;
import processor.UnmarkTaskProcessor;
import tasks.TaskList;
import ui.Ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    public class FactoryStub extends Factory {
        private AddTaskProcessor addTaskProcessor;
        private DeleteTaskProcessor deleteTaskProcessor;
        private MarkTaskProcessor markTaskProcessor;
        private UnmarkTaskProcessor unmarkTaskProcessor;
        public FactoryStub(AddTaskProcessor addTaskProcessor, DeleteTaskProcessor deleteTaskProcessor, MarkTaskProcessor markTaskProcessor, UnmarkTaskProcessor unmarkTaskProcessor) {
            super(new TaskList(), new Ui());
            this.addTaskProcessor = addTaskProcessor;
            this.deleteTaskProcessor = deleteTaskProcessor;
            this.markTaskProcessor = markTaskProcessor;
            this.unmarkTaskProcessor = unmarkTaskProcessor;
        }
    
        @Override
        public AddTaskProcessor createAddTaskProcessor() {
            return addTaskProcessor;
        }
    
        @Override
        public DeleteTaskProcessor createDeleteTaskProcessor() {
            return deleteTaskProcessor;
        }

        @Override
        public MarkTaskProcessor createMarkTaskProcessor() {
            return markTaskProcessor;
        }

        @Override
        public UnmarkTaskProcessor createUnmarkTaskProcessor() {
            return unmarkTaskProcessor;
        }
    
    }

    public class AddTaskProcessorStub extends AddTaskProcessor {
        List<String> methodsCalled = new ArrayList<>();
    
        public AddTaskProcessorStub(TaskList taskList, Ui chatbotUi) {
            super(taskList, chatbotUi);
        }
    
        @Override
        public void processCommand(String userInput) {
            methodsCalled.add("processCommand");
        }
    }
    public class DeleteTaskProcessorStub extends DeleteTaskProcessor {
        private List<String> methodsCalled = new ArrayList<>();
    
        public DeleteTaskProcessorStub(TaskList taskList, Ui chatbotUi) {
            super(taskList, chatbotUi);
        }
    
        @Override
        public void processCommand(String userInput) {
            methodsCalled.add("processCommand");
        }
    }

    public class MarkTaskProcessorStub extends MarkTaskProcessor {
        private List<String> methodsCalled = new ArrayList<>();
    
        public MarkTaskProcessorStub(TaskList taskList, Ui chatbotUi) {
            super(taskList, chatbotUi);
        }
    
        @Override
        public void processCommand(String userInput) {
            methodsCalled.add("processCommand");
        }
    }

    public class UnmarkTaskProcessorStub extends UnmarkTaskProcessor {
        private List<String> methodsCalled = new ArrayList<>();
    
        public UnmarkTaskProcessorStub(TaskList taskList, Ui chatbotUi) {
            super(taskList, chatbotUi);
        }
    
        @Override
        public void processCommand(String userInput) {
            methodsCalled.add("processCommand");
        }
    }

    private Parser parser;
    private Factory factory;
    private AddTaskProcessorStub addTaskProcessorStub;
    private DeleteTaskProcessorStub deleteTaskProcessorStub;
    private MarkTaskProcessorStub markTaskProcessorStub;
    private UnmarkTaskProcessorStub unmarkTaskProcessorStub;



    @BeforeEach
    public void setUp() {
        addTaskProcessorStub = new AddTaskProcessorStub(new TaskList(), new Ui());
        deleteTaskProcessorStub = new DeleteTaskProcessorStub(new TaskList(), new Ui());
        markTaskProcessorStub = new MarkTaskProcessorStub(new TaskList(), new Ui());
        unmarkTaskProcessorStub = new UnmarkTaskProcessorStub(new TaskList(), new Ui());

        // Initialize factory with stubs
        factory = new FactoryStub(addTaskProcessorStub, deleteTaskProcessorStub, markTaskProcessorStub, unmarkTaskProcessorStub);
        parser = new Parser(factory);
    }

    @Test
    public void test_parse_addTaskProcessor() throws IOException {
        String userInput = "todo task1";
        parser.parse(userInput);
        assertTrue(addTaskProcessorStub.methodsCalled.contains("processCommand"));
    }

    @Test
    public void test_parse_deleteTaskProcessor() throws IOException {
        String userInput = "delete 1";
        parser.parse(userInput);
        assertTrue(deleteTaskProcessorStub.methodsCalled.contains("processCommand"));
    }

    @Test
    public void test_parse_markTaskProcessor() throws IOException {
        String userInput = "mark 1";
        parser.parse(userInput);
        assertTrue(markTaskProcessorStub.methodsCalled.contains("processCommand"));
    }

    @Test
    public void test_parse_unmarkTaskProcessor() throws IOException {
        String userInput = "unmark 1";
        parser.parse(userInput);
        assertTrue(unmarkTaskProcessorStub.methodsCalled.contains("processCommand"));
    }

}


