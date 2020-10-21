
# Developer Guide 
## Table of content
### [1. Introduction](#1-Introduction1)
#### [1.1 Software Overview](#11-Software-Overview1)
#### [1.2 Purpose](#12-Purpose1)
#### [1.3 Scope](#13-Scope1)
### [2. Setting up and getting started](#2-Setting-up-and-getting-started1)
### [3. Design](#3-Design1)
#### [3.1 Architecture](#31-Architecture1)
#### [3.2 Classes](#32-Classes1)
##### [3.2.1 Ui class](#321-Ui-class1)
##### [3.2.2 Parser class](#322-Parser-class1)
##### [3.2.3 Module class](#323-Module-class1)
##### [3.2.4 Task class](#324-Task-class1)
##### [3.2.5 TimeTable class](#325-TimeTable-class1)
### [4. Implementation](#4-Implementation1)
### [5. Testing](#5-Testing1)
### [6. Appendix: Requirements](#6-Appendix-Requirements1)
#### [6.1 Product scope](#61-Product-scope1)
#### [6.1.1 Target user profile](#611-Target-user-profile1)
#### [6.1.2 Value proposition](#612-Value-proposition1)
#### [6.2 User stories](#62-User-stories1)



## 1. Introduction
### 1.1 Software Overview
CEGMods is a command line (CLI) application that helps computer engineering (CEG) students to manage their modules, tasks and projects. 

CEGMods will allow students to categorize their tasks based on their modules and automatically help prioritize them by deadlines. It will also help students manage project deadlines by splitting them into mini-milestones.
### 1.2 Purpose
This document describes the architecture and software design of CEGMods. The goal of this document is to cover the high-level system architecture and design.

The document is divided into three main parts: design, implementation and documantation. The design includes the architecture diagram and the introduction of each class. The implementation describes some details on how certain features are implemented. The documentation details the logging, testing and configuration of CEGMods. It also includes the requirement and the instructions for manual testing in the appendices.
### 1.3 Scope
The intended audience of this document is the developers, designers, and software testers of CEGMods.

## 2. Setting up and getting started
Setting up the project in your computer
> <font color=#FF5733>Caution: </font> <font color=#00000><font color=#00000>  **Please follow each steps in the guide carefully.** </font>

#### Before you start:
Please ensure you have Java 11 installed in you computer.

