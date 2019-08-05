package com.tameofthrones.constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.tameofthrones.validator.TextInput;


public final class Southeros {

    public enum Kingdom {
        AIR("Owl"), FIRE("Dragon"), ICE("Mammoth"), LAND("Panda"), SPACE("Gorilla"), WATER("Octopus");

        private String emblem;

        Kingdom(String emblem) {
            this.emblem = emblem;
        }

        public String emblem() {
            return emblem;
        }
    }

    public enum King {
        AIR(""), FIRE(""), ICE(""), LAND(""), SPACE("Shan"), WATER("");

        private String title;

        King(String title) {
            this.title = title;
        }

        public String title() {
            return title;
        }
    }

    private static TextInput input = TextInput.instance();

    public static final String getEmblem(final String kingdomName) {
        for (Kingdom kingdom : Kingdom.values()) {
            if (kingdom.name()
                       .equalsIgnoreCase(kingdomName)) {
                return kingdom.emblem();
            }
        }
        return null;
    }

    public static final String getKingdomName(final String kingName) {
        if (input.empty(kingName)) {
            return null;
        }
        return Stream.of(King.values())
                     .filter(king -> king.title()
                                         .equalsIgnoreCase(kingName))
                     .findFirst()
                     .get()
                     .name();
    }

    public static final String getKingName(final String kingdomName) {
        if (input.empty(kingdomName)) {
            return null;
        }
        return Stream.of(King.values())
                     .filter(kingdom -> kingdom.name()
                                               .equalsIgnoreCase(kingdomName))
                     .findFirst()
                     .get()
                     .title();
    }

    public static final List<String> getAllKingdoms() {
        return Stream.of(Kingdom.values())
                     .map(Enum::name)
                     .collect(Collectors.toList());
    }

    public static final List<String> getAllEmblems() {
        return Stream.of(Kingdom.values())
                     .map(kingdom -> kingdom.emblem())
                     .collect(Collectors.toList());
    }
}
