# Corporate Employee Management System

Welcome to the **Corporate Employee Management System**! This Java-based application is designed to help organizations manage their employees, departments, and projects efficiently. Built using **Object-Oriented Programming (OOP)** principles, this system provides a robust and user-friendly interface for handling corporate data.

---

## Features

1. **Employee Management**:
    - Add **Full-Time** and **Part-Time** employees.
    - Calculate bonuses for employees based on their type.
    - Display detailed employee information.

2. **Department Management**:
    - Create departments and assign employees to them.
    - Generate department reports with employee details.

3. **Project Management**:
    - Create projects and associate them with departments.
    - Display project details, including the assigned department.

4. **Corporate Overview**:
    - Generate a comprehensive corporate report summarizing departments, employees, and projects.

5. **Input Validation**:
    - Ensures all inputs are valid and within acceptable ranges.
    - Prompts users to re-enter data in case of invalid inputs.

---

## How It Works

### 1. **Employee Class Hierarchy**
- **`Employee`** (Abstract Class):
    - Base class for all employees.
    - Contains common attributes like `employeeID`, `name`, `age`, and `salary`.
    - Abstract methods: `calculateBonus()` and `displayDetails()`.
- **`FullTimeEmployee`**:
    - Inherits from `Employee`.
    - Includes additional attributes like `benefitsPacket` and calculates a 20% bonus.
- **`PartTimeEmployee`**:
    - Inherits from `Employee`.
    - Includes additional attributes like `hourlyRate` and `hoursWorked` and calculates a 10% bonus.

### 2. **Department Class**
- Manages a list of employees.
- Allows adding and removing employees.
- Generates a department report with employee details.

### 3. **Project Class**
- Associates projects with departments.
- Displays project details, including the assigned department.

### 4. **Corporate Class**
- Manages departments and projects.
- Generates a corporate report summarizing all departments and projects.

---

## Usage

### Running the Program
1. Clone the repository or download the source code.
2. Compile and run the `Fir10Pract` class.
3. Follow the on-screen prompts to:
    - Enter corporate details.
    - Add departments and employees.
    - Create projects.
    - Generate reports.

### Example Workflow
1. **Create Corporate**:
    - Enter the corporate name.
    - Specify the number of departments and projects.

2. **Add Departments**:
    - For each department:
        - Enter department ID and name.
        - Add employees (Full-Time or Part-Time).

3. **Create Projects**:
    - For each project:
        - Enter project ID and name.
        - Assign the project to a department.

4. **Generate Reports**:
    - View a detailed corporate report summarizing departments, employees, and projects.

---

## Code Structure

### Key Classes
- **`Fir10Pract`**:
    - Main class to run the program.
    - Handles user input and output.
- **`Employee`**:
    - Abstract base class for all employees.
- **`FullTimeEmployee`**:
    - Represents full-time employees.
- **`PartTimeEmployee`**:
    - Represents part-time employees.
- **`Department`**:
    - Manages employees within a department.
- **`Project`**:
    - Manages projects and their associated departments.
- **`Corporate`**:
    - Manages the entire corporate structure.

---

## Example Output

### Corporate Report
# Bash Script to Output Corporate Report

Below is a Bash script that, when executed, prints the following Corporate Report:

```bash
====================================================
Corporate Report for: TechCorp
====================================================

Departments Overview:
====================================================
Department Details:
Department ID: 101
Department Name: Engineering
Employee List:
Details about Full-Time Employee: 
Employee ID: 1
Employee name: John Doe
Employee age: 30
Employee salary: 5000.0
Benefits Package: Health Insurance
After Bonus
Employee salary: 6000.0
====================================================

Projects Overview:
====================================================
Project Details:
Project ID: 201
Project name: AI Development
Project department: Engineering
====================================================
```

---

## Technologies Used
- **Java**: Core programming language.
- **OOP Concepts**: Abstraction, Encapsulation, Inheritance, and Polymorphism.
- **Exception Handling**: Ensures robust input validation and error handling.

---

## How to Contribute
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Submit a pull request with a detailed description of your changes.

---

## Author
Aakash Modi  
[work.aakash.modi@gmail.com](mailto:work.aakash.modi@gmail.com)

---

# Enjoy managing your corporate data with ease! 🚀