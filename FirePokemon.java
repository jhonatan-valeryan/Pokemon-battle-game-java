import java.util.*; 

 

public class FirePokemon extends Pokemon { 

 

public FirePokemon(String name, int HP, int DEF, int ATK,  ArrayList<MoveSkill> Moves) { 

super(name, HP, DEF, ATK, "Fire", Moves); 

// TODO Auto-generated constructor stub 

} 

@Override 

public int DamageEffectiveness(Pokemon attacker, Pokemon defender, MoveSkill ms) {  

 

        int baseDamage = attacker.getATK();  

        double Multipler = ms.getPower();  

        int damage; 

        String attackerType = attacker.getType();
        String defenderType = defender.getType(); 

 

 

        if (attackerType.equals("Fire")) {  

            if (defenderType.equals("Grass")) {  

            	System.out.println("Effective!!!"); 

               double dmg = baseDamage * Multipler * 2.5; 

               damage = (int) dmg;  

            }  

            else if (defenderType.equals("Water")) {  

            	System.out.println("Less Effective!!!"); 

            	double dmg = baseDamage * Multipler * 1.5; 

                damage = (int) dmg; 

            }  

             

            else { 

            	double dmg = baseDamage * Multipler * 2.0; 

                damage = (int) dmg; 

            } 

        } 

         

        else{ 

        	double dmg = baseDamage * Multipler * 2.0; 

            damage = (int) dmg; 

        } 

         

        damage -= defender.getDEF() / 2;  

        if (damage < 0) damage = 0;  

        return damage;  

} 

 

}