# **LeBron**
>"The key is not to prioritize whats on your schedule, but to schedule your priorities." – Stephen Covey ([source](https://everydaypower.com/quotes-for-efficiency/))

LeBron helps you record your tasks and makes it easier for you to manage them.
- Fast
* Easy
+ Fun

All you need to do is,
1. download it from [here](https://github.com/Chiarena/ip/releases/tag/A-Jar).
2. double click it.
3. add your tasks.
4. let it manage your tasks for you.

And it is **FREE**!

Features:

- [ ] Adding, deleting, marking tasks
- [ ] Manage todo, deadlines and events
- [ ] View list of tasks and find tasks

If you are a Java programmer, you can use it to practice Java too. Here's tje main method:
~~~
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
~~~
## **Here are some commands you can use**


### **1.Add a Todo to your task list**
Command: `todo [TASK DESCRIPTION]`

Response
```
todo homework
____________________________________________________________

Got it.I've added this task:
[T] [ ] homework
Now you have 9 tasks in the list.
____________________________________________________________


```
### **2.Add a Deadline to your task list**
Command: `deadline [TASK DESCRIPTION] /by [dd/MM/yyyy HHmm]`

Response
```
deadline do GUI /by 26/2/2025 2359
____________________________________________________________

Got it.I've added this task:
[D] [ ] do GUI (by:Feb 26 2025 23:59)
Now you have 11 tasks in the list.
____________________________________________________________
______
```
### **3.Add a Event to your task list**
Command: `event [TASK DESCRIPTION] /from [dd/MM/yyyy HHmm] /to [dd/MM/yyyy HHmm]`

Response
```
event mental break down /from 26/2/2024 2349 /to 26/2/2024 2359
____________________________________________________________

Got it.I've added this task:
[E] [ ] mental break down (from:Feb 26 2024 23:49 to:Feb 26 2024 23:59)
Now you have 12 tasks in the list.
____________________________________________________________

```
### **4.Mark a task in your list**
Command: `mark [INDEX]`

Response
```
mark 10
____________________________________________________________

Nice! I've marked this task as done:
[T] [X] do GUI
____________________________________________________________
```
### **5.Unmark a task in your list**
Command: `unmark [INDEX]`

Response
```
unmark 10
____________________________________________________________

OK, I've marked this task as not done yet-:
[T] [ ] do GUI
____________________________________________________________

```
### **6.View your task list**
Command: `list`

Response
```
list
Here are the tasks in your list:
____________________________________________________________

1. [D] [X] read (by:Dec 02 2023 18:00)
2. [E] [X] read (from:Dec 02 2023 18:00 to:Dec 03 2023 18:00)
3. [D] [ ] read (by:Dec 02 2023 18:00)
4. [E] [ ] read (from:Dec 02 2023 18:00 to:Dec 03 2023 08:30)
5. [T] [ ] read
6. [E] [ ] work (from:Dec 02 2023 18:00 to:Dec 04 2023 08:30)
7. [D] [ ] eat (by:Dec 02 2023 18:00)
8. [D] [ ] read (by:Dec 02 2023 17:00)
9. [T] [ ] homework
10. [T] [ ] do GUI
11. [D] [ ] do GUI (by:Feb 26 2025 23:59)
12. [E] [ ] mental break down (from:Feb 26 2024 23:49 to:Feb 26 2024 23:59)
____________________________________________________________
```
### **7.Delete a task**
Command: `delete [INDEX]`

Response
```
delete 12
____________________________________________________________

Noted. I've removed this task:
[E] [ ] mental break down (from:Feb 26 2024 23:49 to:Feb 26 2024 23:59)
Now you have 11 tasks in the list.
____________________________________________________________

```
### **8.Find tasks using a keyword**
Command: `find [KEYWORD]`

Response
```
find read
____________________________________________________________

Here are some task you could be looking for:
1. [D] [X] read (by:Dec 02 2023 18:00)
2. [E] [X] read (from:Dec 02 2023 18:00 to:Dec 03 2023 18:00)
3. [D] [ ] read (by:Dec 02 2023 18:00)
4. [E] [ ] read (from:Dec 02 2023 18:00 to:Dec 03 2023 08:30)
5. [T] [ ] read
6. [D] [ ] read (by:Dec 02 2023 17:00)
____________________________________________________________

```