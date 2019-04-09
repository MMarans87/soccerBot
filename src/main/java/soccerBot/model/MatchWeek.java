package soccerBot.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class MatchWeek {
	private List<Match> mathces;

	public List<Match> getMathces() {
		return mathces;
	}

	public void setMathces(List<Match> mathces) {
		this.mathces = mathces;
	}

	@Override
	public String toString() {
		return "MatchWeek [mathces=" + mathces + "]";
	}

}