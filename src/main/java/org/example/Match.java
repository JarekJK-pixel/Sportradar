package org.example;

import java.time.Instant;
import java.util.Objects;

/**
 * Represents a match between two teams, including score and timestamp information.
 */
public class Match {
    public static final String INVALID_SCORE_MESSAGE = "Scores must be non-negative.";

    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final long startTimestamp;

    /**
     * Creates a Match between two teams with initial scores of zero.
     *
     * @param homeTeam the home team name
     * @param awayTeam the away team name
     */
    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTimestamp = Instant.now().toEpochMilli();
    }

    /**
     * Creates a copy of the specified Match.
     *
     * @param other the Match to copy
     */
    public Match(Match other) {
        this.homeTeam = other.homeTeam;
        this.awayTeam = other.awayTeam;
        this.homeScore = other.homeScore;
        this.awayScore = other.awayScore;
        this.startTimestamp = other.startTimestamp;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public long getStartTimestamp() {
        return startTimestamp;
    }

    /**
     * Updates the scores for the home and away teams.
     * <p>
     * This method checks that the provided scores are non-negative before updating
     * the scores. If either score is negative, an IllegalArgumentException is thrown
     * with a message indicating that scores must be non-negative.
     *
     * @param homeScore the new score for the home team
     * @param awayScore the new score for the away team
     * @throws IllegalArgumentException if either score is negative
     */
    public void updateScore(int homeScore, int awayScore) {
        validateScores(homeScore, awayScore);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    /**
     * Calculates the total score of the match by summing the scores of the home
     * and away teams.
     *
     * @return the total score of the match
     */
    public int getTotalScore() {
        return homeScore + awayScore;
    }

    /**
     * Validates the provided scores for the home and away teams.
     * <p>
     * This method checks if the scores are non-negative. If any of the scores
     * are negative, an {@link IllegalArgumentException} is thrown with a message
     * indicating that scores must be non-negative.
     * </p>
     *
     * @param homeScore the score of the home team
     * @param awayScore the score of the away team
     * @throws IllegalArgumentException if any score is negative
     */
    private void validateScores(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException(INVALID_SCORE_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return String.format("%s:%d vs %s:%d (Started: %d)", homeTeam, homeScore, awayTeam, awayScore, startTimestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match match)) return false;
        return homeScore == match.homeScore &&
                awayScore == match.awayScore &&
                startTimestamp == match.startTimestamp &&
                Objects.equals(homeTeam, match.homeTeam) &&
                Objects.equals(awayTeam, match.awayTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore, startTimestamp);
    }


}
