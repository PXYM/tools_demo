package com.jf.genericity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author seinfish
 * @version 1.0
 * @description TODO
 * @date 2023/6/11 19:41
 */
public class GenTest {

    @Test
    public void testTypeErasure() {
        List<String> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        Class<? extends List> l1Class = l1.getClass();
        Class<? extends List> l2Class = l2.getClass();

        System.out.println(Arrays.asList(l1Class.getTypeParameters()));
        System.out.println(l2Class);
    }
}
