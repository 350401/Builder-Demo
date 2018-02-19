package com.mkyong;

import com.mkyong.builder.Builder;
import com.mkyong.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class WelcomeController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    private AppProperties app;

    @Qualifier("CartConfigBuilder")
    @Autowired
    private Builder builder;

    @Autowired
    public void setApp(AppProperties app) {
        this.app = app;
    }


    @RequestMapping("/hello")
    public String welcome(Map<String, Object> model) {

        String appProperties = app.toString();
         builder.build(null);
         logger.debug("Welcome {}, {}", app);
         return "welcome";
    }

    @PostMapping("/create")
    public ResponseEntity checkPersonInfo(@Valid Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.hasErrors());
        }

        return return ResponseEntity.ok("Employee Success");
    }

}
