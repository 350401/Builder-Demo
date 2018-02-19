package com.mkyong.builder;

import com.mkyong.exceptions.ValidationException;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by D-QU92GL on 19/02/2018.
 */
public interface Builder {

    Object build(Object request);
    default boolean validate() throws ValidationException {
        return true;
    }
}
