package com.mkyong.builder.configuration;

import com.mkyong.builder.Builder;
import com.mkyong.exceptions.BuilderCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by D-QU92GL on 19/02/2018.
 */
@Component
public class ConfigBuilder {

    @Autowired
    private ApplicationContext applicationContext;

    public Builder getBuilder(String builderClass) throws BuilderCreationException {
        try {
            return (Builder) applicationContext.getBean(builderClass);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new BuilderCreationException(e.getMessage());
        }
    }

    public List<Builder> getBuilders(List<String> builders) throws BuilderCreationException {
            //Prepare List of Builders
            return builders.stream().map(this::getBuilder).collect(Collectors.toList());
    }
}
