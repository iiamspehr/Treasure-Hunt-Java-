import java.util.Random;
import java.util.Date;

public class new_game implements game_board {

    Random random = new Random(new Date().getTime());

    player player1;
    player player2;

    public static final String WHITE_BACKGROUND = "\033[0;107m";
    public static final String YELLOW = "\033[0;93m";
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\033[1;90m";
    public static final String RED = "\033[0;91m";
    int turn = 1;

    public static void main(String[] args) {
        new new_game();
    }

    new_game()
    {
        boolean game_over=false;

        player1=new player(9, 0, "PL1", false, 0, 5, 6, 3, 3);
        player2=new player(0, 9, "PL2", false, 0, 5, 6, 3, 3);
        board[0][9]=player2;
        board[9][0]=player1;

        while (true) {

            int randx=random.nextInt(0, 10);
            int randy=random.nextInt(0, 10);

            if (board[randx][randy] == null) {
                board[randx][randy] = new treasure(randx, randy, "TSR", false);
                break;
            }
        }

        for(int i=0;i<20;i++) {
            while (true) {            
                int randx=random.nextInt(0, 10);
                int randy=random.nextInt(0, 10);
                if(board[randx][randy]==null){
                    board[randx][randy] = new traps(randx, randy, "BMB", true);
                    break;
                }
            }
        }

        for(int j=0;j<10;j++) {
            while(true){
                int randx=random.nextInt(0, 10);
                int randy=random.nextInt(0, 10);
                if(board[randx][randy]==null){
                    board[randx][randy] = new traps(randx, randy, "TNT", false);
                    break;
                }
            }
        }

        for(int k=0;k<15;k++) {
            while (true) {
                int randx=random.nextInt(0, 10);
                int randy=random.nextInt(0, 10);
                if(board[randx][randy]==null){
                    board[randx][randy] = new walls(randx, randy, "BRW", true);
                    break;
                }
            }
        }
        
        for(int t=0;t<5;t++) {
            while(true){ 
                int randx=random.nextInt(0, 10);
                int randy=random.nextInt(0, 10);
                if(board[randx][randy]==null){
                    board[randx][randy] = new walls(randx, randy, "UBW", false);
                    break;
                } 
            }
        }

        while (!game_over) {

            if(turn%2==1){
                System.out.println(player2.act_getter());
                print(player1);
                player1.options(board);
                turn++;
            }
            else if(turn%2==0){
                System.out.println(player1.act_getter());
                print(player2); 
                player2.options(board);
                turn++;                
            }

            if (player1.get_hp() <= 0 || player2.get_hp() <= 0 || player1.get_point() == 100 || player2.get_point() == 100) {                
                game_over=true;    
                if(player1.get_hp() <=0 ){
                    System.out.println("PL1 died . victory for PL2 ! ");
                    break;
                } else if(player1.get_point() >= 100){
                    System.out.println("PL1 reached 100 points !! It's a victory !!");
                    break;
                }
                
                if(player2.get_hp() <=0 ){
                    System.out.println("PL2 died . victory for PL1 ! ");
                    break;
                } else if(player2.get_point() >= 100){
                    System.out.println("PL2 reached 100 points !! It's a victory !!");
                    break;
                }                
            }
        }
    }

    @Override
    public void print(player player) {
        System.out.println("╔═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╦═════╗");
        for (int i = 0; i < board.length; i++) {
            System.out.print("║");
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    System.out.print("     ║");
                }
                else if(board[i][j] instanceof player) {
                    System.out.print(" " + WHITE_BACKGROUND + BLACK + board[i][j] + RESET + " ║");
                }
                else if(board[i][j] instanceof treasure) {
                    System.out.print(" " + YELLOW + board[i][j] + RESET + " ║");
                }
                else if(board[i][j] instanceof walls) {
                    System.out.print(" " + YELLOW + board[i][j] + RESET + " ║");
                }
                else if(board[i][j] instanceof traps) {
                    System.out.print(" " + RED + board[i][j] + RESET + " ║");
                }
                // else {
                //     switch (board[i][j].getClass().getSimpleName()) {
                //         case "player":
                //             System.out.print(" " + WHITE_BACKGROUND + BLACK + board[i][j] + RESET + " ║");
                //             break;
                //         case "treasure":
                //             System.out.print(" " + YELLOW + board[i][j] + RESET + " ║");
                //             break;
                //         case "walls":
                //             System.out.println(" " + YELLOW + board[i][j] + RESET + " ║");
                //             break;
                //         case "traps":
                //             System.out.println(" " + RED + board[i][j] + RESET + " ║");
                //             break;
                //     }
                // }
            }
            System.out.println();
            if (i < board.length - 1) {
                System.out.println("╠═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╬═════╣");
            }
        }
        System.out.println("╚═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╩═════╝");

        System.out.println("PL1" + " Score: " + player1.get_point() + " | " + "PL2" + " Score: " + player2.get_point());
        System.out.println("PL1" + " HP: " + player1.get_hp() + " | " + "PL2" + " HP: " + player2.get_hp());
        System.out.println("PL1" + " Abilities -> " + player1.get_abilities() + "\nPL2" + " Abilities -> " + player2.get_abilities());
        System.out.println("------------- Turn: " + turn + " " + player + "'s Turn , Choose an action -------------");
        System.out.println("W. Move Up - A. Move Left - S. Move Down - D. Move Right - D. Destruction - J. Long Jump - T. Spawn Trap");
        System.out.println("in order to use your abilities first Enter the Direction key then the key representing the ability (e.g., DT)");

    }
}