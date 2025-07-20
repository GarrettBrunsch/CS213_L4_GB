// Garrett Brunsch
// Lab #4 - Exceptions
// Due 7/20

import java.util.Scanner;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main
{
    enum MenuOptions
    {
        INVALID, TEST_EXCEPTIONS, QUIT
    }

    public static void main(String[] args) throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        int choice = 0;
        MenuOptions menuChoice = MenuOptions.INVALID;

        while (menuChoice != MenuOptions.QUIT)
        {
            displayMenu();
            choice = getUserChoice(scanner);

            choice = (choice >= MenuOptions.TEST_EXCEPTIONS.ordinal() && choice <= MenuOptions.QUIT.ordinal()) ? choice : 0;
            menuChoice = MenuOptions.values()[choice];

            switch (menuChoice)
            {
                case TEST_EXCEPTIONS:
                    testExceptions();
                    break;
                case QUIT:
                    System.out.println("Now exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid menu option");
                    break;
            }
        }
        scanner.close();
    }

    public static void displayMenu()
    {
        System.out.print("\n\n=== MAIN MENU ===\n" +
                "1. Test Exceptions\n" +
                "2. Quit\n" +
                "Choice: ");
    }

    public static int getUserChoice(Scanner scanner)
    {
        String input = scanner.nextLine().trim();
        int choice = 0;

        try
        {
            if (input != null && !input.isEmpty())
            {
                choice = Integer.parseInt(input);
            }
        }
        catch (NumberFormatException e)
        {
            System.out.print("Incorrect format detected - ");
            choice = 0;
        }
        return choice;
    }

    public static void testExceptions()
    {
        System.out.println("\n=== TESTING EXCEPTIONS ===\n");

        String[] creatureTestDescriptions =
                {
                        "1. Testing invalid name:",
                        "2. Testing invalid health:",
                        "3. Testing invalid strength:",
                        "4. Testing invalid setters:",
                        "5. Testing valid creature creation"
                };

        String[] armyTestDescriptions =
                {
                        "1. Testing invalid army name:",
                        "2. Testing invalid army size:",
                        "3. Testing valid army creation:"
                };

        System.out.println("Testing Creature Exceptions:");
        runCreatureTests(creatureTestDescriptions);

        System.out.println("\nTesting Army Exceptions:");
        runArmyTests(armyTestDescriptions);

        System.out.println("\n===Testing completed===");
    }

    private static void runCreatureTests(String[] descriptions)
    {
        String[] errorMessages = new String[Constants.MAX_TEST_CASES];
        int errorCount = 0;
        int testNumber = 0;

        // Test 1: Invalid name
        try
        {
            System.out.println(descriptions[testNumber]);
            Creature testCreature1 = new Creature("ab", "Bahamut", 100, 100);
            System.out.println("Test #" + (testNumber + 1) + " Passed: No exceptions thrown");
            testNumber++;
        }
        catch (ExceptionCreature e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: ExceptionCreature: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        // Test 2: Invalid health
        try
        {
            System.out.println(descriptions[testNumber]);
            Creature testCreature2 = new Creature("ValidName", "Macara", 100, -25);
            System.out.println("Test #" + (testNumber + 1) + " Passed: No exceptions thrown");
            testNumber++;
        }
        catch (ExceptionCreature e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: ExceptionCreature: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        // Test 3: Invalid strength
        try
        {
            System.out.println(descriptions[testNumber]);
            Creature testCreature3 = new Creature("ValidName", "Nuggle", 200, 100);
            System.out.println("Test #" + (testNumber + 1) + " Passed: No exceptions thrown");
            testNumber++;
        }
        catch (ExceptionCreature e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: ExceptionCreature: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        // Test 4: Invalid setter
        try
        {
            System.out.println(descriptions[testNumber]);
            Creature testCreature4 = new Creature("ValidName", "Ceffyl", 100, 100);
            testCreature4.setName("xy");
            System.out.println("Test #" + (testNumber + 1) + " Passed: No exceptions thrown");
            testNumber++;
        }
        catch (ExceptionCreature e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: ExceptionCreature: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        // Test 5: Valid creature creation
        try
        {
            System.out.println("5. Testing valid creature creation:");
            Creature testCreature5 = new Creature("Bruce", "Bahamut", 100, 100);
            System.out.println("Test #" + (testNumber + 1) + " Passed: Successfully created creature: " + testCreature5.getNameAndType());
            testNumber++;
        }
        catch (ExceptionCreature e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: ExceptionCreature: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        if (errorCount > 0)
        {
            System.out.println("\nErrors found:");
            for (int i = 0; i < errorCount; i++)
            {
                System.out.println(errorMessages[i]);
            }
        }
    }

    private static void runArmyTests(String[] descriptions)
    {
        String[] errorMessages = new String[Constants.MAX_TEST_CASES];
        int errorCount = 0;
        int testNumber = 0;

        // Test 1: Invalid army name
        try
        {
            System.out.println(descriptions[testNumber]);
            Army testArmy1 = new Army("Red", 5);
            System.out.println("Test #" + (testNumber + 1) + " Passed: No exceptions thrown");
            testNumber++;
        }
        catch (ExceptionArmy e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: ExceptionArmy: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        // Test 2: Invalid army size
        try
        {
            System.out.println(descriptions[testNumber]);
            Army testArmy2 = new Army("ValidArmyName", 15);
            System.out.println("Test #" + (testNumber + 1) + " Passed: No exceptions thrown");
            testNumber++;
        }
        catch (ExceptionArmy e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed:ExceptionArmy: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        // Test 3: Valid army creation
        try
        {
            System.out.println(descriptions[testNumber]);
            Army validArmy = new Army("ValidArmyName", 5);
            System.out.println("Test #" + (testNumber + 1) + " Passed: Successfully created army: " + validArmy.getArmyName());
            testNumber++;
        }
        catch (ExceptionArmy e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: ExceptionArmy: " + e.getMessage();
            testNumber++;
        }
        catch (Exception e)
        {
            errorMessages[errorCount++] = "Test #" + (testNumber + 1) + " Failed: Unexpected exception: " + e.getMessage();
            testNumber++;
        }

        if (errorCount > 0)
        {
            System.out.println("\nErrors found:");
            for (int i = 0; i < errorCount; i++)
            {
                System.out.println(errorMessages[i]);
            }
        }
    }
}

class ExceptionArmy extends Exception
{
    public ExceptionArmy(String message)
    {
        super(message);
    }
}

class ExceptionCreature extends Exception
{
    public ExceptionCreature(String message)
    {
        super(message);
    }
}

class Constants
{
    // General Defaults
    public static final Random rand = new Random();
    public static final int MAX_TEST_CASES = 10;

    public static final String HERO_NAMES_FILE = "src/in_hero_names.txt";
    public static final String VILLAIN_NAMES_FILE = "src/in_villain_names.txt";
    public static final String OUTPUT_FILE = "src/out_battle_results.txt";

    // Creature Defaults
    public static final int MIN_STRENGTH = 40;
    public static final int MAX_STRENGTH = 160;

    public static final int MIN_HEALTH = 40;
    public static final int MAX_HEALTH = 160;

    public static final int MIN_NAME_LENGTH = 3;
    public static final String DEFAULT_NAME = "Unknown";
    public static final String DEFAULT_TYPE = "Unknown";

    public static final String[] CREATURE_TYPES = {"Bahamut", "Macara", "Nuggle", "Ceffyl"};

    public static final int BAHAMUT_BONUS_DAMAGE = 20;
    public static final int BAHAMUT_CHANCE = 10;

    public static final int NUGGLE_CHANCE = 15;

    // Army Defaults
    public static final int MIN_ARMY_SIZE = 1;
    public static final int MAX_ARMY_SIZE = 10;
    public static final int DEFAULT_ARMY_SIZE = MIN_ARMY_SIZE;
    public static final String ARMY_1_NAME = "Red Team";
    public static final String ARMY_2_NAME = "Blue Team";
}

class Game
{
    private StringBuilder battleLog;

    public Game()
    {
        battleLog = new StringBuilder();
    }

    private void printAndAppend(String message)
    {
        System.out.println(message);
        battleLog.append(message + "\n");
    }

    public void playGame(Scanner mainScanner) throws IOException
    {
        battleLog.setLength(0);
        int armySize = getArmySize(mainScanner);

        try
        {
            Army army1 = new Army(Constants.ARMY_1_NAME, armySize);
            Army army2 = new Army(Constants.ARMY_2_NAME, armySize);

            army1.loadCreatureNames(Constants.HERO_NAMES_FILE);
            army2.loadCreatureNames(Constants.VILLAIN_NAMES_FILE);

            printAndAppend("\n=== NEW BATTLE ===\n" + "Army Stats Before Battle:\n" +
                    army1.toString() + "\n" + army2.toString());

            conductBattle(army1, army2);

            printAndAppend("\nArmy Stats After Battle:\n" + army1.toString() + "\n" + army2.toString());

            announceWinner(army1, army2);

            writeAllToFile();

            army1.resetArmy();
            army2.resetArmy();

            System.out.println("\nArmies have been reset for another battle");
        }
        catch (ExceptionArmy e)
        {
            System.out.println("Army creation error: " + e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Unexpected error during game: " + e.getMessage());
        }
    }

    private int getArmySize(Scanner mainScanner)
    {
        int size = 0;
        boolean validInput = false;

        while (!validInput)
        {
            System.out.print("Enter army size (1-10): ");
            String input = mainScanner.nextLine().trim();

            try
            {
                if (input != null && !input.isEmpty())
                {
                    size = Integer.parseInt(input);
                    if (size >= Constants.MIN_ARMY_SIZE && size <= Constants.MAX_ARMY_SIZE)
                    {
                        validInput = true;
                        System.out.println();
                    }
                    else
                    {
                        System.out.println("Army size must be between " + Constants.MIN_ARMY_SIZE + " and " + Constants.MAX_ARMY_SIZE);
                    }
                }
                else
                {
                    System.out.println("Please enter a valid number");
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Please enter a valid number");
            }
        }

        return size;
    }

    private void conductBattle(Army army1, Army army2)
    {
        System.out.println("\n--- BATTLE BEGINS ---");

        battleLog.append("\n--- BATTLE LOG ---\n");

        int armySize = army1.getArmySize();

        for (int position = 0; position < armySize; position++)
        {
            Creature creature1 = army1.getCreature(position);
            Creature creature2 = army2.getCreature(position);

            if (creature1 != null && creature2 != null && creature1.getHealth() > 0 && creature2.getHealth() > 0)
            {
                printAndAppend("\nBattle " + (position + 1) + ": " + creature1.getName() +
                        " vs " + creature2.getName());

                String battleHeader = String.format("%-20s | %-6s | %-10s | %-20s | %-15s | %-10s",
                        "Attacker", "Damage", "Army", "Defender", "Defender Health", "Army");
                String separator = "\n----------------------------------------------------------------------------------------";

                printAndAppend(battleHeader + separator);

                battleCreatures(creature1, creature2, army1.getArmyName(), army2.getArmyName());
            }
        }
    }

    private void battleCreatures(Creature creature1, Creature creature2, String army1Name, String army2Name)
    {
        int currentTurn = Constants.rand.nextInt(2);

        while (creature1.getHealth() > 0 && creature2.getHealth() > 0)
        {
            Creature attacker = (currentTurn == 0) ? creature1 : creature2;
            Creature defender = (currentTurn == 0) ? creature2 : creature1;
            String attackerArmy = (currentTurn == 0) ? army1Name : army2Name;
            String defenderArmy = (currentTurn == 0) ? army2Name : army1Name;

            int damage = attacker.getDamage();

            try
            {
                int newHealth = defender.getHealth() - damage;
                if (newHealth < 0)
                {
                    newHealth = 0;
                }
                defender.setHealth(newHealth);
            }
            catch (ExceptionCreature e)
            {
                System.out.println("Error setting health during battle: " + e.getMessage());
            }

            String battleLine = String.format("%-20s | %6d | %-10s | %-20s | %15d | %-10s",
                    attacker.getNameAndType(), damage, attackerArmy,
                    defender.getNameAndType(), defender.getHealth(), defenderArmy);

            printAndAppend(battleLine);

            currentTurn = (currentTurn + 1) % 2;
        }

        Creature winner = (creature1.getHealth() > 0) ? creature1 : creature2;
        printAndAppend("Winner: " + winner.getNameAndType());
    }

    private void announceWinner(Army army1, Army army2)
    {
        int army1Health = army1.getTotalHealth();
        int army2Health = army2.getTotalHealth();

        String winnerMessage = (army1Health > army2Health) ? army1.getArmyName() + " wins the war!" :
                (army2Health > army1Health) ? army2.getArmyName() + " wins the war!" :
                        "The war ends in a tie!";

        String finalResults = "\n\n=== FINAL RESULTS ===\n" +
                army1.getArmyName() + " total health: " + army1Health + "\n" +
                army2.getArmyName() + " total health: " + army2Health + "\n" +
                winnerMessage;

        printAndAppend(finalResults);
    }

    private void writeAllToFile() throws IOException
    {
        FileWriter writer = new FileWriter(Constants.OUTPUT_FILE, true);
        writer.write(battleLog.toString());
        writer.close();
    }
}

class Creature
{
    private String name = Constants.DEFAULT_NAME;
    private String type = Constants.DEFAULT_TYPE;
    private int strength = Constants.MIN_STRENGTH;
    private int health = Constants.MIN_HEALTH;

    public Creature() throws ExceptionCreature
    {
        setCreature(Constants.DEFAULT_NAME, Constants.DEFAULT_TYPE, Constants.MIN_STRENGTH, Constants.MIN_HEALTH);
    }

    public Creature(String name, String type, int strength, int health) throws ExceptionCreature
    {
        setCreature(name, type, strength, health);
    }

    public void setCreature(String creatureName, String creatureType, int creatureStrength, int creatureHealth) throws ExceptionCreature
    {
        StringBuilder errorMessages = new StringBuilder();

        if (creatureName == null || creatureName.length() < Constants.MIN_NAME_LENGTH)
        {
            errorMessages.append("Creature name must be at least " + Constants.MIN_NAME_LENGTH + " characters long. Provided: '").append(creatureName).append("'. ");
        }

        if (creatureHealth < 0)
        {
            errorMessages.append("Creature health cannot be less than zero. Provided: ").append(creatureHealth).append(". ");
        }

        if (creatureStrength > Constants.MAX_STRENGTH)
        {
            errorMessages.append("Creature strength cannot be above ").append(Constants.MAX_STRENGTH).append(". Provided: ").append(creatureStrength).append(". ");
        }

        if (!errorMessages.isEmpty())
        {
            throw new ExceptionCreature(errorMessages.toString().trim());
        }

        // Guarantees modification only happens after passing all validation checks
        name = creatureName;
        type = creatureType;
        strength = creatureStrength;
        health = creatureHealth;
    }

    public void setName(String creatureName) throws ExceptionCreature
    {
        setCreature(creatureName, type, strength, health);
    }

    public void setStrength(int creatureStrength) throws ExceptionCreature
    {
        setCreature(name, type, creatureStrength, health);
    }

    public void setHealth(int newHealth) throws ExceptionCreature
    {
        setCreature(name, type, strength, newHealth);
    }

    public void reset() throws ExceptionCreature
    {
        setCreature(Constants.DEFAULT_NAME, Constants.DEFAULT_TYPE, Constants.MIN_STRENGTH, Constants.MIN_HEALTH);
    }

    public static int loadCreatureNames(String[] names, String filepath) throws IOException
    {
        final int MAX_NAMES = names.length;
        int count = 0;

        File inputFile = new File(filepath);

        if (inputFile.exists() && inputFile.canRead())
        {
            Scanner fileScanner = new Scanner(inputFile);

            while (fileScanner.hasNextLine() && count < MAX_NAMES)
            {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty())
                {
                    names[count] = line;
                    count++;
                }
            }
            fileScanner.close();
        }
        else
        {
            System.out.println("Error: File not found: " + filepath);
        }
        return count;
    }

    public int getHealth()
    {
        return health;
    }

    public int getStrength()
    {
        return strength;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }

    public String getNameAndType()
    {
        return name + " the " + type;
    }

    public int getDamage()
    {
        int damage = 0;

        if (strength > 0)
        {
            damage = Constants.rand.nextInt(strength) + 1;

            switch (type.toLowerCase())
            {
                case "bahamut":
                    if ((Constants.rand.nextInt(100)) < Constants.BAHAMUT_CHANCE)
                    {
                        damage = damage + Constants.BAHAMUT_BONUS_DAMAGE;
                    }
                    break;

                case "macara":
                    damage = damage * 2;
                    break;

                case "nuggle":
                    if ((Constants.rand.nextInt(Constants.NUGGLE_CHANCE)) == 0)
                    {
                        damage = damage * 2;
                    }
                    break;

                default:
                    break;
            }
        }
        return damage;
    }

    public String toString()
    {
        return String.format("%-15s | %-15s | %10d | %10d", name, type, strength, health);
    }
}

class Army
{
    private String armyName = Constants.DEFAULT_NAME;
    private int armySize = Constants.DEFAULT_ARMY_SIZE;
    private Creature[] creatures = new Creature[Constants.MAX_ARMY_SIZE];

    public Army() throws ExceptionArmy
    {
        setArmy(Constants.DEFAULT_NAME, Constants.DEFAULT_ARMY_SIZE);
        initializeCreatures();
    }

    public Army(int size) throws ExceptionArmy
    {
        setArmy(Constants.DEFAULT_NAME, size);
        initializeCreatures();
    }

    public Army(String name) throws ExceptionArmy
    {
        setArmy(name, Constants.DEFAULT_ARMY_SIZE);
        initializeCreatures();
    }

    public Army(String name, int size) throws ExceptionArmy
    {
        setArmy(name, size);
        initializeCreatures();
    }

    public void setArmy(String name, int size) throws ExceptionArmy
    {
        if (name == null || name.length() < 5)
        {
            throw new ExceptionArmy("Army name must be at least 5 characters long. Provided: '" + name + "'");
        }

        if (size > Constants.MAX_ARMY_SIZE)
        {
            throw new ExceptionArmy("Army size cannot be more than " + Constants.MAX_ARMY_SIZE + ". Provided: " + size);
        }

        int processedSize = (size >= Constants.MIN_ARMY_SIZE && size <= Constants.MAX_ARMY_SIZE) ? size : Constants.DEFAULT_ARMY_SIZE;

        armyName = name;
        armySize = processedSize;
    }

    public void setArmyName(String name) throws ExceptionArmy
    {
        if (name == null || name.length() < 5)
        {
            throw new ExceptionArmy("Army name must be at least 5 characters long. Provided: '" + name + "'");
        }
        armyName = name;
    }

    public void setArmySize(int size) throws ExceptionArmy
    {
        if (size > Constants.MAX_ARMY_SIZE)
        {
            throw new ExceptionArmy("Army size cannot be more than " + Constants.MAX_ARMY_SIZE + ". Provided: " + size);
        }
        armySize = (size >= Constants.MIN_ARMY_SIZE && size <= Constants.MAX_ARMY_SIZE) ? size : Constants.DEFAULT_ARMY_SIZE;
    }

    public int getArmySize()
    {
        return armySize;
    }

    public String getArmyName()
    {
        return armyName;
    }

    public Creature getCreature(int index)
    {
        Creature result = null;

        if (index >= 0 && index < armySize)
        {
            result = creatures[index];
        }

        return result;
    }

    private void initializeCreatures()
    {
        for (int i = 0; i < armySize; i++)
        {
            try
            {
                String creatureType = Constants.CREATURE_TYPES[Constants.rand.nextInt(Constants.CREATURE_TYPES.length)];
                int strength = Constants.rand.nextInt(Constants.MAX_STRENGTH - Constants.MIN_STRENGTH + 1) + Constants.MIN_STRENGTH;
                int health = Constants.rand.nextInt(Constants.MAX_HEALTH - Constants.MIN_HEALTH + 1) + Constants.MIN_HEALTH;

                creatures[i] = new Creature(Constants.DEFAULT_NAME, creatureType, strength, health);
            }
            catch (ExceptionCreature e)
            {
                System.out.println("Error creating creature: " + e.getMessage());
            }
        }
    }

    public void loadCreatureNames(String filepath) throws IOException
    {
        String[] names = new String[Constants.MAX_ARMY_SIZE];
        int nameCount = Creature.loadCreatureNames(names, filepath);

        assignNames(names, nameCount);
    }

    private void assignNames(String[] names, int nameCount)
    {
        for (int i = 0; i < armySize; i++)
        {
            String selectedName = Constants.DEFAULT_NAME;

            if (nameCount > 0)
            {
                selectedName = names[Constants.rand.nextInt(nameCount)];
            }

            try
            {
                creatures[i].setCreature(selectedName, creatures[i].getType(),
                        creatures[i].getStrength(), creatures[i].getHealth());
            }
            catch (ExceptionCreature e)
            {
                System.out.println("Error assigning name to creature: " + e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("Unexpected error assigning name: " + e.getMessage());
            }
        }
    }

    public int getTotalHealth()
    {
        int totalHealth = 0;

        for (int i = 0; i < armySize; i++)
        {
            totalHealth += creatures[i].getHealth();
        }

        return totalHealth;
    }

    public void resetArmy()
    {
        for (int i = 0; i < armySize; i++)
        {
            try
            {
                creatures[i].reset();
            }
            catch (ExceptionCreature e)
            {
                System.out.println("Error resetting creature: " + e.getMessage());
            }
            catch (Exception e)
            {
                System.out.println("Unexpected error resetting creature: " + e.getMessage());
            }
        }
    }

    public String toString()
    {
        String headerFormat = "%-15s | %-15s | %10s | %10s";
        String result = armyName + " Stats\n";
        result += String.format(headerFormat, "Name", "Type", "Strength", "Health") + "\n";
        result += "----------------------------------------------------------------\n";

        for (int i = 0; i < armySize; i++)
        {
            result += creatures[i].toString() + "\n";
        }

        result += "Total Health: " + getTotalHealth() + "\n";

        return result;
    }
}

/*
=== MAIN MENU ===
1. Test Exceptions
2. Quit
Choice: z
Incorrect format detected - Invalid choice. Please select a valid menu option


=== MAIN MENU ===
1. Test Exceptions
2. Quit
Choice: 3
Invalid choice. Please select a valid menu option


=== MAIN MENU ===
1. Test Exceptions
2. Quit
Choice: 1

=== TESTING EXCEPTIONS ===

Testing Creature Exceptions:
1. Testing invalid name:
2. Testing invalid health:
3. Testing invalid strength:
4. Testing invalid setters:
5. Testing valid creature creation:
Test #5 Passed: Successfully created creature: Bruce the Bahamut

Errors found:
Test #1 Failed: ExceptionCreature: Creature name must be at least 3 characters long. Provided: 'ab'.
Test #2 Failed: ExceptionCreature: Creature health cannot be less than zero. Provided: -25.
Test #3 Failed: ExceptionCreature: Creature strength cannot be above 160. Provided: 200.
Test #4 Failed: ExceptionCreature: Creature name must be at least 3 characters long. Provided: 'xy'.

Testing Army Exceptions:
1. Testing invalid army name:
2. Testing invalid army size:
3. Testing valid army creation:
Test #3 Passed: Successfully created army: ValidArmyName

Errors found:
Test #1 Failed: ExceptionArmy: Army name must be at least 5 characters long. Provided: 'Red'
Test #2 Failed:ExceptionArmy: Army size cannot be more than 10. Provided: 15

===Testing completed===


=== MAIN MENU ===
1. Test Exceptions
2. Quit
Choice: 2
Now exiting program...

 */
