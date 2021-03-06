package view;

import model.Alarm;
import model.Time;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AlarmListPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    ListPanel listPanel;
    MenuPanel menuPanel = new MenuPanel();
    JLabel curTimeLabel = new JLabel();
    JLabel alarm1Label = new JLabel();
    JLabel alarm2Label = new JLabel();
    JLabel alarm3Label = new JLabel();
    JLabel alarm4Label = new JLabel();
    JLabel[] alarmLabels = {alarm1Label, alarm2Label, alarm3Label, alarm4Label};

    public AlarmListPanel() {
        listPanel = new ListPanel();

        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(GUI.font40);
        curTimeLabel.setText("10:12:40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, listPanel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(listPanel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }

    class ListPanel extends JPanel {
        public ListPanel() {
            this.setLayout(new GridLayout(4, 1));
            this.setBackground(Color.WHITE);

            alarmLabels[0].setFont(GUI.font30);
            alarmLabels[0].setText("[OFF] 13:30:20");
            alarmLabels[0].setOpaque(true);
            alarmLabels[0].setBackground(Color.LIGHT_GRAY);
            alarmLabels[0].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarmLabels[0]);

            alarmLabels[1].setFont(GUI.font30);
            alarmLabels[1].setText("[OFF] 13:20:42");
            alarmLabels[1].setOpaque(true);
            alarmLabels[1].setBackground(Color.WHITE);
            alarmLabels[1].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarmLabels[1]);

            alarmLabels[2].setFont(GUI.font30);
            alarmLabels[2].setText("");
            alarmLabels[2].setOpaque(true);
            alarmLabels[2].setBackground(Color.WHITE);
            alarmLabels[2].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarmLabels[2]);

            alarmLabels[3].setFont(GUI.font30);
            alarmLabels[3].setText("");
            alarmLabels[3].setOpaque(true);
            alarmLabels[3].setBackground(Color.WHITE);
            alarmLabels[3].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarmLabels[3]);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        Time curTime = (Time) objects[0];
        ArrayList<Alarm> alarmList = null;
        if (objects[1] != null) {
            alarmList = (ArrayList<Alarm>) objects[1];
        }

        int pointer = (int) objects[2];
        int count = 0;

        String alarmEnable;

        curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));

        if (alarmList != null) {
            for (int i = 0; i < alarmList.size(); i++) {
                Alarm alarm = alarmList.get(i);

                if (alarm.enable) {
                    alarmEnable = "[ON]";
                } else {
                    alarmEnable = "[OFF]";
                }
                alarmLabels[i].setText(alarmEnable + " " + String.format("%02d", alarm.alarmTime.hour) + ":" + String.format("%02d", alarm.alarmTime.minute) + ":" + String.format("%02d", alarm.alarmTime.second));
                count++;
            }

        }

        for (int i = 0; i < 4 - count; i++) {
            alarmLabels[count + i].setText("");
        }


        // check pointer
        System.out.println("GUI: pointer is " + pointer);
        for (int i = 0; i < 4; i++) {
            if (i == pointer) {
                alarmLabels[i].setBackground(Color.LIGHT_GRAY);
            } else {
                alarmLabels[i].setBackground(Color.WHITE);
            }
        }

        menuPanel.setDisplay(3, enableMode);
    }
}