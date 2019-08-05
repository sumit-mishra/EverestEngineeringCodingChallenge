package com.tameOfthrones.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tameofthrones.communicator.Communicator;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TameOfThronesApplicationTests {

    @Test
    public void initiateTheGame_ThenCommunicatorShouldRun() {
        Communicator communicator = new Communicator();
        Assert.assertEquals(true, communicator.isRunning());
    }

}
