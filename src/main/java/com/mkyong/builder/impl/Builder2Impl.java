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
@Component("Builder2Impl")
public class Builder2Impl implements Builder {

    private static final Logger logger = LoggerFactory.getLogger(Builder2Impl.class);

    @Override
    public void build(Object request) {
        CommonPOJO commonPOJO = (CommonPOJO)request;
        commonPOJO.setService2Data("PriceServiceData");
        logger.debug("inside Builder1Imp2 build method ");
        //return commonPOJO;
    }

    @Override
    public boolean validate() throws ValidationException {
        return false;
    }
}
