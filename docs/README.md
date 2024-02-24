# HAASNT User Guide

![expected output](./Screen Shot 2567-02-24 at 11.15.12.png)

**If you always forgot what you have to do, HASSNT is here to help you record such tasks.**
HAASNT is a chatbot that allows you to keep your tasks recorded
## Adding Tasks
**There are 3 types of tasks currently available:** 

    1. todo for tasks that has no specifc deadline
        - to add todo task, type: todo <task description>
    2. deadline for task that has specific deadline
        - to add deadline task, type: 
            deadline <task description> by <deadline> 
            deadline format has two formats currently:
                1. MM-dd hh:mm am/pm 
                2. MM-dd format
    3. event for tasks that span for a period
        - to add event tasks type:
            event <task> followed by <event span>
            now event span has two formats:
                1. hh:mm am/pm to hh:mm am/pm
                2. MM/dd hh:mm am/pm to hh:mm am/pm

    **Notes**
    1. MM represents months in xx format ie. 01
    2. always include am or pm in your deadline date


Example: event t 10:00 am to 12:00 am

expected output:
![expected output](./Screen Shot 2567-02-24 at 11.04.24.png)

## Todo Lists Commands

1. **list:** to show the current tasks in the list
2. **clearl:** to clear the list
3. **remove (index):** remove particular task based on the index of that task (type list to see the index)
4. **mark (index):** mark task at index as done
5. **unmark (index):** unmark task at index as not done
