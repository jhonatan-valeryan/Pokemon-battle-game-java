import java.util.*; 

 

public class Battle { 

 

    private int BattleScore; 

    private Player p; 

    private ArrayList<Pokemon> OpponentPokemon; 

    private Scanner scanner; 

 

    public Battle(Player p1, Scanner scanner) { 

        BattleScore = 0; 

        p = p1; 

        OpponentPokemon = Pokemon.GenerateOpponentPokemon(); 

        this.scanner = scanner; 

    } 

 

    public int getBattleScore() { 

        return BattleScore; 

    } 

    public Player getPlayer() {

        return p;
    }
 

    public Player getP() { 

        return p; 

    } 

 

    public Scanner getScanner() { 

        return scanner; 

    } 

 

    public void StartBattle() { 

        System.out.println("Let's Begin!"); 

        System.out.println("As a new player in this game you can choose a starter Pokemon as your first Pokemon!!"); 

        System.out.println("Select 1 out of these 3 Pokemon to start (1, 2, or 3)"); 

        ArrayList<Pokemon> randompokemon = Pokemon.NewbieBenefit(); 

        p.catchNewbie(randompokemon, scanner); 

 

        System.out.println("A Wild Pokemon has Appeared!"); 

        OpponentPokemon = Pokemon.GenerateOpponentPokemon(); 

         

        for (Pokemon pokemon : OpponentPokemon) { 

            System.out.println(pokemon); 

        } 

 

        Pokemon playerPokemon = null; 

 

        // Display player's Pokémon 

        p.getPokemonDetails(); 

        System.out.println("Select the particular Pokémon index accordingly that you want to send to the battlefield:"); 

 

        while (true) { 

            try { 

                int index = scanner.nextInt() - 1; // Convert to zero-based index 

                if (index < 0 || index >= p.getPokemons().size()) { 

                    throw new IndexOutOfBoundsException(); 

                } 

                playerPokemon = p.sendPokemon(index); 

                break; 

            } catch (IndexOutOfBoundsException e) { 

                System.out.println("Error. Invalid Pokémon index. Please select a valid index."); 

            } catch (InputMismatchException e) { 

                System.out.println("Invalid input. Please enter a number."); 

                scanner.nextLine(); // Clear the invalid input 

            } 

        } 

 

        ArrayList<Pokemon> BattlePokemonList = new ArrayList<>(); 

        BattlePokemonList.add(playerPokemon); 

 

        while (true) { 

            while (areAnyAlive(BattlePokemonList) && areAnyAlive(OpponentPokemon)) { 

                YourTurn(BattlePokemonList, OpponentPokemon); 

                if (areAnyAlive(OpponentPokemon)) { 

                    EnemyTurn(BattlePokemonList, OpponentPokemon); 

                } 

            } 

 

            CountingBattleScore(OpponentPokemon); 

            PrintResult(BattlePokemonList, OpponentPokemon); 

 

            if (!areAnyAlive(BattlePokemonList) || !areAnyAlive(OpponentPokemon)) { 

                break; 

            } 

        } 

    } 


    public void YourTurn(

        ArrayList<Pokemon> bp,

        ArrayList<Pokemon> op) {


        System.out.println("Your Turn");


        for (int i = 0; i < bp.size(); i++) {

        System.out.println( (i + 1) + " . " + bp.get(i));

        }


        int pokemonIndex = -1;


        while (pokemonIndex < 0 || pokemonIndex >= bp.size()) {
            
            System.out.println( "Select the Pokémon you want to use:");

            try {
                
                pokemonIndex = scanner.nextInt() - 1;
                
                if (pokemonIndex < 0 || pokemonIndex >= bp.size()) {

                System.out.println("Invalid Pokémon number.");

                continue;
                }

                if (bp.get(pokemonIndex).isDefeated()) { 
                    System.out.println(
                        "That Pokémon has already been defeated."
                    );

                    pokemonIndex = -1;
                }

            } 
            catch (InputMismatchException e) {
                
                System.out.println(
                    "Invalid input. Please enter a number."
                );

                scanner.nextLine();
            }
        }

        Pokemon selectedPokemon = bp.get(pokemonIndex);

        for (int i = 0; i < selectedPokemon.getMoves().size(); i++) {
            
            System.out.println(
                (i + 1)
                + " . "
                + selectedPokemon.getMoves().get(i)
            );
        }

        int moveIndex = -1;

        while (moveIndex < 0 || moveIndex >= selectedPokemon.getMoves().size()) {
            
            System.out.println(
                "Select the move skill you want to perform:"
            );

            try {
                
                moveIndex = scanner.nextInt() - 1;
                
                if (moveIndex < 0 || moveIndex >= selectedPokemon.getMoves().size()) {
                    
                    System.out.println(
                        "Invalid move number."
                    );
                }

            } 
            catch (InputMismatchException e) {
                
                System.out.println(
                    "Invalid input. Please enter a number."
            );

                scanner.nextLine();
            }
        }

        Pokemon targetedPokemon = chooseTarget(op);
        MoveSkill selectedMove = selectedPokemon.getMoves().get(moveIndex);

        int damage = selectedPokemon.DamageEffectiveness(
                    selectedPokemon,
                    targetedPokemon,
                    selectedMove
                );

        targetedPokemon.takeDamage(damage);

        System.out.printf(
            "You have used %s's %s on %s%n",
            selectedPokemon.getName(),
            selectedMove.getMoveName(),
            targetedPokemon.getName()
        );

        System.out.println(
            targetedPokemon.getName()
            + " has "
            + targetedPokemon.getHP()
            + " HP left"
        );
    }

 

