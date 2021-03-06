package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class SchelduleModeTest {
    ScheduleMode scheduleMode = new ScheduleMode();

    @Test
    public void makingScheduleMode(){
    assertEquals(new ArrayList<Schedule>(), scheduleMode.getList());
}
    @Test
    public void setSchedule(){
        //Test Number 2-1, 2-2
        Time tmp = new Time();
        Schedule tmpSchedule = scheduleMode.saveValue(-1, tmp);

        assertEquals(tmp, tmpSchedule.scheduleTime);
        assertEquals(Info.SCH_TYPE_ASL, tmpSchedule.scheduleType);
        assertEquals(1, scheduleMode.getList().size());
    }

    @Test
    public void modifySchedule(){
        //Test Number 3-2
        Time tmp = new Time();
        Schedule tmpSchedule = scheduleMode.saveValue(-1, tmp);

        tmpSchedule.scheduleTime.hour = 1;
        Schedule tmpSchedule2 = scheduleMode.saveValue(0, tmpSchedule.scheduleTime);
        assertEquals(1, tmpSchedule2.scheduleTime.hour);
    }

    @Test
    public void deleteSchedule(){
        //Test Number 4-2
        assertFalse(scheduleMode.deleteValue(0));

        //Test Number 4-1
        Time tmp = new Time();
        Schedule tmpSchedule = scheduleMode.saveValue(-1, tmp);
        assertTrue(scheduleMode.deleteValue(0));

        //Test Number 4-2
        assertFalse(scheduleMode.deleteValue(0));
    }

    @Test
    public void isAvailableAdd(){
        //Test Number 2-3, 3-3
        Time tmpTime = new Time();
        tmpTime.month = 1;
        tmpTime.day = 1;
        tmpTime.hour = 1;

        Schedule schedule = new Schedule(); //01.01 00:00:00
        assertFalse(scheduleMode.isAvailAdd(tmpTime, schedule));

        Schedule schedule2 = new Schedule();
        schedule2.scheduleTime.hour = 2;    //01.01 02:00:00
        assertTrue(scheduleMode.isAvailAdd(tmpTime, schedule2));
    }

    @Test
    public void sortSchedule() {
        //Test Number 1-1 in Add Schedule: insert schedules in reverse order. (4,3,2,1)
        for (int i = 0; i < 4; i++) {
            Time tmpTime = new Time();
            tmpTime.hour = 4 - i;
            scheduleMode.saveValue(-1, tmpTime);
        }

        for(int i=0; i<4; i++)
            assertEquals(i+1, scheduleMode.getList().get(i).scheduleTime.hour);

        //Test Number 1-1 in Delete Schedule
        assertTrue(scheduleMode.deleteValue(2));
        for(int i=0; i<3; i++) {
            if(i<2)
                assertEquals(i+1, scheduleMode.getList().get(i).scheduleTime.hour);
            else
                assertEquals(i+2, scheduleMode.getList().get(i).scheduleTime.hour);
        }

        //Test Number 1-1 in Modify Schedule
        Time temp1 = new Time();
        Time temp2 = new Time();
        temp1.hour = 6;
        temp2.hour = 2;
        scheduleMode.saveValue(1, temp1);
        scheduleMode.saveValue(0, temp2);
        for(int i=0; i<3; i++)
            assertEquals((i+1)*2, scheduleMode.getList().get(i).scheduleTime.hour);
    }
}
