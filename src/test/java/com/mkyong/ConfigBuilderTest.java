package com.mkyong;

import com.mkyong.builder.Builder;
import com.mkyong.builder.configuration.ConfigBuilder;
import com.mkyong.builder.impl.Builder1Impl;
import com.mkyong.builder.impl.Builder2Impl;
import com.mkyong.data.CommonPOJO;
import com.mkyong.exceptions.BuilderCreationException;
import com.mkyong.exceptions.BuilderProcessException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
public class ConfigBuilderTest {

    private List<String> strBuilders;

    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private ConfigBuilder configBuilder = new ConfigBuilder();

    @Before
    public void setup(){
        strBuilders = new ArrayList<>();
        strBuilders.add("Builder1Impl");
        strBuilders.add("Builder2Impl");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getBuilders_Test() {
        Mockito.when(applicationContext.getBean(strBuilders.get(0)))
                .thenReturn(new Builder1Impl());
        Mockito.when(applicationContext.getBean(strBuilders.get(1)))
                .thenReturn(new Builder2Impl());
        assertEquals(configBuilder.getBuilders(strBuilders).size(),2);
    }

    @Test
    public void getBuilder_Test() {
        Mockito.when(applicationContext.getBean(strBuilders.get(0)))
                .thenReturn(new Builder1Impl());
        assertNotNull(configBuilder.getBuilder(strBuilders.get(0)));
    }

    @Test(expected = BuilderCreationException.class)
    public void Buildprocess_BuilderCreationException() throws ClassNotFoundException{
        Mockito.when(applicationContext.getBean(strBuilders.get(0)))
                .thenThrow(new NoSuchBeanDefinitionException("Invalid Bean Class Found"));
        configBuilder.getBuilder(strBuilders.get(0));
    }
}
