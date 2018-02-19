package com.mkyong;

import com.mkyong.builder.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}