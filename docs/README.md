# Lex User Guide
<img src="Ui.png" alt="Ui" style="max-height: 500px;">

Lex is a desktop chat bot for managing tasks, optimized for use via a Command Line Interface (CLI).
While commands 

## List
List all tasks in the list with their respective statuses and types.

Command: `list`

Example: `list`
```
expected output
```

## Add todo
Adds a new todo task to the list.

Command: `todo <description>`

Example: `todo complete assignment`
```
Got it. I've added this task:
  [T][ ] complete assignment
Now you have 1 tasks in the list.
```

## Add Deadline
Adds a new deadline task to the list.

Command: `deadline <description> /by <YYYY-MM-DD>`

Example: `deadline return book /by 2024-12-24`
```
Got it. I've added this task:
  [D][ ] return book (by: Dec 24 2024)
Now you have 2 tasks in the list.
```

## Add event
Adds a new event task to the list.

Command: `event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

Example: `event trip to new york /from 2024-06-14 /to 2024-06-24`

```
Got it. I've added this task:
  [E][ ] trip to new york (from: Jun 14 2024 to: Jun 24 2024)
Now you have 3 tasks in the list.
```

## Mark
Mark a task as completed.

Command: `mark <index>`

Example: `mark 3`

```
Nice! I've marked this task as done:
  [E][X] trip to new york (from: Jun 14 2024 to: Jun 24 2024)
```

## Unmark
Unmark a task as completed.

Command: `unmark <index>`

Example: `unmark 3`

```
OK, I've unmarked this task as not done yet:
  [E][ ] trip to new york (from: Jun 14 2024 to: Jun 24 2024)
```

## Delete
Delete a task from the list.

Command: `delete <index>`

Example: `delete 3`

```
Noted. I've removed this task:
  [E][ ] trip to new york (from: Jun 14 2024 to: Jun 24 2024)
Now you have 2 tasks in the list.
```

## Find
Finds tasks with the given keywords.

Command: `find <keyword>`

Example: `find trip`

```
Here are the matching tasks in your list:
  [E][ ] trip to new york (from: Jun 14 2024 to: Jun 24 2024)
  [E][ ] trip to london (from: Aug 22 2024 to: Aug 29 2024)
```

## Help
TODO

Command: `TODO`

Example: `TODO`

```
Here are the available commands:
  1. todo <description> - Adds a todo task.
  2. deadline <description> /by <YYYY-MM-DD> - Adds a deadline task.
  3. event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD> - Adds an event task.
  4. list - Lists all tasks.
  5. mark <index> - Marks a task as done.
  6. unmark <index> - Unmarks a task as done.
  7. delete <index> - Deletes a task.
  8. find <keyword> - Finds tasks with the given keywords.
  9. help - Shows the available commands.
  10. bye - Exits the program.
```

## Bye
Gracefully exits the program and saves the list to disk.

Command: `bye`

Example: `bye`
```

```
