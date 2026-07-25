import java.util.ArrayList; 

 

public class ElectricPokemon extends Pokemon { 

 

public ElectricPokemon(String name, int HP, int DEF, int ATK, ArrayList<MoveSkill> Moves) { 

super(name, HP, DEF, ATK, "Electric", Moves); 

// TODO Auto-generated constructor stub 

} 

 

@Override 

public int DamageEffectiveness(Pokemon attacker, Pokemon defender, MoveSkill ms) {  
		
 

        int baseDamage = attacker.getATK();  

        double Multipler = ms.getPower();  

        int damage; 

        String attackerType = attacker.getType();
        String defenderType = defender.getType();  

 

 

        if (attackerType.equals("Electric")) {  

            if (defenderType.equals("Water")) {  

               System.out.println("Effective!!!"); 

               double dmg = baseDamage * Multipler * 2.5; 

               damage = (int) dmg;  

            }  

            else if (defenderType.equals("Earth")) {  

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