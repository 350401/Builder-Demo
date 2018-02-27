package com.mkyong.config;

import com.mkyong.exceptions.ConstraintExceptionMapper;
import com.mkyong.resource.EmployeeResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Created by D-QU92GL on 27/02/2018.
 */
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        //packages("com.mkyong.resource");
        register(EmployeeResource.class);
        register(ConstraintExceptionMapper.class);
    }
}
