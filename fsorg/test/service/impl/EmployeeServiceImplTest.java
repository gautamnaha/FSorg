/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import dao.IEmployeeDao;
import entity.Employee;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

/**
 *
 * @author gautamnaha
 */
public class EmployeeServiceImplTest {

    IEmployeeDao dao;
    EmployeeServiceImpl instance;

    public EmployeeServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        //    when(dao.addEmployee(e))
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        dao = mock(IEmployeeDao.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addEmployeeService method, of class EmployeeServiceImpl.
     */
    @Test
    public void testAddEmployeeService_success() {
        System.out.println("addEmployeeService");
        Employee e = new Employee(802, "Manpreet", "Chawla", "Director", 3, 990, "01/02/2019", 100);
        List<Employee> list = new ArrayList<>();
        list.add(e);
        Mockito.when(dao.addEmployees(list)).thenReturn(Boolean.TRUE);
        instance = new EmployeeServiceImpl(dao);
        boolean op = instance.addEmployeeService(list);
        assertEquals(op, true);
        Mockito.verify(dao, Mockito.times(1)).addEmployees(list);

    }

}
