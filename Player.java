import java.util.*; 

 

 

 

public class Player {  

    private String PlayerName;  

    private String Password; 

    private ArrayList<Pokemon> pokemons;  

    private ArrayList<Pokeball> balllist; 

     

    public Player(String PlayerName, String password) {  

        this.PlayerName = PlayerName;  

        this.Password = password; 

        this.pokemons = new ArrayList<>();  

        this.balllist = Pokeball.PokeballList(); 

    }  

     

    public String getPlayerName() {  

        return PlayerName;  

    }  

     

    public void setPlayerName(String PlayerName) {  

        this.PlayerName = PlayerName;  

    }  

 

    public void addPokemon(Pokemon pokemon) {  

        pokemons.add(pokemon);  

    }  

 

    public String getPassword() { 

        return Password; 

    } 

 

    public void removePokemon(int index) {  

        if (index >= 0 && index < pokemons.size()) { 

            pokemons.remove(index);  

        } else { 

            System.out.println("Invalid index to remove Pokémon."); 

        } 

    }  

 

    public ArrayList<Pokemon> getPokemons() { 

        return pokemons; 

    } 

 

    public ArrayList<Pokeball> getBalllist() { 

        return balllist; 

    } 

     

    public boolean validatePassword(String password) { 

        return Password.equals(password); 

    } 

 

    public void getPokemonDetails() {  

        if (pokemons.isEmpty()) {  

            System.out.println("No Pokémon available.");  

            return;  

        }  

        System.out.println(PlayerName + "'s Pokémon:");  

        for (int i = 0; i < pokemons.size(); i++) {  

            System.out.println((i + 1) + ". " + pokemons.get(i));  

        }  

    }  

 

    public boolean NotEligibleForBattle() { 

        return pokemons.size() < 2; 

    } 

     

    public Pokemon RentPokemonForBattle(Scanner scanner) { 

        ArrayList<Pokemon> p = Pokemon.NewbieBenefit(); 

        Pokemon RentedPokemon = null; 

        if (pokemons.size() < 2) { 

            for (Pokemon poke: p) { 

                System.out.println(poke); 

            } 

            System.out.println("Select 1 out of these 3 Pokemon to rent during the battle"); 

            System.out.println("Enter the pokemon name you want to rent: "); 

            String PokemonName = scanner.nextLine(); 

            for (Pokemon poke: p) { 

                if (PokemonName.equals(poke.getName())){ 

                    RentedPokemon = poke; 

                } 

            } 

        } 

        return RentedPokemon; 

    } 

 

    public Pokemon sendPokemon(int index) {  

        if (index < 0 || index >= pokemons.size()) {  

            throw new IllegalArgumentException("Invalid Pokemon index.");  

        }  

        return pokemons.get(index); 

    }  

     

    public boolean catchPokemon(Pokemon pokemon) {  

        return pokemons.size() < 6;  

    }  

     

    public void catchNewbie(
        ArrayList<Pokemon> availablePokemons,Scanner scanner) {

        if (!pokemons.isEmpty()) {
            return;
        }

        for (int i = 0;
            i < availablePokemons.size();
            i++) 
            { 
                System.out.println( (i + 1) + ". "+ availablePokemons.get(i));
            }

        int index = -1;

        while (index < 1 || index > availablePokemons.size()) {

            System.out.println(
                "Select your starter Pokémon:"
            );

            try {
                index = scanner.nextInt();
                scanner.nextLine();

                if (index < 1 || index > availablePokemons.size()) {
                    
                    System.out.println(
                        "Invalid index. Please try again."
                    );
                }

            } 
            catch (InputMismatchException e) {
                
                System.out.println(
                    "Invalid input. Please enter a number."
                );

                scanner.nextLine();
                index = -1;
            }
        }

        Pokemon selectedPokemon = availablePokemons.get(index - 1);

        addPokemon(selectedPokemon);

        System.out.println("You have selected "+ selectedPokemon.getName());
    }    

  

    public void CalculateCatchProb(Pokemon p) { 

        //Check if the player can catch more pokemon
        if (!catchPokemon(p)) {
        
            System.out.println("You can't catch more than 6 Pokémon.");
        
            return;
        
        }

            Random randint = new Random(); 

            //Randomly select a Pokeball from the player's balllist
            int index = randint.nextInt(balllist.size()); 

            Pokeball ball = balllist.get(index); 

            System.out.println("Using " + ball.getType() + " to catch " + p.getName()); 

            //Catch probability
            double catch_value = ball.getCatchmultipler() * p.getATK(); 

            double random_double = p.getATK() * p.getDEF(); 

            if (catch_value >= random_double) { 

                System.out.printf("You have caught %s with %s !", p.getName(), ball.getType()); 

                addPokemon(p); 

            } else { 

                System.out.println(p.getName() + " has ran away!"); 

            } 

    } 

} 