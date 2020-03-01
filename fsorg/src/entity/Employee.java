/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author gautamnaha
 */
public class Employee {

    int employeeNum;
    String firstName;
    String lastName;
    String role;
    int level;
    int managerNum;
    String startDate;
    int teamId;

    public static final int EMPLOYEE_LEVEL = 5;
    public static final int MANAGER_LEVEL = 4;
    public static final int DIRECTOR_LEVEL = 3;
    public static final int VP_LEVEL = 2;
    public static final int CEO_LEVEL = 1;
    public static final String EMPLOYEE_ROLE = "Employee";
    public static final String MANAGER_ROLE = "Manager";
    public static final String DIRECTOR_ROLE = "Director";
    public static final String VP_ROLE = "Vice President";
    public static final String CEO_ROLE = "CEO";

    public Employee() {

    }

    public Employee(int employeeNum, String firstName, String lastName, String role, int level, int managerNum,
            String startDate, int teamId) {
        this.employeeNum = employeeNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.level = level;
        this.managerNum = managerNum;
        this.startDate = startDate;
        this.teamId = teamId;
    }

    /**
     * @return the employeeNum
     */
    public int getEmployeeNum() {
        return employeeNum;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the managerNum
     */
    public int getManagerNum() {
        return managerNum;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @return the teamId
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * @param employeeNum the employeeNum to set
     */
    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @param managerNum the managerNum to set
     */
    public void setManagerNum(int managerNum) {
        this.managerNum = managerNum;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @param teamId the teamId to set
     */
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

}
