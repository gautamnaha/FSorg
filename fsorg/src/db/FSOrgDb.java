/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import entity.Employee;
import entity.Team;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gautamnaha
 */
public final class FSOrgDb {

    static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    static final String JDBC_URL = "jdbc:derby:fsorgdb;create=true";
    static String QUERY_EMPLOYEE = "select * from employee";
    static String QUERY_TEAM = "select * from team";
    static Connection conn;
    static FSOrgDb instance;

    static {
        try {
            init();
            createDB();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FSOrgDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void init() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(JDBC_URL);
        dropDB();
    }

    private static void dropDB() {
        try {
            conn.createStatement().execute("drop table employee");
            conn.createStatement().execute("drop table team");
        } catch (SQLException ex) {
            Logger.getLogger(FSOrgDb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void createDB() throws ClassNotFoundException, SQLException {
        //conn.createStatement().execute("drop table employee");
        try {
            conn.createStatement().execute("create table employee (empnum int primary key, firstname varchar(10), lastname varchar(10), role varchar(10), startdate varchar(10), managernum int, level int, teamid int)");
            conn.createStatement().execute("create table team (teamid int, teamname varchar(20))");
        } catch (Exception e) {
        }
    }

    public static boolean insertTeam(Team t) {
        final String insertStr = "insert into team values(" + t.getTeamId() + ","
                + "'" + t.getName() + "'" + ")";
        try {
            conn.createStatement().execute(insertStr);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static int getManagerForTeam(int teamId) {
        final String query = "select empnum from employee where teamid = " + teamId + " and level = 4";
        int r = 0;
        try {
            ResultSet resultSet = conn.createStatement().executeQuery(query);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                //       Map<String, String> map = new HashMap<>();
                for (int j = 1; j <= columnCount; j++) {
                    r = resultSet.getInt(j);
                }
            }
            //   r = resultSet.getInt(1);
        } catch (SQLException ex) {

        }
        return r;
    }

    public static boolean insertEmployee(Employee e) {
        final String insertStr = "insert into employee values(" + e.getEmployeeNum() + ","
                + "'" + e.getFirstName() + "'" + ","
                + "'" + e.getLastName() + "'" + ","
                + "'" + e.getRole() + "'" + ","
                + "'" + e.getStartDate() + "'" + ","
                + e.getManagerNum() + ","
                + e.getLevel() + ","
                + e.getTeamId() + ")";
        //       System.out.println(insertStr);
        try {
            conn.createStatement().execute(insertStr);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static List<Map<String, String>> getManagerReports(int managerNum) {
        final String query = "select * from employee where managernum = " + managerNum;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Map<String, String>> list = new ArrayList<>();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, String> map = new HashMap<>();
                for (int j = 1; j <= columnCount; j++) {
                    map.put(resultSetMetaData.getColumnName(j), resultSet.getString(j));
                }
                list.add(map);
            }
            //    System.out.println(list);
            return list;
        } catch (SQLException e) {
            return null;
        }
    }

    public static void queryDB(String table) throws SQLException {
        String query = "select * from " + table;
        if (table.equals("employee")) {
            query += " order by teamid, level";
        }
        System.out.println("TABLE : " + table.toUpperCase());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            System.out.format("%15s", resultSetMetaData.getColumnName(i) + " | ");
        }
        while (resultSet.next()) {
            System.out.println();
            for (int j = 1; j <= columnCount; j++) {
                System.out.format("%15s", resultSet.getString(j) + " | ");
            }
        }
        System.out.println();
        System.out.println();
    }

    public static boolean updateEmployee(Employee e) {
        //       empnum , firstname , lastname , role , startdate , managernum, level , teamid
        final String updateStr = "update employee set firstname = " + "'" + e.getFirstName() + "'"
                + ", lastname = " + "'" + e.getLastName() + "'"
                + ", role = " + "'" + e.getRole() + "'"
                + ", startdate = " + "'" + e.getStartDate() + "'"
                + ", managernum = " + e.getManagerNum()
                + ", level = " + e.getLevel()
                + ", teamid = " + e.getTeamId()
                + " where empnum = " + e.getEmployeeNum();
        try {
            conn.createStatement().execute(updateStr);
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public static Employee getEmployee(int empNumber) throws SQLException {
        String query = "select * from employee where empnum = " + empNumber;
//        System.out.println("TABLE : " + table.toUpperCase());
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        Employee e = new Employee();
        while (resultSet.next()) {
            //   System.out.println()
            for (int j = 1; j <= columnCount; j++) {
                String s = resultSetMetaData.getColumnName(j);
                String val = resultSet.getString(j);
                switch (s.toLowerCase()) {
                    case "empnum":
                        e.setEmployeeNum(Integer.parseInt(val));
                        break;
                    case "firstname":
                        e.setFirstName(val);
                        break;
                    case "lastname":
                        e.setLastName(val);
                        break;
                    case "role":
                        e.setRole(val);
                        break;
                    case "startdate":
                        e.setStartDate(val);
                        break;
                    case "managernum":
                        e.setManagerNum(Integer.parseInt(val));
                        break;
                    case "level":
                        e.setLevel(Integer.parseInt(val));
                        break;
                    case "teamid":
                        e.setTeamId(Integer.parseInt(val));
                        break;
                    default:
                        break;
                }
            }
        }
        return e;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        FSOrgDb obj = new FSOrgDb();
        obj.dropDB();
        obj.createDB();
        Team t1 = new Team(100, "Platform");
        Team t2 = new Team(102, "R&D");
        insertTeam(t1);
        insertTeam(t2);
        queryDB("team");
        Employee e = new Employee(201, "David", "Dhawan", "VP", 2, 1, "01/02/2000", 200);
        Employee e1 = new Employee(1, "Tom", "Singh", "CEO", 1, -1, "01/02/2005", 200);
        insertEmployee(e);
        insertEmployee(e1);
        queryDB("employee");
        getManagerReports(1);
    }
}
