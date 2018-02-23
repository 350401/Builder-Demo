package com.mkyong.service;

import com.mkyong.data.Employee;
import org.springframework.stereotype.Service;

/**
 * Created by D-QU92GL on 22/02/2018.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee getEmployeeByID(int employeeId) {
        Employee employee = new Employee();
        if(employeeId == 1){
            employee.setName("pavan");
            employee.setAge(30);
        }
        return employee;
    }
}
