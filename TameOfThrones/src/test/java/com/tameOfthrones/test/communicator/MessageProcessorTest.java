package com.tameOfthrones.test.communicator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.tameofthrones.communicator.MessageProcessor;
import com.tameofthrones.constants.Script;
import com.tameofthrones.operation.Answer;
import com.tameofthrones.operation.Competitor;
import com.tameofthrones.operation.Quest;
import com.tameofthrones.pojo.Message;
import com.tameofthrones.pojo.Question;
import com.tameofthrones.registry.SupporterRegistry;
import com.tameofthrones.validator.TextInput;


public class MessageProcessorTest {

    private MessageProcessor processor;
    private SupporterRegistry registry;
    private Quest quest;
    private Competitor competitor;
    private TextInput input;

    @Before
    public void setUp() {
        processor = new MessageProcessor();
        registry = SupporterRegistry.instance();
        quest = new Quest(registry);
        competitor = Competitor.instance();
        input = TextInput.instance();
    }

    @Test
    public void whenQuestionIsWhoIsTheRuler_ThenAnswerIsNone() {
        registry.clear();
        Question question = new Question(Script.WHO_IS_THE_RULER_OF_SOUTHEROS);
        Answer actualAnswer = processor.execute(question);
        assertEquals("Should pass", "None", actualAnswer.getContent());
    }

    @Test
    public void whenQuestionIsAlliesOfRuler_ThenAnswerIsNone() {
        registry.clear();
        Question question = new Question(Script.ALLIES_OF_RULER);
        Answer actualAnswer = processor.execute(question);
        assertEquals("Should pass", "None", actualAnswer.getContent());
    }

    @Test
    public void whenInputIsKingdomNames_ThenReturnTrue() {
        boolean actual = input.isKingdomsName("AIR space LanD");
        assertEquals("Should pass", true, actual);
    }

    @Test
    public void whenSecretMessageIsCorrect_ThenRegisterAllegiance() {
        registry.clear();
        processor.kingShanSendingMsg(true);
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Air", "Let’s swing the sword together"));
        processor.execute(new Message(null, "Water", "Summer is coming"));

        assertTrue(registry.getKingdomAndSupporters()
                           .values()
                           .size() > 0);
    }

    @Test
    public void whenKingShanSendsTwoSuccessfulMessages_TheRulesIsNone() {
        registry.clear();
        processor.kingShanSendingMsg(true);
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Air", "Let’s swing the sword together"));
        processor.execute(new Message(null, "Water", "Summer is coming"));
        String actualRuler = quest.findTheRuler()
                                  .getContent();
        String expected = "None";
        assertEquals("Should pass", expected, actualRuler);
    }

    @Test
    public void whenKingShanSendsThreeSuccessfulMessages_TheRulesIsKingShan() {
        registry.clear();
        processor.kingShanSendingMsg(true);
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Air", "Let’s swing the sword together"));
        processor.execute(new Message(null, "Ice", "Ahoy! Fight for me with men and money"));
        processor.execute(new Message(null, "Water", "Summer is coming"));
        String actualRuler = quest.findTheRuler()
                                  .getContent();
        String expected = "Shan";
        assertEquals("Should pass", expected, actualRuler);
    }

    @Test
    public void whenKingShanSendsFourSuccessfulMessages_TheGetFourAllies() {
        registry.clear();
        processor.kingShanSendingMsg(true);
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Fire", "Drag on Martin!"));
        processor.execute(new Message(null, "Land", "Die or play the tame of thrones"));
        processor.execute(new Message(null, "Air", "Let’s swing the sword together"));
        processor.execute(new Message(null, "Ice", "Ahoy! Fight for me with men and money"));
        processor.execute(new Message(null, "Water", "Summer is coming"));
        String actualRuler = quest.findAlliesOfTheRuller()
                                  .getContent();
        String expected = "Fire,Land,Air,Ice";
        assertEquals("Should pass", expected, actualRuler);
    }

    @Test
    public void whenValidKingdomsAddedToTheCompetition_ThenUpdateCompetitors() {
        competitor.removeAll();
        String[] kingdoms = {"Air", "Space", "Land"};
        competitor.update(kingdoms);
        HashSet<String> expected = new HashSet<String>(Arrays.asList(kingdoms));
        Set<String> actual = competitor.getKingdoms()
                                       .stream()
                                       .map(kingdom -> kingdom.getName())
                                       .collect(Collectors.toSet());
        assertTrue("Should Pass", expected.size() == actual.size() && expected.containsAll(actual));
    }

    @Test
    public void whenValidKingdomsAddedToTheCompetition_ThenPriestCallForBallot() {
        registry.clear();
        competitor.removeAll();
        // processor.execute(new Message(null, null,
        // Script.ENTER_THE_KINGDOMS_COMPETING_TO_BE_THE_RULER));
        processor.prcocessExpectingKingdoms(true);
        Answer answer = processor.execute(new Message(null, null, "Air Space Land"));

        assertTrue("Should Pass", !answer.getContent()
                                         .isEmpty());
    }

    @Test
    public void whenMessageIsKingdomsCompetingToBeTheRuler_ThenWaitForInput() {
        registry.clear();
        competitor.removeAll();
        processor.execute(new Message(null, null, Script.ENTER_THE_KINGDOMS_COMPETING_TO_BE_THE_RULER));
        processor.prcocessExpectingKingdoms(true);
        Answer answer = processor.execute(new Message(null, null, "Air Space Land"));

        assertTrue("Should Pass", !answer.getContent()
                                         .isEmpty());
    }

    @Test
    public void whenTheResultIsATie_ThenUpdateCompetitors() {
        // TODO
    }

}
