package soccerBot.client;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import soccerBot.model.Match;
import soccerBot.model.Team;
import soccerBot.model.errors.GenericError;
import soccerBot.utility.Constants;

public class FileUtilities {

	public final static String FORMAT = "|%-15s|%-6s|%-6s|%-6s|%-6s|";

	public static Boolean updateResponse(String fileName, String data) throws IOException, GenericError {
		try {
			Resource resource = new FileSystemResource(fileName);
			File file = resource.getFile();
			FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8, true);
		} catch (Exception e) {
			throw new GenericError("Impossible to update file");
		}
		return true;
	}

	public static String readResponse(String fileName) throws IOException {
		Resource resource = new FileSystemResource(fileName);
		File file = resource.getFile();
		String out = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		return out;
	}

	public static Boolean saveResponse(String fileName, String data) throws IOException, GenericError {
		try {
			Resource resource = new FileSystemResource(fileName);
			File file = resource.getFile();
			FileUtils.writeStringToFile(file, data, StandardCharsets.UTF_8);
		} catch (IOException e) {
			throw new GenericError("Impossible to save file");
		}
		return true;
	}

	public String retrieveFilePathFromUrl(String url, String prefix) {
		String fileName = url;
		fileName = fileName.replace("/{", ".").replace("}/", ".").replace("/", ".").replace("}", "");
		if (fileName.startsWith(".")) {
			fileName = fileName.substring(1);
		}
		return Constants.PREFIX_PATH + fileName.toLowerCase() + "." + prefix;
	}

	public static String retrieveFilePath(String fileName) {
		return Constants.PREFIX_PATH + fileName.toLowerCase();
	}

	public static void deleteFile(String fileName) throws IOException {
		Resource resource = new FileSystemResource(fileName);
		File file = resource.getFile();
		FileUtils.deleteQuietly(file);
	}

	public static String outputMatch(Match match) {
		return String.format("%15s %1s - %1s %-15s", match.getHome(), match.getHomeGoals(), match.getAwayGoals(), match.getAway());
	}

	public static String outputTeams(List<Team> teams) {
		String rank = "PLACEMENT:" + "\n";
		rank += String.format(FORMAT, "SQUADRA", "PUNTI", "GOAL F", "GOAL S", "STRIKE") + "\n";
		Collections.sort(teams, new Comparator<Team>() {
			@Override
			public int compare(Team t1, Team t2) {
				if (t1.getPoints() < t2.getPoints())
					return 1;
				if (t1.getPoints() > t2.getPoints())
					return -1;
				return comparePoints(t1.getGoals() - t1.getConceded(), t2.getGoals() - t2.getConceded());
			}

			private int comparePoints(int i, int j) {
				if (i < j)
					return 1;
				return -1;
			}
		});
		for (Team team : teams) {
			rank += String.format(FORMAT, team.getName(), team.getPoints(), team.getGoals(), team.getConceded(), team.getWinStrike()) + "\n";
		}
		return rank;
	}

	public static String outputWinner(List<Team> teams) {
		List<String> winners = new ArrayList<>();
		int winnerPoints = 0;
		for (Team team : teams) {
			if (team.getPoints() == winnerPoints) {
				winners.add(team.getName());
			} else {
				if (team.getPoints() > winnerPoints) {
					winners.clear();
					winners.add(team.getName());
					winnerPoints = team.getPoints();
				}
			}
		}
		return winners.get(0).toString();
	}

}
