# Jun Jie User Guide

![Ui.png](Ui.png)

Jun Jie is a chat-bot who can help you remember your tasks for the day, but don't mess around because he's

- *OFFENSIVE*
- RUDE 🤬
- Zero tolerance for incorrect inputs 💀

All you need to do is,

1. download it from [here](https://github.com/martinng01/ip/releases/tag/A-Jar).
2. double-click it.
3. let jun jie manage your tasks for you 😉

## Adding tasks (Todos, Deadlines, Events): `todo`, `deadline`, `event`

There are 3 types of tasks you can add to Jun Jie.
1. **Todo**: A task without any date/time attached to it.
2. **Deadline**: A task that needs to be done before a specific date/time.
3. **Event**: A task that starts at a specific time and ends at a specific time.

Format:
1. `todo [taskname]` for todo tasks.
2. `deadline [taskname] /by [date]` for deadlines tasks.
3. `event [taskname] /from [date] /to [date]` for event tasks.

The datetime should be in the format `yyyy-mm-dd`.

Examples:
1. `todo read book`
2. `deadline return book /by 2021-09-30`
3. `event project meeting /from 2021-09-29 /to 2021-09-30`

## Deleting tasks: `delete`

Deletes tasks at INDEX. 

Format: `delete [INDEX]`

INDEX refers to the index number shown in the displayed task list
when the command `list` is given.

Example:
- `list` followed by `delete 2` deletes the 2nd task shown in the task list.

## List all tasks: `list`

List all tasks tracked.

Format: `list`

## Find specific tasks: `find`

Find specific tasks containing a keyword.

Format: `find [KEYWORD]`

Example:
- `find buy` returns `buy cheese` and `buy eggs`.

## Mark tasks: `done`, `undone`

Mark tasks as done/undone

Format: 
- `done [INDEX]` to mark as done.
- `undone [INDEX]` to mark as undone.

## Exit the program: `bye`

Exits the program.

Format: `bye`