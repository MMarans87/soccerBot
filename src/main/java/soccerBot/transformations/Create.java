package soccerBot.transformations;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import soccerBot.model.Team;
import soccerBot.model.errors.GenericError;
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

	public static Map<String, List<Team>> TeamsRound(List<Team> teams, int rounds) throws GenericError {
		Map<String, List<Team>> teamsMap = new HashMap<>();
		int size = 0;
		try {
			size = teams.size() / rounds;
		} catch (Exception e) {
			throw new GenericError();
		}
		if (size != 0) {
			for (int i = 0; i < rounds; i++) {
				List<Team> round = new ArrayList<>();
				for (int j = 0; j < size; j++) {
					int index = getRandomIndex(teams);
					round.add(teams.get(index));
					teams.remove(index);
				}
				teamsMap.put(String.valueOf(i+1), round);
			}
		}
		return teamsMap;
	}

	public static int getRandomIndex(List<?> list) {
		SecureRandom rand = new SecureRandom();
		return rand.nextInt(list.size());
	}

}
