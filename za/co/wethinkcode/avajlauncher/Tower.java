package za.co.wethinkcode.avajlauncher;

import java.util.ArrayList;

import java.util.List;

public abstract class Tower
{
    /*
    * Attributes
    */
    private ArrayList<Flyable>  observers = new ArrayList<Flyable>();

    /*
    * Methods
    */
    public void register(Flyable flyable)
    {
        observers.add(flyable);
        return ;
    }

    public void unregister(Flyable flyable)
    {
        observers.remove(observers.indexOf(flyable));
        return ;
    }

    protected void  conditionsChanged()
    {
        for(int i = 0; i < observers.size(); i++)
            observers.get(i).updateConditions();
        return ;
    }
}
