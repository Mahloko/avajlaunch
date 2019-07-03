package za.co.wethinkcode.avajlauncher;

public abstract class Aircraft
{
	/*
    * Attributes
    */
	protected long			id;
	protected String		name;
	protected Coordinates	coordinates;
	private	static long			idCounter = 0;

	/*
    * Constructor
    */
	protected		Aircraft(String name, Coordinates coordinates)
	{
		this.name = name;
		this.coordinates = coordinates;
		id = nextId();
		return ;
	}

	/*
    * Methods
    */
	private long	nextId()
	{
		return ++idCounter;
	}
}
