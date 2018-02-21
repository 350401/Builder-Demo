package com.mkyong;

import com.mkyong.data.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by D-QU92GL on 21/02/2018.
 */
@RestController
public class EmployeeController {

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public Employee firstPage() {

        Employee emp = new Employee();
        emp.setName("emp1");
        emp.setAge(3000);

        return emp;
    }
}
