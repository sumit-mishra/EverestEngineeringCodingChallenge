package com.tameOfthrones.test.communicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.tameofthrones.constants.Script;
import com.tameofthrones.operation.Quest;
import com.tameofthrones.pojo.Kingdom;
import com.tameofthrones.registry.SupporterRegistry;


public class QuestTest {
    private Quest quest;

    @Before
    public void setUp() {
        quest = new Quest(SupporterRegistry.instance());
    }

    @Test
    public void whenValidKingdomStringIsInput_ThenResultIsKingdomObject() {
        assertTrue(quest.getKingdomByKingdomName("Space") instanceof Kingdom);
    }

    @Test
    public void whenKingdomStringIsNull_ThenResultIsNull() {
        Kingdom actual = quest.getKingdomByKingdomName(null);
        assertEquals(null, actual);
    }

    @Test
    public void whenKingdomStringIsEmpty_ThenResultIsNull() {
        Kingdom actual = quest.getKingdomByKingdomName(Script.EMPTY);
        assertEquals(null, actual);
    }

    @Test
    public void whenValidKingStringIsInput_ThenResultIsKingdomObject() {
        assertTrue(quest.getKingdomByKingName("Shan") instanceof Kingdom);
    }

    @Test
    public void whenKingStringIsNull_ThenResultIsNull() {
        Kingdom actual = quest.getKingdomByKingName(null);
        assertEquals(null, actual);
    }

    @Test
    public void whenKingStringIsEmpty_ThenResultIsNull() {
        Kingdom actual = quest.getKingdomByKingName(Script.EMPTY);
        assertEquals(null, actual);
    }
}
