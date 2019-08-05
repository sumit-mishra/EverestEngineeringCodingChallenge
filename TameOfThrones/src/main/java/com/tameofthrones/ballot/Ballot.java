package com.tameofthrones.ballot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.tameofthrones.constants.Script;
import com.tameofthrones.constants.Southeros;
import com.tameofthrones.operation.Competitor;
import com.tameofthrones.operation.Evaluation;
import com.tameofthrones.operation.Quest;
import com.tameofthrones.operation.Result;
import com.tameofthrones.pojo.Kingdom;
import com.tameofthrones.pojo.Message;
import com.tameofthrones.registry.SupporterRegistry;


public class Ballot {
    private SupporterRegistry registry = SupporterRegistry.instance();
    private Competitor competitor;
    private MessageBox box;

    public Ballot(Competitor competitor, MessageBox box) {
        this.competitor = competitor;
        this.box = box;
    }

    private List<String> pullRandomMessages(int numberOfMessages) {
        Collections.shuffle(box.messages());
        return box.messages()
                  .subList(0, numberOfMessages);
    }

    private List<Kingdom> senders() {
        List<Kingdom> kingdoms = new ArrayList<Kingdom>();
        kingdoms.addAll(competitor.getKingdoms());
        return kingdoms;
    }

    private List<Kingdom> receviers() {
        Quest quest = new Quest(registry);
        return Southeros.getAllKingdoms()
                        .stream()
                        .map(kingdomName -> quest.getKingdomByKingdomName(kingdomName))
                        .collect(Collectors.toList());
    }

    private List<Message> randomMessages(List<String> messageFromTheBox) {
        List<Message> messages = new ArrayList<Message>();
        Random random = new Random();
        Message message;
        for (String textMessage : messageFromTheBox) {
            Kingdom senderKingdom = senders().get(random.nextInt(senders().size()));
            Kingdom recevierKingdom = receviers().get(random.nextInt(receviers().size()));
            message = new Message(senderKingdom.getName(), recevierKingdom.getName(), textMessage);
            messages.add(message);
        }
        return messages;
    }

    public Result run() {
        List<String> messageFromTheBox = pullRandomMessages(Script.SIX);
        return new Evaluation(randomMessages(messageFromTheBox)).getResult();
    }
}
