# HughJazz User Guide

![Ui.png](Ui.png)

HughJazz is a Chatbot Task Manager for your everyday tasks!

## List your current task list
To display your current task list: `list` <br/>
![List_screenshot.png](List_screenshot.png)

## Adding todo tasks, deadlines and events
*All timings should follow dd/MM/yyyy HHmm format*
1. To add a new todo task, simply enter `todo {description}`
2. To add a new deadline, simply enter `deadline {description} /by {by timing}`
3. To add a new deadline, simply enter `event {description} /from {from timing} /to {to timing}`

Example: `todo borrow book` <br/>
Example: `deadline return book /by 12/12/2012 1200` <br/>
Example: `event project meeting /from 02/02/2001 1400 /to 22/08/2011 1600` <br/>

![Todo_screenshot.png](Todo_screenshot.png)
![Deadline_screenshot.png](Deadline_screenshot.png)
![Event_screenshot.png](Event_screenshot.png)

## Mark and Unmark tasks 
*A marked task is indicated by an 'X' in the square brackets in front of the task*
<br/>
<br/>
To mark a task as done: `mark {task number}` <br/>
![Mark_screenshot.png](Mark_screenshot.png) <br/>
<br/>
To unmark a task as done: `unmark {task number}` <br/>
![Unmark_screenshot.png](Unmark_screenshot.png)

## Delete a task
To delete a task from your task list: `delete {task number}`<br/>
![Delete_screenshot.png](Delete_screenshot.png)

## Find tasks by keyword
To find matching tasks from your task list based on a keyword: `find {keyword}`<br/>
![Find_screenshot.png](Find_screenshot.png)

## Exiting the window
To exit the application: `bye` <br/>
<br/>
*Exiting the application automatically saves the current task list and its details in the application*
<br/>
*Upon reloading the application again, your tasks will be automatically loaded into the application, allowing you to resume your progress!*