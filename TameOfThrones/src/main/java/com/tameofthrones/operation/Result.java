package com.tameofthrones.operation;

import java.util.List;
import java.util.Map;

import com.tameofthrones.pojo.Kingdom;
import com.tameofthrones.registry.SupporterRegistry;


public class Result {
    SupporterRegistry registry;

    public Result(SupporterRegistry registry) {
        this.registry = registry;
    }

    public Map<Kingdom, List<Kingdom>> publish() {
        return registry.getKingdomAndSupporters();
    }

    public boolean isATie() {
        return registryHasMoreThanOneKingdom() && hasKingdomSameNumnberOfSupporters();
    }

    private boolean hasKingdomSameNumnberOfSupporters() {
        int nos = -1;
        Map<Kingdom, List<Kingdom>> kingdomAndSupporters = registry.getKingdomAndSupporters();
        for (Kingdom kingdom : kingdomAndSupporters.keySet()) {
            int noOfSupporters = kingdomAndSupporters.get(kingdom)
                                                     .size();
            if (nos == noOfSupporters) {
                return true;
            } else {
                nos = noOfSupporters;
            }
        }
        return false;
    }

    private boolean registryHasMoreThanOneKingdom() {
        return registry.getKingdomAndSupporters()
                       .keySet()
                       .size() > 1;
    }
}
