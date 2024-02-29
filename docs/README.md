# Riri User Guide

![Ui.png](https://Rishit02.github.io/ip/Ui.png)

Welcome to Riri, your personal chatbot assistant! Riri helps you manage tasks efficiently through a simple chat interface.

## Adding Todos

To add todos for tasks, use the following format:
`todo <description>`

Example: `todo play video games`

After entering this command, a deadline task will be added to your task list.

Expected Output:

```
Got it. Added: T[][] play video games
```

## Adding Deadlines

To add deadlines for tasks, use the following format:
`deadline <description> /by <date>`

Example: `deadline return books /by 2/2/2024`

After entering this command, a deadline task will be added to your task list.

Expected Output:

```
Got it. Added: [D][] return books (by: Feb 2 2024)
```

## Adding Events

To add future events to your task list, follow this format:
`event <description> /from <start_date> /to <end_date>`

Example: `event birthday party /from 2/2/2024 /to 3/2/2024`

After entering this command, an event task will be added to your task list.

Expected Output:

```
Got it. Added: [E][] birthday party (from: Feb 2 2024 to Mar 3 2024)
```

## Marking a Task as Done

To mark a task as done follow this format:
`mark <task number>`

Example: `mark 3`

After entering this command, the third task will be marked as done.

Expected Output:

```
[T][X] read book
```

## Marking a Task as not done

To mark a task as not done follow this format:
`unmark <task_number>`

Example: `unmark 3`

After entering this command, the third task will be marked as not done.

Expected Output:

```
[T][] read book
```

## Finding Tasks

Riri allows you to find tasks in your task list based on keywords. Use the following format:
`find <task>`

Example: `find books`

After entering this command, Riri will search your task list for tasks containing the keyword "books."

### Tips for Using Find

- The `find` command is not case-sensitive, so it will match tasks regardless of case.
- You can use partial keywords to broaden your search. For example, `find book` will match tasks with the word "book" in them.

Feel free to experiment with different keywords to quickly locate the tasks you are looking for.

## Deleting a task

In Riri, you have the ability to delete tasks from your task list.

Example: `delete 3`

This would result in the 3rd task in your task list to be deleted


## Saving tasks

Your task list is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


## Exit program

We can exit the chatbot and save messages by writing `bye`

Example: `bye`

The chatbot will exit.

## Summary

| Command  | Format                                                  | Example                                            |
|----------|---------------------------------------------------------|----------------------------------------------------|
| List     | `list`                                                  | `list`                                             |
| Mark     | `mark <task_number>`                                    | `mark 3`                                           |
| Unmark   | `unmark <task_number>`                                  | `unmark 3`                                         |
| Todo     | `todo <description>`                                    | `todo play video games`                            |
| Deadline | `deadline <description> /by <date>`                     | `deadline return books /by 2/2/2024`               |
| Event    | `event <description> /from <start_date> /to <end_date>` | `event birthday party /from 2/2/2024 /to 3/2/2024` |
| Delete   | `delete <task_number>`                                  | `delete 3`                                         |
| Find     | `find <task>`                                           | `find book`                                        |
| Bye      | `bye`                                                   | `bye`                                              |