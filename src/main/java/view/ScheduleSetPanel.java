package view;

import model.Info;
import model.Schedule;
import model.Time;

import javax.swing.*;
import java.awt.*;

public class ScheduleSetPanel extends JPanel {
    MenuPanel menuPanel = new MenuPanel();
    JLabel curTimeLabel = new JLabel();
    SchTimePanel curSchTimePanel = new SchTimePanel();
    SchDatePanel curSchDatePanel = new SchDatePanel();

    public ScheduleSetPanel() {
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(Color.WHITE);

        curTimeLabel.setFont(GUI.font40);
        curTimeLabel.setText("10:12:40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curTimeLabel);

        this.add(curSchTimePanel);

        this.add(curSchDatePanel);

        this.add(menuPanel);
    }

    class SchTimePanel extends JPanel {
        JLabel hourLabel = new JLabel();
        JLabel minuteLabel = new JLabel();
        JLabel colon1 = new JLabel();

        public SchTimePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.WHITE);

            hourLabel.setFont(GUI.font75);
            hourLabel.setText("10");
            hourLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(hourLabel);

            colon1.setFont(GUI.font75);
            colon1.setText(":");
            colon1.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon1);

            minuteLabel.setFont(GUI.font75);
            minuteLabel.setText("12");
            minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(minuteLabel);
        }
    }

    class SchDatePanel extends JPanel {
        JLabel schTypeLabel = new JLabel();
        JLabel monthLabel = new JLabel();
        JLabel dayLabel = new JLabel();
        JLabel spaceLabel = new JLabel();
        JLabel dot1 = new JLabel();

        public SchDatePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.WHITE);

            schTypeLabel.setFont(GUI.font50);
            schTypeLabel.setText("CLA");
            schTypeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(schTypeLabel);

            spaceLabel.setFont(GUI.font50);
            spaceLabel.setText(" ");
            spaceLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(spaceLabel);

            monthLabel.setFont(GUI.font50);
            monthLabel.setText("01");
            monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(monthLabel);

            dot1.setFont(GUI.font50);
            dot1.setText(".");
            dot1.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(dot1);

            dayLabel.setFont(GUI.font50);
            dayLabel.setText("01");
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(dayLabel);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        Time curTime = (Time) objects[0];
        Time curSchedule = (Time) objects[1];
        int pointer = (int) objects[2];

        String scheduleType;
        JLabel[] labels = new JLabel[5];
        int idx = -1;

        System.out.println("GUI: Schedule Set Panel, pointer(" + pointer + ")");

        curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));

        switch (curSchedule.second) {
            case Info.SCH_TYPE_CLA:
                scheduleType = "CLA";
                break;
            case Info.SCH_TYPE_MET:
                scheduleType = "MEE";
                break;
            case Info.SCH_TYPE_EVE:
                scheduleType = "EVE";
                break;
            case Info.SCH_TYPE_ASL:
                scheduleType = "ASL";
                break;
            case Info.SCH_TYPE_ETC:
                scheduleType = "ETC";
                break;
            default:
                scheduleType = "ERR";
                System.out.println("GUI: Schedule Type Error!");
                break;
        }

        curSchTimePanel.hourLabel.setText(String.format("%02d", curSchedule.hour));
        curSchTimePanel.minuteLabel.setText(String.format("%02d", curSchedule.minute));

        curSchDatePanel.schTypeLabel.setText(scheduleType);
        curSchDatePanel.monthLabel.setText(String.format("%02d", curSchedule.month));
        curSchDatePanel.dayLabel.setText(String.format("%02d", curSchedule.day));

        switch (pointer) {
            case Info.TIME_POINTER_NULL: // 0
                System.out.println("GUI: Pointer Error! (Null)");
                break;
            case Info.TIME_POINTER_HOUR: // 1
                idx = 0;
                break;
            case Info.TIME_POINTER_MINUTE: // 2
                idx = 1;
                break;
            case Info.TIME_POINTER_SCHETYPE: // 3
                idx = 2;
                break;
            case Info.TIME_POINTER_MONTH: // 5
                idx = 3;
                break;
            case Info.TIME_POINTER_DAY: // 6
                idx = 4;
                break;
            default:
                System.out.println("GUI: Pointer Error! (Default)");
                break;
        }

        labels[0] = curSchTimePanel.hourLabel;
        labels[1] = curSchTimePanel.minuteLabel;
        labels[2] = curSchDatePanel.schTypeLabel;
        labels[3] = curSchDatePanel.monthLabel;
        labels[4] = curSchDatePanel.dayLabel;

        // check pointer
        for (int i = 0; i < 5; i++) {
            if (i == idx) {
                GUI.underline(labels[i]);
            } else {
                if ((i == 0) || (i == 1)) {
                    GUI.deleteUnderline(labels[i], GUI.font75);
                } else {
                    GUI.deleteUnderline(labels[i], GUI.font50);
                }
            }
        }

        menuPanel.setDisplay(5, enableMode);
    }
}
