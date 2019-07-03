package za.co.wethinkcode.avajlauncher;

public interface Flyable
{
	/*
    * Known as pure abstract methods
    */
	public void	updateConditions();
	public void	registerTower(WeatherTower weatherTower);
}
