package com.tameOfthrones.test.communicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tameofthrones.operation.Evaluation;
import com.tameofthrones.operation.Quest;
import com.tameofthrones.operation.Result;
import com.tameofthrones.pojo.Message;
import com.tameofthrones.registry.SupporterRegistry;


public class EvaluationTest {

    private Quest quest;
    SupporterRegistry registry;

    @Before
    public void setUp() {
        registry = SupporterRegistry.instance();
        quest = new Quest(registry);
    }

    @Test
    public void whenTwoCompetitorsAndSpaceSendsFourCorrectMessages_ThenResultIsNotATie() {
        registry.clear();
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Space", "Land", "a1d22n333a4444p"));
        messages.add(new Message("Space", "Air", "Let’s swing the sword together"));
        messages.add(new Message("Space", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Space", "Fire", "Drag on Martin!"));
        messages.add(new Message("Water", "Ice", "Summer is coming"));
        messages.add(new Message("Water", "Fire", "Drag on Martin!"));

        Evaluation evaluation = new Evaluation(messages);
        Result actual = evaluation.getResult();

        assertFalse("Should pass", actual.isATie());
    }

    @Test
    public void whenTwoCompetitorsAndBothSendCorrectMessages_ThenResultIsATie() {
        registry.clear();
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Space", "Land", "a1d22n333a4444p"));
        messages.add(new Message("Space", "Air", "Let’s swing the sword together"));
        messages.add(new Message("Water", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Water", "Air", "Let’s swing the sword together"));

        Evaluation evaluation = new Evaluation(messages);
        Result actual = evaluation.getResult();

        assertTrue("Should pass", actual.isATie());

    }

    @Test
    public void whenSpaceSendsFourCorrectMessages_ThenFourAllies() {
        registry.clear();
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Space", "Land", "a1d22n333a4444p"));
        messages.add(new Message("Space", "Air", "Let’s swing the sword together"));
        messages.add(new Message("Space", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Space", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Space", "Fire", "Drag on Martin!"));
        messages.add(new Message("Water", "Ice", "Summer is coming"));
        messages.add(new Message("Water", "Fire", "Drag on Martin!"));

        Evaluation evaluation = new Evaluation(messages);
        Result result = evaluation.getResult();
        String actualRuler = quest.findAlliesOfTheRuller()
                                  .getContent();
        String expected = "Land,Air,Ice,Fire";
        assertEquals("Should pass", expected, actualRuler);
    }

    @Test
    public void whenTwoCompetitorsAndSpaceSendsFourCorrectMessages_ThenSpaceIsTheRuler() {
        registry.clear();
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Space", "Land", "a1d22n333a4444p"));
        messages.add(new Message("Space", "Air", "Let’s swing the sword together"));
        messages.add(new Message("Space", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Space", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Space", "Fire", "Drag on Martin!"));
        messages.add(new Message("Water", "Ice", "Summer is coming"));
        messages.add(new Message("Water", "Fire", "Drag on Martin!"));

        Evaluation evaluation = new Evaluation(messages);
        Result result = evaluation.getResult();
        String actualRuler = quest.findTheRuler()
                                  .getContent();
        assertEquals("Should pass", "Space", actualRuler);
    }

    @Test
    public void whenTwoCompetitors_ThenResultContainsTwoKingdoms() {
        registry.clear();
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Space", "Land", "a1d22n333a4444p"));
        messages.add(new Message("Space", "Air", "Let’s swing the sword together"));
        messages.add(new Message("Space", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Space", "Fire", "Drag on Martin!"));
        messages.add(new Message("Water", "Ice", "Summer is coming"));
        messages.add(new Message("Water", "Fire", "Drag on Martin!"));

        Evaluation evaluation = new Evaluation(messages);
        Result actual = evaluation.getResult();
        int actualNumberOfKingdoms = actual.publish()
                                           .keySet()
                                           .size();
        assertEquals(2, actualNumberOfKingdoms);
    }

    @Test
    public void whenReceiverKingdomIsCompeting_ThenItsAlleiganceDoesNotCount() {
        registry.clear();
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Space", "Air", "Let’s swing the sword together"));
        messages.add(new Message("Space", "Ice", "Ahoy! Fight for me with men and money"));
        messages.add(new Message("Space", "Fire", "drag it on"));
        messages.add(new Message("Fire", "Land", "pan da de kung fu"));
        messages.add(new Message("Fire", "Space", "let's go to space."));

        Evaluation evaluation = new Evaluation(messages);
        Result result = evaluation.getResult();
        int actualSupporters = result.publish()
                                     .get(quest.getKingdomByKingdomName("Space"))
                                     .size();
        assertEquals("Should pass", 2, actualSupporters);

    }

    @Test
    public void whenResultIs_0_0_1_Then_1_IsTheRuler() {
        registry.clear();
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message("Space", "Air", "Let’s swing the sword together"));
        messages.add(new Message("Space", "Ice", "me sending crap."));
        messages.add(new Message("Water", "Land", "me goes adios"));
        messages.add(new Message("Water", "Space", "me sending kudos"));
        messages.add(new Message("Fire", "Space", "fire in the space"));

        Evaluation evaluation = new Evaluation(messages);
        Result result = evaluation.getResult();
        String actualRuler = quest.findTheRuler()
                                  .getContent();
        assertEquals("Should pass", "Space", actualRuler);
    }
}
