import java.util.*; 

public class Pokeball { 

 

private String type; 

private double catchmultipler; 

 

public Pokeball(String n, double p) { 

this.type = n; 

this.catchmultipler = p; 

} 

 

public double getCatchmultipler() { 

return catchmultipler; 

} 

 

public String getType() { 

return type; 

} 

 

@Override 

public String toString() { 

return String.format("%s pokeball", type); 

} 

 

public static ArrayList<Pokeball> PokeballList(){ 

ArrayList<Pokeball> pokeballlist = new ArrayList<Pokeball>(); 

pokeballlist.add(new Pokeball("Poke Ball", 7.5)); 

pokeballlist.add(new Pokeball("Great Ball", 12.5)); 

pokeballlist.add(new Pokeball("Ultra Ball", 20.0)); 

pokeballlist.add(new Pokeball("Master Ball", 100.0)); 

 

return pokeballlist; 

} 

 

 

}