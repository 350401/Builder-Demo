package com.mkyong.builder.configuration;

import com.mkyong.builder.Builder;
import com.mkyong.exceptions.BuilderCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by D-QU92GL on 19/02/2018.
 */
public abstract class ConfigBuilder implements Builder {

    @Autowired
    private ApplicationContext applicationContext;

    public Builder getBuilder(String builderClass) throws BuilderCreationException {
        try {
            return (Builder) applicationContext.getBean(Class.forName(builderClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<Builder> getBuilders(List<String> builders) throws BuilderCreationException {
            //Prepare List of Builders
            return builders.stream().map(this::getBuilder).collect(Collectors.toList());
    }
}
