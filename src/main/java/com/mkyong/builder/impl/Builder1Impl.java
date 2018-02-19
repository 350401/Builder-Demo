package com.mkyong.builder.impl;

import com.mkyong.builder.Builder;
import com.mkyong.data.CommonPOJO;
import com.mkyong.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by D-QU92GL on 19/02/2018.
 */
@Component("Builder1Impl")
public class Builder1Impl implements Builder{
    private static final Logger logger = LoggerFactory.getLogger(Builder1Impl.class);
    @Override
    public Object build(Object request) {
        CommonPOJO commonPOJO = (CommonPOJO)request;
        commonPOJO.setService1Data(" Set Response Recived from Offer Micro Service");
        logger.debug("inside Builder1Impl build method ");
        return commonPOJO;
    }

    @Override
    public boolean validate() throws ValidationException {
        return false;
    }
}
