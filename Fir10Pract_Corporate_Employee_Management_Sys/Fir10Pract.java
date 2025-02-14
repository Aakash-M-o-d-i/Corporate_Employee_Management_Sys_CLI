
package Fir10Pract_Corporate_Employee_Management_Sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Fir10Pract_Corporate_Employee_Management_Sys.Fir10Pract.sc;

public class Fir10Pract {
    public static Scanner sc = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void clearConsole() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("linux") || os.contains("mac")) {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Error while trying to clear the console.");
        }
    }

    public static void main(String[] args) {
        clearConsole();
        boolean success = false;
        while (!success) {
            try {
                Corporate corporate = Corporate.createFromUserInput();
                clearConsole();
                corporate.generateCorporateReport();
                System.out.println(Fir10Pract.ANSI_GREEN+"\n======================= Details Successfully Added!. =======================");
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
                System.out.print(Fir10Pract.ANSI_CYAN + "Enter Employee ID: " + Fir10Pract.ANSI_RESET);
                emp.setEmployeeID(sc.nextInt());
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        System.out.print(Fir10Pract.ANSI_CYAN + "Enter Name: " + Fir10Pract.ANSI_RESET);
        emp.setName(sc.nextLine());

        while (true) {
            try {
                System.out.print(Fir10Pract.ANSI_CYAN + "Enter Age: " + Fir10Pract.ANSI_RESET);
                emp.setAge(sc.nextInt());
                sc.nextLine(); // Consume newline
                if (emp.getAge() <= 0) {
                    throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Age must be greater than 0.");
                }
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        while (true) {
            try {
                System.out.print(Fir10Pract.ANSI_CYAN + "Enter Base Salary: " + Fir10Pract.ANSI_RESET);
                emp.setSalary(sc.nextDouble());
                sc.nextLine(); // Consume newline
                if (emp.getSalary() < 0) {
                    throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Salary cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
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
        while (true) {
            System.out.println(Fir10Pract.ANSI_CYAN + "Enter Benefits Package:" + Fir10Pract.ANSI_RESET);
            for (int i = 0; i < benefitsList.size(); i++) {
                System.out.println(Fir10Pract.ANSI_CYAN + "    " + (i + 1) + ". " + benefitsList.get(i) + Fir10Pract.ANSI_RESET);
            }
            try {
                System.out.print(Fir10Pract.ANSI_CYAN + "Select Benefits: " + Fir10Pract.ANSI_RESET);
                int choice = sc.nextInt() - 1;
                sc.nextLine(); // Consume newline

                // Validate input range
                if (choice < 0 || choice >= benefitsList.size()) {
                    throw new IndexOutOfBoundsException(Fir10Pract.ANSI_RED + "Invalid benefits selection.");
                }

                // Store the selected benefit
                emp.benefitsPacket = benefitsList.get(choice);

                // Set the selected benefit to the employee object
                emp.setBenefitsPacket(emp.benefitsPacket);
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED + "Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        emp.calculateBonus();
        return emp;
    }

    @Override
    public void displayDetails() {
        System.out.println(Fir10Pract.ANSI_GREEN + "Details about Full-Time Employee: " + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee ID: " + employeeID + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee name: " + name + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee age: " + age + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee salary: " + salary + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Benefits Package: " + benefitsPacket + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "After Bonus" + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee salary: " + bonus + Fir10Pract.ANSI_RESET);
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
                System.out.print(Fir10Pract.ANSI_CYAN + "Enter Hourly Rate: " + Fir10Pract.ANSI_RESET);
                emp.setHourlyRate(sc.nextDouble());
                sc.nextLine(); // Consume newline
                if (emp.getHourlyRate() < 0) {
                    throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Hourly rate cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        while (true) {
            try {
                System.out.print(Fir10Pract.ANSI_CYAN + "Enter Hours Worked: " + Fir10Pract.ANSI_RESET);
                emp.setHoursWorked(sc.nextInt());
                sc.nextLine(); // Consume newline
                if (emp.getHoursWorked() < 0) {
                    throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Hours worked cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        emp.calculateBonus();
        return emp;
    }

    @Override
    public void displayDetails() {
        System.out.println(Fir10Pract.ANSI_GREEN + "Details of Part-Time Employee:" + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee ID: " + employeeID + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee name: " + name + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee age: " + age + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee salary: " + salary + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Hour per rate: " + hourlyRate + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Total hours worked: " + hoursWorked + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "After Bonus:" + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee salary: " + bonus + Fir10Pract.ANSI_RESET);
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
                System.out.println(Fir10Pract.ANSI_CYAN + "\n=== Create New Department ===" + Fir10Pract.ANSI_RESET);
                System.out.print(Fir10Pract.ANSI_CYAN + "Enter Department ID: " + Fir10Pract.ANSI_RESET);
                dept.setDeptID(sc.nextInt());
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        System.out.print(Fir10Pract.ANSI_CYAN + "Enter Department Name: " + Fir10Pract.ANSI_RESET);
        dept.setName(sc.nextLine());

        dept.setEmployeeList(new ArrayList<>());

        int empCount = 0;
        while (true) {
            try {
                System.out.print(Fir10Pract.ANSI_CYAN + "How many employees in this department? " + Fir10Pract.ANSI_RESET);
                empCount = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (empCount < 0) {
                    throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Number of employees cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        for (int i = 0; i < empCount; i++) {
            System.out.println(Fir10Pract.ANSI_CYAN + "\nAdding Employee " + (i + 1) + Fir10Pract.ANSI_RESET);
            int empType = 0;
            while (true) {
                try {
                    System.out.print(Fir10Pract.ANSI_CYAN + "Is this a (1) Full-Time or (2) Part-Time employee? " + Fir10Pract.ANSI_RESET);
                    empType = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    if (empType != 1 && empType != 2) {
                        throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Invalid choice. Please enter 1 or 2.");
                    }
                    break;
                } catch (Exception e) {
                    System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter 1 or 2.");
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
        System.out.println(Fir10Pract.ANSI_GREEN + "Department Details:" + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Department ID: " + deptID + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Department Name: " + name + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN + "Employee List:" + Fir10Pract.ANSI_RESET);
        for (Employee emp : employeeList) {
            emp.displayDetails();
            System.out.println(Fir10Pract.ANSI_GREEN + "====================================================" + Fir10Pract.ANSI_RESET);
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
                System.out.println(Fir10Pract.ANSI_CYAN + "\n=== Create New Project ===" + Fir10Pract.ANSI_RESET);
                System.out.print(Fir10Pract.ANSI_CYAN + "Enter Project ID: " + Fir10Pract.ANSI_RESET);
                proj.setProjectID(sc.nextInt());
                sc.nextLine(); // Consume newline
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        System.out.print(Fir10Pract.ANSI_CYAN + "Enter Project Name: " + Fir10Pract.ANSI_RESET);
        proj.setProjectName(sc.nextLine());

        while (true) {
            try {
                System.out.println(Fir10Pract.ANSI_CYAN + "Available Departments:" + Fir10Pract.ANSI_RESET);
                for (int i = 0; i < departments.size(); i++) {
                    System.out.println(Fir10Pract.ANSI_CYAN + (i + 1) + ". " + departments.get(i).getName() + Fir10Pract.ANSI_RESET);
                }
                System.out.print(Fir10Pract.ANSI_CYAN + "Select Department: " + Fir10Pract.ANSI_RESET);
                int choice = sc.nextInt() - 1;
                sc.nextLine(); // Consume newline
                if (choice < 0 || choice >= departments.size()) {
                    throw new IndexOutOfBoundsException(Fir10Pract.ANSI_RED+"Invalid department selection.");
                }
                proj.setDepartment(departments.get(choice));
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
            }
        }

        return proj;
    }

    public void displayProjectDetails() {
        System.out.println(Fir10Pract.ANSI_GREEN + "Project Details:" + Fir10Pract.ANSI_RESET);
        System.out.println(Fir10Pract.ANSI_GREEN +"Project ID: " + projectID);
        System.out.println(Fir10Pract.ANSI_GREEN +"Project name: " + projectName);
        System.out.println(Fir10Pract.ANSI_GREEN +"Project department: " + department.getName());
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
        System.out.print(Fir10Pract.ANSI_CYAN +"Enter Corporate Name: ");
        corp.setName(sc.nextLine());

        int deptCount = 0;
        while (true) {
            try {
                System.out.print(Fir10Pract.ANSI_CYAN+"How many departments? ");
                deptCount = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (deptCount < 0) {
                    throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Number of departments cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
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
                System.out.print(Fir10Pract.ANSI_CYAN+"\nHow many projects? ");
                projCount = sc.nextInt();
                sc.nextLine(); // Consume newline
                if (projCount < 0) {
                    throw new IllegalArgumentException(Fir10Pract.ANSI_RED+"Number of projects cannot be negative.");
                }
                break;
            } catch (Exception e) {
                System.out.println(Fir10Pract.ANSI_RED+"Invalid input! Please enter a valid number.");
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
        System.out.println(Fir10Pract.ANSI_GREEN+"====================================================");
        System.out.println(Fir10Pract.ANSI_GREEN+"Corporate Report for: " + name);
        System.out.println(Fir10Pract.ANSI_GREEN+"====================================================");

        System.out.println(Fir10Pract.ANSI_GREEN+"\nDepartments Overview:");
        System.out.println(Fir10Pract.ANSI_GREEN+"====================================================");
        for (Department dept : departmentList) {
            dept.generateDepartmentReport();
        }

        System.out.println(Fir10Pract.ANSI_GREEN+"\nProjects Overview:");
        System.out.println(Fir10Pract.ANSI_GREEN+"====================================================");
        for (Project prj : projectList) {
            prj.displayProjectDetails();
        }
        System.out.println(Fir10Pract.ANSI_GREEN+"====================================================");
    }
}