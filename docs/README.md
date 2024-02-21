# Unim User Guide

## Welcome to Unim!
Unim is your friendly chatbot designed to assist you with task management. Whether you're a student, professional, or just someone who likes to stay organized, Unim is here to make your life easier. Below is a simple guide to help you make the most out of Unim's features.

![Unim Chat Interface](Ui.png)

## Getting Started

### 1. **Welcome Message**
Upon starting Unim, you'll receive a warm welcome message as seen below. This is your cue to start interacting with the chatbot.
````
Hey there, welcome back to Unim!
What's on your mind today?
````

## Basic Commands
### 1. Adding Tasks:
To add a Todo task: 
`todo <task description>
`

For Example:
`todo eat lunch
`

You should be able to see the following output:
````
Your to-do: [ ][T] eat lunch is on the list! You're gonna nail it :)
Just 1 tasks in your list now.
````

To add a Deadline task:
`deadline <task description> /by <due date (M/d/yyyy HHmm or String)>
`

For Example:
`deadline finish assignment /by Sunday 10am
`

You should be able to see the following output:
````
New deadline alert!
[][D] finish assignment (by: Sunday 10am)!
Now you have 2 tasks in your list.
````

To add an Event task:
`event <event description> /from <start date (M/d/yyyy HHmm)> /to <end date (M/d/yyyy HHmm)>
`

For Example:
`event team lunch /from 02/18/2024 1200 /to 02/18/2024 1330
`

You should be able to see the following output:
````
Ok! [][E] team lunch (from: Feb 18 2024 1200 to: Feb 18 2024 1330) is on the list!
Now you have 3 tasks in your list.
````

### 2. Listing Tasks:
To see your task list: 
`list
`

You should be able to see your list, for example:

````
Here are your to-dos:
1. [][D] finish assignment (by: Sunday 10am)
2. [][T] eat lunch
3. [][E] team lunch (from: Feb 18 2024 1200 to: Feb 18 2024 1330)
````

### 3. Marking and Unmarking Tasks:
To mark a task as done:
`mark <task number>`

For Example:
`mark 2`

You should be able to see the following output:
````
Boom! [X][T] eat lunch is marked done.
You're crushing it!! What's next?
````

To unmark a task: 
`unmark <task number>`

For Example:
`unmark 2`

You should be able to see the following output:
````
Not done yet? Time is ticking!! Try to finish this task soon:
[][T] eat lunch
````

### 4. Deleting Tasks:
To delete a task: 
`delete <task number>`

For Example:
`delete 2`

You should be able to see the following output:
````
Poof! This task has been removed from your list:
[][T] eat lunch
Now you have 2 tasks in your list.
````

## Advanced Features 
### 1. Finding Tasks:
To search for tasks with a keyword: 
`find <keyword>`

For Example:
`find lunch`

You should be able to see the following output:
````
Here are the tasks with lunch in them:
You're going to nail this!
1. [][T] eat lunch
2. [][E] team lunch (from: Feb 18 2024 to: Feb 18 2024)
````

## **Goodbye Message**
To say goodbye to Unim, simply type the command `bye` and you should see this:

````
Leaving ALREADY? BYEBYE :(
Remember, I'm just a message away.
````
