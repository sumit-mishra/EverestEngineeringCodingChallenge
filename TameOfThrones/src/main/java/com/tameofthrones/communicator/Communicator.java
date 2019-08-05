package com.tameofthrones.communicator;

import com.tameofthrones.constants.Script;
import com.tameofthrones.exception.TameOfThronesException;
import com.tameofthrones.operation.Answer;
import com.tameofthrones.pojo.Message;
import com.tameofthrones.pojo.Question;
import com.tameofthrones.validator.TextInput;


public class Communicator {

    private MessageProcessor processor;
    private CommandLine command;
    private String message;
    private TextInput input;
    private boolean isRunning;

    public Communicator() {
        processor = new MessageProcessor();
        command = CommandLine.instance();
        input = TextInput.instance();
        isRunning = true;
    }

    public void initiateCommunication() {
        try {
            while (isInputOK()) {
                forwardInputForExecution(message.trim());
            }
        } catch (TameOfThronesException exception) {
            command.printInCommandLine(exception.getMessage());
        } finally {
            closeInteraction();
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    private void forwardInputForExecution(final String inputMsg) throws TameOfThronesException {
        if (input.empty(inputMsg)) {
            processor.kingShanSendingMsg(false);
            return;
        }
        if (input.isQuestion(inputMsg)) {
            Answer answer = executeQuestion(inputMsg);
            command.printInCommandLine(Script.OUTPUT + answer.getContent());
            return;
        }
        if ((input.isValid(inputMsg) && input.isMsgToKingdom(inputMsg)) || input.isKingdomsName(inputMsg)) {
            Answer answer = processMessage(inputMsg);
            command.printInCommandLine(answer.getContent());
            return;
        }
        command.printInCommandLine(Script.NO_PROPER_QUESTION_OR_MESSAGE);
    }

    private Answer processMessage(final String inputMsg) {
        return processor.execute(getMessage(inputMsg));
    }

    private Answer executeQuestion(final String inputMsg) {
        return processor.execute(new Question(inputMsg));
    }

    private Message getMessage(String message) {
        if (input.isSecretMsgFormat(message)) {
            String[] content = message.split(Script.COMMA);
            return new Message(null, content[0], content[1]);
        } else {
            return new Message(null, null, message);
        }
    }

    private boolean isInputOK() {
        if (processor.isExpectingInput()) {
            command.printInCommandLine(Script.INPUT);
        }
        message = command.getUserInput();
        return !message.equalsIgnoreCase("exit");
    }

    private void closeInteraction() {
        command.close();
        isRunning = false;
        command.printInCommandLine("Buh Bye !!");
    }
}
