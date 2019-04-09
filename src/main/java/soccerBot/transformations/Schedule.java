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

		int numDays = (ListTeam.size() - 1); // Days needed to complete tournament
		int halfSize = ListTeam.size() / 2;

		List<String> teams = new ArrayList<>();

		teams.addAll(ListTeam);
		teams.remove(0);

		int teamsSize = teams.size();

		for (int day = 0; day < numDays; day++) {
			MatchWeek matchWeek = new MatchWeek();
			List<Match> matches = new ArrayList<>();
			matchWeek.setMathces(matches);

			int teamIdx = day % teamsSize;
			Match match = new Match();
			matches.add(match);
			match.setHome(teams.get(teamIdx));
			match.setAway(ListTeam.get(0));

			for (int idx = 1; idx < halfSize; idx++) {
				int firstTeam = (day + idx) % teamsSize;
				int secondTeam = (day + teamsSize - idx) % teamsSize;
				Match alternativeMatch = new Match();
				matches.add(alternativeMatch);
				alternativeMatch.setHome(teams.get(firstTeam));
				alternativeMatch.setAway(teams.get(secondTeam));
			}

			schedule.add(matchWeek);

		}
		return schedule;
	}

}
