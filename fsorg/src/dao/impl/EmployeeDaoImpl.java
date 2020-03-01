/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.impl;

import dao.IEmployeeDao;
import db.FSOrgDb;
import entity.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gautamnaha
 */
public class EmployeeDaoImpl implements IEmployeeDao {

    @Override
    public boolean addEmployees(List<Employee> employees) {
        for (Employee emp : employees) {
            FSOrgDb.insertEmployee(emp);
        }
        return true;
    }

    @Override
    public boolean updateEmployees(List<Employee> employees) {
        for (Employee emp : employees) {
            FSOrgDb.updateEmployee(emp);
        }
        return true;
    }

    @Override
    public List<Employee> getManagerReports(int managerNum) {
        List<Map<String, String>> list = FSOrgDb.getManagerReports(managerNum);
        List<Employee> result = new ArrayList<>();
        for (Map<String, String> map : list) {
            Employee e = new Employee();
            for (String key : map.keySet()) {
                switch (key.toLowerCase()) {
                    case "empnum":
                        e.setEmployeeNum(Integer.parseInt(map.get(key)));
                        break;
                    case "firstname":
                        e.setFirstName(map.get(key));
                        break;
                    case "lastname":
                        e.setLastName(map.get(key));
                        break;
                    case "role":
                        e.setRole(map.get(key));
                        break;
                    case "startdate":
                        e.setStartDate(map.get(key));
                        break;
                    case "managernum":
                        e.setManagerNum(Integer.parseInt(map.get(key)));
                        break;
                    case "level":
                        e.setLevel(Integer.parseInt(map.get(key)));
                        break;
                    case "teamid":
                        e.setTeamId(Integer.parseInt(map.get(key)));
                        break;
                    default:
                        break;
                }

            }
            result.add(e);
        }
        return result;
    }

    @Override
    public int getManagerForTeam(int teamId) {
        return FSOrgDb.getManagerForTeam(teamId);
    }

    @Override
    public Employee getEmployee(int empNumber) {
        try {
            return FSOrgDb.getEmployee(empNumber);
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
