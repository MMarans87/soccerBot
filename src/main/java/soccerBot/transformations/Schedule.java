package soccerBot.transformations;

import java.util.ArrayList;
import java.util.List;

import soccerBot.model.Match;
import soccerBot.model.MatchWeek;
import soccerBot.utility.Constants;
import soccerBot.utility.Utils;

public class Schedule {

	public static List<MatchWeek> generate() {
		List<String> ListTeam = Utils.GenerateTeams();
		List<MatchWeek> schedule = new ArrayList<>();

		if (ListTeam.size() % 2 != 0) {
			ListTeam.add(Constants.SKIP); // If odd number of teams add a dummy
		}

		List<String> homeTeams = new ArrayList<>();
		List<String> awayTeams = new ArrayList<>();

		for (int k = 0; k < ListTeam.size() / 2; k++) {
			homeTeams.add(ListTeam.get(k));
			awayTeams.add(ListTeam.get(ListTeam.size() - 1 - k));
		}

		for (int k = 0; k < ListTeam.size() - 1; k++) {
			MatchWeek matchWeek = new MatchWeek();
			List<Match> matches = new ArrayList<>();
			matchWeek.setMathces(matches);

			if (k % 2 == 0) {
				for (int i = 0; i < ListTeam.size() / 2; i++) {
					Match match = new Match();
					matches.add(match);
					match.setHome(homeTeams.get(i));
					match.setAway(awayTeams.get(i));
				}
			} else {
				for (int i = 0; i < ListTeam.size() / 2; i++) {
					Match match = new Match();
					matches.add(match);
					match.setHome(awayTeams.get(i));
					match.setAway(homeTeams.get(i));
				}
			}

			// shifto l'array away per creare differenti matchup
			String shifted = awayTeams.get(awayTeams.size() - 1);
			awayTeams = shift(awayTeams, shifted);
			schedule.add(matchWeek);

		}
		return duplicate(schedule);
	}

	public static List<MatchWeek> duplicate(List<MatchWeek> schedule) {
		List<MatchWeek> back = new ArrayList<>();
		for (MatchWeek tempMatchWeek : schedule) {
			MatchWeek matchWeek = new MatchWeek();
			List<Match> matches = new ArrayList<>();
			matchWeek.setMathces(matches);
			for (Match tempMatch : tempMatchWeek.getMathces()) {
				Match match = new Match();
				match.setHome(tempMatch.getAway());
				match.setAway(tempMatch.getHome());
				matches.add(match);
			}
			back.add(matchWeek);
		}
		schedule.addAll(back);
		return schedule;
	}

	private static List<String> shift(List<String> awayTeams, String add) {
		List<String> temp = new ArrayList<>();
		for (int i = 1; i < awayTeams.size(); i++) {
			temp.add(awayTeams.get(i - 1));
		}
		temp.add(0, add);
		return temp;
	}
}
