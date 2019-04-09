package soccerBot.transformations;

import java.util.ArrayList;
import java.util.List;

import soccerBot.model.Team;
import soccerBot.utility.Utils;

public class Create {

	public static List<Team> teams() {
		List<Team> teams = new ArrayList<>();
		for (String teamName : Utils.GenerateTeams()) {
			Team team = new Team();
			team.setId(Utils.GenerateTeams().indexOf(teamName));
			team.setName(teamName);
			team.setPoints(0);
			team.setWinStrike(0);
			teams.add(team);
		}
		return teams;
	}

}
