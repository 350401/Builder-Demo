package com.mkyong;

import com.mkyong.builder.Builder;
import com.mkyong.builder.configuration.CartConfigBuilder;
import com.mkyong.builder.configuration.ConfigBuilder;
import com.mkyong.builder.impl.Builder1Impl;
import com.mkyong.builder.impl.Builder2Impl;
import com.mkyong.data.CommonPOJO;
import com.mkyong.exceptions.BuilderCreationException;
import com.mkyong.exceptions.BuilderProcessException;
import com.mkyong.exceptions.ValidationException;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
public class CartConfigBuilderTest {

    @InjectMocks
    private CartConfigBuilder cartConfigBuilder = new CartConfigBuilder();

    @Mock
    private ConfigBuilder configBuilder;

    private List<String> strBuilders;

    private List<Builder> builderObjList ;

    @Before
    public void setup(){
        System.out.print("SetUp Method " + configBuilder);
        strBuilders = new ArrayList<>();
        strBuilders.add("com.mkyong.builder.impl.Builder1Impl");
        strBuilders.add("com.mkyong.builder.impl.Builder2Impl");

        builderObjList = new ArrayList<>();
        try {
            builderObjList.add((Builder)Class.forName(strBuilders.get(0)).newInstance());
            builderObjList.add((Builder)Class.forName(strBuilders.get(1)).newInstance());
        }catch (Exception exp){
            exp.printStackTrace();
        }

        assertNotNull(builderObjList);
        assertEquals(strBuilders.size(),builderObjList.size());

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void validateBuilderList(){
        assertNotNull(builderObjList);
        assertEquals(strBuilders.size(),builderObjList.size());
    }

    @Test
    public void Buildprocess_Success() {
        cartConfigBuilder.setBuilders(strBuilders);
        Mockito.when(configBuilder.getBuilders(strBuilders)).thenReturn(builderObjList);
        CommonPOJO commonPOJO = new CommonPOJO();
        cartConfigBuilder.build(commonPOJO);
        System.out.print("  commonPOJO : " + commonPOJO);
        assertEquals(commonPOJO.getService1Data(),"OfferServiceData");
    }

    @Test(expected = BuilderProcessException.class)
    public void Buildprocess_ValidationException() {

        Builder builder = new Builder() {
            @Override
            public void build(Object request) {
                //Do Nothing
            }
            public boolean validate() throws ValidationException {
                throw new ValidationException("Validation Exception for testCase");
            }
        };

        Arrays.asList(builder);
        when(configBuilder.getBuilders(anyList())).thenReturn(Arrays.asList(builder));
        cartConfigBuilder.setBuilders(strBuilders);
        cartConfigBuilder.build(new CommonPOJO());
    }
}
