package soccerBot.transformations;

import java.util.List;
import java.util.Random;

import soccerBot.model.Match;
import soccerBot.model.Team;

public class Engine {

	public static void calculate(Match match) {
		match.setHomeGoals(calculateScore());
		match.setAwayGoals(calculateScore());
	}

	public static void update(List<Team> teams, Match match) {
		for (Team team : teams) {
			if (team.getName().equals(match.getHome())) {
				team.setPoints(team.getPoints() + evaluatePoints(match.getHomeGoals(), match.getAwayGoals()));
				team.setGoals(team.getGoals() + match.getHomeGoals());
				team.setWinStrike(evaluateWinStrike(evaluatePoints(match.getHomeGoals(), match.getAwayGoals()), team.getWinStrike()));
			}
			if (team.getName().equals(match.getAway())) {
				team.setPoints(team.getPoints() + evaluatePoints(match.getAwayGoals(), match.getHomeGoals()));
				team.setGoals(team.getGoals() + match.getAwayGoals());
				team.setWinStrike(evaluateWinStrike(evaluatePoints(match.getAwayGoals(), match.getHomeGoals()), team.getWinStrike()));
			}
		}
	}

	private static int evaluateWinStrike(int evaluatePoints, int winStrike) {
		return evaluatePoints != 0 ? (winStrike + 1) : 0;
	}

	public static Integer calculateScore() {
		Random rnd = new Random();
		Integer value;
		do {
			value = (int) Math.round(rnd.nextGaussian() * 1.2 + 1.4d);
		} while (value < 0 || value > 9);
		return value;
	}

	public static String evaluateResult(Integer valueH, Integer valueA) {
		if (valueH == valueA) {
			return "DRAW";
		}
		if (valueH > valueA) {
			return "HOME TEAM WIN";
		}
		return "AWAY TEAM WIN";
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
}
