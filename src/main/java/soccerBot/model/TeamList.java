package soccerBot.model;

public enum TeamList {

	ATALANTA("ATALANTA"), BOLOGNA("BOLOGNA"), CAGLIARI("CAGLIARI"), CHIEVO("CHIEVO"), EMPOLI("EMPOLI"), FIORENTINA("FIORENTINA"), FROSINONE("FROSINONE"), GENOA("GENOA"), INTER("INTER"), JUVENTUS("JUVENTUS"), LAZIO("LAZIO"), MILAN("MILAN"), NAPOLI("NAPOLI"), PARMA("PARMA"), ROMA("ROMA"), SAMPDORIA("SAMPDORIA"), SASSUOLO("SASSUOLO"), SPAL("SPAL"), TORINO("TORINO"), UDINESE("UDINESE");

	private String value;

	private TeamList(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
