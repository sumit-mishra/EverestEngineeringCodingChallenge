package com.tameofthrones.communicator;

import com.tameofthrones.ballot.HighPriest;
import com.tameofthrones.constants.Script;
import com.tameofthrones.constants.Southeros;
import com.tameofthrones.operation.Answer;
import com.tameofthrones.operation.Competitor;
import com.tameofthrones.operation.Quest;
import com.tameofthrones.pojo.Message;
import com.tameofthrones.pojo.Question;
import com.tameofthrones.registry.SupporterRegistry;
import com.tameofthrones.validator.TextInput;


public class MessageProcessor {

    private boolean isMsgFromKingShan;
    private boolean isExpectParticipatingKingdoms;
    private HighPriest priest;
    private TextInput input;

    public MessageProcessor() {
        priest = new HighPriest();
        input = TextInput.instance();
    }

    public Answer execute(Question question) {
        return getAnswer(question);
    }

    public Answer execute(Message message) {
        if (message.getText()
                   .equalsIgnoreCase(Script.INPUT_MESSAGES_TO_KINGDOMS_FROM_KING_SHAN)) {
            isMsgFromKingShan = true;
            return Answer.instance()
                         .empty();
        }
        if (input.isMessageForCompetetion(message.getText())) {
            isExpectParticipatingKingdoms = true;
            return Answer.instance()
                         .empty();
        }
        if (isExpectParticipatingKingdoms && input.isKingdomsName(message.getText())) {
            Competitor.instance()
                      .update(message.getText()
                                     .split(Script.BLANK_SPACE));
            return priest.initiateBallot();
        }
        if (input.isKingdomName((message.getReceiverKingdom()))) {
            if (isMsgFromKingShan) {
                message.setSenderKingdom(Southeros.Kingdom.SPACE.name());
            }
            registerAllegiance(message);
            return Answer.instance()
                         .empty();
        } else {
            isMsgFromKingShan = false;
            isExpectParticipatingKingdoms = false;
            return Answer.instance()
                         .canNotProcessMessage();
        }
    }

    private void registerAllegiance(Message message) {
        if (input.containsEmblem(message.getText(), Southeros.getEmblem(message.getReceiverKingdom()))) {
            SupporterRegistry.instance()
                             .register(message.getSenderKingdom(), message.getReceiverKingdom());
        }
    }

    private Answer getAnswer(Question question) {
        Quest quest = new Quest(SupporterRegistry.instance());
        if (question.getContent()
                    .equals(Script.WHO_IS_THE_RULER_OF_SOUTHEROS)) {
            return quest.findTheRuler();
        }
        if (question.getContent()
                    .equalsIgnoreCase(Script.ALLIES_OF_KING_SHAN)) {
            return quest.findAlliesOfTheRuller();
        }
        if (question.getContent()
                    .equalsIgnoreCase(Script.ALLIES_OF_RULER)) {
            return quest.findAlliesOfTheRuller();
        } else {
            return Answer.instance()
                         .invalidQuestion();
        }
    }

    public boolean isExpectingInput() {
        return isMsgFromKingShan || isExpectParticipatingKingdoms;
    }

    public void kingShanSendingMsg(final boolean expectation) {
        isMsgFromKingShan = expectation;
    }

    public void prcocessExpectingKingdoms(final boolean expectation) {
        isExpectParticipatingKingdoms = expectation;
    }
}
