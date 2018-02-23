package com.mkyong;

import com.mkyong.builder.Builder;
import com.mkyong.builder.configuration.CartConfigBuilder;
import com.mkyong.data.CommonPOJO;
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

        CartConfigBuilder cartConfigBuilder = new CartConfigBuilder();
        CommonPOJO commonPOJO = new CommonPOJO();
/*        cartConfigBuilder.build1234(mybuilder->{
            // Build
            mybuilder.build(commonPOJO);
            //Validate Builder
            final boolean validate = builder.validate();
        });*/
         cartConfigBuilder.build(commonPOJO);
         logger.debug("Welcome {}, {}", app);
         return "welcome";
    }

    @PostMapping("/create")
    public ResponseEntity checkPersonInfo(@Valid Employee employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.hasErrors());
        }

        return ResponseEntity.ok("Employee Success");
    }

}