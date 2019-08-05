package com.tameofthrones.ballot;

import java.util.ArrayList;
import java.util.List;

import com.tameofthrones.constants.Script;
import com.tameofthrones.operation.Answer;
import com.tameofthrones.operation.Competitor;
import com.tameofthrones.operation.Quest;
import com.tameofthrones.operation.Result;
import com.tameofthrones.pojo.Kingdom;
import com.tameofthrones.registry.SupporterRegistry;


public class HighPriest {

    private Ballot ballot;
    private boolean isResultTie;
    private Competitor competitor = Competitor.instance();
    private Result result;

    public Answer initiateBallot() {
        int roundNumber = Script.ZERO;
        updateBallot();
        List<String> noOfRoundsAndResult = new ArrayList<String>();
        boolean isFirstBallot = true;
        while (isFirstBallot || isResultTie) {
            isFirstBallot = false;
            String result = callForBallot();
            noOfRoundsAndResult.add("round" + ++roundNumber + Script.COLON + result);
        }
        return prepareAnswer(noOfRoundsAndResult);
    }

    private void updateBallot() {
        if (isResultTie) {
            updateCompetitor();
        }
        ballot = new Ballot(competitor, MessageBox.instance());
    }

    private void updateCompetitor() {
        competitor.getKingdoms()
                  .clear();
        competitor.update(tiedKingdoms());
    }

    private String callForBallot() {
        result = ballot.run();
        isResultTie = result.isATie();
        List<String> kingdomAndNumberOfSupporter = new ArrayList<String>();
        for (Kingdom kingdom : competitor.getKingdoms()) {
            String allies = "Allies for " + kingdom.getName() + Script.COLON + numberOfSupporters(result, kingdom);
            kingdomAndNumberOfSupporter.add(allies);
        }
        return String.join(Script.COMMA, kingdomAndNumberOfSupporter);
    }

    private int numberOfSupporters(Result result, Kingdom kingdom) {
        if (result.publish()
                  .containsKey(kingdom)) {
            return result.publish()
                         .get(kingdom)
                         .size();
        }
        return 0;
    }

    private Answer prepareAnswer(List<String> noOfRoundsAndResult) {
        Answer answer = Answer.instance();
        answer.setContent(String.join(Script.COMMA, noOfRoundsAndResult));
        return answer;
    }

    private String[] tiedKingdoms() {
        List<Kingdom> kingdoms = new Quest(SupporterRegistry.instance()).kingdomWithMaxSupporters();
        String[] kingdomNames = new String[kingdoms.size()];
        int index = 0;
        for (Kingdom kingdom : kingdoms) {
            kingdomNames[index] = kingdom.getName();
            index += 1;
        }
        return kingdomNames;
    }
}
