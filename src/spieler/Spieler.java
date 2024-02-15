package spieler;

public class Spieler
{
	private String nach_name;
	private String vor_name;
	private String email;

	public Spieler()
	{
		
	}

	public Spieler(String nachName, String vorName,  String e_mail)
	{
		super();
		this.nach_name = nachName;
		this.vor_name = vorName;
		this.email = e_mail;
	}

	public String getNachName()
	{
		return this.nach_name;
	}

	public void setNachName(String name)
	{
	    this.nach_name = name;
	}
	
	public String getVorName()
	{
		return this.vor_name;
	}
	
	public void setVorName(String name)
	{
	    this.vor_name = name;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(String e)
	{
	    this.email = e;
	}
	
	@Override
	public String toString() {
		return String
				.format("Spieler [nach_name=%s, vor_name=%s, email=%s]",
						nach_name, vor_name, email);
	}

}
