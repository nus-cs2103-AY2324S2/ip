# Morty Chatbot

<img src="./Ui.png" alt="Morty Chatbot UI" />

**Morty** is your go-to chatbot for managing todos, deadlines, and events with ease. Designed to be text-based for simplicity and speed, Morty is an essential tool for anyone looking to streamline their task management. Now with a graphical user interface (GUI) on the horizon, managing your tasks with Morty is about to get even easier.

> “Wubba Lubba Dub Dub!” — Rick ([source](https://rickandmorty.fandom.com/wiki/Wubba_Lubba_dub-dub))

## Features

### Adding a Todo: `todo`

Adds a todo item to the task list.

- **Format**: `todo <DESCRIPTION>`
- **Description**: Adds a todo with the specified DESCRIPTION. The description should be a brief text explaining the task. It must be a String that clearly identifies the todo item.
- **Examples**:
  - `todo CS2103 Project`

### Adding a Deadline: `deadline`

Adds a task with a specific deadline to the task list.

- **Format**: `deadline <DESCRIPTION> /by <DATE>`
- **Description**: Adds a task with a deadline, where DATE is in the format `yyyy-mm-dd`. This helps in managing tasks that need to be completed by a certain date.
- **Examples**:
  - `deadline Submit Assignment /by 2024-03-15`

### Adding an Event: `event`

Adds an event task to the task list.

- **Format**: `event <DESCRIPTION> /at <DATE>`
- **Description**: Schedules an event for the specified DATE, allowing for effective management of upcoming events and appointments.
- **Examples**:
  - `event Team Meeting /at 2024-04-22`

### Listing Tasks

Lists all tasks in the task list.

- **Format**: `list`
- **Description**: Displays all the tasks added to Morty, including todos, deadlines, and events, along with their status (completed or pending).

### Marking a Task as Done: `done`

Marks a specified task as completed.

- **Format**: `done <INDEX>`
- **Description**: Marks the task at the specified INDEX as completed. The index refers to the position of the task in the task list displayed by the `list` command.
- **Examples**:
  - `done 2` marks the second task in the list as completed.

### Deleting a Task: `delete`

Deletes a specified task from the task list.

- **Format**: `delete <INDEX>`
- **Description**: Deletes the task at the specified INDEX. The index must be a positive integer (1, 2, 3, ...) indicating the position of the task in the task list. This command is useful for removing tasks that are no longer relevant or have been added by mistake.
- **Examples**:
  - Listing tasks and then `delete 2` removes the second task in the task list.

### Archiving Tasks: `archive`

Creates a local copy archive of the current task list.

- **Format**: `archive`
- **Description**: This command archives the current state of the task list, providing a backup that can be referred to or restored later. It's particularly useful for maintaining a record of tasks over time.

Each feature is designed to make managing your tasks with Morty straightforward and efficient, from adding new tasks to organizing and prioritizing your workload.

## Getting Started

To get started with Morty:

1. Download it from [here](https://github.com/rickkoh/ip/releases/tag/A-JUnit).
2. Double-click it.
3. Add your tasks.
4. Let Morty manage your tasks for you 😉

And it's **FREE!**

## Building and Running Locally

For those interested in building and running the program locally, the `gradlew` has been set up for your convenience. Please run the following commands based on your operating system:

```shell
# For macOS
./gradlew run

# For Windows
gradlew.exe run
```

## Releases

Keep your Morty chatbot up-to-date by downloading the latest version. Our releases are designed to improve your experience with new features and enhancements. Here's where you can find the most recent version:

| Version    | Release Link                                                        |
| ---------- | ------------------------------------------------------------------- |
| Morty v0.1 | [Download here](https://github.com/rickkoh/ip/releases/tag/A-JUnit) |

For future updates, please keep an eye on our GitHub releases page. We are constantly working on adding new functionalities and making Morty even more helpful for managing your daily tasks.
