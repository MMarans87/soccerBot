package soccerBot.utility;

import java.util.ArrayList;
import java.util.List;

import soccerBot.model.TeamList;

/**
 * The Class Constants.
 */

public final class Utils {

	// -------------------- GENERATE TEAMS FROM ENUMERATION --------------------//
	public static List<String> GenerateTeams() {
		List<String> teams = new ArrayList<>();
		for (TeamList team : TeamList.class.getEnumConstants()) {
			teams.add(team.getValue());
		}
		return teams;
	}
}
