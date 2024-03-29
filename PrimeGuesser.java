import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class PrimeGuesser {
    static Scanner scnr = new Scanner(System.in);

    public static int getInput() {
        return scnr.nextInt();
    }

    private static ArrayList<Integer> GeneratePrimes(int limit) {
        int iterate = 1;
        ArrayList<Integer> primeList = new ArrayList<>();
        while (iterate <= limit) {
            int divisible = 0;
            int subCounter = 1;
            while (subCounter <= iterate) {
                if (iterate % subCounter == 0) {
                    divisible += 1;
                }
                subCounter++;
            }
            if (divisible < 3) {
                primeList.add(iterate);
            }
            iterate++;
        }
        return primeList;
    }

    public static void main(String[] args) {
        System.out.println("Guess That Prime!\n");
        System.out.println("The target of the game is to guess a prime number based on a limit that you set.\nIt's kinda like \"Higher or Lower\", but using prime numbers to make it more interesting.");
        System.out.println("\nHere are the rules:\n1. You can guess an infinite amount of times!\n2. However, your amount of lives (by default, 3) will be reduced each time you guess a Non-Prime number.\n3. Same goes for if you guess outside of your range. I don't know why you would, but that's also a loss condition.");
        System.out.println("\nTo start, why don't you tell me what you want the limit to be?");
        int limit;
        while (true) {
            System.out.print("Your answer: ");
            try {
                limit = getInput();
                break;
            } catch (Exception e) {
                System.out.println("Seems like you either didn't input a number, you typed in some decimal points. Either way, that's not gonna work.");
                System.out.println("Let's try this again. What limit would you like to use?");
            }
        }
        Random rand = new Random();
        ArrayList<Integer> primeList = GeneratePrimes(limit);
        int answer = primeList.get(rand.nextInt(primeList.size()));
        System.out.println("-----------------\nFantastic! Let's start the game. Start by telling me a number, and I'll tell you if it's higher or lower.");
        byte life = 3;
        while (life > 0) {
            byte winCondition = gameTime(answer, primeList, life);
            if (winCondition == 2) {
                life -= 1;
            }
        }
    }

    public static byte gameTime(int answer, ArrayList<Integer> primeList, byte life) {
        byte winCondition = 0;
        while (true) {
            int userInput = getInput();
            try {
                if (!primeList.contains(userInput)) {
                    life -= 1;
                    System.out.println("That's one life gone. You now have " + life + " left.");
                    winCondition = 2;
                }
                if (life == 0) {
                    System.out.println("You lost. Sorry mate.");
                    break;
                } else {
                    if (userInput < answer) {
                        if (answer - userInput < 10) {
                            System.out.println("You're very close! Higher!");
                            break;
                        }
                        System.out.println("Higher.");
                        break;
                    } else if (userInput > answer) {
                        if (Math.abs(answer - userInput) < 10) {
                            System.out.println("You're very close! Lower!");
                            break;
                        }
                        System.out.println("Lower.");
                        break;
                    } else {
                        winCondition = 1;
                        System.out.println("Correct! You win. I don't know what you won, but you did. Congratulations!");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("You made a typo, probably. Try that again, please?");
            }
        }
        return winCondition;
    }
}