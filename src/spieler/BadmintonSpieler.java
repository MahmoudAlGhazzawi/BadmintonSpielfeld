package spieler;

public class BadmintonSpieler
{
	private String spieler_name;
	private String team_name;

	public BadmintonSpieler()
	{
			
	}

	public BadmintonSpieler(String spielerName, String teamName)
	{
		super();
		this.spieler_name = spielerName;
		this.team_name = teamName;
	}

	public String getSpielerName()
	{
		return this.spieler_name;
	}

	public void setSpielerName(String name)
	{
		this.spieler_name = name;
	}
	
	public String getTeamName()
	{
		return this.team_name;
	}

	public void setTeamName(String name)
	{
		this.team_name = name;
	}
		
		
		
	@Override
	public String toString() 
	{
		return String
				.format("Spieler [nach_name=%s], Team [vor_name=%s]",
						spieler_name, team_name);
	}


}
