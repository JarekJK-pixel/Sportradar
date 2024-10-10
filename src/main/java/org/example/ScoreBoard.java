package org.example;

import java.util.*;

/**
 * Represents a scoreboard that tracks multiple matches.
 */
public class ScoreBoard {

    private final Map<String, Match> matches;

    public ScoreBoard() {
        this.matches = new HashMap<>();
    }

    /**
     * Starts a match between two teams.
     *
     * @param homeTeam the home team name
     * @param awayTeam the away team name
     */
    public void startMatch(String homeTeam, String awayTeam) {
        matches.put(generateKey(homeTeam, awayTeam), new Match(homeTeam, awayTeam));
    }

    /**
     * Updates the score for a specific match.
     *
     * @param homeTeam  the home team name
     * @param awayTeam  the away team name
     * @param homeScore the new score of the home team
     * @param awayScore the new score of the away team
     */
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = matches.get(generateKey(homeTeam, awayTeam));
        if (match != null) {
            match.updateScore(homeScore, awayScore);
        }
    }

    /**
     * Finishes a match and removes it from the scoreboard.
     *
     * @param homeTeam the home team name
     * @param awayTeam the away team name
     */
    public void finishMatch(String homeTeam, String awayTeam) {
        matches.remove(generateKey(homeTeam, awayTeam));
    }


    /**
     * Returns a summary of the matches sorted by total score and start timestamp.
     *
     * @return a list of matches sorted by total score and start timestamp
     */
    public final List<Match> getSummary() {
        return matches.values().stream()
                .sorted(Comparator.comparingInt(Match::getTotalScore).reversed()
                        .thenComparing(Comparator.comparingLong(Match::getStartTimestamp).reversed()))
                .map(Match::new)
                .toList();
    }

    /**
     * Generates a unique key for a match based on the home and away team names.
     * <p>
     * This method combines the home and away team names with a hyphen (-)
     * to create a unique identifier that can be used to store and retrieve
     * matches from the scoreboard.
     * </p>
     *
     * @param homeTeam the name of the home team
     * @param awayTeam the name of the away team
     * @return a unique key for the match in the format "homeTeam-awayTeam"
     */
    private String generateKey(String homeTeam, String awayTeam) {
        return homeTeam + "-" + awayTeam;
    }

    /**
     * Retrieves a match from the scoreboard using the home and away team names.
     * <p>
     * This method looks up the match in the scoreboard by generating a key
     * from the provided home and away team names. If a match is found, it
     * returns the corresponding {@link Match} object; otherwise, it returns
     * null.
     * </p>
     *
     * @param homeTeam the name of the home team
     * @param awayTeam the name of the away team
     * @return the {@link Match} object associated with the specified teams,
     * or null if no match is found
     */
    private Match findMatch(String homeTeam, String awayTeam) {
        return matches.get(generateKey(homeTeam, awayTeam));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreBoard that)) return false;
        return Objects.equals(matches, that.matches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matches);
    }
}
