package view;

import controller.Controller;
import model.GameType;
import model.Gesture;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIView implements  View{

    private final Model model ;
    private final Controller controller ;
    private JComboBox<Gesture> firstPlayerChoice;
    private JComboBox<GameType> gameType;
    private JButton startButton;
    private JButton playButton;
    private JTextArea gameResult;
    private JButton newGameButton;

    public GUIView(Model model, Controller controller) {
        this.model = model;
        this.controller = controller;
        this.controller.setView(this);
    }


    @Override
    public void createView() {
        JPanel panel = new JPanel(new FlowLayout());
        JFrame frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300, 180));
        frame.setResizable(false);

        // add  (hidden) game GUI elements
        GameType[] gameTypes = model.getGameTypes();

        gameType = new JComboBox<>(gameTypes);
        panel.add(gameType);

        startButton = new JButton("start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.startGame((GameType) gameType.getSelectedItem());
            }
        });

        panel.add(startButton);

        Gesture[] choices = model.getPossibleGestures();

        firstPlayerChoice = new JComboBox<Gesture>(choices);
        firstPlayerChoice.setVisible(false);

        panel.add(firstPlayerChoice);

        playButton = new JButton("play");
        playButton.setVisible(false);
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setFirstPlayer((Gesture) firstPlayerChoice.getSelectedItem());
                controller.play();
            }
        });

        panel.add(playButton);

        gameResult = new JTextArea(5,23);
        gameResult.setVisible(false);
        panel.add(gameResult);

        newGameButton = new JButton("new game");
        newGameButton.setVisible(false);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.newGame();
            }
        });

        panel.add(newGameButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);


    }

    @Override
    public void showFirstPlayerChoices() {
        firstPlayerChoice.setVisible(true);

    }

    @Override
    public void hideFirstPlayerChoices() {
        firstPlayerChoice.setVisible(false);

    }

    @Override
    public void showPlayButton() {
        playButton.setVisible(true);

    }

    @Override
    public void hidePlayButton() {
        playButton.setVisible(false);

    }

    @Override
    public void showStartButton() {
        startButton.setVisible(true);

    }

    @Override
    public void hideStartButton() {
        startButton.setVisible(false);

    }

    @Override
    public void showGameType() {
        gameType.setVisible(true);

    }

    @Override
    public void hideGameType() {
        gameType.setVisible(false);

    }

    @Override
    public void showNewGameButton() {
        newGameButton.setVisible(true);

    }

    @Override
    public void hideNewGameButton() {
        newGameButton.setVisible(false);

    }

    @Override
    public void showGameResult(String result) {
        gameResult.setText(result);
        gameResult.setVisible(true);
    }

    @Override
    public void hideGameResult() {
        gameResult.setVisible(false);

    }
}
