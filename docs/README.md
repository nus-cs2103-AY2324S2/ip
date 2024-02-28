# ChatBro User Guide
Tired of always forgetting to complete your tasks or deadlines? Or perhaps forgetting about and missing classes or events?

ChatBro's got you, bro!

ChatBro is a simple to use task manager application that allows you to add tasks to a task list, mark (or unmark) these tasks as 'done', delete tasks or search for tasks. ChatBro remembers your task list across all sessions.

Let ChatBro remember and handle your tasks for you!
<p align="center">
<img width="304" height="460" src="https://cdn.discordapp.com/attachments/523125126540165121/1212420395316023317/image.png?ex=65f1c57b&is=65df507b&hm=0163641fe6250b403967de4c6f2884567b5bc70e2898ecff709047571caaceaf&">
</p>

## Features:

Click on the underlined features to jump to the respective section!

1. [Adding Tasks](#exit):
   [To-Do](#todo)  |  [Deadline](#deadline)  |  [Interval Deadline](#interval)  |  [Event](#event)
2. [Viewing your task list](#view-list)
3. [Marking / Unmarking tasks as "done"](#mark)
4. [Deleting tasks](#delete)
5. [Searching for tasks](#search)
6. [Exiting the program and saving tasks](#exit)

## 1. Adding tasks

ChatBro can't help you keep track of your tasks if he doesn't know what tasks you have. You'll have to add tasks to your task list to make use of the other features!
This section details the types of tasks you can add to your list, as well as how to do so.

Each task type is represented differently in your task list, but all of their representations will start with:
`[Task type][Status] <task description>`, where "status" is `[ ]` for incomplete tasks or `[X]` for complete tasks.

Deadline, Interval deadline and Event tasks will require a `<date>` specifier in your input.

For all task types that require `<date>` specifier, `<date>` must follow the format:
<p align="center"> **dd-MM-yyyy HHmm** </p>

    (dd: day, MM: month, yyyy: year, HH: hours, mm: minutes)
    HHmm is optional.

More details will be given in the subsections below!

### 1.1 Adding To-Do task <a id="todo"></a>

A To-Do task is a task without any associated deadline, starting time or ending time. It only has a task description.
A todo task is represented in your list as `[T][Status] <task description>`.

**Command format:** `todo <task description>`

**Example usage:** `todo buy new shoes`

### 1.2 Adding Deadline task <a id="deadline"></a>

A Deadline task is a task with an associated deadline. It has a `<date>` specifier `<due date>`.

**Command format:** `deadline <task description> /by <due date>`

**Example usage:** `deadline Complete Math assignment /by 10-04-2024`

### 1.3 Adding Interval Deadline task <a id="interval"></a>

Have a task that needs to be completed within a given time period?
If you have a task where the deadline is an interval of time, instead of an instance of time, you may create an Interval Deadline task! It has `<date>` specifiers `<interval start date>` and `<interval end date>`.

**Command format:** `interval <task description> /from <interval start date> /to <interval end date>`

**Example usage:** `interval Complete IPPT /from 23-02-2024 /to 31-03-2024`

### 1.4 Adding Event task <a id="event"></a>
An event task with a starting time and ending time. Examples include meetings, classes, dinner reservations at a restaurant, etc. It has `<date>` specifiers `<start time>` and `<end time>`.

**Command format:** `event <task description> /from <start time> /to <end time>`

**Example usage:** `event Group project meeting /from 25-02-2024 1300 /to 25-02-2024 1530`

## 2. Viewing your task list <a id="view-list"></a>

To view your list of tasks, use the 'list' command.

**Command format:** `list`

ChatBro replies with a list of your tasks, including task type, status, description, and any other task information.

## 3. Marking and unmarking tasks as "done" <a id="mark"></a>

Now that we know how to view our task list, we can mark some tasks as "done"! First, you'll need to know the task number of the task to be marked, which you can find using the 'list' command. Then, type `mark <task number>` to mark the task. To unmark the task, type `unmark <task number>`. This will be reflected in the task list.

For example, if the task "buy new shoes" is done but the task "(CS2100) Complete Tutorial 3 worksheet" is not done yet, using the list command:

<p align="center">
<img width="350" height="223" src="https://cdn.discordapp.com/attachments/523125126540165121/1212419963344789564/image.png?ex=65f1c514&is=65df5014&hm=62be53e1e451adba792a1aec8f27a8f59be6b2d114e8f818ed1cb3f578d89b71&">
</p>
we can see that "buy new shoes" has the status marked as "X", i.e. it is done.

**Command format:** `mark <task number>` or  `unmark <task number>`

**Example usage:** `mark 3` or  `unmark 1`

## 4. Deleting tasks <a id="delete"></a>

You may delete tasks as well, if you've made an error in some of the task details, or if the task is completed already and you no longer need to keep a record of it. To do this, you will need the task number of the task to be deleted, which you can find using the 'list' command.

**Command format:** `delete <task number>`

**Example usage:** `delete 2`

## 5. Searching for tasks <a id="search"></a>

As you start adding tasks to your list, you may find that the 'list' command will result in ChatBro giving you a reply that is too long to effectively find tasks that you need to complete.

In this case, you may use the 'find' command to search for tasks! ChatBro will fetch all tasks with the ***task description*** containing the given keyword and replies with a list of these tasks.

**Command format:** `find <keyword>`

**Example usage:** `find Timothy`

Note that this command performs a *partial matching* of the keyword:
(e.g. `find elo` will fetch a task with description "`Hello`", since 'Hello' contains the substring 'elo')

To illustrate how 'find' can help filter the list effectively, assume we are finding all tasks related to the course "CS2103".

Using 'list':
<p align="center">
<img width="270" height="380" src="https://cdn.discordapp.com/attachments/523125126540165121/1212420395316023317/image.png?ex=65f1c57b&is=65df507b&hm=0163641fe6250b403967de4c6f2884567b5bc70e2898ecff709047571caaceaf&">
</p>

Using 'find':
<p align="center">
<img width="320" height="250" src="https://cdn.discordapp.com/attachments/523125126540165121/1212420651000926228/image.png?ex=65f1c5b8&is=65df50b8&hm=6465cefb05cfd271475c0f4c592f17b8de09f1dc310ed534772e76b0fa3d8b51&">
</p>

## 6. Exiting program and saving your tasks  <a id="exit"></a>

When you're done using the application, you can exit the program using the simple command `bye`.
When you execute this command, ChatBro saves all your tasks to a text file  (savedTasks.txt) and bids farewell to you, closing the application. This text file will be found in the same folder as `ChatBro.jar`.

**Command format:** `bye`

**NOTE: You <u>_must_</u> exit the program with `bye` for ChatBro to save your tasks. If you close the application using the X at the top right corner of the window, your tasks will NOT be saved.**

If `savedTasks.txt` is in the wrong format, you will see this pop-up alert when you run ChatBro:
<p align="center">
  <img width="415" height="150" src="https://cdn.discordapp.com/attachments/523125126540165121/1210286867006881906/image.png?ex=65ea027b&is=65d78d7b&hm=e7a5e69c3fec31607ee979692df955c815247f9d8b45f35f7af1d44d96ab3222&">
</p>

Simply follow the instructions given and delete `savedTasks.txt`, then start ChatBro again.
**(NOTE: Your saved task list will be *deleted*, and you will have to add your tasks into the list again)**