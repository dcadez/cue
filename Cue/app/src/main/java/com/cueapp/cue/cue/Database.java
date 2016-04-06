package com.cueapp.cue.cue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prateek on 06/04/16.
 */
public class Database {
    private static Database ourInstance = new Database();
    public static Database getInstance() {
        return ourInstance;
    }

    private List<Reminder> allReminders ;

    public Database()
    {
        if(allReminders ==  null){
            allReminders = new ArrayList<>();
        }
    }

    public boolean AddNewReminder(Reminder newReminder)
    {
        for(int i=0;i< allReminders.size();i++)
        {
            if(allReminders.get(i).getId()==newReminder.getId())
            {
                return false;
            }
        }

        allReminders.add(newReminder);
        return true;
    }

}
