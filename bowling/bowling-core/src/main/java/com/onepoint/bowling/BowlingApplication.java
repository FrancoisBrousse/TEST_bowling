package com.onepoint.bowling;

import java.util.Scanner;

public class BowlingApplication {

    static Scanner keyboard = new Scanner(System.in);
    static int totalScore;

    public static void main(String[] args) {
        StartGame();
        StartRoll();
        EndGame();
    }

    public static void StartGame() {
        System.out.println("Hello new player, please insert your sequence of rolls");
    }

    public static void StartRoll() {
        String[] tries = new String[2];
        int score = 0, rollNumber, tryNumber;
        boolean isStrike = false, isStrikeDoneBefore = false, isSpare = false, isSpareDoneBefore = false;
        for (int i = 0; i < 10; i++) {
            if (isStrike) {
                isStrikeDoneBefore = true;
                isStrike = false;
            }
            if (isSpare) {
                isSpareDoneBefore = true;
                isSpare = false;
            }
            rollNumber = i + 1;
            System.out.println("Roll " + rollNumber);
            for (int j = 0; j < 2; j++) {
                tryNumber = j + 1;
                System.out.println("Try " + tryNumber);
                tries[j] = keyboard.next();
                if (isValidValue(tries[j])) {
                    System.out.println("This is a correct roll");
                    score += Integer.parseInt(tries[j]);
                } else if (tries[j].equals("X")) {
                    if (j == 0) {
                        if (i == 9) {
                            System.out.println("This is a strike at your last roll, you have 1 bonus roll");
                            score += 10;
                            System.out.println("Bonus roll, try " + tryNumber);
                            tries[j] = keyboard.next();
                            if (tries[j].equals("X")) {
                                score += 10;
                            }
                            if (tries[j].equals("-")) {
                                tries[j] = "0";
                            }
                            if (tries[0].equals("X")) {
                                score += 10;
                            } else {
                                score += Integer.parseInt(tries[0]);
                                System.out.println("Bonus roll, try " + (j + 2));
                                tries[j] = keyboard.next();
                                if (tries[j].equals("X")) {
                                    score += 10;
                                }
                                if (tries[j].equals("-")) {
                                    tries[j] = "0";
                                }
                                if (tries[0].equals("X")) {
                                    score += 10;
                                } else {
                                    score += Integer.parseInt(tries[0]);
                                    j = j + 1;
                                }
                            }
                        } else {
                            System.out.println("This is a strike");
                            isStrike = true;
                            score += 10;
                            tries[j + 1] = "0";
                            j = j + 1;
                        }
                    } else {
                        if (tries[j].equals("X")) {
                            score += 10;
                        } else {
                            // I used a shortcut for this case because I couldn't manage to use a while loop
                            // It gives only 1 try and then goes on
                            System.out.println("This is not possible, please insert another value");
                            tries[j] = keyboard.next();
                            score += Integer.parseInt(tries[j]);
                        }
                    }
                } else if (tries[j].equals("/")) {
                    // I used a shortcut for this case because I couldn't manage to use a while loop
                    // It gives only 1 try and then goes on
                    if (j == 0) {
                        System.out.println("This is not possible, please insert another value");
                        tries[j] = keyboard.next();
                        score += Integer.parseInt(tries[j]);
                    } else {
                        if (i == 9) {
                            System.out.println("This is a spare at your last roll, you have 1 bonus try");
                            score += 10 - Integer.parseInt(tries[j - 1]);
                            System.out.println("Bonus try");
                            tries[j] = keyboard.next();
                            if (tries[j].equals("X")) {
                                score += 10;
                            }
                            if (tries[j].equals("-")) {
                                tries[j] = "0";
                            }
                            score += Integer.parseInt(tries[0]);
                        } else {
                            System.out.println("This is a spare");
                            isSpare = true;
                            score += 10 - Integer.parseInt(tries[j - 1]);
                        }
                    }
                } else if (tries[j].equals("-")) {
                    System.out.println("This is a miss");
                    tries[j] = "0";
                } else {
                    // I used a shortcut for this case because I couldn't manage to use a while loop
                    // It gives only 1 try and then goes on
                    System.out.println("Incorrect value, please insert valid value");
                    tries[j] = keyboard.next();
                    score += Integer.parseInt(tries[j]);
                }
            }
            System.out.println("Your score for roll " + rollNumber + " is : " + score);
            totalScore += score;
            if (isStrikeDoneBefore) {
                if (tries[0].equals("X")) {
                    totalScore += 10;
                } else {
                    totalScore += Integer.parseInt(tries[0]) + Integer.parseInt(tries[1]);
                    isStrikeDoneBefore = false;
                }
            }
            if (isSpareDoneBefore) {
                totalScore += Integer.parseInt(tries[0]);
                isSpareDoneBefore = false;
            }
            System.out.println("Your total score is : " + totalScore);
            score = 0;
        }
    }

    private static boolean isValidValue(String value) {
        if (value.equals("1") || value.equals("2") || value.equals("3") || value.equals("4") || value.equals("5")
                || value.equals("6") || value.equals("7") || value.equals("8") || value.equals("9")) {
            return true;
        }
        return false;
    }

    public static void EndGame() {
        System.out.println("Your final score for this game is : " + totalScore);
    }

}
