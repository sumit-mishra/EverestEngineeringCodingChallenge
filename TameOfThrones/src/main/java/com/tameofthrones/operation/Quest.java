package com.tameofthrones.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tameofthrones.constants.Script;
import com.tameofthrones.constants.Southeros;
import com.tameofthrones.pojo.King;
import com.tameofthrones.pojo.Kingdom;
import com.tameofthrones.registry.SupporterRegistry;
import com.tameofthrones.validator.TextInput;


public class Quest {
    private SupporterRegistry registry;
    private Answer answer;
    private TextInput input;

    public Quest(final SupporterRegistry registry) {
        this.registry = SupporterRegistry.instance();
        this.answer = Answer.instance();
        this.input = TextInput.instance();
    }

    public Kingdom getKingdomByKingName(final String kingName) {
        if (input.empty(kingName)) {
            return null;
        }
        String kingdomName = Southeros.getKingdomName(kingName);
        return new Kingdom(new King(kingName), kingdomName, Southeros.getEmblem(kingdomName));
    }

    public Kingdom getKingdomByKingdomName(final String kingdomName) {
        if (input.empty(kingdomName)) {
            return null;
        }
        return new Kingdom(new King(Southeros.getKingName(kingdomName)), kingdomName, Southeros.getEmblem(kingdomName));
    }

    public Answer findTheRuler() {
        answer.setContent(Script.NONE);
        if (numberOfCompetitors() == 1 && registry.getKingdomAndSupporters()
                                                  .get(kingdomWithMaxSupporters().get(0))
                                                  .size() >= 3) {
            answer.setContent(getKingNameOrKingdomName(kingdomWithMaxSupporters().get(0)));
        } else if (numberOfCompetitors() > 1 && kingdomWithMaxSupporters().size() == 1) {

            answer.setContent(kingdomWithMaxSupporters().get(0)
                                                        .getName());
        }
        return answer;
    }

    public Answer findAlliesOfTheRuller() {
        if (alliesOfRuler() == null || alliesOfRuler().size() == Script.ZERO) {
            answer.setContent(Script.NONE);
            return answer;
        }
        answer.setContent(allies());
        return answer;
    }

    public List<Kingdom> kingdomWithMaxSupporters() {
        List<Kingdom> kingdoms = new ArrayList<Kingdom>();
        int highestNoOfSupporters = -1;
        Kingdom winnerKingdom = new Kingdom();
        Map<Kingdom, List<Kingdom>> kingdomAndSupporters = registry.getKingdomAndSupporters();
        for (Kingdom kingdom : kingdomAndSupporters.keySet()) {
            int noOfSupporters = kingdomAndSupporters.get(kingdom)
                                                     .size();
            if (noOfSupporters > highestNoOfSupporters) {
                winnerKingdom = kingdom;
                highestNoOfSupporters = noOfSupporters;
                noOfSupporters = -1;
            }
            if (highestNoOfSupporters == noOfSupporters) {
                winnerKingdom = kingdom;
                kingdoms.add(winnerKingdom);
            }
        }
        kingdoms.add(winnerKingdom);
        return kingdoms;
    }

    private String allies() {
        List<String> alliesNames = alliesOfRuler().stream()
                                                  .map(kingdom -> kingdom.getName())
                                                  .collect(Collectors.toList());
        return String.join(Script.COMMA, alliesNames);
    }

    private String getKingNameOrKingdomName(Kingdom kingdom) {
        return kingdom.getKing()
                      .getKingName()
                      .isEmpty() ? kingdom.getName()
                                      : kingdom.getKing()
                                               .getKingName();
    }

    private int numberOfCompetitors() {
        return registry.getKingdomAndSupporters()
                       .keySet()
                       .size();
    }

    private List<Kingdom> alliesOfRuler() {
        if (registry.getKingdomAndSupporters()
                    .size() > 0) {
            boolean isMatchFound = Southeros.getAllKingdoms()
                                            .stream()
                                            .anyMatch(name -> name.equalsIgnoreCase(findTheRuler().getContent()));
            Kingdom kingdom;
            if (isMatchFound) {
                kingdom = getKingdomByKingdomName(findTheRuler().getContent());
            } else {
                kingdom = getKingdomByKingName(findTheRuler().getContent());
            }

            return registry.getKingdomAndSupporters()
                           .get(kingdom);
        }
        return null;
    }
}
