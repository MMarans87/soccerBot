package soccerBot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Team {
	private int id;
	private String name;
	private int winStrike;
	private int points;
	private int goals;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWinStrike() {
		return winStrike;
	}

	public void setWinStrike(int winStrike) {
		this.winStrike = winStrike;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", winStrike=" + winStrike + ", points=" + points + ", goals=" + goals + "]";
	}

}