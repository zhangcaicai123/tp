# User Guide
CEGMods is a desktop application to manage NUS CEG's courses via a Command Line Interface (CLI). If you are an NUS CEG student and looking for an app to manage your course and project schedule, CEGMods can help you get all your tasks down in an organized manner.
## Content page
### [1. Quick Start](#1-quick-start-1)
### [2. Features](#2-features-1)
#### [2.1 Adding a Module: `add mod`](#21-adding-a-module-add-mod-1)
#### [2.2 Deleting a Module: `delete mod`](#22-deleting-a-module-delete-mod-1)
#### [2.3 Viewing Timetable: `timetable`](#23-viewing-timetable-timetable-1)
#### [2.4 Viewing Task List: `task list`](#24-viewing-to-do-list-task-list-1)
#### [2.5 Adding a Task:](#25-adding-a-task-1)
##### [2.5.1 Adding a Todo Task: `todo`](#251-adding-a-todo-task-todo-1) 
##### [2.5.2 Adding a Deadline Task: `deadline`](#252-adding-a-deadline-deadline)
##### [2.5.3 Adding a Event Task: `event`](#253-adding-an-event-event)
#### [2.6 Marking a Task as Done: `done`](#26-marking-a-task-as-done-done-1)
#### [2.7 Deleting a Task: `delete`](#27-deleting-a-task-delete-1)
#### [2.8 Finding a Task with Keyword: `find`](#28-Finding-a-Task-with-Keyword-find1)
#### [2.9 Adding a Project Task: `project task`](#29-Adding-a-Project-Task-project-task1)
#### [2.10 Viewing project task list: `project task list`](#210-Viewing-project-task-list-project-task-list1)
#### [2.11 Viewing project progress: `project progress`](#211-Viewing-project-progress-project-progress1)
#### [2.12 Exiting the Program: `exit`](#212-Exiting-the-Program-exit1)
#### [2.13 Viewing Help: `help`](#213-Viewing-Help-help1)
### [3. FAQ](#3-FAQ1)
### [4. Command Summary](#4-Command-Summary1)

## 1. Quick Start
1. Ensure you have Java 11 installed in your Computer.
2. Download the jar file from (link).
3. Copy the file to the folder you want to use as the home folder for your CEGmods
4. Open a command window. Run the java -version command to ensure you are using Java 11. 
5. Launch the jar file using the java -jar command (do not use double-clicking).
6. Type `help` in the command box as prompted and press Enter to execute it. You will see the  commands available.
6. Refer to the Features below for details of each command.

## 2. Features
### 2.1 Adding a Module: `add mod`
You can add a module to the module list and timetable by typing in the module code and its time slots of lectures and tutorials (the time slot of lab is optional).

Format: `add mod/<MODULE_CODE_IN>`

Example of usage: 

**Step 1:** Type in the module code of the module you want to add following the command format. 

For example: `add mod/CG2271`.

Then you will see the following: 
```
Module code: CG2271
Title: Real-Time Operating Systems
Description: Real-time systems must respond quickly to inputs from the environment in order to work effectively and safely, and realtime operating systems (RTOS) are a critical part of such systems. In this course the student is exposed to basic RTOS concepts like tasks, scheduling algorithms, RTOS customisation and concurrent real-time programming. By the end of this course a student will not only understand how an RTOS is built, but will also gain practical hands-on experience in customising RTOSs and in writing real-time programs.
_______________________________________________________
Please enter your time slots for lectures, tutorials, and labs for this module.
Please enter your time slots for lectures, tutorials, and labs for this module.
The format of the time slots is: Day HH:MM-HH:MM (Eg. Thur 12:00-13:00)
If the time slot does not exit, please enter null.
```
**Step 2:** Type in the lecture slot and tutorial slot of this module, and indicate whether there is lab for this module as prompted.

Format: `day HH:MM-HH:MM`
Example of usage: `Thur 12:00-13:00`

For example, if you key in the following:

