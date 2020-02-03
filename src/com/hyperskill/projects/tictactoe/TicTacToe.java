package com.hyperskill.projects.tictactoe;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    static final String delimiter = "---------";
    static final Scanner sc = new Scanner(System.in);
    static final int FIELD_SIZE = 9;
    static final char[] field = new char[FIELD_SIZE];

    public static void main(String[] args) {
        play();
    }

    public static void play() {
        char[][] matrix = new char[3][3];
        boolean hasXWon = false;
        boolean hasOWon = false;
        boolean isXTurn = true;
        int x, y;

        Arrays.fill(field, ' ');
        for (int i = 0; i < 3; i++) {
            matrix[i] = Arrays.copyOfRange(field, i * 3, i * 3 + 3);
        }

        printField(matrix);

        while (getEmptyCells(matrix) != 0 && !hasOWon && !hasXWon) {
            System.out.print("\nEnter the coordinates: ");
            String[] line = sc.nextLine().split(" ");
            try {
                y = Integer.parseInt(line[0]) - 1;
                x = 3 - Integer.parseInt(line[1]);
                if (x > 2 || x < 0 || y > 2 || y < 0) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (matrix[x][y] == ' ') {
                    matrix[x][y] = isXTurn ? 'X' : 'O';
                    printField(matrix);
                    isXTurn = !isXTurn;
                    hasOWon = checkIfWon(matrix, 'O');
                    hasXWon = checkIfWon(matrix, 'X');
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
            }
        }

        System.out.println(getEnding(hasXWon, hasOWon));
    }

    public static int getEmptyCells(char[][] array) {
        int countEmpty = 0;
        for (char[] row : array) {
            for (char cell : row) {
                countEmpty += cell == ' ' ? 1 : 0;
            }
        }
        return countEmpty;
    }

    public static void printField(char[][] array) {
        System.out.print(delimiter);
        for (char[] row : array) {
            System.out.print("\n| ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.print("|");
        }
        System.out.print("\n" + delimiter);
    }

    public static boolean checkIfWon(char[][] arr, char player) {
        int countInRow = 0;
        int countInCol = 0;
        int countDiag = 0;
        int count = 0;
        boolean hasWon = false;

        for (int i = 0, reverseDiag = 0; i < arr.length; i++) {
            char reverseDiagCell = arr[i][arr.length - i - 1];
            reverseDiag += reverseDiagCell == player ? 1 : 0;
            for (int j = 0; j < arr[i].length; j++) {
                char currentCell = arr[i][j];
                count += currentCell == player ? 1 : 0;
                countInRow += currentCell == player ? 1 : 0;
                countInCol += arr[j][i] == player ? 1 : 0;
            }
            countDiag += arr[i][i] == player ? 1 : 0;
            hasWon = hasWon ? hasWon : countInRow == 3 || countInCol == 3 || countDiag == 3 || reverseDiag == 3;
            countInRow = countInCol = 0;
        }

        return hasWon;
    }

    public static String getEnding(boolean hasXWon, boolean hasOWon) {
        if (hasXWon && !hasOWon) {
            return "\nX wins";
        } else if (!hasXWon && hasOWon) {
            return "\nO wins";
        } else {
            return "\nDraw";
        }
    }
}
