package parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processor.*;

import tasks.TaskList;
import ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class ParserTest {

    public class FactoryStub extends Factory {
        private AddTaskProcessor addTaskProcessor;
        private DeleteTaskProcessor deleteTaskProcessor;
    
        public FactoryStub(AddTaskProcessor addTaskProcessor, DeleteTaskProcessor deleteTaskProcessor) {
            super(new TaskList(), new Ui());
            this.addTaskProcessor = addTaskProcessor;
            this.deleteTaskProcessor = deleteTaskProcessor;
        }
    
        @Override
        public AddTaskProcessor createAddTaskProcessor() {
            return addTaskProcessor;
        }
    
        @Override
        public DeleteTaskProcessor createDeleteTaskProcessor() {
            return deleteTaskProcessor;
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
        List<String> methodsCalled = new ArrayList<>();
    
        public DeleteTaskProcessorStub(TaskList taskList, Ui chatbotUi) {
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



    @BeforeEach
    public void setUp() {
        addTaskProcessorStub = new AddTaskProcessorStub(new TaskList(), new Ui());
        deleteTaskProcessorStub = new DeleteTaskProcessorStub(new TaskList(), new Ui());

        // Initialize factory with stubs
        factory = new FactoryStub(addTaskProcessorStub, deleteTaskProcessorStub);
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

}


