package com.tameOfthrones.constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tameofthrones.constants.Southeros;


public class SoutherosTest {
    List<String> emblems;
    List<String> kingdoms;

    @Before
    public void setup() {
        emblems = new ArrayList<String>();
        emblems.add("Owl");
        emblems.add("Gorilla");
        emblems.add("Octopus");
        emblems.add("Panda");
        emblems.add("Dragon");
        emblems.add("Mammoth");
        Collections.sort(emblems);

        kingdoms = new ArrayList<String>();
        kingdoms.add("AIR");
        kingdoms.add("SPACE");
        kingdoms.add("WATER");
        kingdoms.add("LAND");
        kingdoms.add("FIRE");
        kingdoms.add("ICE");
        Collections.sort(kingdoms);
    }

    @Test
    public void shouldReturnAllTheEmblems() {
        List<String> actual = Southeros.getAllEmblems();
        Collections.sort(actual);
        Assert.assertEquals(emblems, actual);
    }

    @Test
    public void shouldReturnAllTheKingdoms() {
        List<String> actual = Southeros.getAllKingdoms();
        Collections.sort(actual);
        Assert.assertEquals(kingdoms, actual);
    }

    @Test
    public void shouldReturnShan_WhenInputIsSpace() {
        String kingdomName = Southeros.getKingdomName("Shan");
        Assert.assertEquals("SPACE", kingdomName);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void shouldReturnSpace_WhenInputIsShan() {
        String kingName = Southeros.getKingName("hero");
        Assert.assertEquals("Shan", kingName);
    }

    @Test
    public void shoudReturnSpace_WhenInputIsShan() {
        String kingName = Southeros.getKingName("space");
        Assert.assertEquals("Shan", kingName);
    }
}
