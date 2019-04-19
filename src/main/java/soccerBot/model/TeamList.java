package soccerBot.model;

public enum TeamList {

	ALBINOLEFFE("ALBINOLEFFE"),
	ASCOLI("ASCOLI"),
	ATALANTA("ATALANTA"),
	AVELLINO("AVELLINO"),
	BARI("BARI"),
	BOLOGNA("BOLOGNA"),
	BRESCIA("BRESCIA"),
	CAGLIARI("CAGLIARI"),
	CATANIA("CATANIA"),
	CATANZARO("CATANZARO"),
	CERVIA("CERVIA"),
	CESENA("CESENA"),
	CHIEVO("CHIEVO"),
	CITTADELLA("CITTADELLA"),
	COMO("COMO"),
	COSENZA("COSENZA"),
	CREMONESE("CREMONESE"),
	CROTONE("CROTONE"),
	EMPOLI("EMPOLI"),
	FIDELIS_ANDRIA("FIDELIS ANDRIA"),
	FIORENTINA("FIORENTINA"),
	FOGGIA("FOGGIA"),
	FROSINONE("FROSINONE"),
	GENOA("GENOA"),
	HELLAS_VERONA("HELLAS VERONA"),
	INTER("INTER"),
	JUVE_STABIA("JUVE STABIA"),
	JUVENTUS("JUVENTUS"),
	VICENZA("L.R.VICENZA"),
	LATINA("LATINA"),
	LAZIO("LAZIO"),
	LECCE("LECCE"),
	LIVORNO("LIVORNO"),
	MILAN("MILAN"),
	MODENA("MODENA"),
	MONZA("MONZA"),
	NAPOLI("NAPOLI"),
	NOVARA("NOVARA"),
	PADOVA("PADOVA"),
	PALERMO("PALERMO"),
	PARMA("PARMA"),
	PERUGIA("PERUGIA"),
	PESCARA("PESCARA"),
	PIACENZA("PIACENZA"),
	PRO_PATRIA("PRO PATRIA"),
	PRO_SESTO("PRO SESTO"),
	PRO_VERCELLI("PRO VERCELLI"),
	REGGIANA("REGGIANA"),
	REGGINA("REGGINA"),
	RIMINI("RIMINI"),
	ROBUR_SIENA("ROBUR SIENA"),
	ROMA("ROMA"),
	SALERNITANA("SALERNITANA"),
	SAMPDORIA("SAMPDORIA"),
	SASSUOLO("SASSUOLO"),
	SPAL("SPAL"),
	TERNANA("TERNANA"),
	TORINO("TORINO"),
	TREVISO("TREVISO"),
	TRIESTINA("TRIESTINA"),
	UDINESE("UDINESE"),
	VARESE("VARESE"),
	VENEZIA("VENEZIA"),
	VIAREGGIO("VIAREGGIO");

	private String value;

	private TeamList(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
