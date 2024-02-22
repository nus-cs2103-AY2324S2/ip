# Solaire User Guide

![Ui.png](docs/Ui.png)

Solaire of Astora is here to guide you through the darkness of your tasks.
> Praise the Sun! - Solaire of Astora, Dark Souls

Solaire is a chatbot that helps you manage your tasks, deadlines and events.
The app is mainly meant for CLI style usage, but also has a basic GUI.

## Setting up

1. Ensure you have Java 11 installed on your computer.
2. Download the latest `solaire.jar` from the releases page.
3. Run the jar file by double clicking it or running `java -jar solaire.jar` in the terminal.
4. The app should start and you should see a welcome message.
5. Type in commands to interact with Solaire.

# Features

## Adding todos

Add a todo to the task list.

Format: `todo <description>`

Example: `todo read a book`

```
Added [T][ ] read a book to your list
```

## Adding deadlines

Add a deadline to the task list.

Format: `deadline <description> /by <date>`

Example: `deadline finish assignment /by 2021-09-17`

Output:

```
Added [D][ ] finish assignment (by: 17 SEP 2021) to your list
```

## Adding events

Add an event to the task list with a start and end indicator.

Format: `event <description> /from <start date> /to <end date>`

Example: `event career fair /from 12 pm /to 5 pm`

Output:

```
Added [E][ ] career fair (from: 12 pm to 5 pm) to your list
```

## Listing all tasks

List all current tasks.

Format: `list`

Output:

```
Here are the tasks in your list:
1. [T][ ] read a book
2. [D][ ] finish assignment (by: 17 SEP 2021)
3. [E][ ] career fair (from: 12 pm to 5 pm)
```

## Marking tasks as done

Mark a task as done.

Format: `mark <task number>`

Example: `mark 1`

Output:

```
Marked item number 1. [T][X] read a book as done
```

## Unmarking tasks

Mark a task as not done.

Format: `unmark <task number>`

Example: `unmark 1`

Output:

```
Marked item number 1. [T][ ] read a book as not done
```

## Deleting tasks

Delete a task from the list.

Format: `delete <task number>`

Example: `delete 1`

Output:

```
Removed 1. [T][ ] read a book from your list
```

## Finding tasks

Find tasks that contain a specific keyword.

Format: `find <keyword>`

Example: `find book`

Output:

```
Here are the matching tasks in your list:
-------------------------------------------
1. [T][ ] read a book
2. [D][ ] return book (by: 17 SEP 2021)
```

## Reminders

Show tasks that are due within a specified number of days.

Format: `remindme <number of days>`

Example: `remindme 1`

Output:

```
Here's what's due in 1 day:
1. [D][ ] finish assignment (by: 24 FEB 2022)
```

## Exiting the program

Exit Solaire.

Format: `bye`

Output:

```
Farewell, and may the sun shine upon you!
```




