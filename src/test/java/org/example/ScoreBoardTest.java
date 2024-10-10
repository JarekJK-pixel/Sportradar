package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreBoardTest {

    @Test
    void should_startMatchWithNoScores() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA = "A";
        String awayTeamB = "B";
        scoreBoard.startMatch(homeTeamA, awayTeamB);

        // when
        List<Match> summary = scoreBoard.getSummary();
        Match match = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA) && m.getAwayTeam().equals(awayTeamB))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(1, summary.size());
        assertNotNull(match);
        assertEquals(homeTeamA, match.getHomeTeam());
        assertEquals(awayTeamB, match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
    }

    @Test
    void should_updateMatchWithScores() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA = "A";
        String awayTeamB = "B";
        int homeScores = 2;
        int awayScores = 3;
        scoreBoard.startMatch(homeTeamA, awayTeamB);
        scoreBoard.updateScore(homeTeamA, awayTeamB, homeScores, awayScores);

        // when
        List<Match> summary = scoreBoard.getSummary();

        Match match = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA) && m.getAwayTeam().equals(awayTeamB))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(1, summary.size());
        assertNotNull(match);
        assertEquals(homeTeamA, match.getHomeTeam());
        assertEquals(awayTeamB, match.getAwayTeam());
        assertEquals(homeScores, match.getHomeScore());
        assertEquals(awayScores, match.getAwayScore());
    }

    @Test
    void should_startTwoMatchesWithNoScores() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA1 = "A1";
        String awayTeamB1 = "B1";
        scoreBoard.startMatch(homeTeamA1, awayTeamB1);
        String homeTeamA2 = "A2";
        String awayTeamB2 = "B2";
        scoreBoard.startMatch(homeTeamA2, awayTeamB2);

        // when
        List<Match> summary = scoreBoard.getSummary();

        Match firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        Match secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(2, summary.size());
        assertNotNull(firstMatch);
        assertNotNull(secondMatch);
        assertEquals(homeTeamA1, firstMatch.getHomeTeam());
        assertEquals(awayTeamB1, firstMatch.getAwayTeam());
        assertEquals(0, firstMatch.getHomeScore());
        assertEquals(0, firstMatch.getAwayScore());
        assertEquals(homeTeamA2, secondMatch.getHomeTeam());
        assertEquals(awayTeamB2, secondMatch.getAwayTeam());
        assertEquals(0, secondMatch.getHomeScore());
        assertEquals(0, secondMatch.getAwayScore());
    }

    @Test
    void should_startTwoMatchesAndUpdateScoresOfTheSecond() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA1 = "A1";
        String awayTeamB1 = "B1";
        scoreBoard.startMatch(homeTeamA1, awayTeamB1);
        String homeTeamA2 = "A2";
        String awayTeamB2 = "B2";
        int homeScoreA2 = 3;
        int awayScoreB2 = 2;
        scoreBoard.startMatch(homeTeamA2, awayTeamB2);
        scoreBoard.updateScore(homeTeamA2, awayTeamB2, homeScoreA2, awayScoreB2);

        // when
        List<Match> summary = scoreBoard.getSummary();

        Match firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        Match secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(2, summary.size());
        assertNotNull(firstMatch);
        assertNotNull(secondMatch);
        assertEquals(homeTeamA1, firstMatch.getHomeTeam());
        assertEquals(awayTeamB1, firstMatch.getAwayTeam());
        assertEquals(0, firstMatch.getHomeScore());
        assertEquals(0, firstMatch.getAwayScore());
        assertEquals(homeTeamA2, secondMatch.getHomeTeam());
        assertEquals(awayTeamB2, secondMatch.getAwayTeam());
        assertEquals(homeScoreA2, secondMatch.getHomeScore());
        assertEquals(awayScoreB2, secondMatch.getAwayScore());
    }

    @Test
    void should_startTwoMatchesAndFinishTheSecondMatch() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA1 = "A1";
        String awayTeamB1 = "B1";
        scoreBoard.startMatch(homeTeamA1, awayTeamB1);
        String homeTeamA2 = "A2";
        String awayTeamB2 = "B2";
        int homeScoreA2 = 3;
        int awayScoreB2 = 2;
        scoreBoard.startMatch(homeTeamA2, awayTeamB2);
        scoreBoard.updateScore(homeTeamA2, awayTeamB2, homeScoreA2, awayScoreB2);

        // when
        List<Match> summary = scoreBoard.getSummary();

        Match firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        Match secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(2, summary.size());
        assertNotNull(firstMatch);
        assertNotNull(secondMatch);
        assertEquals(homeTeamA1, firstMatch.getHomeTeam());
        assertEquals(awayTeamB1, firstMatch.getAwayTeam());
        assertEquals(0, firstMatch.getHomeScore());
        assertEquals(0, firstMatch.getAwayScore());
        assertEquals(homeTeamA2, secondMatch.getHomeTeam());
        assertEquals(awayTeamB2, secondMatch.getAwayTeam());
        assertEquals(homeScoreA2, secondMatch.getHomeScore());
        assertEquals(awayScoreB2, secondMatch.getAwayScore());

        //
        // finish second match
        //
        scoreBoard.finishMatch(homeTeamA2, awayTeamB2);

        // when
        summary = scoreBoard.getSummary();

        firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(1, summary.size());
        assertNotNull(firstMatch);
        assertNull(secondMatch);
        assertEquals(homeTeamA1, firstMatch.getHomeTeam());
        assertEquals(awayTeamB1, firstMatch.getAwayTeam());
        assertEquals(0, firstMatch.getHomeScore());
        assertEquals(0, firstMatch.getAwayScore());
    }

    @Test
    void should_startTwoMatchesAndFinishTwoMatches() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA1 = "A1";
        String awayTeamB1 = "B1";
        scoreBoard.startMatch(homeTeamA1, awayTeamB1);
        String homeTeamA2 = "A2";
        String awayTeamB2 = "B2";
        int homeScoreA2 = 3;
        int awayScoreB2 = 2;
        scoreBoard.startMatch(homeTeamA2, awayTeamB2);
        scoreBoard.updateScore(homeTeamA2, awayTeamB2, homeScoreA2, awayScoreB2);

        // when
        List<Match> summary = scoreBoard.getSummary();

        Match firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        Match secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(2, summary.size());
        assertNotNull(firstMatch);
        assertNotNull(secondMatch);
        assertEquals(homeTeamA1, firstMatch.getHomeTeam());
        assertEquals(awayTeamB1, firstMatch.getAwayTeam());
        assertEquals(0, firstMatch.getHomeScore());
        assertEquals(0, firstMatch.getAwayScore());
        assertEquals(homeTeamA2, secondMatch.getHomeTeam());
        assertEquals(awayTeamB2, secondMatch.getAwayTeam());
        assertEquals(homeScoreA2, secondMatch.getHomeScore());
        assertEquals(awayScoreB2, secondMatch.getAwayScore());

        //
        // finish the first and the second match
        //
        scoreBoard.finishMatch(homeTeamA1, awayTeamB1);
        scoreBoard.finishMatch(homeTeamA2, awayTeamB2);

        // when
        summary = scoreBoard.getSummary();

        firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(0, summary.size());
        assertNull(firstMatch);
        assertNull(secondMatch);
    }

    @Test
    void should_tryToUpdateTheSecondNotExistingMatchDoNothing() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA1 = "A1";
        String awayTeamB1 = "B1";
        scoreBoard.startMatch(homeTeamA1, awayTeamB1);
        String homeTeamA2 = "A2";
        String awayTeamB2 = "B2";
        int homeScoreA2 = 3;
        int awayScoreB2 = 2;
        scoreBoard.updateScore(homeTeamA2, awayTeamB2, homeScoreA2, awayScoreB2);

        // when
        List<Match> summary = scoreBoard.getSummary();

        Match firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        Match secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(1, summary.size());
        assertNotNull(firstMatch);
        assertNull(secondMatch);
        assertEquals(homeTeamA1, firstMatch.getHomeTeam());
        assertEquals(awayTeamB1, firstMatch.getAwayTeam());
        assertEquals(0, firstMatch.getHomeScore());
        assertEquals(0, firstMatch.getAwayScore());
    }

    @Test
    void should_tryToFinishTheSecondNotExistingMatchDoNothing() {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();
        String homeTeamA1 = "A1";
        String awayTeamB1 = "B1";
        scoreBoard.startMatch(homeTeamA1, awayTeamB1);
        String homeTeamA2 = "A2";
        String awayTeamB2 = "B2";
        scoreBoard.finishMatch(homeTeamA2, awayTeamB2);

        // when
        List<Match> summary = scoreBoard.getSummary();

        Match firstMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA1) &&
                        m.getAwayTeam().equals(awayTeamB1))
                .findFirst()
                .orElse(null);

        Match secondMatch = summary.stream()
                .filter(m -> m.getHomeTeam().equals(homeTeamA2) &&
                        m.getAwayTeam().equals(awayTeamB2))
                .findFirst()
                .orElse(null);

        // then
        assertEquals(1, summary.size());
        assertNotNull(firstMatch);
        assertNull(secondMatch);
        assertEquals(homeTeamA1, firstMatch.getHomeTeam());
        assertEquals(awayTeamB1, firstMatch.getAwayTeam());
        assertEquals(0, firstMatch.getHomeScore());
        assertEquals(0, firstMatch.getAwayScore());
    }

    @Test
    public void shouldCheckDescribedScenario() throws InterruptedException {
        // given
        ScoreBoard scoreBoard = new ScoreBoard();

        scoreBoard.startMatch(Teams.MEXICO, Teams.CANADA);
        Thread.sleep(1);
        scoreBoard.startMatch(Teams.SPAIN, Teams.BRAZIL);
        Thread.sleep(1);
        scoreBoard.startMatch(Teams.GERMANY, Teams.FRANCE);
        Thread.sleep(1);
        scoreBoard.startMatch(Teams.URUGUAY, Teams.ITALY);
        Thread.sleep(1);
        scoreBoard.startMatch(Teams.ARGENTINA, Teams.AUSTRALIA);

        // when
        scoreBoard.updateScore(Teams.MEXICO, Teams.CANADA, 0, 5);
        scoreBoard.updateScore(Teams.SPAIN, Teams.BRAZIL, 10, 2);
        scoreBoard.updateScore(Teams.GERMANY, Teams.FRANCE, 2, 2);
        scoreBoard.updateScore(Teams.URUGUAY, Teams.ITALY, 6, 6);
        scoreBoard.updateScore(Teams.ARGENTINA, Teams.AUSTRALIA, 3, 1);

        // then
        List<Match> summary = scoreBoard.getSummary();
        assertEquals(5, summary.size());

        int summaryIndex = 0;
        assertEquals(Teams.URUGUAY, summary.get(summaryIndex).getHomeTeam());
        assertEquals(Teams.ITALY, summary.get(summaryIndex).getAwayTeam());
        assertEquals(6, summary.get(summaryIndex).getHomeScore());
        assertEquals(6, summary.get(summaryIndex).getAwayScore());

        summaryIndex = 1;
        assertEquals(Teams.SPAIN, summary.get(summaryIndex).getHomeTeam());
        assertEquals(Teams.BRAZIL, summary.get(summaryIndex).getAwayTeam());
        assertEquals(10, summary.get(1).getHomeScore());
        assertEquals(2, summary.get(1).getAwayScore());

        summaryIndex = 2;
        assertEquals(Teams.MEXICO, summary.get(summaryIndex).getHomeTeam());
        assertEquals(Teams.CANADA, summary.get(summaryIndex).getAwayTeam());
        assertEquals(0, summary.get(summaryIndex).getHomeScore());
        assertEquals(5, summary.get(summaryIndex).getAwayScore());

        summaryIndex = 3;
        assertEquals(Teams.ARGENTINA, summary.get(summaryIndex).getHomeTeam());
        assertEquals(Teams.AUSTRALIA, summary.get(summaryIndex).getAwayTeam());
        assertEquals(3, summary.get(summaryIndex).getHomeScore());
        assertEquals(1, summary.get(summaryIndex).getAwayScore());

        summaryIndex = 4;
        assertEquals(Teams.GERMANY, summary.get(summaryIndex).getHomeTeam());
        assertEquals(Teams.FRANCE, summary.get(summaryIndex).getAwayTeam());
        assertEquals(2, summary.get(summaryIndex).getHomeScore());
        assertEquals(2, summary.get(summaryIndex).getAwayScore());
    }

}