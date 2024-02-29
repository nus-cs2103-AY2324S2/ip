# Damon User Guide

![Ui](Ui.png)



## <code style="color : Orange">Feature 1 - Add a new Task</code>

- Add a new Task to current TaskList.
- Command:
  - Command to add ToDo Task: <code style="color : Blue">todo {DESCRIPTION}</code>
  - Command to add Deadline Task: <code style="color : Blue">deadline {DESCRIPTION} /by {DUE_DATE}</code>
      *(DUE_DATE should be in yyyy-mm-dd format)*
  - Command to add Event Task: <code style="color : Blue">event {DESCRIPTION} /from {START_TIME /to {END_TIME}</code>
  - Command to add FixedDuration Task: <code style="color : Blue">fixedduration {DESCRIPTION} /needs {DURATION}</code>


## <code style="color : Orange">Feature 2 - Delete a Task</code>

- Delete the Task of given index
- Command: <code style="color : Blue">delete {TASK_INDEX}</code>


## <code style="color : Orange">Feature 3 - Mark a Task as done</code>

- Mark the Task of given index as done
- Command: <code style="color : Blue">mark {TASK_INDEX}</code>


## <code style="color : Orange">Feature 4 - Unmark a Task to not done</code>


- Unmark the Task of given index to not done
- Command: <code style="color : Blue">unmark {TASK_INDEX}</code>


## <code style="color : Orange">Feature 5 - Find Task by keyword</code>

- Find Tasks including given keywords in description
- Command: <code style="color : Blue">find {KEYWORD}</code>


## <code style="color : Orange">Feature 6 - Exit Damon</code>

- Say Goodbye to Damon!
- Command: <code style="color : Blue">bye</code>


## <code style="color : Orange">Feature 7 - Save TaskList to hard disk</code>

- Damon will automatically save TaskList in "Damon.txt" file and update the file once you change the TaskList. You may change the storage path in **iP\src\main\java\damon\gui\Main.java**, i.e.,
```java
private Damon damon = new Damon("put your storage path here!");
```