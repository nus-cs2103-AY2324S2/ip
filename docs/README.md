# Beepo User Guide

![Image of Beepo Ui](Ui.png)

Beepo is a friendly robot to help you with your daily task management :)  
Beepo is optimized for use via Command Line Interface (CLI), but it also provides a
Graphical User Interface (GUI) for easy viewing.

## Features
- Keep track of different types of tasks
  - todo tasks
  - deadline tasks
  - event tasks
  - doafter tasks
- Track completion status of tasks
- Quick overview of all tasks
- Filter tasks by keyword

## Usage
### `Todo {description}` Adding todo tasks

This adds a todo task to the task list. It contains a description.

Example of usage: `todo Read a book`

Expected outcome: A todo task with the description "Read a book" is added to the task list.
```
Got it. I've added this task:
[T][] Read a book
Now you have 1 tasks in the list.
```

### `deadline {description} /by {deadline}` Adding deadline tasks

This adds a deadline task to the task list. It contains a description and a deadline.

Example of usage: `deadline Finish book report /by 2024-01-01 1600`

Expected outcome: A deadline tasks with the description "Finish book report" is added to the task list.
It has a deadline of 2024-01-01 1600.
```
Got it. I've added this task:
[D][] Finish book report (by: Jan 01 2024 16:00)
Now you have 2 tasks in the list.
```

### `event {description} /from {start} /to {end}` Adding event tasks

This adds an event task to the task list. It contains a description, a start time, and an end time.

Example of usage: `event Watch TV show - Suits /from 2024-01-15 1600 /to 2024-01-15 1800`

Expected outcome: An event task with the description "Watch TV show - Suits" is added to the task list.
It has an event period from 2024-01-15 1600 to 2024-01-15 1800.
```
Got it. I've added this task:
[E][] Watch TV show - Suits (from: Jan 15 2024 16:00 to: Jan 15 2024 18:00)
Now you have 3 tasks in the list.
```

### `doafter {description} /after {index}` Adding doafter tasks

This adds a doafter task to the task list. It contains a description, and can only be completed after
a specified task is completed first.

Example of usage: `doafter Eat dinner /after 3`

Expected outcome: A doafter task with the description "Eat dinner" is added to the task list.
It can only be completed after the current 3rd task in the task list is completed.
```
Got it. I've added this task:
[A][] Eat dinner (after: Watch TV show - Suits)
Now you have 4 tasks in the list.
```

### `list` Listing all tasks

This lists all tasks currently in the task list.

Example of usage: `list`

Expected outcome: All tasks in the task list are displayed.
```
Here are the tasks in your list:
1. [T][] Read a book
2. [D][] Finish book report (by: Jan 01 2024 16:00)
3. [E][] Watch TV show -Suits (from: Jan 15 2024 16:00 to: Jan 15 2024 18:00)
4. [A][] Eat dinner (after: Watch TV show - Suits)
```

### `mark {index}` Marking tasks as complete

This marks a task as complete.

Example: `mark 1`

Expected outcome: The first task in the task list is marked as done.
```
Nice! I've marked this task as done:
[T][X] Read a book
```

### `unmark {index}` Unmarking tasks as complete

This unmarks a task as complete.

Example: `unmark 1`

Expected outcome: This first task in the task list is unmarked as complete.
```
Nice! I've unmarked this task:
[T][] Read a book
```
### `find {keyword}` Finding tasks by keyword

This finds and lists all tasks containing a specified keyword.

Example: `find book`

Expected outcome: All tasks in the task list that contain the 'book' keyword are displayed.
```
Here are the matching tasks in your list:
1. [T][X] Read a book
2. [D][] Finish book report (by: Jan 01 2024 16:00)
```

### `delete {index}` Delete tasks

This deletes a task.

Example: `delete 2`

Expected outcome: The second task in the task list is deleted.
```
Noted. I've removed this task:
[D][] Finish book report (by: Jan 01 2024 16:00)
```

### `bye` Exit the program

This saves the current data is saved to a .txt file and exits the program.

Example: `bye`

Expected outcome: The current data is saved and the program exits.