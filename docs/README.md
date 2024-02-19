# Echon User Guide

![Product Screenshot](Ui.png)

Echon is a chatbot for managing your tasks, events and deadlines. It uses
the command line interface, optimized for users who can type fast.

The task list is persistently stored in the user's computer in text file format.

<!-- @@author benson1029-reused -->
<!-- Adapted from AB3 user guide https://se-education.org/addressbook-level3/UserGuide.html#features -->
> ### Note
> 1. Words in `UPPER_CASE` are the parameters to be supplied by the user.
>    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Add Project Increment`.
> 2. Items in square brackets are optional.
>    e.g. in `event DESCRIPTION [/tentative]`, the argument `/tentative` is optional.
> 3. Tokens starting with `/` refers to a parameter name. Parameters can be in any order.
>    e.g. If the command specifies, `/from FROM /to TO`, `/to TO /from FROM` is also acceptable.
<!-- @@author -->

## Adding a todo task: `todo`

Adds a todo task to the task list. A todo task only consists of a description.

Format: `todo DESCRIPTION`

> ### Tip
> The parameter `DESCRIPTION` can contain multiple space-separated words.

Example: `todo Add Project Increment`

Echon will display the added task and the number of tasks in the task list.

```
Got it. I've added this task:
  [T][ ] Add Project Increment
Now you have 4 tasks in the list.
```

## Adding a deadline: `deadline`

Adds a deadline to the task list. A deadline consists of a description and a due time.

Format: `deadline DESCRIPTION /by DUE-TIME`

> ### Important
> Times must be in the format `YYYY-MM-DD hh:mm`. For example, `2024-02-29 23:59` is an
> acceptable time. Times not in the expected format will be rejected.

Example: `deadline Submit Project /by 2024-02-23 23:59`

Echon will display the added task and the number of tasks in the task list.

```
Got it. I've added this task:
  [D][ ] Submit Project (by: Feb 23 2024 23:59)
Now you have 4 tasks in the list.
```

## Adding an event: `event`

Adds an event to the task list. An event consists of a description, a start time and an end time.
An event can be tentative, which means that the start and end times of the event are subject to change.

Format: `event DESCRIPTION [/tentative] /from START-TIME /to END-TIME`

Examples:
- `event CS2103T Tutorial /from 2024-02-16 13:00 /to 2024-02-16 14:00`
- `event CS2103T Tutorial /tentative /from 2024-02-16 13:00 /to 2024-02-16 14:00`

Echon will display the added task and the number of tasks in the task list.

```
Got it. I've added this task:
  [E][ ] CS2103T Tutorial (from: Feb 16 2024 13:00 to:
Feb 16 2024 14:00)
Now you have 4 tasks in the list.
```

## Listing all tasks: `list`

Lists all tasks in the task list.

Format: `list`

Echon will display the current list of tasks together with their indices.

```
Here are the tasks in your list:
1.[T][ ] Add Project Increment
2.[D][ ] Submit Project (by: Feb 23 2024 23:59)
3.[E][ ] CS2103T Tutorial (from: Feb 16 2024 13:00 to:
Feb 16 2024 14:00)
```

## Marking a task as done: `mark`

Marks a task in the task list as done. The index of the task needs to be supplied.

Format: `mark INDEX`

Example: `mark 1`

Echon will display the updated status of the task.

```
Nice! I've marked this task as done:
  [T][X] Add Project Increment
```

## Unmarking a task as not done yet: `unmark`

Unmarks a task in the task list as not done yet. Works in the same way as `mark`.

Format: `unmark INDEX`

Example: `unmark 1`

Echon will display the updated status of the task.

```
OK, I've marked this task as not done yet:
  [T][ ] Add Project Increment
```

## Deleting a task: `delete`

Deletes a task in the task list with the specified index.

Format: `delete INDEX`

Example: `delete 1`

Echon will display the deleted task and the number of tasks in the task list.

```
Noted. I've removed this task:
  [T][ ] Add Project Increment
Now you have 2 tasks in the list.
```

## Finding all tasks with a keyword: `find`

Finds all tasks whose description contains a supplied keyword as a substring.

Format: `find KEYWORD`

Example: `find Project`

Echon will display the list of tasks that match the keyword.

```
Here are the matching tasks in your list:
1.[T][ ] Add Project Increment
2.[D][ ] Submit Project (by: Feb 23 2024 23:59)
```

## Adding a tentative time slot: `add-slot`

Adds a tentative time slot to a tentative event. The newly added time slot will coexist with
the existing tentative time slots, each with a different slot index. The index of the task needs
to be supplied and it must be a tentative event.

Format: `add-slot INDEX /from START-TIME /to END-TIME`

Example: `add-slot 3 /from 2024-02-16 14:00 /to 2024-02-16 15:00`

Echon will display the updated list of tentative time slots of the task.

```
Got it. I've added this tentative slot to the event:
  [E][ ] CS2103T Tutorial (tentative slot 1 from: Feb
16 2024 13:00 to: Feb 16 2024 14:00) (tentative slot
2 from: Feb 16 2024 14:00 to: Feb 16 2024 15:00)
```

## Confirming a tentative time slot: `confirm-slot`

Confirms one of the tentative time slots in a tentative event and turns it into an event
with a single time slot only. The index of the task and the index of the slot chosen needs
to be supplied.

Format: `confirm-slot INDEX /slot SLOT-INDEX`

Example: `confirm-slot 3 /slot 2`

Echon will display the event with the confirmed time slot.

```
Got it. I've confirmed this slot for the event:
  [E][ ] CS2103T Tutorial (from: Feb 16 2024 14:00 to:
Feb 16 2024 15:00)
```

## Saying bye to the Echon: `bye`

Says bye to Echon.

Format: `bye`

Echon will respond by this message:

```
Bye. Hope to see you again soon!
```

> ### Note
> This command does *not* terminate the Echon application.


---

## Command summary

| **Action** | **Format** |
| ------ | ---------------- |
| Adding a todo task | `todo DESCRIPTION` |
| Adding a deadline | `deadline DESCRIPTION /by DUE-TIME` |
| Adding an event | `event DESCRIPTION [/tentative] /from START-TIME /to END-TIME` |
| Listing all tasks | `list` |
| Marking a task as done | `mark INDEX` |
| Unmarking a task as not done yet | `unmark INDEX` |
| Deleting a task | `delete INDEX` |
| Finding all tasks with a keyword | `find KEYWORD` |
| Adding a tentative time slot | `add-slot INDEX /from START-TIME /to END-TIME` |
| Confirming a tentative time slot | `confirm-slot INDEX /slot SLOT-INDEX` |
| Saying bye to the Echon | `bye` |