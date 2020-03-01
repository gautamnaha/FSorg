/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Employee;
import java.util.List;

/**
 *
 * @author gautamnaha
 */
public interface IEmployeeService {

    boolean addEmployeeService(List<Employee> employees);

    boolean updateEmployeeService(List<Employee> employees);

    boolean updateEmployeeTeamService(int empNumber, int teamId);

    boolean updateEmployeeHoliday(int empNumber);
}
