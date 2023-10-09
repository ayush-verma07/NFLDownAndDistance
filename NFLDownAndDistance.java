//Created by Ayush Verma

import java.util.Scanner;

public class NFLDownAndDistance {
    public static void main(String[] args) {

        //Print the game introduction text
        printIntro();

        // Initialize game state
        int down = 1;
        int distance = 10;
        int yardLine = 20;

        //Boolean to check the game is still playing
        boolean gamePlaying = true;

        Scanner scanner = new Scanner(System.in);

        while (gamePlaying) {
            // Display current game state
            String yardStr = "";

            //Prints the yard line as either negative (team's side of field) or positive (opponent's side of field)
            if(yardLine < 50) {yardStr = "-" + yardLine;}
            else if (yardLine >= 50) {yardStr = "" + (100 - yardLine);}

            //Prints team's current position on field
            System.out.println("Down: " + down + ", Distance: " + distance + ", Yard Line: " + yardStr);

            // Get user input for the play
            System.out.print("Enter play (1: run, 2: pass, 3: punt, 4: kick, 5: quit): ");
            int playChoice = scanner.nextInt();
            int yardsGained = 0;

            //Play logic
            switch (playChoice) {
                case 1:
                case 2:
                    System.out.print("Enter yards gained: ");
                    yardsGained = scanner.nextInt();
                    yardLine += yardsGained;
                    down++;

                    //Subtract yardsGained from distance if it's less than 10
                    if (yardsGained < 10) {
                        distance -= yardsGained;
                    } else {
                        distance = 0;
                    }

                    //Check if the yards gained is greater than or equal to the remaining distance for a first down
                    if (distance <= 0) {
                        System.out.println("First down!");
                        distance = 10;
                        down = 1;
                    }
                    break;
                case 3:
                    System.out.print("Enter how far the ball travelled on the punt (yards): ");
                    int puntYards = scanner.nextInt();
                    yardLine += puntYards;
                    
                    //If the recieving team catch the ball inside their 25 yard line, they are eligible to call for a fair catch
                    if(yardLine >= 75) {
                        System.out.print("Did the recieving team call a fair catch? (1: Yes, 2: No): ");
                        int fairCatchChoice = scanner.nextInt();
                        if (fairCatchChoice == 1) {
                            yardLine = 75; //Place the ball on the opponent's 25-yard line after a fair catch
                        } else {
                            System.out.print("Enter return yards from the punt: ");
                            int returnYards = scanner.nextInt();
                            yardLine -= returnYards;
                        }
                        //The recieving team is outside of their 25 yard line and must return the punt
                    } else {
                        System.out.print("Enter return yards from the punt: ");
                        int returnYards = scanner.nextInt();
                        yardLine -= returnYards;
                    }
                
                    down = 1;
                    distance = 10;


                    //Prints the opponent's position on field
                    String oppYardStr = "";

                    if(yardLine < 50) {oppYardStr = "" + yardLine;}
                    else if (yardLine >= 50) {oppYardStr = "-" + (100 - yardLine);}
                    System.out.println("Opponent's Down: " + down + ", Distance: " + distance + ", Yard Line: " + oppYardStr);

                    //Check if the opponent scored a touchown on the punt return
                    if (yardLine >= 100) {
                        System.out.println("Touchdown for the opponent! You lost!");
                        return;
                    }
                    System.out.println("Game Over! Thanks for playing!");
                    gamePlaying = false;
                    break;
                    
                case 4:
                    //Check if the player is within kicking range and attempt a field goal
                    System.out.println("Field goal attempt from " + (110 - yardLine) + " yards...");
                    if (yardLine >= 70) {
                        System.out.println("Kick is good! You scored 3 points!");
                    } else if (yardLine >= 55) {
                        System.out.println("Woah! Barely made it. You scored 3 points!");
                    } else {
                        System.out.println("The kick falls short. No points scored.");
                    }
                    System.out.println("Game Over! Thanks for playing!");
                    gamePlaying = false;
                    break;
                case 5:
                    //User manually quit the game
                    System.out.println("Quitting the game...");
                    System.out.println("Thanks for playing!");
                    gamePlaying = false;                
                default:
                    //User entered a integer not between 1 and 5
                    System.out.println("Invalid choice. Try again.");
                    continue;
            }

            //Check game conditions and end game if any condition is true
            if (yardLine >= 100) {
                System.out.println("Touchdown! You scored!");
                gamePlaying = false;
            } else if (yardLine < 0) {
                System.out.println("Safety!");
                gamePlaying = false;
            } else if (down > 4) {
                System.out.println("Turnover on downs!");
                gamePlaying = false;
            }
        }
        scanner.close();
    }


    public static void printIntro() {
        //Prints all intro text for the game.
        System.out.println("Welcome to the NFL Down and Distance Simulator!");
        System.out.println("In this game, you are the coach (or super obsessed fan) of a football team.");
        System.out.println("Your objective is to simulate a football match in order to prepare different scenarios for the game next week.");
        System.out.println("You are allowed to control how far your team throws, runs, punts, or kicks the ball. You can also control how far the opponents can run the ball back after a punt.");
        System.out.println("After every play, your team's position (down, distance from 1st down, and yard line) on the field will be shown.");
        System.out.println("If the opponent recieves the ball, their position on the field will be displayed instead.");
        System.out.println("The game will begin with your team with possession on your own 20-yard line.");
        System.out.println("Yard lines displayed as a negative value means you are on your own side of the field. Positive values mean your team is in oppponent territory.");
        System.out.println("There are no interceptions or fumbles in this simulator. You can quit the game manually after any play by choosing 5 from the play choices.");
        System.out.println("Let the game begin!");
        System.out.println("---------------------------------------------------");
        System.out.println("");
    }
}
