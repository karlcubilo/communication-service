package com.communication.util;

import com.communication.base.BaseUnitTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArrayUtilsTest extends BaseUnitTest {

    @Test
    public void convertListToArray_whenListProvided_returnArray() {

        String[] arrays = ArrayUtils.convertListToArray(List.of("123"));
        assertThat(arrays[0], equalTo("123"));
    }

}