Lecture slot: `Wed 09:00-11:00`
Tutorial slot: `Tue 08:00-10:00`
Does this modules have lab? [Y/N]
`Y`
Lab slot: `Fri 08:00-10:00`
You will see the following if it is successful:
```
Got it! I have add CG2271 to timetable.
```
If you have **conflicting module timeslots**, you will see the following as per the example:
```
OOPS!!! There is a time conflict.
_______________________________________________________
Which module do you want to keep? Please enter the module name.
Module: CS1010
Lecture Slot: Mon 13:00-14:00
Tutorial Slot: Fri 16:00-17:00
Lab Slot: null
_______________________________________________________
Module: CG2271
Lecture Slot: Wed 09:00-11:00
Tutorial Slot: Tue 08:00-10:00
Lab Slot: Fri 08:00-10:00
_______________________________________________________
```
Please enter the module you wish to keep, and you will see the following:
```
CG2271
Got it! I have add CG2271 to timetable.
```
### 2.2 Deleting a Module: `delete mod`
This will delete a module from the module list.

Format: `delete mod/<MODULE_CODE>`
Example of usage: `delete mod/CG1111`

Expected outcome:
```
Noted. I've removed this module.
```

### 2.3 Viewing Timetable: `timetable`
This will view the whole timetable.

Format: 
1.`today timetable`: viewing today's timetable
2.`this week timetable`: viewing weekly timetable

Expected outcome:
```
today timetable
_______________________________________________________
2020-10-21
_______________________________________________________
14:00-15:00 CG1111 tutorial
_______________________________________________________

```
```
weekly timetable
_______________________________________________________
2020-10-21
_______________________________________________________
14:00-15:00 CG1111 tutorial
_______________________________________________________
_______________________________________________________
2020-10-22
_______________________________________________________
_______________________________________________________
_______________________________________________________
2020-10-23
_______________________________________________________
_______________________________________________________
_______________________________________________________
2020-10-24
_______________________________________________________
10:00-12:00 CG1111 lecture
_______________________________________________________
_______________________________________________________
2020-10-25
_______________________________________________________
_______________________________________________________
_______________________________________________________
2020-10-26
_______________________________________________________
_______________________________________________________
_______________________________________________________
2020-10-27
_______________________________________________________
9:00-12:00 CG1111 lab
_______________________________________________________

```

### 2.4 Viewing Task List: `task list`
This will view the to-do list.

Format: `task list`

Constraint: The task list must contain at least 1 task.

Expected outcome:
```
____________________________________________________________
     Here are the tasks in your list:
      1.[T][T]debug project
      2.[D][F]project (by:2020-11-09 00:00)
____________________________________________________________
```
### 2.5 Adding a Task
#### 2.5.1 Adding a Todo Task: `todo`
This will add a task todo to the task list.

Format: `todo <DESCRIPTION> `
Example of usage: `todo read book`

Expected outcome:
```
____________________________________________________________
     Got it. I've added this task:
      [T][F]read book
     Now you have 2 tasks in the list.
____________________________________________________________
```
#### 2.5.2 Adding a Deadline: `deadline`
This will add a deadline to the task list.
Format: `deadline <DESCRIPTION> /by <YYYY-MM-DD HH-MM>`
Example of usage: `deadline lab report /by 2020-11-26 00:00`

Expected outcome:
```
____________________________________________________________
     Got it. I've added this task:
      [D][F]lab report (by:2020-11-26 00:00)
     Now you have 4 tasks in the list.
____________________________________________________________
```
#### 2.5.3 Adding an Event: `event`
This will add an event to the task list.
Format: `event <DESCRIPTION> /at <YYYY-MM-DD HH-MM>`
Example of usage: 
`event team meeting /at 2020-09-10 10:00`
Please type the duration of the event in hours:(e.g. 1, 0.5)
`1`

Expected outcome:
```
____________________________________________________________
     Got it. I've added this task:
      [E][F]party (at:2020-09-10 08:00)
     Now you have 5 tasks in the list.
    ____________________________________________________________
```
### 2.6 Marking a Task as Done: `done`
This will mark a task as done.

Format: `done task/<TASK_INDEX>`
Example of usage: `done task/2`

Expected outcome:
```
_______________________________________________________
 	Nice! I've marked this task as done:
	[T][T]lab report
_______________________________________________________

```
### 2.7 Deleting a Task: `delete`
This will delete a task from task list.

