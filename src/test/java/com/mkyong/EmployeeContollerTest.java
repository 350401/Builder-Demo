package com.mkyong;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mkyong.data.Employee;
import com.mkyong.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.mock.mockito.*;

public class EmployeeContollerTest extends BasicTest {

   @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Mock
    private EmployeeService employeeService;

    @Test
    public void testEmployee() throws Exception {

        System.out.println(" employeeService : " + employeeService.getEmployeeByID(1));
        Employee emp = new Employee("Gazal", 27);
        Mockito.when(this.employeeService.getEmployeeByID(1))
                .thenReturn(new Employee("Gazal", 27));

        System.out.println(" employeeService after : " + employeeService.getEmployeeByID(1));

        mockMvc.perform(get("/employee/id")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.name").value("pavan"))
                .andExpect(jsonPath("$.age").value("30"));

    }

}