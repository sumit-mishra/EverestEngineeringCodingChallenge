package com.tameOfthrones.test.communicator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.tameofthrones.communicator.CommandLine;
import com.tameofthrones.constants.Script;
import com.tameofthrones.validator.TextInput;


public class CommandLineTest {

    private CommandLine command;
    private TextInput input;

    @Before
    public void setUp() {
        command = CommandLine.instance();
        input = TextInput.instance();
    }

    @Test
    public void whenInputIsQuestion_ThenReturnTrue() {
        boolean isQuestion = input.isQuestion("Hello?");
        assertTrue(isQuestion);
    }

    @Test
    public void whenInputIsInSecretMsgFormat_ThenReturnTrue() {
        boolean secretMsgFormat = input.isSecretMsgFormat("Air, Hello");
        assertTrue(secretMsgFormat);
    }

    @Test
    public void whenInputIsNotEmptyOrNotNull_ThenReturnTrue() {
        boolean aMessage = input.isMsgToKingdom("hello");
        assertTrue(aMessage);
    }

    @Test
    public void whenInputIsMsgToKingdomsFromKingShan_ThenReturnTrue() {
        boolean messageFromKingShan = input.isMessageFromKingShan(Script.INPUT_MESSAGES_TO_KINGDOMS_FROM_KING_SHAN);
        assertTrue(messageFromKingShan);
    }

    @Test
    public void WhenInputIsNullOrEmpty_ThenReturnTrue() {
        boolean emptyInput = input.empty(null);
        assertTrue(emptyInput);
    }
}
