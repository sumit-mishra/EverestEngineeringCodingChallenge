package com.tameofthrones.validator;

import com.tameofthrones.constants.Script;
import com.tameofthrones.constants.Southeros;


public final class TextInput {
    private TextInput() {
    }

    private static class Holder {
        static final TextInput INSTANCE = new TextInput();
    }

    public static TextInput instance() {
        return Holder.INSTANCE;
    }

    // Null-safe, short-circuit evaluation.
    public final boolean empty(final String text) {
        return text == null || text.trim()
                                   .isEmpty();
    }

    public final boolean isSecretMsgFormat(final String message) {
        return !empty(message) && message.contains(Script.COMMA) && !message.split(Script.COMMA)[0].trim()
                                                                                                   .isEmpty()
                        && !message.split(Script.COMMA)[1].trim()
                                                          .isEmpty();
    }

    public final boolean isMessageFromKingShan(final String message) {
        if (empty(message)) {
            return false;
        }
        return message.equalsIgnoreCase(Script.INPUT_MESSAGES_TO_KINGDOMS_FROM_KING_SHAN);
    }

    public final boolean isQuestion(String message) {
        return !empty(message) && message.contains(Script.QUESTION_MARK) && message.length() > 1;
    }

    public final boolean isMsgToKingdom(String message) {
        if (empty(message)) {
            return false;
        }
        return true;
    }

    public final boolean isValid(final String message) {
        boolean isMessagePresentInScript = Script.communicationMessages()
                                                 .stream()
                                                 .anyMatch(scriptMsg -> scriptMsg.equalsIgnoreCase(message));
        return isMessagePresentInScript || isSecretMsgFormat(message);
    }

    public final boolean isKingdomsName(final String text) {
        String[] kingdomNames = text.split(Script.BLANK_SPACE);
        for (String kingdomName : kingdomNames) {
            if (isKingdomName(kingdomName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isKingdomName(final String kingdomName) {
        return Southeros.getAllKingdoms()
                        .stream()
                        .anyMatch(name -> name.equalsIgnoreCase(kingdomName));
    }

    public final boolean containsEmblem(String secretMsg, String emblem) {
        boolean containsEmblem = true;
        secretMsg = secretMsg.toLowerCase();
        String uniqueLettersOfEmblem = emblem.toLowerCase();// TODO _getAllUniqueLetters
        for (char letter : uniqueLettersOfEmblem.toCharArray()) {
            if (secretMsg.contains(String.valueOf(letter)) && countNumberOfChars(secretMsg, letter) >= countNumberOfChars(emblem, letter)) {
                continue;
            } else {
                containsEmblem = false;
            }
        }
        return containsEmblem;
    }

    public final boolean isMessageForCompetetion(String message) {
        return message.equalsIgnoreCase(Script.ENTER_THE_KINGDOMS_COMPETING_TO_BE_THE_RULER);
    }

    private long countNumberOfChars(final String input, final char neededCharToCount) {
        return input.chars()
                    .filter(

                        characterFromInput -> characterFromInput == neededCharToCount)
                    .count();
    }
}
