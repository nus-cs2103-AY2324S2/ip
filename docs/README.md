# Artemis User Guide

![Product Screenshots](https://kaiyi27.github.io/ip/Ui.png)

---

ArtemisPro frees your mind of having to remember things you need to do. It's,

- text-based
- easy to learn
- ~~FAST~~ _SUPER FAST_ to use
- Efficient for fast typer

All you need to do is,

1. download it from [here](https://github.com/kaiyi27/ip).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😉

And it is **FREE**!

Features:

- [x] Managing todo tasks
- [x] Managing deadlines tasks
- [x] Managing events tasks

---

## Adding deadlines: `deadline`

Add a deadline to the task list with the date and time. 

The outcome should reflect the successful addition of this deadline to the task list with unmark box [ ] and [D] specified deadline.

Format: `deadline [task] /by [dd-mm-yyyy hhmm]`

Example:

- `deadline assignment /by 01-01-2024 2359`

```
    _______________________________________________    
     Got it. I've added this task:
     [D][ ] assignment (by: Jan 01 2024 11:59PM)
     Now you have 1 tasks in the list.
    _______________________________________________    
```

## Adding events: `event`

Add an event to the task list with the date and time.

The outcome should reflect the successful addition of this event to the task list with unmark box [ ] and [E] specified event.

Format: `event [task] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]`

Example: 

- `event meeting /from 01-01-2024 1000 /to 01-01-2024 1200`

```
    _______________________________________________    
     Got it. I've added this task:
     [E][ ] meeting (from: Jan 01 2024 10:00AM to: Jan 01 2024 12:00PM)
     Now you have 1 tasks in the list.
    _______________________________________________    
```

## Adding todo: `todo`

Add a todo to the task list with the date and time.

The outcome should reflect the successful addition of this todo to the task list with unmark box [ ] and [T] specified todo.

Format: `todo [task]`

Example: 

- `todo return book`

```
    _______________________________________________    
     Got it. I've added this task:
     [T][ ] return book
     Now you have 1 tasks in the list.
    _______________________________________________    
```

## Listing all tasks: `list`

Shows a list of all tasks in the task list.

Format: `list`

## Marking Tasks: `mark`
Mark the specified task as done from the task list.

Format: `mark INDEX`

Example:

- `mark 5`
- The index refers to the index number shown in the displayed task list.
- Index **must be a positive integer** 1, 2, 3...

## Unmarking Tasks: `unmark`
Mark the specified task as undone from the task list.

Format: `unmark INDEX`

Example:

- `unmark 5`
- The index refers to the index number shown in the displayed task list.
- Index **must be a positive integer** 1, 2, 3...

## Deleting Tasks: `delete`

Delete the specified task from the task list.

Format: `delete INDEX`

Example:

- `delete 5`
- The index refers to the index number shown in the displayed task list.
- Index **must be a positive integer** 1, 2, 3...

## Finding Tasks: `find`
Use find followed by keyword to search for tasks.

Format: `find KEYWORDS`
- The search is not case-insensitive. e.g: `Book` will not match `book`.
- Partial words will be matched e.g: `bo` will match `book`.
- The order of the keywords is matter. e.g. `book return` will not match `return book`.

Example:

- `find book` will return all the task with description that contains `book`.
- e.g: `return book`, `borrow book`, `buy book`...

## Exit the Application: `bye`
Use bye to exit the application.

Format: `bye`

## Saving the data
Task list are saved in the hard disk automatically after exit the application. Artemis data are saved automatically as a txt file `[JAR file location]/data/artemis.txt`. Advanced users are welcome to update data directly by editing that data file.

---

## Command Summary

| Action   | Format, Examples                                                                                                             |
|----------|------------------------------------------------------------------------------------------------------------------------------|
| Todo     | `todo TASK`<br>e.g., `todo return book`                                                                                      |
| Deadline | `deadline TASK /by dd-mm-yyyy hhmm`<br>e.g., `deadline assignment /by 01-01-2024 2359`                                       |
| Event    | `event meeting /from dd-mm-yyyy hhmm /to dd-mm-yyyy hhmm`<br>e.g., `event meeting /from 01-01-2024 1000 /to 01-01-2024 1200` |
| List     | `list`                                                                                                                       |
| Mark     | `mark INDEX`<br>e.g., `mark 1`                                                                                               |
| Unmark   | `unmark INDEX`<br>e.g., `unmark 1`                                                                                           |
| Delete   | `delete INDEX`<br>e.g., `delete 1`                                                                                           |
| Find     | `find KEYWORD`<br>e.g., `find book`                                                                                          |



