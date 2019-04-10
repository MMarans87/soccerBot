package soccerBot.service;

import java.util.List;

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
			List<Team> teams = Create.teams();
			List<MatchWeek> schedule = Schedule.generate();
			FileUtilities.saveResponse(Constants.RESULTS, Constants.START);
			FileUtilities.updateResponse(Constants.RESULTS, Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
			for (MatchWeek matchWeek : schedule) {
				FileUtilities.updateResponse(Constants.RESULTS, (Constants.DAY + (schedule.indexOf(matchWeek) + 1)) + Constants.EMPTY);
				for (Match match : matchWeek.getMathces()) {
					Engine.calculate(match, teams);
					Engine.update(teams, match);
					FileUtilities.updateResponse(Constants.RESULTS, FileUtilities.outputMatch(match) + Constants.EMPTY);
				}
				FileUtilities.updateResponse(Constants.RESULTS, Constants.EMPTY + FileUtilities.outputTeams(teams) + Constants.EMPTY + Constants.SEPARATOR + Constants.EMPTY);
			}
			FileUtilities.updateResponse(Constants.RESULTS, FileUtilities.outputWinner(teams));
		} catch (Exception e) {
			throw new GenericError("Generic Error: " + e.getMessage(), e);
		}
	}

}
