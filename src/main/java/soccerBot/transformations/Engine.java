package soccerBot.transformations;

import java.util.List;
import java.util.Random;

import soccerBot.model.Match;
import soccerBot.model.Team;

public class Engine {

	public static void calculate(Match match, List<Team> teams) {

		Team homeTeam = new Team();
		Team awayTeam = new Team();
		for (Team team : teams) {
			if (match.getHome().equals(team.getName())) {
				homeTeam = team;
			} else if (match.getAway().equals(team.getName())) {
				awayTeam = team;
			}
		}
		calculateScore(homeTeam, awayTeam, match);
	}

	public static void update(List<Team> teams, Match match) {
		for (Team team : teams) {
			if (team.getName().equals(match.getHome())) {
				team.setPoints(team.getPoints() + evaluatePoints(match.getHomeGoals(), match.getAwayGoals()));
				team.setGoals(team.getGoals() + match.getHomeGoals());
				team.setConceded(team.getConceded() + match.getAwayGoals());
				team.setWinStrike(evaluateWinStrike(evaluatePoints(match.getHomeGoals(), match.getAwayGoals()), team.getWinStrike()));
			}
			if (team.getName().equals(match.getAway())) {
				team.setPoints(team.getPoints() + evaluatePoints(match.getAwayGoals(), match.getHomeGoals()));
				team.setGoals(team.getGoals() + match.getAwayGoals());
				team.setConceded(team.getConceded() + match.getHomeGoals());
				team.setWinStrike(evaluateWinStrike(evaluatePoints(match.getAwayGoals(), match.getHomeGoals()), team.getWinStrike()));
			}
		}
	}

	private static int evaluateWinStrike(int evaluatePoints, int winStrike) {
		return evaluatePoints == 3 ? (winStrike + 1) : 0;
	}

	public static void calculateScore(Team homeTeam, Team awayTeam, Match match) {

		// calculate home goals
		Random homeRnd = new Random();
		Integer homeGoals;
		do {
			homeGoals = (int) Math.round(homeRnd.nextGaussian() * 1.1 + (1.2d + 0.1d + ((homeTeam.getPoints() - awayTeam.getPoints()) * 0.018d)));
		} while (homeGoals < 0 || homeGoals > 9);
		match.setHomeGoals(homeGoals);

		// calculate home goals
		Random awayRnd = new Random();
		Integer awayGoals;
		do {
			awayGoals = (int) Math.round(awayRnd.nextGaussian() * 1.1 + (1.2d + ((awayTeam.getPoints() - homeTeam.getPoints()) * 0.018d)));
		} while (awayGoals < 0 || awayGoals > 9);
		match.setAwayGoals(awayGoals);
	}

	public static int evaluatePoints(Integer teamScore, Integer oppositeScore) {
		if (teamScore == oppositeScore) {
			return 1;
		}
		if (teamScore > oppositeScore) {
			return 3;
		}
		return 0;
	}

	public static void calculateScoreElim(Team homeTeam, Team awayTeam, Match match) {
		// calculate home goals
		Random homeRnd = new Random();
		Integer homeGoals;
		Integer awayGoals;
		do {
			do {
				homeGoals = (int) Math.round(homeRnd.nextGaussian() * 1.1 + (1.2d + ((homeTeam.getPoints() - awayTeam.getPoints()) * 0.018d)));
			} while (homeGoals < 0 || homeGoals > 9);
			match.setHomeGoals(homeGoals);

			// calculate home goals
			Random awayRnd = new Random();
			do {
				awayGoals = (int) Math.round(awayRnd.nextGaussian() * 1.1 + (1.2d + ((awayTeam.getPoints() - homeTeam.getPoints()) * 0.018d)));
			} while (awayGoals < 0 || awayGoals > 9);
			match.setAwayGoals(awayGoals);
		} while (homeGoals == awayGoals);
	}
}
