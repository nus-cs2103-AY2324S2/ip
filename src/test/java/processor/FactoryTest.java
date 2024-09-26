package processor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tasks.TaskList;
import ui.Ui;

public class FactoryTest {
    private TaskList taskList;
    private Ui chatbotUi;
    private Factory factory;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        chatbotUi = new Ui();
        factory = new Factory(taskList, chatbotUi);
    }

    @Test
    public void test_create_addTaskProcessor() {
        AddTaskProcessor addTaskProcessor = factory.createAddTaskProcessor();
        assertEquals(taskList, addTaskProcessor.taskList);
        assertEquals(chatbotUi, addTaskProcessor.chatbotUi);
    }

    @Test
    public void test_create_deleteTaskProcessor() {
        DeleteTaskProcessor deleteTaskProcessor = factory.createDeleteTaskProcessor();
        assertEquals(taskList, deleteTaskProcessor.taskList);
        assertEquals(chatbotUi, deleteTaskProcessor.chatbotUi);
    }

        @Test
        public void test_create_findTaskProcessor() {
            FindTaskProcessor findTaskProcessor = factory.createFindTaskProcessor();
            assertEquals(taskList, findTaskProcessor.taskList);
            assertEquals(chatbotUi, findTaskProcessor.chatbotUi);
        }

        @Test
        public void test_create_listTasksProcessor() {
            ListTasksProcessor listTasksProcessor = factory.createListTasksProcessor();
            assertEquals(taskList, listTasksProcessor.taskList);
            assertEquals(chatbotUi, listTasksProcessor.chatbotUi);
        }

        @Test
        public void test_create_markTaskProcessor() {
            MarkTaskProcessor markTaskProcessor = factory.createMarkTaskProcessor();
            assertEquals(taskList, markTaskProcessor.taskList);
            assertEquals(chatbotUi, markTaskProcessor.chatbotUi);
        }

        @Test
        public void test_create_unmarkTaskProcessor() {
            UnmarkTaskProcessor unmarkTaskProcessor = factory.createUnmarkTaskProcessor();
            assertEquals(taskList, unmarkTaskProcessor.taskList);
            assertEquals(chatbotUi, unmarkTaskProcessor.chatbotUi);
        }

        @Test
        public void test_create_sortTaskProcessor() {
            SortTaskProcessor sortTaskProcessor = factory.createSortTaskProcessor();
            assertEquals(taskList, sortTaskProcessor.taskList);
            assertEquals(chatbotUi, sortTaskProcessor.chatbotUi);
        }
    }
