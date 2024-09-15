<style>
  code {
    white-space : pre-wrap !important;
    word-break: break-word;
  }
</style>
# Index
- [Index](#index)
- [Zero User Guide](#zero-user-guide)
  - [Adding To-Dos with `todo`](#adding-to-dos-with-todo)
  - [Adding Deadlines with `deadline`](#adding-deadlines-with-deadline)
  - [Adding Events with `event`](#adding-events-with-event)
  - [Viewing tasks with `list`](#viewing-tasks-with-list)
  - [Marking tasks with `mark`](#marking-tasks-with-mark)
  - [Unmarking tasks with `unmark`](#unmarking-tasks-with-unmark)
  - [Removing tasks with `delete`](#removing-tasks-with-delete)
  - [Finding tasks with `find`](#finding-tasks-with-find)
  - [Tagging tasks with `tag`](#tagging-tasks-with-tag)
  - [Leaving with `exit` or `bye`](#leaving-with-exit-or-bye)


# Zero User Guide
This is Zero, your friendly companion which helps you keep track of tasks. This project is created from the Duke project template (for a greenfield Java project).

![GUI preview image](Ui.png)

>[!NOTE]
>Enter nothing to receive a cute cat line-art.
>```
>      ╱|、
>    (˚ˎ 。7
>     |、˜〵
>     じしˍ,)ノ
>```

## Adding To-Dos with `todo`
Add a To-Do task by using the command:
```
todo [name of To-Do task]
```
Example, an input `todo run` will yield:

```
Got it. I've added this task:
  [T][ ] run
Now you have 1 task(s) in the list.
```
[Back to index](#index)

## Adding Deadlines with `deadline`
Add a deadline by using the command:
```
deadline [name of deadline] /by [DD/MM/YY HHMM]
```
Example, an input `deadline print report /by 23/3/24 1800` will yield:

```
Got it. I've added this task:
  [D][ ] print report (by: Sat, 23 Mar 2024, 06:00 PM)
Now you have 1 task(s) in the list.
```
[Back to index](#index)

## Adding Events with `event`
Add an event by using the command:
```
event [name of event] /from [DD/MM/YY HHMM] /to [DD/MM/YY HHMM]
```
> [!TIP]
> `/from` and `/to` parameter order can be swapped.

Example, an input `event marathon /from 23/3/24 0700 /to 23/3/24 1800` will yield:

```
Got it. I've added this task:
  [E][ ] marathon (from: Sat, 23 Mar 2024, 07:00 AM) (to: Sat, 23 Mar 2024, 06:00 PM)
Now you have 1 task(s) in the list.
```
[Back to index](#index)

## Viewing tasks with `list`
List all tasks by using the command:
```
list
```
Example, an input `list` will may yield:
```
Here are the tasks in your list:
1.[T][ ] run
2.[D][ ] print report (by: Sat, 23 Mar 2024, 06:00 PM)
3.[E][ ] marathon (from: Sat, 23 Mar 2024, 07:00 AM) (to: Sat, 23 Mar 2024, 06:00 PM)
```
This output will depend on your current list.<br>
[Back to index](#index)

## Marking tasks with `mark`
Mark a task as done by using the command:
```
mark [index of task]
```
Example, an input `mark 1` will yield:
```
Nice! I've marked this task as done:
  [T][X] run
```
[Back to index](#index)

## Unmarking tasks with `unmark`
Mark a task as not done by using the command:
```
unmark [index of task]
```
Example, an input `unmark 1` will yield:
```
OK, I've marked this task as not done yet:
  [T][ ] run
```
[Back to index](#index)

## Removing tasks with `delete`
Delete a task from the list by using the command:
```
delete [index of task]
```
Example, an input `delete 1` will yield:
```
Noted. I've removed this task:
  [T][ ] run
Now you have 0 task(s) in the list.
```
[Back to index](#index)

## Finding tasks with `find`
Find all tasks that match by using the command:
```
find [search string]
```
Example, an input `find walk` will yield:
```
Here are the matching tasks in your list:
1.[E][ ] walkathon (from: Sun, 3 Mar 2024, 02:00 PM) (to: Sat, 3 Mar 2024, 06:00 PM)
2.[T][ ] walk
```
[Back to index](#index)

## Tagging tasks with `tag`
Tag task for easy grouping by using the command:
```
tag [index of task] [name of tag]
```
Example, an input `tag 1 tiring` will yield:
```
I've tagged this task for you:
  [T][ ] run #tiring
```
[Back to index](#index)

## Leaving with `exit` or `bye`
Exit the software by using the command:
```
bye
```
or
```
exit
```
[Back to index](#index)
