package com.tameofthrones.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tameofthrones.operation.Quest;
import com.tameofthrones.pojo.Kingdom;


public class SupporterRegistry {

    private static SupporterRegistry registry;
    private Map<Kingdom, List<Kingdom>> kingdomAndSupporter = new HashMap<Kingdom, List<Kingdom>>();
    private List<String> supporters = new ArrayList<String>();
    private List<Kingdom> allegiance = new ArrayList<Kingdom>();

    private SupporterRegistry() {
    }

    private static class Holder {
        private static final SupporterRegistry INSTANCE = new SupporterRegistry();
    }

    public static SupporterRegistry instance() {
        return Holder.INSTANCE;
    }

    public void register(final String kingdomName, final String suportingKingdom) {
        Quest quest = new Quest(registry);
        if (kingdomName != null) {
            Kingdom kingdom = quest.getKingdomByKingdomName(kingdomName);
            Kingdom supportingKingdom = quest.getKingdomByKingdomName(suportingKingdom);
            if (kingdomAndSupporter.containsKey(kingdom)) {
                addSupporter(kingdom, supportingKingdom);
            } else {
                addKingdomAndSupporter(kingdom, supportingKingdom);
            }
        }
    }

    public final Map<Kingdom, List<Kingdom>> getKingdomAndSupporters() {
        return kingdomAndSupporter;
    }

    public void clear() {
        kingdomAndSupporter.clear();
        supporters.clear();
        allegiance.clear();
    }

    private void addKingdomAndSupporter(final Kingdom kingdom, final Kingdom supportingKingdom) {
        if (supportingKingdom != null) {
            kingdomAndSupporter.put(kingdom, new ArrayList<Kingdom>(Arrays.asList(supportingKingdom)));
        } else {
            kingdomAndSupporter.put(kingdom, new ArrayList<Kingdom>());
        }
    }

    private void addSupporter(final Kingdom kingdom, final Kingdom supportingKingdom) {
        allegiance = kingdomAndSupporter.get(kingdom);
        if (allegiance != null && supportingKingdom != null && !allegiance.contains(supportingKingdom)) {
            allegiance.add(supportingKingdom);
            kingdomAndSupporter.put(kingdom, allegiance);
        }
    }
}
