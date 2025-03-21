/**
 * Author: Brianna Levon
 * 
 * Purpose: Create a program that generates and shuffles seven letters taken from a word in the file words.txt and prompts the user to only use the letters given as many times as they want to spell words. If the words entered by the user are listed in words.txt the user gets 1 point for a four letter word and one point per letter in word greater than 4 letters long. Additinally the previous word guesses cannot be reused to get more points.
 */
import java.util.*;
import java.io.*;
public class WordGame {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        File fileHandler = new File("words.txt");
        Scanner reader = new Scanner(fileHandler);
        int score = 0;
        ArrayList<String> userGuesses = new ArrayList<>();

        // Calls the method getFileContents and inserts the Scanner reader into the method, which returns a String Array List that is assigned to the String Array List fileContents
        ArrayList<String> fileContent = getFileContents(reader);

        // Generative AI Usage: ChatGPT
        // Calls the method getWordLetters and inserts the String Array Lists fileContent and userGuesses into the method, which returns an Char Array List that is assigned to the Char Array List roundLetters
        ArrayList<Character> roundLetters = getWordLetters(fileContent, userGuesses);

        // Calls the method displayAndShuffleLetters and inserts the Char Array List roundLetters into the method
        displayAndShuffleLetters(roundLetters);

        while(true){
            String userWord = input.nextLine();

            // Checks if the word entered by the user is 'mix', while ignoring case sensitivity; if true, the method displayAndShuddleLetters is called and inserts the Char Array List roundLetters into the method
            if(userWord.equalsIgnoreCase("mix")){
                displayAndShuffleLetters(roundLetters);
            }
            // Checks if the word entered by the user is 'ls', while ignoring case sensitivity; if true a for loop is run which takes each String stored in the String Array List userGuesses and assigns it wor the String variable words, which is printed out during each iteration
            else if(userWord.equalsIgnoreCase("ls")){
                for(String words : userGuesses){
                    System.out.println("     " + words);
                }
            }
            // Checks if the word entered by the user is 'bye', while ignoring case sensitivity; if true the code breaks/exits out of the while loop
            else if(userWord.equalsIgnoreCase("bye")){
                break;
            }
            else{
                // Generative AI Usage: ChatGPT
                // Checks if the word entered by the user if valid, but calling the method isValid and inputing the value of userWord and the Array Lists: roundLetters, userGuesses, and fileContent into the method. If the word is valid the method calcScore is called with userWords, returns a int value that is stored into the current value of score, and adds userWord to userGuesses
                if(isValid(userWord, roundLetters, userGuesses, fileContent)){
                    score += calcScore(userWord);
                    userGuesses.add(userWord);
                }
            }
            // Prints out the user's overall score after each word they enter
            System.out.println("Score: " + score);
        }
        reader.close();
    }

    /**
     * Calculates the user's score after entering a valid word
     * NOTE: I set the max points a user can get to 15 since the longest word in the file is 15 letters long
     * @param userWord represents the word the user entered that should be spelled using the seven letters displayed
     * @return points
     */
    public static int calcScore(String userWord){
        int points = 0;
        if(userWord.length() == 4){
            points = 1;
        }
        else if(userWord.length() == 5){
            points = 5;
        }
        else if(userWord.length() == 6){
            points = 6;
        }
        else if(userWord.length() == 7){
            points = 7;
        }
        else if(userWord.length() == 8){
            points = 8;
        }
        else if(userWord.length() == 9){
            points = 9;
        }
        else if(userWord.length() == 10){
            points = 10;
        }
        else if(userWord.length() == 11){
            points = 11;
        }
        else if(userWord.length() == 12){
            points = 12;
        }
        else if(userWord.length() == 13){
            points = 13;
        }
        else if(userWord.length() == 14){
            points = 14;
        }
        else if(userWord.length() == 15){
            points = 15;
        }

        return points;
    }

    /**
     * Randomly selects a seven letter word from the Array List fileContents and shuffles the letters to be used as the letters displayed on the screen for the user to spell words with
     * @param fileContents represents the contents of the file, contains all the exact words listed in the words.txt file
     * @param userGuesses represents all the previous words the user entered that were correct and increased their game score
     * @return roundLetters
     */
    public static ArrayList<Character> getWordLetters(ArrayList<String> fileContents, ArrayList<String> userGuesses){
        ArrayList<String> validWords = new ArrayList<>();
        ArrayList<Character> roundLetters = new ArrayList<>();

        // Creates a loop that reads through the String Array fileContents, stores the words listed in fileContents into the String variable word during each loop, checks if the words are seven words long then adds those words to the String Array List validWords
        for(String word: fileContents){
            if(word.length() == 7){
                validWords.add(word);
            }
        }  

        // Shuffles all the seven letter words stored in validWords, then takes the first word listed at index 0 and stores that value into the String variable currentWord; This prevents the same seven letter word being used for every round when you restart the game
        Collections.shuffle(validWords);
        String currentWord = validWords.get(0);

        // Creates a loop that converts currentWord to a Char Array, reads through each character within currentWord, stores each character value into String variable c during each loop, and adds the characters to the Char Array List roundLetters
        for(char c: currentWord.toCharArray()){
            roundLetters.add(c);
        }

        return roundLetters;
    }

    /**
     * Shuffles/scrambles the characters listed in an array so they don't show which word they were taken from in words.txt and prints out each character/letter so the user knows which letters they need to spell words with
     * @param roundLetters represents the current seven letters, taken from a 7 letter word in words.txt, displayed that the user must spell words with that match the words.txt file content
     */
    public static void displayAndShuffleLetters(ArrayList<Character> roundLetters){
        Collections.shuffle(roundLetters);

        for(char letter: roundLetters){
            System.out.print("     " + letter);
        }
        System.out.println();
    }

    /**
     * Checks if the word the user entered is valid/they correctly spelled a word that is within the words.txt file, made using the letters presented, and is not a previous word the user entered
     * @param userWord represents the word the user entered that should be spelled using the seven letters displayed
     * @param roundLetters represents the current seven letters, taken from a 7 letter word in words.txt, displayed that the user must spell words with that match the words.txt file content
     * @param userGuesses represents all the previous words the user entered that were correct and increased their game score
     * @param fileContents represents the contents of the file, contains all the exact words listed in the words.txt file
     * @return false or true
     */
    public static boolean isValid(String userWord, ArrayList<Character> roundLetters, ArrayList<String> userGuesses, ArrayList<String> fileContents){
        // Checks if the String variable userWord is listed within the String Array List userGuesses, if true the code returns the boolean value false
        if(userGuesses.contains(userWord)){
            return false;
        }
        
        // Checks if the String variable userWord is listed within the String Array List fileContents, if false the code returns the boolean value false and if true goes through a for loop, which when exited returns the boolean value true
        // the code converts userWord to a Char Array, which is checked to see if any of the letters with userWord were not apart of the round's seven letters
        if(fileContents.contains(userWord)){

            // Generative AI Usage: ChatGPT
            // Creates a loop that converts userWord to a Char Array, reads through each character within userWord, stores each character value into String variable c during each loop, and checks if any of userWord's characters are not listed with roundLetters, if the boolean value false is returned
            for(char c: userWord.toCharArray()){
                if(!roundLetters.contains(c)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Reads through the words.txt file and adds each line from the file to the String Array List named fileContent
     * @param reader represents the scanner used to read the words.txt file
     * @return fileContent
     */
    public static ArrayList<String> getFileContents(Scanner reader){
        ArrayList<String> fileContent = new ArrayList<>();

        while(reader.hasNextLine()){
            String fileLines = reader.nextLine();
            fileContent.add(fileLines);
        }
        return fileContent;
    }
}
