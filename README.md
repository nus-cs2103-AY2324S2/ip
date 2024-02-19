# Jiayou User Guide
![Ui](https://github.com/ljy0422/ip/assets/111126607/21431f1a-0356-4fe9-b89a-3b0b1a7331e0)
Jiayou is a simple chatbot which can help you manage your daily tasks with cute personality. It's,  
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

All you need to do is,
1. download it from [here](https://github.com/ljy0422/ip/releases/tag/A-Release)
2. double-click it
3. add your tasks
4. let it manage your tasks for you 😉

The following is the features of Jiayou and how you can use them.

## Getting the Whole Task List
You can ask Jiayou to display the whole task list so that you can clearly see how many tasks you have.

You can the following command to see the complete task list.  

Example: `list` to see the complete list  

## Adding Tasks -- Todos, Deadlines and Events
You can add tasks to the task list, so that Jiayou can manage it for you!

Tasks are divided into three types. 
1. Todos are the simplest tasks with solely a decription
2. Deadlines have a description and a deadline date
3. Events have a description, a starting date and an ending date
   
You can use the following commands to add a task.

Example 1: `todo <description>` to add a todo task  
Example 2: `deadline <description> /by <date>` to add a deadline task  
Example 3: `event <description> /from <date> /to <date>` to add an event task

## Deleting tasks
You can delete tasks to make your task list always clean and organized.

You can use the following commands to delete tasks.

Example 1: `delete <id>` to delete a task  
Example 2: `delete <id><id><id>` to delete multiple tasks

## Searching Tasks -- By Keywords or By Dates
You can easily search for tasks which
1. contain a certain keyword
2. fall on a certain date

You can use the following commands to search tasks.

Example 1: `search_by_date <date>` to search tasks by date  
Example 2: `search_by_keyword <keyword>` to search tasks by keyword  

## Marking/Unmarking Tasks
You can mark a task as done or reversely unmark it to leave it undone, so that you can clearly see your current progress in your task list.

You can use the following commands to mark/unmark tasks.

Example 1: `mark <id>` to mark a task  
Example 2: `mark <id><id><id>` to mark multiple tasks  
Example 3: `unmark <id>` to unmark a task  
Example 4: `unmark <id><id><id>` to unmark multiple tasks  

## Rescheduling Tasks
You can reschedule the date of a deadline or event task, so that you can flexibly manage your tasks without the trouble of deleting the original task and adding it again with the new date.

You can use the following commands to reschedule tasks.

Example 1: `reschedule <id> /by` to reschedule the deadline time of a deadline task  
Example 2: `reschedule <id> /from <date>` to reschedule the starting time of an event task  
Example 3: `reschedule <id> /to <date>` to reschedule the ending time of an event task

## Help and Bye
Whenever you want Jiayou to introduce all the functionalities to you, enter `help` to get the guide.  
If you want to exit the application, say `bye` to Jiayou.