Format: `delete task/<TASK_INDEX>`
Example of usage: `delete task/1`

Expected outcome:
```
____________________________________________________________
	Noted. I've removed this task:
	   [T][T]lab report
    Now you have 1 task in the list.
    ____________________________________________________________
```
### 2.8 Finding a Task with Keyword `find`
This will find a task from task list with the keyword.

Format: `find <KEYWORD>`
Example of usage: `find book`

Expected outcome:
```
____________________________________________________________
	Here are the matching tasks in your list:
	1.[T][F]read book
____________________________________________________________
```
### 2.9 Adding a Project Task: `projectTask`
This will add a subtask of a specific project to the task list.

Format: `mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE>`
Example of usage: `mod/CG2271 ptask/write report by/Fri 23:59`

Expected outcome:
```
_______________________________________________________
Is there any material you need for this project task?
Enter your materials or NA
NA
Module: CG2271
ProjectTask: write report
By: Fri 23:59
Materials: NA
_______________________________________________________
    ____________________________________________________________
     Got it. I've added this task:
      [P][F]write report (by:Fri 23:59) material: NA
     Now you have 2 tasks in the list.
    ____________________________________________________________

```
### 2.10 Viewing project task list: `project task list`
This will view the project task list.

Format: `mod/<MODULE_CODE> project task list`
Example of usage: `mod/CG2271 project task list`

Expected outcome:
```
CG2271

1. [P][F]write report (by:Fri 23:59) material: NA
```
### 2.11 Viewing project progress: `project progress`
This will view the progress of a specific project.

Format: `mod/<MODULE_CODE> progress`
Example of usage: `mod/CG2271 progress`

Expected outcome:
```
CG2271
You have done 0/1 (0.00%).
```
### 2.12 Exiting the Program: `exit`
This will exit the program.

Format: `exit`

Expected outcome:
```
_______________________________________________________
Bye! Have a nice day with CEG!
_______________________________________________________
```
### 2.13 Viewing Help: `help`
This will view help message.

Format: `help`

Expected outcome:
```
_______________________________________________________
1. Add a module: add mod/<MODULE_CODE>
2. Delete a module: delete mod/<MODULE_CODE>
3. Add a task to do: todo <DESCRIPTION>
4. Add a deadline: deadline <DESCRIPTION> /by <YYYY-MM-DD HH-MM>
5. Add an event: event <DESCRIPTION> /at <YYYY-MM-DD HH-MM>
6. View today's timetable: today timetable
7. View weekly timetable: this week timetable
8. Add a project subtask: mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE>
9. View project task list: mod/<MODULE_CODE> project task list
10. View project progress: mod/<MODULE_CODE> progress
11. View task list: task list
12. Delete task: delete <TASK_INDEX>
13. Mark task as done: done <TASK_INDEX>
14. Exit CEGMods: exit
_______________________________________________________
```

## 3. FAQ

## 4. Command Summary

|Feature | Command |
|---|--- |
|Adding a Module| `add mod/<MODULE_CODE>`|
|Deleting a Module| `delete m/<MODULE_CODE>`|
|Viewing Timetable| `today timetable`, `this week timetable`|
|Viewing Task List| `task list`|
|Adding a Todo Task| `todo <DESCRIPTION>`| 
|Adding a Deadline Task| `deadline <DESCRIPTION> /by <YYYY-MM-DD HH-MM`|
|Adding an Event Task| `event <DESCRIPTION> /at <YYYY-MM-DD HH-MM>`| 
|Marking a Task as Done| `done <TASK_INDEX>`|
|Deleting a Task| `delete <TASK_INDEX>`|
|Finding a Task| `find/ <KEYWORD>`|
|Adding a Project Task| `mod/<MODULE_CODE> ptask/<DESCRIPTION> by/<DEADLINE>`|
|Viewing Project Task List| `mod/<MODULE_CODE> project task list`|
|Viewing project progress| `mod/<MODULE_CODE> progress`|
|Exiting the Program| `exit`|
|Viewing Help| `help`|
