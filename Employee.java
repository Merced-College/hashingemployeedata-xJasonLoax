// Jason Loa
// 11/13/25
// Employee class to hold employee data

import java.util.Objects;

public class Employee {
    String lastName;
    String firstName;
    String jobTitle;
    String department;
    double annualSalary;
    double estimatedAnnualMinusFurloughs;

    public Employee(String lastName, String firstName, String jobTitle,
                    String department, double annualSalary, double estMinusFurloughs) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.jobTitle = jobTitle;
        this.department = department;
        this.annualSalary = annualSalary;
        this.estimatedAnnualMinusFurloughs = estMinusFurloughs;
    }

    // add a no-argument constructor
    public Employee() {
        this.lastName = "";
        this.firstName = "";
        this.jobTitle = "";
        this.department = "";
        this.annualSalary = 0.0;
        this.estimatedAnnualMinusFurloughs = 0.0;
    }

    //add setters and getters for all fields
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getEstimatedAnnualMinusFurloughs() {
        return estimatedAnnualMinusFurloughs;
    }

    public void setEstimatedAnnualMinusFurloughs(double estimatedAnnualMinusFurloughs) {
        this.estimatedAnnualMinusFurloughs = estimatedAnnualMinusFurloughs;
    }

    //add an equals() and hashCode() method (use lastName, firstName, and department to determine equality)

    //this hash code method was made for standardization and will not
    // be used in our current implementation
    // public int hashCode() {
    //     return (firstName + lastName).hashCode();
    // }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + department + ")";
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        
        return lastName.equalsIgnoreCase(employee.lastName) &&
           firstName.equalsIgnoreCase(employee.firstName) &&
           department.equalsIgnoreCase(employee.department);
    }

    
    @Override
    public int hashCode() {
        return Objects.hash(
            lastName == null ? 0 : lastName.toLowerCase(),
            firstName == null ? 0 : firstName.toLowerCase(),
            department == null ? 0 : department.toLowerCase()
        );
    }


}
