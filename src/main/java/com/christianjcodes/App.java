package com.christianjcodes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;

public class App implements Runnable, ActionListener {

    JFrame frame = new JFrame();
    JTextArea outputArea = new JTextArea();
    String userWord;
    JButton submit;
    JTextField input;

    // Checks if a letter is included in the provided word/array
    public static boolean containsLetter(char a, char[] arrB) {

        for (int i = 0; i < arrB.length; i++) {
            if (a == arrB[i]) {
                return true;
            }
        }
        return false;
    }

    // Determines the number of times a character is used in a word
    public static int charUsage(char a, char[] arrB) {

        int count = 0;

        for (int i = 0; i < arrB.length; i++) {
            if (arrB[i] == a) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void run() {

        ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("anagram-2.png"));

        Image image2 = image.getImage();
        Image newImg = image2.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
        image = new ImageIcon(newImg);

        JLabel description = new JLabel();
        description.setText("Let's make some anagrams");
        description.setIcon(image);
        description.setHorizontalTextPosition(JLabel.CENTER);
        description.setVerticalTextPosition(JLabel.TOP);
        description.setHorizontalAlignment(JLabel.CENTER);
        description.setVerticalAlignment(JLabel.TOP);
        description.setFont(new Font("Z003", Font.PLAIN, 30));

        input = new JTextField();
        input.setPreferredSize(new Dimension(250, 40));
        input.setFont(new Font("Impact", Font.PLAIN, 35));

        submit = new JButton("Submit");
        submit.addActionListener(this);
        submit.setBounds(320, 300, 100, 50);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setBounds(0, 0, 640, 212);
        titlePanel.add(description);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.white);
        inputPanel.setBounds(0, 212, 640, 50);
        inputPanel.add(input);
        inputPanel.add(submit);

        JPanel outputPanel = new JPanel();
        outputPanel.setBackground(Color.white);
        outputPanel.setBounds(0, 262, 640, 378);
        outputPanel.add(outputArea);
        outputArea.setFont(new Font("Impact", Font.PLAIN, 20));

        frame.setTitle("Anagram Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 640);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.add(titlePanel, BorderLayout.CENTER);
        frame.add(outputPanel, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {

            InputStream stream = App.class.getClassLoader().getResourceAsStream("enable1.txt");

            HashSet<String> wordList = new HashSet<>();
            Scanner file = new Scanner(stream);

            while (file.hasNext()) {
                wordList.add(file.next());
            }

            userWord = input.getText();

            String inputWord = userWord.toLowerCase();

            // Create an array and add the characters from the input word to it
            char[] usableChars = new char[inputWord.length()];

            for (int i = 0; i < inputWord.length(); i++) {
                char letter = inputWord.charAt(i);
                usableChars[i] = letter;
            }

            // Create an empty HashSet to contain the found anagram words
            HashSet<String> narrowedList = new HashSet<>();

            // Check each word in wordList against the input word to find anagrams
            for (String a : wordList) {

                boolean wrongLetter = false;

                // Check for any letters that aren't in inputWord
                for (int i = 0; i < a.length(); i++) {
                    char letter = a.charAt(i);
                    if (!containsLetter(letter, usableChars)) { // If containsLetter returns false, we have a wrong letter
                        wrongLetter = true;
                    }
                }

                /* If the length is the same and there are no wrong letters, create an array that
                stores the characters of the current word
                */
                if (!wrongLetter && a.length() == usableChars.length) {
                    char[] aChars = new char[a.length()];

                    for (int i = 0; i < a.length(); i++) {
                        char letter = a.charAt(i);
                        aChars[i] = letter;
                    }

                    int match = 0;

                    /* Compare the input word to the current word to check firstly if each word uses each letter
                    the same number of times (accounting for multiple letter usage), and then increment a variable
                    that will be used to determine if the character count is the same with multiple letters.
                    */
                    for (int i = 0; i < usableChars.length; i++) {
                        for (int j = 0; j < aChars.length; j++) {
                            if (charUsage(usableChars[i], usableChars) == charUsage(aChars[j], aChars)) {
                                if (usableChars[i] == aChars[j]) {
                                    match++;
                                    break;
                                }
                            }
                        }
                    }

                    // Add the word to the final list if matches, and if it's not the original input word
                    if (match == inputWord.length() && !a.equals(inputWord)) {
                        narrowedList.add(a);
                    }
                }
            }

            String narrowedString = String.join(", ", narrowedList);

            try {
                int i = Integer.parseInt(userWord);
                outputArea.setText("Words, not numbers!");
            } catch (NumberFormatException exception) {
                if (inputWord.isEmpty()) {
                    outputArea.setText("Enter a word!");
                } else if (narrowedList.isEmpty()){
                    outputArea.setText("No anagrams :(");
                } else {
                    outputArea.setText(narrowedString);
                }
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new App());

    }
}
