public class User 
{
	private String name;
	private String id;
	private String ic;
	
	// Default Constructor
	public User()
	{

	}

	// Constructor
	public User(String name, String id, String ic)
	{
		this.name = name;
		this.id = id;
		this.ic = ic;
	}
	
	// Accessors and Mutators
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getID()
	{
		return id;
	}
	
	public void setID(String id)
	{
		this.id = id;
	}
	
	public String getIC()
	{
		return ic;
	}
	
	public void setIC(String ic)
	{
		this.ic = ic;
	}

}
