package com.tameofthrones.constants;

import java.util.ArrayList;
import java.util.List;


public final class Script {

    /**
     * special characters
     */
    public static final String COMMA = ",";
    public static final String QUESTION_MARK = "?";
    public static final String EMPTY = "";
    public static final String BLANK_SPACE = " ";
    public static final String COLON = " : ";

    /**
     * words & scripts
     */
    public static final String INPUT = "Input: ";
    public static final String OUTPUT = "Output: ";
    public static final String KING = "King";
    public static final String NONE = "None";
    public static final String INVALID_QUESTION = "Invalid question.";
    public static final String CAN_NOT_PROCESS_MESSAGE = "Can not process message.";
    public static final String PLEASE_ENTER_A_VALID_MESSAGE = "Please enter a valid messge.";
    public static final String WHO_IS_THE_RULER_OF_SOUTHEROS = "Who is the ruler of Southeros?";
    public static final String ALLIES_OF_RULER = "Allies of Ruler?";
    public static final String ALLIES_OF_KING_SHAN = "Allies of King Shan?";
    public static final String INPUT_MESSAGES_TO_KINGDOMS_FROM_KING_SHAN = "Input Messages to kingdoms from King Shan:";
    public static final String ENTER_THE_KINGDOMS_COMPETING_TO_BE_THE_RULER = "Enter the kingdoms competing to be the ruler:";
    public static final String RESULTS_AFTER_ROUND_ONE_BALLOT_COUNT = "Results after round one ballot count";
    public static final String FILE_NAME = "SecretMessages.txt";

    /**
     * error messages
     */
    public static String NO_PROPER_QUESTION_OR_MESSAGE = "No proper question or message";

    /**
     * default numeric values
     */
    public static final int ZERO = 0;
    public static final int SIX = 6;

    /**
     * methods
     */
    public static final List<String> communicationMessages() {
        List<String> messageScripts = new ArrayList<String>();
        messageScripts.add(WHO_IS_THE_RULER_OF_SOUTHEROS);
        messageScripts.add(ALLIES_OF_RULER);
        messageScripts.add(INPUT_MESSAGES_TO_KINGDOMS_FROM_KING_SHAN);
        messageScripts.add(ENTER_THE_KINGDOMS_COMPETING_TO_BE_THE_RULER);
        return messageScripts;
    }
}
