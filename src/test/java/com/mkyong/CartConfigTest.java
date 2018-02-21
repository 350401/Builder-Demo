package com.mkyong;

import com.mkyong.builder.configuration.CartConfigBuilder;
import com.mkyong.data.CommonPOJO;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D-QU92GL on 21/02/2018.
 */
public class CartConfigTest extends BasicTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public CartConfigBuilder cartConfigBuilder() {
            return new CartConfigBuilder();
        }
    }

    @Autowired
    private CartConfigBuilder cartConfigBuilder;

    //@MockBean
    //private ApplicationContext applicationContext;

    @Test
    public void builderTest() {
        List<String> builders = new ArrayList<>();
        builders.add("com.mkyong.builder.impl.Builder1Impl");
        builders.add("com.mkyong.builder.impl.Builder2Impl");
        cartConfigBuilder.setBuilders(builders);
        //Mockito.when(cartConfigBuilder.getBuilders(builders)).then(null);
        CommonPOJO commonPOJO = new CommonPOJO();
        cartConfigBuilder.build(commonPOJO);
        System.out.print("  commonPOJO : " + commonPOJO);
    }
}
