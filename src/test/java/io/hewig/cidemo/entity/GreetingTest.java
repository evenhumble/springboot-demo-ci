package io.hewig.cidemo.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class GreetingTest {

    @Test
    public void test_init(){
        Greeting g  = new Greeting(1000L,"test");
        assertEquals(g.getId(),1000);
        assertEquals(g.getContent(),"test");
    }

}