
import java.util.*;

abstract class Employee {
    private String name;
    private int id;

    // Correct constructor
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee[name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public PartTimeEmployee(String name, int id, double hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

class PayrollSystem {
    private ArrayList<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee toRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                toRemove = employee;
                break;
            }
        }
        if (toRemove != null) {
            employeeList.remove(toRemove);
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payroll = new PayrollSystem();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Payroll System Menu ---");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String ftName = sc.next();
                    System.out.print("Enter ID: ");
                    int ftId = sc.nextInt();
                    System.out.print("Enter monthly salary: ");
                    double salary = sc.nextDouble();
                    payroll.addEmployee(new FullTimeEmployee(ftName, ftId, salary));
                    break;

                case 2:
                    System.out.print("Enter name: ");
                    String ptName = sc.next();
                    System.out.print("Enter ID: ");
                    int ptId = sc.nextInt();
                    System.out.print("Enter hourly rate: ");
                    double rate = sc.nextDouble();
                    System.out.print("Enter hours worked: ");
                    int hours = sc.nextInt();
                    payroll.addEmployee(new PartTimeEmployee(ptName, ptId, rate, hours));
                    break;

                case 3:
                    System.out.print("How many employees do you want to remove? ");
                    int count = sc.nextInt();
                    for (int i = 0; i < count; i++) {
                        System.out.print("Enter employee ID to remove: ");
                        int id = sc.nextInt();
                        payroll.removeEmployee(id);
                    }
                    break;

                case 4:
                    payroll.displayEmployees();
                    break;

                case 5:
                    running = false;
                    System.out.println("Exiting Payroll System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        sc.close();
    }
}