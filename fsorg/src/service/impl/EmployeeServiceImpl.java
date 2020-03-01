/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IEmployeeDao;
import dao.impl.EmployeeDaoImpl;
import db.FSOrgDb;
import entity.Employee;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.IEmployeeService;

/**
 *
 * @author gautamnaha
 */
public class EmployeeServiceImpl implements IEmployeeService {

    IEmployeeDao dao;

    public EmployeeServiceImpl(IEmployeeDao dao) {
        this.dao = dao;
    }

    @Override
    public boolean addEmployeeService(List<Employee> employees) {
        dao.addEmployees(employees);
        return true;
    }

    @Override
    public boolean updateEmployeeService(List<Employee> employees) {
        dao.updateEmployees(employees);
        return true;
    }

    @Override
    public boolean updateEmployeeTeamService(int empNumber, int teamId) {
        Employee e = dao.getEmployee(empNumber);
        List<Employee> updateList = new ArrayList<>();
        if (isManager(e)) {
            Employee seniorEmployee = getSeniorEmployeeInTeam(e.getEmployeeNum());
            List<Employee> reportList = dao.getManagerReports(e.getEmployeeNum());
            for (Employee emp : reportList) {
                if (emp.getEmployeeNum() != seniorEmployee.getEmployeeNum()) {
                    emp.setManagerNum(seniorEmployee.getEmployeeNum());
                } else {
                    emp.setManagerNum(e.getManagerNum());
                    emp.setLevel(Employee.MANAGER_LEVEL);
                    emp.setRole(Employee.MANAGER_ROLE);
                }
            }
            updateList.addAll(reportList);
        }
        int managerNumForTeam = dao.getManagerForTeam(teamId);
        e.setManagerNum(managerNumForTeam);
        e.setTeamId(teamId);
        e.setLevel(Employee.EMPLOYEE_LEVEL);
        e.setRole(Employee.EMPLOYEE_ROLE);
        updateList.add(e);
        updateEmployeeService(updateList);
        return true;
    }

    boolean isManager(Employee e) {
        return e.getLevel() > 1;
    }

    private Employee getSeniorEmployeeInTeam(int empNum) {
        List<Employee> list = dao.getManagerReports(empNum);
        Collections.sort(list, (Employee e1, Employee e2) -> {
            try {
                Date date1 = new SimpleDateFormat("mm/dd/yyyy").parse(e1.getStartDate());
                Date date2 = new SimpleDateFormat("mm/dd/yyyy").parse(e2.getStartDate());
                return date1.compareTo(date2);
            } catch (ParseException ex) {
                Logger.getLogger(EmployeeServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        });
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean updateEmployeeHoliday(int empNumber) {
        Employee e = dao.getEmployee(empNumber);
        List<Employee> reports = dao.getManagerReports(e.getEmployeeNum());
        for (Employee report : reports) {
            report.setManagerNum(e.getManagerNum());
        }
        updateEmployeeService(reports);
        return true;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //       FSOrgDb db = FSOrgDb.getInstance();
        //int employeeNum, String firstName, String lastName, String role, int level, int managerNum,String startDate, int teamId
        Employee e = new Employee(801, "Neil", "Chawla", "Manager", 4, 991, "01/02/2000", 100);
        Employee e1 = new Employee(802, "Gautam", "Singh", "Employee", 5, 801, "01/02/2024", 100);
        Employee e2 = new Employee(803, "Harvey", "Parks", "Employee", 5, 801, "01/02/2010", 100);
        Employee e3 = new Employee(804, "Dave", "Cameron", "Employee", 5, 801, "01/02/2020", 100);
        Employee e4 = new Employee(805, "Umang", "Gandhi", "Employee", 5, 801, "01/02/2030", 100);
        Employee e5 = new Employee(806, "Dev", "Bahadur", "Manager", 4, 901, "01/02/2010", 102);
        Employee e6 = new Employee(807, "Bhavesh", "Patel", "Employee", 5, 806, "01/02/2020", 102);
        IEmployeeDao dao = new EmployeeDaoImpl();
        IEmployeeService obj = new EmployeeServiceImpl(dao);
        List<Employee> emps = new ArrayList<>();
        emps.add(e);
        emps.add(e1);
        emps.add(e2);
        emps.add(e3);
        emps.add(e4);
        emps.add(e5);
        emps.add(e6);
        obj.addEmployeeService(emps);
        System.out.println("Initial state...........");
        FSOrgDb.queryDB("employee");
        obj.updateEmployeeTeamService(801, 102);
        System.out.println("Employee Number 801 assigned to teamId 102...........");
        FSOrgDb.queryDB("employee");
        obj.updateEmployeeHoliday(803);
        System.out.println("Employee Number 803 on holiday...........");
        FSOrgDb.queryDB("employee");
        //       FSOrgDb.queryDB();
    }

}
