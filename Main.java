import java.util.*; 

public class Main { 

private static Game game = new Game(); 

    private static Scanner scanner = new Scanner(System.in); 

 

    public static void main(String[] args) { 

        while (true) { 

            System.out.println("1. Register"); 

            System.out.println("2. Login"); 

            System.out.println("3. Exit"); 

            System.out.print("Choose an option: "); 

            int option;

            try {

                option = scanner.nextInt(); 
                scanner.nextLine();

            } 

            catch (InputMismatchException e) { 

                System.out.println("Invalid input. Please enter 1, 2, or 3."); 

                scanner.nextLine(); // Clear the invalid input 

                continue; // Restart the loop   
            }
 

            switch (option) { 

                case 1: 

                    register(); 

                    break; 

                case 2: 

                    login(); 

                    break; 

                case 3: 

                	System.out.println("Thank you for playing."); 

                    System.exit(0); 

                    break; 

                default: 

                    System.out.println("Invalid menu option. Try again."); 

            } 

        } 

    } 

 

    private static void register() { 

        boolean registrationSuccessful = false; 

 

        while (!registrationSuccessful) { 

            try { 

                System.out.println("Please register a brand new account."); 

                System.out.print("Enter new username: "); 

                String playername = scanner.nextLine(); 

                System.out.print("Enter new password: "); 

                String password = scanner.nextLine(); 

 

                if (game.registerPlayer(playername, password)) { 

                    System.out.println("Registration successful"); 

                    registrationSuccessful = true; 

                    login();  

                } else { 

                    System.out.println("Oops! Playername already exists."); 

                } 

            } catch (InputMismatchException e) { 

                System.out.println("Input error: " + e.getMessage()); 

                scanner.nextLine();  

            } catch (IllegalArgumentException e) { 

                System.out.println("Error " + e.getMessage()); 

            } catch (Exception e) { 

                System.out.println("An unexpected error occurred: " + e.getMessage()); 

            } 

        } 

    } 

     

     

    private static void login() { 

    boolean loginSuccessful = false; 

 

    while (!loginSuccessful) { 

        try { 

        	System.out.println("Please login to account."); 

            System.out.print("Enter username: "); 

            String username = scanner.nextLine(); 

            System.out.print("Enter password: "); 

            String password = scanner.nextLine(); 

 

            Player player = game.loginPlayer(username, password); 

            if (player != null) { 

                System.out.println("Login successful"); 

                managePlayer(player); 

                loginSuccessful = true;  

                break; 

            } else { 

                System.out.println("Invalid username or password. Please try again."); 

            } 

        } catch (InputMismatchException e) { 

            System.out.println("Input error. " + e.getMessage()); 

            scanner.nextLine();  

        } catch (IllegalArgumentException e) { 

            System.out.println("Error. " + e.getMessage()); 

        } catch (Exception e) { 

            System.out.println("An Error occurred. " + e.getMessage()); 

        } 

 

         

        if (!loginSuccessful) { 

            System.out.println("Would you like to try again? (yes/no): "); 

            String tryagain = scanner.nextLine(); 

            if (tryagain.equals("no")) { 

                System.out.println("Exiting login attempt."); 

                break; 

            } 

        } 

    } 

} 

 

    private static void managePlayer(Player player) {

        System.out.println(
            "Welcome " + player.getPlayerName() + "!"
        );

        boolean loggedIn = true;

        while (loggedIn) {
            System.out.println(
                "What would you like to do?"
            );
            System.out.println("1. Start Battle");
            System.out.println("2. Logout");
            System.out.println("3. Check Inventory");

            int playerAnswer;

            try {
            playerAnswer = scanner.nextInt();
            scanner.nextLine();
            } 
            
            catch (InputMismatchException e) {
                System.out.println(
                    "Invalid input. Please enter 1, 2, or 3."
                );
                scanner.nextLine();
                continue;
            }

            switch (playerAnswer) {
                case 1:
                    Battle battle =
                        new Battle(player, scanner);

                    battle.StartBattle();
                    game.RecordBattleScore(battle);
                    game.PrintLeaderBoard();
                    break;

                case 2:
                    System.out.println(
                        "You have logged out."
                    );
                    loggedIn = false;
                    break;

                case 3:
                    player.getPokemonDetails();
                    break;

                default:
                    System.out.println(
                        "Invalid menu option. Try again."
                    );
                }
            }
        }
    }