1. Fork this repo from this [link](https://github.com/AY2021S1-CS2113T-F11-2/tp.git)
2. Clone the fork to your own computer. You are highly recommended to use Git tool (like Sourcetree) to track your work. 
3. Use your own IDEA to program. You are highly recommended to use IntelliJ IDEA. Please check whether you have correct JDK version (JDK 11) in your computer and congiure the JDK.

    If you are using IntelliJ IDEA: 
    - Open IntelliJ
    - Set up JDK 11 for Gradle
        1. Click `Configure` -> `Project Defaults` -> `Project Structure`
        2. Click `New...` and set it to the directory of the JDK 11 installed
4. Import the project as a Gradle project.
    > <font color=#FF5733>Note: </font> <font color=#00000> It is different from importing a normal Java project.</font>

    If you are using IntelliJ IDEA:
    - Click `Import Project` (or `Open or Import` in newer version of Intellij).
    - Locate the `build.gradle` file (not the root folder as you would do in a normal importing) and select it. Click `OK`.
    - Click `OK` to accept the default settings but do ensure that the selected version of `Gradle JVM` matches the JDK being used for the project.
    - Wait for importing process to complete
5. Do the testing. Please follow the testing guide
   
## 3. Design 
### 3.1 Architecture
The ***Architecture diagram*** given below shows the major components, and explains the structure of the software system.
[![Architecture diagram](https://iili.io/3Ei2Vf.md.png)](https://freeimage.host/i/3Ei2Vf)

### 3.2 Classes

The CEGMods consists of six classes:
* `Main`
* `Ui`
* `Parser`
* `Module`
* `Task`
* `TimeTable`

#### 3.2.1 Ui class
The Ui class reads the uses's input and response relevant messages.
#### 3.2.2 Parser class
The `Parser` class deals with making sense of the user's command and execute the command relevantly:
- add new `Module` into `Timetable`.
- delte `Module` from `Timetable`.
- add new `Task` into tasklist.
- delete `Task` from tasklist.
- print out `Timetable` of a certain day or one week.
  
#### 3.2.3 Module class
The Module class contains the information of a module, including the title, description, and time slots of lectures, tutorials and labs.
#### 3.2.4 Task class
The `Task` class contains the information of a task, including the todo, event and deadline tasks.

#### 3.2.5 TimeTable class
The `Timetable` class shows the timetable for all the module slots and tasks. It also stores `Module` details in an arraylist and checks whether there is a time conflict between two classes.
       

## 4. Implementation
This section provides details on the implementations of certain features.
    
### Feature: Add a module

The user type in command in `module mod/<MODULE_CODE> lec/<LECTURE_DAY> <LECTURE_TIME> tut/<TUTORIAL_DAY> <TUTORIAL_TIME> lab/<LAB_DAY> <LAB_TIME>` this form to add a module into the timetable. The time slot of lab is optional. The program will detect whether the user command is in this form, and store module data in arraylist `modules` in `Timetable` class after Ui passing command and calling `addModule()` in Parser class.

The ***sequence diagram*** below shows the interaction between these classes when the user adds a module.
[![3GRaM7.md.png](https://iili.io/3GRaM7.md.png)](https://freeimage.host/i/3GRaM7)

### Feature: Delete a module
The user enters the command: `delete m/<MODULE_CODE>` to delete a module in the timetable. The `Parser` class will make sense of the command and enable the `deleteModule()` method in the `TimeTable` class.
    

## 5. Testing
- Running tests
- Types of tests

Running tests
-
There are two ways to run tests.

- **Method 1: Using IntelliJ JUnit test runner**
    - To run all tests, you can click `src` -> `test`, right-click on the `java` folder and choose `Run 'Tests in 'tp.test''`
    - To run a subset of tests, you can click `src` -> `test` -> `java` and right-click on a test package, test class, or a test and choose `Run 'ABC'`
- **Method 2: Using Gradle**
    - Click `Terminal` to open a new console and type in the command `gradlew clean test` to run a test (Mac/Linux: ./gradlew clean test)

Types of tests
-
This project has three types of tests: ...

## 6. Appendix: Requirements
### 6.1 Product scope
#### 6.1.1 Target user profile
This application is intended for users who
- are NUS Computer Engineering Students
- have a large variety of lesson (lab, tutorial, lecture, webcast) and commitment types (CCA, external commitments)
- have a certain type of homework based on standard formats (pre-lecture/ post-lecture/ pre-tutorial/ post-tutorial/ pre-lab/ post-lab)
- have various projects ongoing at the same time (individual, team, for technical modules and non-technical modules)
- are reasonably comfortable using CLI apps
#### 6.1.2 Value proposition 
- Manages Tasks based on:
    - the module: eg. CS2113T, CG2271, CS2101 (can be colour-coded according to modules)
    - tasks unrelated to modules can be placed under “others”
    - the deadline of the task: eg.pre-lecture/ post-lecture/ pre-tutorial/ post-tutorial/ pre-lab/ post-lab 
    - urgency: a chronological list of what is due the earliest
    - the amount of time taken for the task: range of 30min-3hours
- Manages Schedule based on:
    - NUSMods schedule, ie. modules
    - Webcast option, ie. can be shifted around
    - Team meetings (regular scheduled and ad hoc)
- Manages Project based on:
    - Deadlines of subtasks (subclass of task but for projects)
    - Percentage of completion 
### 6.2 User stories

| Priority level| As a/an | I want to | so that I can
| -------- | -------- | -------- |-------- |
| must-have    | new user     | add my modules |I can categorise my tasks and schedule
|must-have | new user|add my lecture slots|I can plan my timetable
|must-have|new user|add tutorial slots|I can plan my timetable
|must-have|user|add lab slots|I can plan my timetable
|must-have|user|view my timetable |I can go about my day following the planned timetable
|nict-to-have|user|view my project progress|I can manage my project 
|must-have|user|add subtasks of project |I can divide whole project in various subtasks and record them
|nice-to-have|user|create daily or weekly to-do list |I can view all the tasks to be completed
|must-have|user|add deadline to tasks(assignment, tutorials)|I can know when to complete
|must-have|user|prioritize tasks in to-do list by urgency level|I can plan what to do first 
|must-have|user|mark tasks as done or delete them|
|nice-to-have|user|add alarm to remind the upcoming event|I won't forget or miss relevant events
|nice-to-have|user|add periodic zoom links for online lectures/tutorials|I can join the zoom session quickly before the class
|nice-to-have|user|share project schedule and details with teammates|I can cooperate with my teammate better


