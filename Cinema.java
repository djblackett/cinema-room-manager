package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
//
//        System.out.println("Cinema:");
//    System.out.println("  1 2 3 4 5 6 7 8");
//    for (int i = 1; i < 8; i++) {
//        System.out.println(i + " S S S S S S S S");
//    }

        //gather user input
        System.out.println("Enter the number of rows:");
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = sc.nextInt();


        // create matrix for cinema seats
        char[][] grid = new char[rows + 1][seatsInRow + 1];
        grid[0][0] = ' ';

        // label number of seats
        for (int i = 1; i <= seatsInRow; i++) {
            grid[0][i] = String.valueOf(i).charAt(0);

        }

        // label rows
        for (int i = 1; i <= rows; i++) {
            grid[i][0] = String.valueOf(i).charAt(0);
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seatsInRow; j++) {
                grid[i][j] = 'S';
            }
        }

        int totalSeats = rows * seatsInRow;
        int currentIncome = 0;

        //select option

        while (true) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit\n");
            int selection = sc.nextInt();

            if (selection == 0) {
                System.exit(0);
            } else if (selection == 1) {
                printSeats(rows, seatsInRow, grid);
            } else if (selection == 2) {
                int selectedRow;
                int selectedSeat;
                while (true) {
                    System.out.println("Enter a row number:");
                    selectedRow = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    selectedSeat = sc.nextInt();

                    if (selectedRow < 1 || selectedRow > rows || selectedSeat < 1 || selectedSeat > seatsInRow) {
                        System.out.println("Wrong input!");
                        continue;
                    }
                    else if (grid[selectedRow][selectedSeat] == 'B') {
                        System.out.println("That ticket has already been purchased");
                        continue;
                    }
                    grid[selectedRow][selectedSeat] = 'B';
                    break;
                }

                int ticketPriceA;

                if (totalSeats <= 60) {
                    ticketPriceA = 10;
                    System.out.println("Ticket price : $" + ticketPriceA + "\n");
                    //System.out.println("Total income:\n$" + totalSeats*ticketPriceA);
                }

                if (totalSeats > 60) {
                    int firstRows = rows / 2;
                    int lastRows = rows - firstRows;
                    int frontRowsProfits = firstRows * 10 * seatsInRow;
                    int backRowsProfits = lastRows * 8 * seatsInRow;

                    if (selectedRow > firstRows) {
                        ticketPriceA = 8;
                    } else {
                        ticketPriceA = 10;
                    }

                    int totalIncome = frontRowsProfits + backRowsProfits;
                    //System.out.println("Total income:\n$" + totalIncome);
                    System.out.println("Ticket price : $" + ticketPriceA + "\n");
                    currentIncome += ticketPriceA;
                }
            }
        else if (selection == 3) {
            int ticketsSold = countSoldTickets(grid);
                int firstRows = rows / 2;
                int lastRows = rows - firstRows;
                int frontRowsProfits = firstRows * 10 * seatsInRow;
                int backRowsProfits = lastRows * 8 * seatsInRow;
                int totalIncome = frontRowsProfits + backRowsProfits;
            System.out.println("Number of purchased tickets: " + ticketsSold);
            double percentage = ((1.0*ticketsSold) / totalSeats) *100;
            System.out.printf("Percentage: %.2f", percentage);
            System.out.println("%");
            System.out.println("Current income: $" + currentIncome);
            System.out.println("Total income : $" + totalIncome);

            }
        }
    }

    public static void printSeats(int rows, int seatsInRow, char[][] grid) {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seatsInRow; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    public static int countSoldTickets(char[][] grid) {
        int count = 0;
        for (char[] row :grid) {
            for (char c: row) {
                if (c == 'B') {
                    count++;
                }
            }
        }
        return count;
    }
}