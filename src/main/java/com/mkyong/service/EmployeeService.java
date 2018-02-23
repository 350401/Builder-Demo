package com.mkyong.service;

import com.mkyong.data.Employee;
import org.springframework.stereotype.Service;

/**
 * Created by D-QU92GL on 22/02/2018.
 */
public interface EmployeeService {
    public Employee getEmployeeByID(int employeeId);
}