    public void EnemyTurn(ArrayList<Pokemon> bp, ArrayList<Pokemon> op) { 

        Random rand = new Random(); 

        // Create a list containing only the alive opponent Pokémon
        ArrayList<Pokemon> aliveEnemies = new ArrayList<>();

        for (Pokemon pokemon : op) {

            if (!pokemon.isDefeated()) {

                aliveEnemies.add(pokemon);
            }

        }

        // Create a list containing only the alive player Pokémon
        ArrayList<Pokemon> alivePlayerPokemon = new ArrayList<>();

        for (Pokemon pokemon : bp) {

            if (!pokemon.isDefeated()) {

                alivePlayerPokemon.add(pokemon);
            }

        }

        // Safety check
        if (aliveEnemies.isEmpty() || alivePlayerPokemon.isEmpty()){
            
            return;

        }

        // Randomly select an enemy pokemon that is still alive
        Pokemon enemyPokemon = aliveEnemies.get(rand.nextInt(aliveEnemies.size()));

        //Randomly select a player Pokemon that is still alive
        Pokemon yourPokemon = alivePlayerPokemon.get(rand.nextInt(alivePlayerPokemon.size()));
        
        //Randomly select one of the enemy Pokemon's moves
        int moveIndex = rand.nextInt(enemyPokemon.getMoves().size());

        MoveSkill selectedMove = enemyPokemon.getMoves().get(moveIndex);

        System.out.println(enemyPokemon.getName() + " uses "

        + selectedMove.getMoveName());
        
        yourPokemon.takeDamage(enemyPokemon.DamageEffectiveness(enemyPokemon, yourPokemon, selectedMove));
        
        System.out.println(yourPokemon.getName() + " has " + yourPokemon.getHP() + " HP left");

    } 

 

    public void CountingBattleScore(ArrayList<Pokemon> opponentPokemon) { 

        int score = 0; 

        for (Pokemon pokemon : opponentPokemon) { 

            if (pokemon.isDefeated()) { 

                score += 10; 

            } 

        } 

        BattleScore = score; 

    } 

    public void CatchAttempt(ArrayList<Pokemon> op) {
        while (true) {
            
            System.out.println("Select a Pokémon to catch:");
            
            for (int i = 0; i < op.size(); i++) {
                System.out.println(
                    (i + 1) + ". " + op.get(i).getName()
                );
            }

            System.out.println("0. Do not catch a Pokémon");
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                
                if (choice == 0) {
                    System.out.println("No Pokémon captured.");
                    return;
                }

                if (choice < 1 || choice > op.size()) {
                    System.out.println(
                        "Invalid Pokémon number."
                    );
                    continue;
                }

                p.CalculateCatchProb(op.get(choice - 1));
                return;

            } 
            catch (InputMismatchException e) {
                System.out.println(
                    "Please enter a valid number."
                );
                scanner.nextLine();
            }
        }
    }

    public void PrintResult(ArrayList<Pokemon> bp,ArrayList<Pokemon> op) {
        
        if (!areAnyAlive(bp)) {
            System.out.println("You lost the battle.");

        } 
        else if (!areAnyAlive(op)) {
            System.out.println("You won the battle!");

            while (true) {
                System.out.println("Do you want to catch a Pokémon? " + "(yes/no)");

                String catchPokemon = scanner.nextLine().trim();

                if (catchPokemon.equalsIgnoreCase("yes")) {
                    CatchAttempt(op);
                    break;

                } 
                else if (
                    catchPokemon.equalsIgnoreCase("no")) {
                        
                        System.out.println("No Pokémon captured!");
                        break;

                } 
                else {
                    System.out.println("Please enter yes or no.");
                }
            }
        }

        System.out.println("Your current battle score: " + BattleScore);
    }    

 

    public boolean areAnyAlive(ArrayList<Pokemon> pokemons) { 

        for (Pokemon pokemon : pokemons) { 

            if (!pokemon.isDefeated()) { 

                return true; 

            } 

        } 

        return false; 

    } 

 

    public Pokemon chooseTarget(ArrayList<Pokemon> op) { 

        while (true) {

            System.out.println("Select a Pokémon to target:");

            for (int i = 0; i < op.size(); i++) { 

                Pokemon pokemon = op.get(i);

                System.out.println((i + 1) + ". " + pokemon.getName() + " | HP: " + pokemon.getHP()); 

            }

            try {

                int targetIndex = scanner.nextInt() - 1;

                if (targetIndex < 0 || targetIndex >= op.size()) {

                    System.out.println( "Invalid target number. Please try again" );
                
                    continue;
                }

                Pokemon selectedTarget = op.get(targetIndex);

                if (selectedTarget.isDefeated()) {

                    System.out.println("That Pokémon is already defeated. Please select another target.");

                    continue;

                }  
                
                return selectedTarget;

            } 
            
            catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter a number.");

                scanner.nextLine(); // Clear the invalid input

            }

        } 

    }
}

