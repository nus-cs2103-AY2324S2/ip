# Sophia User Guide

### Welcome to Sophia, Your Personal AI ChatBot Assistant

Sophia is designed to be more than just a chatbot; she's your personal assistant, 
ready to help you manage your day-to-day tasks with ease. Whether it's keeping track 
of your to-dos, scheduling events, setting deadlines, or simply reminding you of 
important dates, Sophia has got you covered. 

![GUI window](Ui.png)

## Key Features of Sophia
Sophia, your AI ChatBot Assistant, is designed to simplify your daily life 
by offering a wide range of features. Here's what Sophia can do for you:

- **Adding Tasks**: Easily add tasks to your to-do list with a simple command 
  or conversation with Sophia. Whether it's a work assignment or a personal errand, Sophia helps you keep track of what needs to be done.

- **Managing Tasks**: Sophia offers comprehensive task management capabilities, including:

    - **Marking Tasks as Completed**: Once you've finished a task, tell Sophia to mark it off your list, giving you a clear view of what's left to tackle.
    - **Unmarking Tasks**: Made a mistake or need to revisit a task? Sophia can unmark tasks, putting them back on your active list.
    - **Deleting Tasks**: Remove tasks that are no longer relevant or have been accidentally added.
    - **Finding Tasks**: Quickly find specific tasks by asking Sophia. Whether you're looking for a task by name, date, or any other detail, 
                         Sophia can help you locate it instantly.
- **Basic Conversation**: Beyond task management, Sophia is equipped for basic conversations. 
                           Feel free to talk about your day, discuss various topics, or share your 
                          thoughts. Sophia is here to make your day a little easier and a lot more 
                            interesting.

