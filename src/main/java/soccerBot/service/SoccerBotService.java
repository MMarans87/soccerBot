package soccerBot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import soccerBot.client.FileUtilities;
import soccerBot.model.Match;
import soccerBot.model.MatchWeek;
import soccerBot.model.Team;
import soccerBot.model.errors.GenericError;
import soccerBot.transformations.Create;
import soccerBot.transformations.Engine;
import soccerBot.transformations.Schedule;
import soccerBot.utility.Constants;

@Service
public class SoccerBotService {

	public static final Logger logger = LoggerFactory.getLogger(SoccerBotService.class);

	public static void socceBot() throws GenericError {
		try {
			Map<String, List<Team>> teamsMap = Create.TeamsRound(Create.teams(), 8);
			FileUtilities.saveResponse(Constants.RESULTS, Constants.START);
			FileUtilities.updateResponse(Constants.RESULTS, Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
			List<String> winners = new ArrayList<>();
			for (String roundNum : teamsMap.keySet()) {
				List<Team> teams = teamsMap.get(roundNum);
				List<String> teamsList = generateTeamsList(teams);
				List<MatchWeek> schedule = Schedule.generate(teamsList);
				FileUtilities.updateResponse(Constants.RESULTS, Constants.EMPTY + "GIRONE " + roundNum + Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
				for (MatchWeek matchWeek : schedule) {
					FileUtilities.updateResponse(Constants.RESULTS, (Constants.DAY + (schedule.indexOf(matchWeek) + 1)) + Constants.EMPTY);
					for (Match match : matchWeek.getMathces()) {
						Engine.calculate(match, teams);
						Engine.update(teams, match);
						FileUtilities.updateResponse(Constants.RESULTS, FileUtilities.outputMatch(match) + Constants.EMPTY);
					}
					FileUtilities.updateResponse(Constants.RESULTS, Constants.EMPTY + FileUtilities.outputTeams(teams) + Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
				}
				winners.add(FileUtilities.outputWinner(teams));
			}
			FileUtilities.updateResponse(Constants.RESULTS, "WINNERS: " + winners.toString() + Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
			// TODO ELIMINATORIE
			do {
				winners = eliminatorie(winners);
			} while (winners.size() > 1);
			FileUtilities.updateResponse(Constants.RESULTS, "THE WINNER IS: " + winners.toString() + Constants.EMPTY);

		} catch (Exception e) {
			throw new GenericError("Generic Error: " + e.getMessage(), e);
		}
	}

	private static List<String> eliminatorie(List<String> teams) throws IOException, GenericError {
		List<String> newWinners = new ArrayList<>();
		List<MatchWeek> schedule = Schedule.generate(teams);
		MatchWeek matchWeek = schedule.get(0);
		FileUtilities.updateResponse(Constants.RESULTS, (Constants.FINAL_DAY + (matchWeek.getMathces().size() * 2)) + Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
		for (Match match : matchWeek.getMathces()) {
			Team home = new Team();
			home.setName(match.getHome());
			Team away = new Team();
			away.setName(match.getAway());
			Engine.calculateScoreElim(home, away, match);
			FileUtilities.updateResponse(Constants.RESULTS, FileUtilities.outputMatch(match) + Constants.EMPTY);
			if (match.getHomeGoals() > match.getAwayGoals()) {
				newWinners.add(match.getHome());
			} else {
				newWinners.add(match.getAway());
			}
		}
		FileUtilities.updateResponse(Constants.RESULTS, Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
		return newWinners;
	}

	private static List<String> generateTeamsList(List<Team> teams) {
		List<String> teamsList = new ArrayList<>();
		for (Team team : teams) {
			teamsList.add(team.getName());
		}
		return teamsList;
	}

}
