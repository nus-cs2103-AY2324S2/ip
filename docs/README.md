# Jivox 🤖

Jivox is a graphical user interface (GUI) desktop app that helps you track your upcoming tasks and Manage them in a Efficient. To start using Jivox, you can:

- Download the `.jar` file under `Releases`,
- Navigate to the directory in terminal,
- run `java -jar jivox.jar`, and Done!

## Features  🖥️

Jivox is packed with various features, such as:

* Addition and deletion of tasks
	* Todos
	* Events
	* Deadlines
* Marking tasks
* Searching tasks with keywords
* Tagging a task with particular tag
* See tasks due on a particular date

## User Guide 🕹

### - `list`

See a list of your tasks.

#### Usage

`list`

```
> list
Here are the tasks in your list:
	1. [T][ ] CS2101 slides
	2. [D][ ] CS2106 quiz (by 16 Sep 2023 23:59)
	3. [E][ ] CS2103T finals (from 12 Nov 2021 16:00 to 12 Nov 2021 18:00)
	4. [T][ ] ES2660 plagiarism quiz
	5. [E][ ] formal dinner (from 19 Nov 2021 18:00 to 20 Nov 2021 00:00)
```

### - `todo`

Create a todo.

#### Usage

`todo <task name>`

```
> todo CS2101 slides
added:
	[T][ ] CS2101 slides
You have 1 tasks in the list.
```

### - `event`

Create an event.

#### Usage

`event <event name> /from <start time> /to <end time>`

`<start time>` and `<end time>` should be `DD/MM/YY HH:MM`.

```
> event CS2103T finals /from 12/11/21 16:00 /to 12/11/21 18:00
added:
	[E][ ] CS2103T finals (from 12 Nov 2021 16:00 to 12 Nov 2021 18:00)
You have 3 tasks in the list.
```

### - `deadline`

Create a deadline.

#### Usage

`deadline <deadline name> /by <time>`

`<time>` should be in the form of `DD/MM/YY HH:MM`.

```
> deadline CS2106 quiz /by 16/09/23 23:59
added:
	[D][ ] CS2106 quiz (by 16 Sep 2023 23:59)
You have 2 tasks in the list.
```

### - `mark`

Marks a task.

#### Usage

`mark <task number>`

```
> mark 3
I've marked this task!
	[E][X] CS2103T finals (from 12 Nov 2021 16:00 to 12 Nov 2021 18:00)
```

### - `unmark`

Unmarks a task.

#### Usage

`unmark <task number>`

```
> unmark 3
I've unmarked this task!
	[E][] CS2103T finals (from 12 Nov 2021 16:00 to 12 Nov 2021 18:00)
```

### - `delete`

Delete a specific task.

#### Usage

`delete <task number>`

```
> delete 1
I've deleted this task from the list!
	[T][ ] CS2101 slides
You have 4 tasks in the list.
```

### - `find`

Search a task based on the keyword provided.

#### Usage

`find <keyword>`

```
> find CS
Here are the matching tasks in your list:
	1. [D][ ] CS2106 quiz (by 16 Sep 2023 23:59)
	2. [E][X] CS2103T finals (from 12 Nov 2021 16:00 to 12 Nov 2021 18:00)
```

### - `show`

Shows all the tasks (Deadline, Event) which are due on particular day.

#### Usage

`show /on <time>`

`<time>` should be in the form of `DD/MM/YY`.

```
> show /on 11/11/2023
You have following task due 11 Nov 2023:
	1. [D][ ] CS2106 quiz (by 11 Nov 2024 23:59)
	2. [E][X] CS2103T finals (from 10 Nov 2023 16:00 to 11 Nov 2023 18:00)
```

### - `bye`

Quit the app.

#### Usage

`bye`

```
> bye
Bye, hope to see you again!
```
