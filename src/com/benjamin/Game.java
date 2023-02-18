package com.benjamin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class Game implements ActionListener {
    JFrame frame = new JFrame("Rock Paper Scissors");
    Timer timer = new Timer(1000, this);
    int x_axis = 950;
    int y_axis = 500;
    ImageIcon rightHand = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/RH.gif")));
    ImageIcon leftHand = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/LH.gif")));
    ImageIcon rightRock = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/rightrock.png")));
    ImageIcon leftRock = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/leftrock.png")));
    ImageIcon rightPaper = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/rightpaper.png")));
    ImageIcon leftPaper = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/leftpaper.png")));
    ImageIcon rightScissors = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/rightscissors.png")));
    ImageIcon leftScissors = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/leftscissors.png")));
    ImageIcon rockIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/buttons/rockB.png")));
    ImageIcon paperIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/buttons/paperB.png")));
    ImageIcon scissorsIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/buttons/scissorsB.png")));
    JPanel head = new JPanel();
    JLabel computer = new JLabel();
    JLabel player = new JLabel();
    JPanel panel = new JPanel();
    JPanel menu = new JPanel();
    JLabel rightHandLabel = new JLabel(rightHand);
    JLabel leftHandLabel = new JLabel(leftHand);
    JRadioButton rock = new JRadioButton(rockIcon);
    JRadioButton paper = new JRadioButton(paperIcon);
    JRadioButton scissors = new JRadioButton(scissorsIcon);
    int computerScore = 0;
    int playerScore = 0;
    String playerName = "";

    Game() {

        head.setBackground(new Color(239, 232, 232, 224));
        head.setBounds(0, 0, x_axis, 80);
        head.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 81, x_axis, 280);
        menu.setBackground(new Color(255, 1, 1));
        menu.setBounds(0, 361, x_axis, 100);
        menu.setLayout(null);

        computer.setBounds((x_axis / 2 - 310), 20, 200, 50);
        computer.setFont(new Font(null, Font.BOLD, 35));
        computer.setText("COMPUTER");
        player.setBounds((x_axis / 2 + 100), 20, 200, 50);
        player.setFont(new Font(null, Font.BOLD, 35));
        try {
            while (playerName.equals(""))
                playerName = JOptionPane.showInputDialog("Enter your player name");
            player.setText(playerName.toUpperCase(Locale.ROOT));
        } catch (NullPointerException e) {
            player.setText("PLAYER");
        }
        head.add(computer);
        head.add(player);

        leftHandLabel.setText(Integer.toString(computerScore));
        leftHandLabel.setHorizontalTextPosition(JLabel.LEFT);
        leftHandLabel.setIconTextGap(30);
        leftHandLabel.setFont(new Font(null, Font.BOLD, 100));
        leftHandLabel.setForeground(Color.RED);

        rightHandLabel.setText(Integer.toString(playerScore));
        rightHandLabel.setIconTextGap(30);
        rightHandLabel.setFont(new Font(null, Font.BOLD, 100));
        rightHandLabel.setForeground(Color.RED);

        int diff = 120;
        rock.addActionListener(this);
        rock.setBounds(((x_axis / 2 - 32) - diff), 20, 64, 64);
        paper.addActionListener(this);
        paper.setBounds((x_axis / 2 - 32), 20, 64, 64);
        scissors.addActionListener(this);
        scissors.setBounds(((x_axis / 2 - 32) + diff), 20, 64, 64);

        ButtonGroup group = new ButtonGroup();
        group.add(rock);
        group.add(paper);
        group.add(scissors);

        rock.setMnemonic(KeyEvent.VK_R);
        rock.setBackground(null);
        paper.setMnemonic(KeyEvent.VK_P);
        paper.setBackground(null);
        scissors.setMnemonic(KeyEvent.VK_S);
        scissors.setBackground(null);

        menu.add(rock);
        menu.add(paper);
        menu.add(scissors);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(x_axis, y_axis);
        frame.setLayout(null);
        ImageIcon logo = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("Images/logo.png")));
        frame.setIconImage(logo.getImage());
        frame.setResizable(false);
        frame.setVisible(true);

        panel.add(leftHandLabel, BorderLayout.WEST);
        panel.add(rightHandLabel, BorderLayout.EAST);
        frame.add(head);
        frame.add(panel);
        frame.add(menu);
    }

    void score(String winner) {
        if (winner.equals("player")) {
            playerScore += 1;
            if (playerScore > 9)
                rightHandLabel.setIconTextGap(10);
            rightHandLabel.setText(Integer.toString(playerScore));
        } else if (winner.equals("computer")) {
            computerScore += 1;
            if (computerScore > 9)
                leftHandLabel.setIconTextGap(10);
            leftHandLabel.setText(Integer.toString(computerScore));
        }
    }

    String computer() {
        Random rand = new Random();
        int choice = rand.nextInt(3) + 1;

        switch (choice) {
            case 1 -> {
                leftHandLabel.setIcon(leftRock);
                return "rock";
            }
            case 2 -> {
                leftHandLabel.setIcon(leftPaper);
                return "paper";
            }
            case 3 -> {
                leftHandLabel.setIcon(leftScissors);
                return "scissors";
            }
        }
        return null;
    }
    void disable(){
        rock.setEnabled(false);
        rock.setDisabledIcon(rockIcon);
        paper.setEnabled(false);
        paper.setDisabledIcon(paperIcon);
        scissors.setEnabled(false);
        scissors.setDisabledIcon(scissorsIcon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rock) {
            timer.start();
            disable();
            rightHandLabel.setIcon(rightRock);
            String computer = computer();
            switch (computer) {
                case "paper" -> score("computer");
                case "scissors" -> score("player");
            }
        }
        if (e.getSource() == paper) {
            timer.start();
            disable();
            rightHandLabel.setIcon(rightPaper);
            String computer = computer();
            switch (computer) {
                case "scissors" -> score("computer");
                case "rock" -> score("player");
            }
        }
        if (e.getSource() == scissors) {
            timer.start();
            disable();
            rightHandLabel.setIcon(rightScissors);
            String computer = computer();
            switch (computer) {
                case "rock" -> score("computer");
                case "paper" -> score("player");
            }
        }
        if (e.getSource() == timer) {
            rightHandLabel.setIcon(rightHand);
            leftHandLabel.setIcon(leftHand);
            timer.stop();
            rock.setEnabled(true);
            paper.setEnabled(true);
            scissors.setEnabled(true);
        }
    }
}
