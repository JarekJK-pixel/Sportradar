package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatchTest {

    public static final int HOME_SCORE_ZERO = 0;
    public static final int AWAY_SCORE_ZERO = 0;
    private final static String homeTeamA = "A";
    private final static String awayTeamB = "B";
    public static final int TOTAL_SCORE_ZERO = 0;

    private Match match;

    @BeforeEach
    public void init() {
        // given
        match = new Match(homeTeamA, awayTeamB);
    }

    @Test
    public void should_createStartMatch() {
        // when-then
        assertEquals(homeTeamA, match.getHomeTeam());
        assertEquals(awayTeamB, match.getAwayTeam());
        assertEquals(HOME_SCORE_ZERO, match.getHomeScore());
        assertEquals(AWAY_SCORE_ZERO, match.getAwayScore());
        assertEquals(TOTAL_SCORE_ZERO, match.getTotalScore());
        assertNotEquals(0, match.getStartTimestamp());
    }

    @Test
    public void should_returnMathTotalScore() {
        // given
        int homeScore = 2;
        int awayScore = 3;
        match.updateScore(homeScore, awayScore);

        // when-then
        assertEquals(homeTeamA, match.getHomeTeam());
        assertEquals(awayTeamB, match.getAwayTeam());
        assertEquals(homeScore, match.getHomeScore());
        assertEquals(awayScore, match.getAwayScore());
        assertEquals(homeScore + awayScore, match.getTotalScore());
    }

    @Test
    void should_UpdateScoreWithValidValues() {
        // given
        int newHomeScore = 3;
        int newAwayScore = 2;

        // when
        match.updateScore(newHomeScore, newAwayScore);

        // then
        assertEquals(homeTeamA, match.getHomeTeam());
        assertEquals(awayTeamB, match.getAwayTeam());
        assertEquals(newHomeScore, match.getHomeScore());
        assertEquals(newAwayScore, match.getAwayScore());
    }

    @Test
    void should_ThrowIllegalArgumentExceptionForNegativeHomeScore() {
        // given
        int invalidHomeScore = -1;
        int awayScore = 2;

        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> match.updateScore(invalidHomeScore, awayScore)
        );

        assertEquals(Match.INVALID_SCORE_MESSAGE, exception.getMessage());
    }

    @Test
    void should_ThrowIllegalArgumentExceptionForNegativeAwayScore() {
        // given
        int homeScore = 2;
        int invalidAwayScore = -1;

        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> match.updateScore(homeScore, invalidAwayScore)
        );

        assertEquals(Match.INVALID_SCORE_MESSAGE, exception.getMessage());
    }

    @Test
    void should_ThrowIllegalArgumentExceptionForBothNegativeScores() {
        // given
        int invalidHomeScore = -3;
        int invalidAwayScore = -2;

        // when & then
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> match.updateScore(invalidHomeScore, invalidAwayScore)
        );

        assertEquals(Match.INVALID_SCORE_MESSAGE, exception.getMessage());
    }
}