package com.example.thugm.gosafe_v1;

/**
 * Created by Felix on 1/3/2017.
 */

public class CarData
{
    /* Health Types are either Progressive (0 - 100), Boolean (0, 1) or Two Step (0, 1, 2)
     * Boolean is either error or working, and two step is: Not working, needs attention, working
     * all health values are better when higher */

    public enum HealthTypes { None, Progressive, Boolean, TwoStep };
    public String Name;
    public String Value;
    public HealthTypes HealthType;
    public int Health;

    public CarData(String name, String value)
    {
        Name = name;
        Value = value;
        HealthType = HealthTypes.None;
        Health = 0;
    }

    public CarData(String name, String value, HealthTypes healthType, int health)
    {
        Name = name;
        Value = value;
        HealthType = healthType;
        Health = health;
    }
}
