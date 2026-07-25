import java.util.ArrayList; 

import java.util.Comparator; 

import java.util.HashMap; 

import java.util.Map; 

 

public class Game { 

private static ArrayList<Battle> LeaderBoard = new ArrayList<Battle>(5); 

    private static Map<String, Player> players = new HashMap<>(); 

 

    public Game() { 

    	players.put("Ash", new Player("Ash", "pikachu123")); 

    	Game.players.get("Ash").addPokemon(Pokemon.PokemonList().get(0)); 

    	Game.players.get("Ash").addPokemon(Pokemon.PokemonList().get(1)); 

        players.put("Misty", new Player("Misty", "starmie456")); 

        players.put("Brock", new Player("Brock", "onix789")); 

        players.put("Joseph", new Player("Joseph", "joseph123")); 

        players.put("Ali", new Player("Ali", "abcdefg")); 

    } 

 

    public boolean registerPlayer(String playername, String password) { 

        if (players.containsKey(playername)) { 

            return false;  

        } 

        Player newPlayer = new Player(playername, password); 

        players.put(playername, newPlayer); 

        return true; 

    } 

 

    public Player loginPlayer(String playername, String password) { 

        Player player = players.get(playername); 

        if (player != null && player.validatePassword(password)) { 

            return player; 

        } 

        return null; 

    } 

     

    public void RecordBattleScore(Battle b1) { 

        LeaderBoard.add(b1);

        LeaderBoard.sort(new Comparator<Battle>() { 

            @Override 

            public int compare(Battle b1, Battle b2) { 

                return Integer.compare(
                    
                    b2.getBattleScore(), 
                    
                    b1.getBattleScore()
                
                ); 

            } 

        });

        while (LeaderBoard.size() > 5) {

            LeaderBoard.remove(LeaderBoard.size() - 1);

        }  	 

    } 

     

    public void PrintLeaderBoard() {
        System.out.println();
        System.out.println("===== TOP 5 LEADERBOARD =====");
        
        if (LeaderBoard.isEmpty()) {
            
            System.out.println("No battle scores recorded yet.");
            return;
        }

        int rank = 1;
        
        for (Battle battle : LeaderBoard) {
            
            System.out.println(
                rank + ". "
                + battle.getPlayer().getPlayerName()
                + " - "
                + battle.getBattleScore()
                + " points"
            );

            rank++;
        }

        System.out.println("=============================");
    } 

}