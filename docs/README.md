# Quacky User Guide
> Feelin Stuck. Talk to the duck

![Screenshot of Quacky UI.](Ui.png)

Quacky makes your life so much easier to manage. It's

- text-based
- easy to learn
- ~~FAST~~ **SUPER FAST** to use

All you need to do is,
1. download it from [here](https://github.com/R-Laksh/ip/releases/tag/v0.3)
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 😉

And it is **FREE!**

**Ensure you have Java 11 installed on your Computer.**

## Features

### Adding todos: `todo`
Adds a task with an unspecified duration.

Example: `todo clean room`

```
	Got it. I've added this task:
		[T][ ] clean room
	Now you have 1 task in the list.
```

### Adding deadlines: `deadline`
Adds a task with that must be completed by a given time.
The time the deadline must be completed by must be in the format yyyy-mm-dd
Example: `deadline submit CS2103T IP /by 2024-02-23`

```
	Got it. I've added this task:
		[D][ ] deadline submit CS2103T IP (by: Feb 23 2024)
	Now you have 2 tasks in the list.
```

### Adding events: `event`
Adds a task with that must be completed by a given point.
The time the event starts and ends must be in the format yyyy-mm-dd
Example: `event Friday Hacks /from 2024-03-1 /to 2024-03-2`

```
	Got it. I've added this task:
		[E][ ] event Friday Hacks (from: Mar 1 2024 to: Mar 2 2024)
	Now you have 3 tasks in the list.
```

### Marking tasks as complete: `mark`
Marks a task at a given index as finished with a **'X'**.
Example: `mark 1`
```
	Quack! I marked this task as done 
		[T][X] submit cs2103t ip
```

### Unmarking complete tasks: `unmark`
Unmarks a completed task at a given index by removing the **'X'**.
Example: `unmark 1`
```
	Quack! I marked this task as not done 
		[T][] submit cs2103t ip
```

### Deleting tasks: `delete`
Deletes a task at a given index permanently.
Example: `delete 1`
```
	Quack! I removed this task:  
		[T][] submit cs2103t ip
	Now you have 4 tasks in the list.
```

### Listing tasks: `list`
Lists all tasks in Quacky.
Example: `list`
```
	1. [T][ ] submit cs2103t ip
	2. [E][ ] event Friday Hacks (from: Mar 1 2024 to: Mar 2 2024)
```

### Finds tasks: `find`
Finds all tasks with a given keyword.
Example: `find ip`
```
	1. [T][ ] submit cs2103t ip
	2. [D][ ] deadline submit CS2103T IP (by: Feb 23 2024)
```

### Say goodbye: `bye`
Exit the program.
Quacky will save tasks for future interactions _only_ if you say `bye`
Example: `bye`