## Table of Contents
1. [Getting Started](#getting-started-)
2. [List of Features](#list-of-features)
3. [Viewing Help](#viewing-help)
4. [Adding Tasks](#adding-tasks)
5. [Managing Tasks](#managing-tasks)
6. [Finding Task]()
7. [Defining Shortcut](#define-a-shortcut)
8. [Basic Conversation]()

## Getting Started 

1. Ensure you have Java 11 or above installed in your computer. If not, [here's how to.](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)

2. Download the latest [Sophia.jar]() from here.

3. Copy the file into a new folder.

4. Open a command terminal, cd into the folder you put the jar file in. 
   Here's how to know the filepath ([Mac](https://www.digitaltrends.com/computing/how-to-find-and-copy-a-file-path-on-mac/) or [Win](https://www.sony.com/electronics/support/articles/00015251)).

5. Use the command `java -jar sophia.jar` command to run the application. 

6. Now, try adding a todo task using `todo bake cakes` and display it using `list`

Refer to the Features below for details of other commands.

## List of Features
Sophia understands a variety of commands to help you manage your tasks and 
interact with her. Below is a comprehensive list of commands you can use,
along with examples of how to use them:
```markdown
List: Display all your current tasks.
Usage: "list" or "lst"

Find: Search for a specific task by keywords.
Usage: "find [keyword]"

Delete: Remove a specific task from your list.
Usage: "delete [task number]" or "del [task number]"

Delete All: Clear all tasks from your list.
Usage: "delete all"

Todo: Add a new to-do task.
Usage: "todo [task description]"
Shortcut: "t [task description]"

Event: Schedule a new event.
Usage: "event [event description] /from [date/time] /to [date/time]"
Shortcut: "e [event description] /from [date/time] /to [date/time]"

Deadline: Set a new deadline for a task.
Usage: "deadline [task description] /by [date/time]."
Shortcut: "dl [task description] /by [date/time]."

Mark: Mark a task as completed.
Usage: "mark [task number]"
Shortcut: "m [task number]"

Unmark: Revert a task to incomplete status.
Usage: "unmark [task number]"
Shortcut: "um [task number]"

Help: Get assistance on how to use Sophia or clarification on commands.
Usage: "help"

Commands: Display a list of all available commands.
Usage: "commands"

Define: Creates a new shortcut or alias for a command. This allows you 
to customize how you interact with Sophia, making commands easier and 
faster to use.
 - Usage: "define [new shortcut]=[existing command]"
 - Example: If you frequently delete tasks and want 
   a quicker way to do it, you might use: "define d=delete"
```

## Viewing help
Get assistance on how to use Sophia or clarification on commands.
Format: `help` or `commands`

## Adding Tasks

### 1. Adding Todos
To add a todo task, use the following __command format__:

`t <description>`
`todo <description>`

Replace `<description>` with the description of your deadline task.

__Example__:

`todo go to gym`

__Expected Output__:
```
Yay! Your task is on the board 📋. Here's what you told me to remember:
 [T][] go to gym
Bringing your total to 1 tasks. Let's keep this productivity train going! 🚂💨
```

### 2. Adding Deadlines
To add a deadline task, use the following __command format__:

`deadline <description> /by <deadline>`

Replace `<description>` with the description of your deadline task and `<deadline>` with the deadline date and time.

The deadline format: `yyyy/MM/dd HH:mm`.

__Example__:

`deadline submit assignment /by 2024-02-18 16:00`

__Expected Output__:
```
Yay! Your task is on the board 📋. Here's what you told me to remember:
 [D][] submit assignment (by: 18 Feb 2024 16:00)
Bringing your total to 2 tasks. Let's keep this productivity train going! 🚂💨
```

### 3. Adding Events
To add an event, use the following __command format__:

`event <description> /from <start time> /to <end time>`

Replace `<description>` with the description of your deadline task,
replace `<start time>` and `<end time>` with the date and time.

Time format: `yyyy/MM/dd HH:mm`.

__Example__:

`event tutorial zoom meeting /from 2024/02/02 1200 /to 2024/02/02 1400`

__Expected Output__:
```
Yay! Your task is on the board 📋. Here's what you told me to remember:
 [E][] tutorial zoom meeting (from: 2 Feb 2024 12:00 to: 2 Feb 2024 14:00)
Bringing your total to 3 tasks. Let's keep this productivity train going! 🚂💨
```


## Managing Tasks
### 1. Listing all tasks
Command format:

`list`

__Example__:

`list`

__Expected Output__:
```
Here are the tasks in your list:
1. [T][] go to gym
2. [D][] submit assignment (by: 18 Feb 2024 16:00)
3. [E][] tutorial zoom meeting (from: 2 Feb 2024 12:00 to: 2 Feb 2024 14:00)
```

### 2. Mark Task as Done
__Command format__:

`mark <task index>`

__Example__:

`mark 1` or `m 1`

__Expected Output__:
```
YAY! 🎉 We did it! Task 1 is now marked as done!
Feels good, doesn't it? Here's a high five! ✋

 [T][] go to gym
```

### 3. Unmark Task as Not Done
__Command format__:

`unmark <task index>` 

__Example__:

`unmark 1` or `um 1`

__Expected Output__:
```
Abracadabra! Your task is back on the list, ready to conquer! 
Remember, completing tasks is a journey, not a sprint.

 [T][] go to gym
```

### 4. Delete Task
__Command format__:

`delete <task number>`

__Example__:

`delete 1` or `del 1`

__Expected Output__:
```
Poof! ✨ Your task vanished into thin air! Here's what we said goodbye to:
 [T][] go to gym
You're now down to 2 tasks in the list.
```

### 5. Delete All Tasks
__Command format__:

`delete all`

__Example__:

`delete all`

__Expected Output__:
```
All clear! 🧹✨ I've removed every single task from the list.
```

## Find a Task
Search for a specific task by keywords.

__Command format__:

`find <keyword>` 

__Example__:

`find gym` or `f gym`

__Expected Output__:

If there are two tasks contain word 'return':
```
Eureka! 🎉 I found some tasks that might be what you're looking for:
[T][] go to gym
[T][X] go to gym with friends
```
If there is no task matching the keyword:
```
Hmm, my crystal ball 🔮 shows no tasks matching that description.
Maybe try a different keyword? I'm sure we'll find something! 🕵️‍♂
```

## Define a shortcut
Sophia is capable of creating a new shortcut or alias for a command. This allows you to customize 
how you interact with Sophia, making commands easier and faster to use.

__Command format__:

`define shortcut=command`

__Example__:

`define d=delete`

__Expected Output__:

```
Woohoo! 🎉 Shortcut 'd' is all set up for command 'delete'
```
If the shortcut already exists:
```
Uh-oh! 😕 Looks like that shortcut/command combo 
is already taken or it's a default command.
```

## Basic Conversation
Sophia is designed to be more than just a task manager; she's also here to
engage in basic conversations, making your interactions more enjoyable and
lively. Below are examples of simple conversational exchanges you can have 
with Sophia:

__Example__:

`how are you`

__Expected Output__:

```
Running at optimal efficiency! Thanks for checking in. 🤖
```

__Example__:

`what can you do`

__Expected Output__:

```
From managing tasks to telling jokes, I'm here to make life a tad easier. What's on your mind?
```
__Example__:

`tell me a joke`

__Expected Output__:

```
Why don't skeletons fight each other? They don't have the guts.
```