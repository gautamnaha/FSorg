/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Employee;
import java.util.List;

/**
 *
 * @author gautamnaha
 */
public interface IEmployeeDao {

    boolean addEmployees(List<Employee> employees);

    boolean updateEmployees(List<Employee> employees);

    Employee getEmployee(int empNumber);

    List<Employee> getManagerReports(int managerNum);

    int getManagerForTeam(int teamId);

}
