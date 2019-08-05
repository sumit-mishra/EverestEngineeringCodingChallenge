package com.tameofthrones.operation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tameofthrones.constants.Southeros;
import com.tameofthrones.pojo.Kingdom;
import com.tameofthrones.registry.SupporterRegistry;


public class Competitor {
    private Set<Kingdom> kingdoms = new HashSet<Kingdom>();

    /**
     * https://stackoverflow.com/questions/21604243/correct-implementation-of-initialization-on-demand-holder-idiom
     */
    private static class Holder {
        static final Competitor INSTANCE = new Competitor();
    }

    public static Competitor instance() {
        return Holder.INSTANCE;
    }

    private Competitor() {
    }

    public void update(String[] kingdomNames) {
        if (areValidKingdoms(kingdomNames)) {
            kingdoms.clear();
            Quest quest = new Quest(SupporterRegistry.instance());
            kingdoms = Arrays.stream(kingdomNames)
                             .map(kingdomName -> quest.getKingdomByKingdomName(kingdomName))
                             .collect(Collectors.toSet());
        }
    }

    public Set<Kingdom> getKingdoms() {
        return kingdoms;
    }

    public boolean areValidKingdoms(String[] kingdomNames) {
        return !empty(kingdomNames) && areAllSutherosKingdoms(kingdomNames);
    }

    public void removeAll() {
        kingdoms.clear();
    }

    private boolean empty(String[] kingdomNames) {
        return kingdomNames == null || kingdomNames.length == 0;
    }

    private boolean areAllSutherosKingdoms(String[] kingdomNames) {
        return southerosKindoms().containsAll(userInputKingdoms(kingdomNames));
    }

    private Set<String> userInputKingdoms(String[] kingdomNames) {
        return Stream.of(kingdomNames)
                     .map(kingdomName -> kingdomName.toUpperCase())
                     .collect(Collectors.toSet());
    }

    private Set<String> southerosKindoms() {
        return Stream.of(Southeros.Kingdom.values())
                     .map(Enum::name)
                     .collect(Collectors.toSet());
    }

}
