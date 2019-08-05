package com.tameofthrones.operation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tameofthrones.constants.Southeros;
import com.tameofthrones.pojo.Message;
import com.tameofthrones.registry.SupporterRegistry;
import com.tameofthrones.validator.TextInput;


public class Evaluation {
    private final List<Message> messages;
    private TextInput input;

    public Evaluation(final List<Message> messages) {
        this.messages = messages;
        input = TextInput.instance();
    }

    public Result getResult() {
        SupporterRegistry registry = SupporterRegistry.instance();
        registry.clear();
        messages.forEach(message -> registerAllegiance(message));
        Result result = new Result(registry);
        return result;
    }

    private void registerAllegiance(Message message) {
        if (competingKindomIsNotTheReceiver(message)) {
            String receiverKingdom = getReceiverAsNullIfEmblemNotFound(message);
            SupporterRegistry.instance()
                             .register(message.getSenderKingdom(), receiverKingdom);
        }
    }

    private String getReceiverAsNullIfEmblemNotFound(Message message) {
        if (input.containsEmblem(message.getText(), Southeros.getEmblem(message.getReceiverKingdom()))) {
            return message.getReceiverKingdom();
        }
        return null;
    }

    private boolean competingKindomIsNotTheReceiver(Message message) {
        return !competingKingdoms().contains(message.getReceiverKingdom());
    }

    private Set<String> competingKingdoms() {
        return messages.stream()
                       .map(message -> message.getSenderKingdom())
                       .collect(Collectors.toSet());
    }
}
