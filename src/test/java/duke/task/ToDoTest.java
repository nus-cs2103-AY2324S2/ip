package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
	@Test
	public void TaskCreationTest(){
		assertEquals(new ToDo("hello").toString(), "[T][ ] hello");
	}

	@Test
	public void markTest(){
		Task markTask = new ToDo("hello");
		markTask.mark();
		assertEquals(markTask.toString(), "[T][X] hello");
	}

	@Test
	public void unmarkTest(){
		Task markTask = new ToDo("hello");
		markTask.mark();
		markTask.unmark();
		assertEquals(markTask.toString(), "[T][ ] hello");
	}
}
