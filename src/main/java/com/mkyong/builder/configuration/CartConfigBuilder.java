package com.mkyong.builder.configuration;

import com.mkyong.builder.Builder;
import com.mkyong.data.CommonPOJO;
import com.mkyong.exceptions.BuilderCreationException;
import com.mkyong.exceptions.BuilderProcessException;
import com.mkyong.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by D-QU92GL on 19/02/2018.
 */
@Component("CartConfigBuilder")
@PropertySource("classpath:cartconfig.properties")
@ConfigurationProperties()
public class CartConfigBuilder implements Builder{

    private static final Logger logger = LoggerFactory.getLogger(CartConfigBuilder.class);


    private List<String> builders ;

    @Autowired
    private ConfigBuilder configBuilder;

    @Override
    public void build(Object request) {
        logger.debug("inside CartConfigBuilder build method ");
        try {
            //Prepare List of Builders
            configBuilder.getBuilders(builders)
            //Call Build on each Builder
           .forEach(builder->{
                // Build
                builder.build(request);
                //Validate Builder
                final boolean validate = builder.validate();
            });

            logger.debug("request : "+ request);

        } catch (ValidationException | BuilderCreationException e) {
           // e.printStackTrace();
            throw new BuilderProcessException(e.getMessage());
        }/*catch (RuntimeException e){
            e.printStackTrace();
        }*/
     }
/*

    public void build1234(Consumer<Builder> action){
        //Prepare List of Builders
        configBuilder.getBuilders(builders)
                //Call Build on each Builder
                .forEach(builder->{
                    action.accept(builder);
                });
    }*/

    public void setBuilders(List<String> builders) {
        this.builders = builders;
    }


}
