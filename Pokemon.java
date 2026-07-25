import java.util.*; 



public class Pokemon { 
	private String name; 
	private int HP; 
	private int DEF; 
	private int ATK; 
	private String Type; 
	private ArrayList<MoveSkill> Moves; 

	
	public Pokemon() {
		
	}

	public Pokemon(String name, int HP, int DEF, int ATK, String Type, ArrayList<MoveSkill> Moves) { 
		this.name = name; 
		this.HP = HP; 
		this.DEF = DEF; 
		this.ATK = ATK; 
		this.Type = Type;  
		this.Moves = Moves; 
	} 

	public String getName() { 
		return name; 
	} 

	public int getHP() { 
		return HP; 
	} 

	public int getDEF() { 
		return DEF; 
	} 

	public int getATK() { 
		return ATK; 
	} 

	public String getType() { 
		return Type; 
	} 

	public ArrayList<MoveSkill> getMoves() { 
		return Moves; 
	} 

	public void takeDamage(int damage) { 
        HP -= damage; 
        if (HP < 0) HP = 0; 
    } 
	
    public boolean isDefeated() { 
        return HP <= 0; 
    } 
    
    public int DamageEffectiveness(Pokemon attacker, Pokemon defender, MoveSkill ms) {
    	
        int baseDamage = attacker.getATK();
        double multiplier = ms.getPower();
        int damage;

        if (ms.getMoveType().equalsIgnoreCase("Elemental")) {
            double dmg = baseDamage * multiplier * 1.0;
            damage = (int) dmg;
            damage -= defender.getDEF() / 2;
            if (damage < 0) damage = 0;
        } else {
            damage = baseDamage * (int) multiplier;
        }

        return damage;
    }

    public static ArrayList<Pokemon> PokemonList() {
        ArrayList<Pokemon> pokemons = new ArrayList<>();
        
        pokemons.add(new FirePokemon("Charizard", 650, 80, 205, createMoveList(new String[]{"Inferno", "Flamethrower", "Dragon Claw"})));
        pokemons.add(new WaterPokemon("Blastoise", 600, 85, 170, createMoveList(new String[]{"Hydro Pump", "Water Pulse", "Tackle"})));
        pokemons.add(new EarthPokemon("Torterra", 515, 105, 150, createMoveList(new String[]{"Headlong Rush", "Mud-Slap", "Body Slam"})));
        pokemons.add(new ElectricPokemon("Pikachu", 425, 40, 150, createMoveList(new String[]{"Thunderbolt", "Volt Tackle", "Quick Attack"})));
        pokemons.add(new GrassPokemon("Venusaur", 480, 70, 175, createMoveList(new String[]{"Solar Beam", "Sleep Powder", "Growl"})));
        pokemons.add(new FirePokemon("Flareon", 470, 86, 195, createMoveList(new String[]{"Ember", "Fire Spin", "Tail Whip"})));
        pokemons.add(new WaterPokemon("Vaporeon", 465, 65, 165, createMoveList(new String[]{"Water Gun", "Aqua Ring", "Tail Whip"})));
        pokemons.add(new EarthPokemon("Garchomp", 685, 95, 170, createMoveList(new String[]{"Sandstorm", "Sand Attack", "Tackle"})));
        pokemons.add(new ElectricPokemon("Jolteon", 570, 125, 165, createMoveList(new String[]{"Thunder Shock", "Thunder Fang", "Tail Whip"})));
        pokemons.add(new GrassPokemon("Leafeon", 615, 90, 155, createMoveList(new String[]{"Razor Leaf", "Leech Seed", "Tail Whip"})));

        return pokemons;
    }

    private static ArrayList<MoveSkill> createMoveList(String[] moves) {
        ArrayList<MoveSkill> moveList = new ArrayList<>();
        for (String move : moves) {
            moveList.add(new MoveSkill(move, "Attack", 0.4)); 
        }
        return moveList;
    }
    
    
    public static ArrayList<Pokemon> NewbieBenefit(){
    	ArrayList<Pokemon> RandomNewbiePokemon = new ArrayList<Pokemon>();
    	while (RandomNewbiePokemon.size() < 3) {
    		Random ran = new Random();
    		int i = ran.nextInt(Pokemon.PokemonList().size());
    		RandomNewbiePokemon.add(Pokemon.PokemonList().get(i));
    	}
    	return RandomNewbiePokemon;
    }
    
    public static ArrayList<Pokemon> GenerateOpponentPokemon() {
		ArrayList<Pokemon> RandomPokemon = new ArrayList<Pokemon>();
    	while (RandomPokemon.size() < 2) {
    		Random ran = new Random();
    		int i = ran.nextInt(Pokemon.PokemonList().size());
    		RandomPokemon.add(Pokemon.PokemonList().get(i));
    	}
    	return RandomPokemon;
	}
    

    
 
    @Override 
    public String toString() { 
    	return String.format("Pokemon: %s | Type: %s | HP: %d | DEF: %d | ATK: %d | Moves: %s", name, Type, HP, DEF, ATK, Moves); 
    } 

     

}
