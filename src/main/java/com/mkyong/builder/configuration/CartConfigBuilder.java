package com.mkyong.builder.configuration;

import com.mkyong.builder.Builder;
import com.mkyong.data.CommonPOJO;
import com.mkyong.exceptions.BuilderCreationException;
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

/**
 * Created by D-QU92GL on 19/02/2018.
 */
@Component("CartConfigBuilder")
@PropertySource("classpath:cartconfig.properties")
@ConfigurationProperties()
public class CartConfigBuilder extends ConfigBuilder{

    private static final Logger logger = LoggerFactory.getLogger(CartConfigBuilder.class);


    private List<String> builders ;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public Object build(Object request) {
        logger.debug("inside CartConfigBuilder build method ");
        logger.debug("Builders : "+ builders.toString());
        CommonPOJO commonPOJO = new CommonPOJO();
        try {
            //Prepare List of Builders
            getBuilders(builders)
            //Call Build on each Builder
           .forEach(builder->{
                // Build
                builder.build(commonPOJO);
                //Validate Builder
                final boolean validate = builder.validate();
            });

            logger.debug("commonPOJO : "+ commonPOJO);

        } catch (ValidationException | BuilderCreationException e) {
            e.printStackTrace();
        }catch (RuntimeException e){
            e.printStackTrace();
        }
        return commonPOJO;
    }



    public List<String> getBuilders() {
        return builders;
    }

    public void setBuilders(List<String> builders) {
        this.builders = builders;
    }


}
