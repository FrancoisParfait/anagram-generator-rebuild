package com.christianjcodes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App implements Runnable, ActionListener {

    JFrame frame = new JFrame();
    JTextArea outputArea = new JTextArea();
    String userWord;
    JButton submit;
    JTextField input;

    @Override
    public void run() {

        JLabel description = new JLabel();
        description.setText("Let's make some anagrams");
        description.setIcon(image);
        description.setHorizontalTextPosition(JLabel.CENTER);
        description.setVerticalTextPosition(JLabel.TOP);
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setVerticalAlignment(JLabel.TOP);
        description.setFont(new Font("Z003", Font.PLAIN, 30));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(0, 0, 640, 212);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.setBounds(0, 212, 640, 50);

        JPanel outputPanel = new JPanel();
        outputPanel.setBackground(Color.white);
        outputPanel.setBounds(0, 262, 640, 378);

        frame.setTitle("Anagram Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 640);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        inputPanel.add(input);
        inputPanel.add(submit);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.CENTER);
        outputPanel.add(outputArea);

        outputArea.setFont(new Font("Impact", Font.PLAIN, 20));

        titlePanel.add(description);

        input = new JTextField();
        input.setPreferredSize(new Dimension(250, 40));
        input.setFont(new Font("Impact", Font.PLAIN, 35));

        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(320, 300, 100, 50);

    }

    @Override
    public void actionPerformed(ActionEvent e) {



    }
}
