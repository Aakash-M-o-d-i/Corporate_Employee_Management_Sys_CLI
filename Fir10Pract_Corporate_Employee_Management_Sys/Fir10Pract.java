package Fir10Pract_Corporate_Employee_Management_Sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Fir10Pract_Corporate_Employee_Management_Sys.Fir10Pract.sc;

public class Fir10Pract {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean success = false;
        while (!success) {
            try {
                Corporate corporate = Corporate.createFromUserInput();
                corporate.generateCorporateReport();
                success = true; // Exit loop if successful
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again.\n");
            } finally {
                sc.nextLine(); // Clear the scanner buffer
            }
        }
        sc.close(); // Close the scanner after successful execution
    }
}

abstract class Employee {
    protected int employeeID;
    protected String name;
    protected int age;
    protected double salary;

    // Getters and Setters
    public int getEmployeeID() { return employeeID; }
    public void setEmployeeID(int employeeID) { this.employeeID = employeeID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    // Input method
    protected static void getCommonInput(Employee emp) {
        while (true) {
            try {
                System.out.print("Enter Employee ID: ");
                emp.setEmployeeID(sc.nextInt());
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        System.out.print("Enter Name: ");
        emp.setName(sc.nextLine());

        while (true) {
            try {
                System.out.print("Enter Age: ");
                emp.setAge(sc.nextInt());
                sc.nextLine(); // Consume newline
                if (emp.getAge() <= 0) {
                    throw new IllegalArgumentException("Age must be greater than 0.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        while (true) {
            try {
                System.out.print("Enter Base Salary: ");
                emp.setSalary(sc.nextDouble());
                sc.nextLine(); // Consume newline
                if (emp.getSalary() < 0) {
                    throw new IllegalArgumentException("Salary cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }
    }

    abstract public void calculateBonus();
    abstract public void displayDetails();
}

class FullTimeEmployee extends Employee {
    protected String benefitsPacket;
    protected double bonus;

    // Getters/Setters
    public String getBenefitsPacket() { return benefitsPacket; }
    public void setBenefitsPacket(String benefitsPacket) {
        this.benefitsPacket = benefitsPacket;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    @Override
    public void calculateBonus() {
        bonus = (salary * 20 / 100);
    }

    public static FullTimeEmployee createFromUserInput() {
        FullTimeEmployee emp = new FullTimeEmployee();
        getCommonInput(emp);

        // Define available benefits in a list
        List<String> benefitsList = List.of(
                "Health Insurance",
                "Dental Coverage",
                "Vision Care",
                "Life Insurance",
                "Disability Protection"
        );

        // Display options
        System.out.println("Enter Benefits Package:");
        for (int i = 0; i < benefitsList.size(); i++) {
            System.out.println("    " + (i + 1) + ". " + benefitsList.get(i));
        }
        try {
            System.out.print("Select Benefits: ");
            int choice = sc.nextInt() - 1;
            sc.nextLine(); // Consume newline

            // Validate input range
            if (choice < 0 || choice >= benefitsList.size()) {
                throw new IndexOutOfBoundsException("Invalid benefits selection.");
            }

            // Store the selected benefit
            emp.benefitsPacket = benefitsList.get(choice);

            // Set the selected benefit to the employee object
            emp.setBenefitsPacket(emp.benefitsPacket);
        } catch (Exception e) {
            System.out.println("Invalid input! Please enter a valid number.");
            sc.nextLine(); // Clear invalid input
        }

        emp.calculateBonus();
        return emp;
    }

    @Override
    public void displayDetails() {
        System.out.println("Details about Full-Time Employee: ");
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Employee name: " + name);
        System.out.println("Employee age: " + age);
        System.out.println("Employee salary: " + salary);
        System.out.println("Benefits Package: " + benefitsPacket);
        System.out.println("After Bonus");
        System.out.println("Employee salary: " + bonus);
    }
}

class PartTimeEmployee extends Employee {
    protected double hourlyRate;
    protected int hoursWorked;
    protected double bonus;

    // Getters/Setters
    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getBonus() { return bonus; }
    public void setBonus(double bonus) { this.bonus = bonus; }

    @Override
    public void calculateBonus() {
        bonus = (salary * 10 / 100);
    }

    // Input Method
    public static PartTimeEmployee createFromUserInput() {
        PartTimeEmployee emp = new PartTimeEmployee();
        getCommonInput(emp);

        while (true) {
            try {
                System.out.print("Enter Hourly Rate: ");
                emp.setHourlyRate(sc.nextDouble());
                sc.nextLine(); // Consume newline
                if (emp.getHourlyRate() < 0) {
                    throw new IllegalArgumentException("Hourly rate cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        while (true) {
            try {
                System.out.print("Enter Hours Worked: ");
                emp.setHoursWorked(sc.nextInt());
                sc.nextLine(); // Consume newline
                if (emp.getHoursWorked() < 0) {
                    throw new IllegalArgumentException("Hours worked cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        emp.calculateBonus();
        return emp;
    }

    @Override
    public void displayDetails() {
        System.out.println("Details of Part-Time Employee:");
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Employee name: " + name);
        System.out.println("Employee age: " + age);
        System.out.println("Employee salary: " + salary);
        System.out.println("Hour per rate: " + hourlyRate);
        System.out.println("Total hours worked: " + hoursWorked);
        System.out.println("After Bonus:");
        System.out.println("Employee salary: " + bonus);
    }
}

class Department {
    protected int deptID;
    protected String name;
    protected List<Employee> employeeList;

    // Getters and Setters
    public int getDeptID() { return deptID; }
    public void setDeptID(int deptID) { this.deptID = deptID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Employee> getEmployeeList() { return employeeList; }
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // Input Method
    public static Department createFromUserInput() {
        Department dept = new Department();

        while (true) {
            try {
                System.out.println("\n=== Create New Department ===");
                System.out.print("Enter Department ID: ");
                dept.setDeptID(sc.nextInt());
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        System.out.print("Enter Department Name: ");
        dept.setName(sc.nextLine());

        dept.setEmployeeList(new ArrayList<>());

        int empCount = 0;
        while (true) {
            try {
                System.out.print("How many employees in this department? ");
                empCount = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (empCount < 0) {
                    throw new IllegalArgumentException("Number of employees cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        for (int i = 0; i < empCount; i++) {
            System.out.println("\nAdding Employee " + (i + 1));
            int empType = 0;
            while (true) {
                try {
                    System.out.print("Is this a (1) Full-Time or (2) Part-Time employee? ");
                    empType = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (empType != 1 && empType != 2) {
                        throw new IllegalArgumentException("Invalid choice. Please enter 1 or 2.");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid input! Please enter 1 or 2.");
                    sc.nextLine(); // Clear invalid input
                }
            }

            Employee emp = null;
            if (empType == 1) {
                emp = FullTimeEmployee.createFromUserInput();
            } else {
                emp = PartTimeEmployee.createFromUserInput();
            }

            dept.addEmployee(emp);
        }

        return dept;
    }

    public void generateDepartmentReport() {
        System.out.println("Department Details:");
        System.out.println("Department ID: " + deptID);
        System.out.println("Department Name: " + name);
        System.out.println("Employee List:");
        for (Employee emp : employeeList) {
            emp.displayDetails();
            System.out.println("====================================================");
        }
    }
}

class Project {
    protected int projectID;
    protected String projectName;
    protected Department department;
    protected List<Employee> employees;

    // Getters and Setters
    public int getProjectID() { return projectID; }
    public void setProjectID(int projectID) { this.projectID = projectID; }

    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() { return employees; }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    // Input Method
    public static Project createFromUserInput(List<Department> departments) {
        Project proj = new Project();

        while (true) {
            try {
                System.out.println("\n=== Create New Project ===");
                System.out.print("Enter Project ID: ");
                proj.setProjectID(sc.nextInt());
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        System.out.print("Enter Project Name: ");
        proj.setProjectName(sc.nextLine());

        while (true) {
            try {
                System.out.println("Available Departments:");
                for (int i = 0; i < departments.size(); i++) {
                    System.out.println((i + 1) + ". " + departments.get(i).getName());
                }
                System.out.print("Select Department: ");
                int choice = sc.nextInt() - 1;
                sc.nextLine(); // Consume newline
                if (choice < 0 || choice >= departments.size()) {
                    throw new IndexOutOfBoundsException("Invalid department selection.");
                }
                proj.setDepartment(departments.get(choice));
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        return proj;
    }

    public void displayProjectDetails() {
        System.out.println("Project Details:");
        System.out.println("Project ID: " + projectID);
        System.out.println("Project name: " + projectName);
        System.out.println("Project department: " + department.getName());
    }
}

class Corporate {
    protected String name;
    protected List<Department> departmentList;
    protected List<Project> projectList;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Department> getDepartmentList() { return departmentList; }
    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Project> getProjectList() { return projectList; }
    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void addDepartment(Department department) {
        departmentList.add(department);
    }

    public void assignProject(Project project) {
        projectList.add(project);
    }

    // Input Method
    public static Corporate createFromUserInput() {
        Corporate corp = new Corporate();

        System.out.println("=== Create New Corporate ===");
        System.out.print("Enter Corporate Name: ");
        corp.setName(sc.nextLine());

        int deptCount = 0;
        while (true) {
            try {
                System.out.print("How many departments? ");
                deptCount = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (deptCount < 0) {
                    throw new IllegalArgumentException("Number of departments cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        List<Department> departments = new ArrayList<>();
        for (int i = 0; i < deptCount; i++) {
            departments.add(Department.createFromUserInput());
        }
        corp.setDepartmentList(departments);

        int projCount = 0;
        while (true) {
            try {
                System.out.print("\nHow many projects? ");
                projCount = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (projCount < 0) {
                    throw new IllegalArgumentException("Number of projects cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        List<Project> projects = new ArrayList<>();
        for (int i = 0; i < projCount; i++) {
            projects.add(Project.createFromUserInput(departments));
        }
        corp.setProjectList(projects);

        return corp;
    }

    public void generateCorporateReport() {
        System.out.println("====================================================");
        System.out.println("Corporate Report for: " + name);
        System.out.println("====================================================");

        System.out.println("\nDepartments Overview:");
        System.out.println("====================================================");
        for (Department dept : departmentList) {
            dept.generateDepartmentReport();
        }

        System.out.println("\nProjects Overview:");
        System.out.println("====================================================");
        for (Project prj : projectList) {
            prj.displayProjectDetails();
        }
        System.out.println("====================================================");
    }
}