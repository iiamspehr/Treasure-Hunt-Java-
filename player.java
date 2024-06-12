import java.util.Scanner;

public class player extends entity {

    Scanner input=new Scanner(System.in);

    private int x, y, point, spawn_traps, hp, long_jumps, destructions;
    private String previous_act;

    public player(int x, int y, String tag, boolean destroyable, int point, int hp, int long_jumps, int destructions, int spawn_traps)
    {
        super(tag, destroyable);
        this.x=x;
        this.y=y;
        this.point=point;
        this.hp=hp;
        this.long_jumps=long_jumps;
        this.destructions=destructions;
        this.spawn_traps=spawn_traps;
    }

    public int get_hp(){
        return hp;
    }

    public void set_hp(int hp){
        this.hp=hp;
    }

    public int get_point() {
        return point;
    }

    public void set_point(int point) {
        this.point = point;
    }
    public int get_long_jumps() {
        return long_jumps;
    }

    public void set_long_jumps(int long_jumps) {
        this.long_jumps = long_jumps;
    }

    public int get_destructions() {
        return destructions;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void set_destructions(int destructions) {
        this.destructions = destructions;
    }

    public int get_spawn_traps() {
        return spawn_traps;
    }

    public void set_spawn_traps(int spawn_traps) {
        this.spawn_traps = spawn_traps;
    }

    public String get_abilities() {
        return "Destruction: " + get_destructions() + " | Long Jump: " + get_long_jumps() + " | Spawn Trap: " + get_spawn_traps();
    }

    public String act_getter(){
        return this.previous_act;
    }

    public void str_setter(String value, String target){
        value=target;
    }
    
    public void options(Object[][] board)
    {
        System.out.println("what do you want to play ?");
        while (true) {
            System.out.println("1.move\n2.long_jumps\n3.destroy a block\n4.add a trap");
            int choice=input.nextInt();
            input.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("enter the first letter of your direction");
                    String d=input.nextLine();
                    movements(board, d);
                    break;
                case 2:
                    System.out.println("enter the first letter of your direction");
                    String e=input.nextLine();
                    long_jump(board, e);
                    break;
                case 3:
                    System.out.println("enter the cordinates of the block you want to destroy .");
                    System.out.println("x : ");            
                    int dx=input.nextInt();
                    // input.nextLine();
                    System.out.println("y : ");
                    int dy=input.nextInt();
                    // input.nextLine();
                    destruct(board, dx, dy);
                    break;
                case 4:
                    System.out.println("please enter the cordinates of the square you want to place a trap .");
                    System.out.println("x : ");    
                    int trapX=input.nextInt();
                    // input.nextLine();
                    System.out.println("y : ");
                    int trapY=input.nextInt();
                    // input.nextLine();
                    trap(board, trapX, trapY);
                    break;
                default:
                    // System.out.println("invalid choice . try again .");
                    continue;
                    // break;
            }
            break;
        }
    }

    public void movements(Object[][] board, String direction) {
        switch (direction.toUpperCase()) {
            case "U":
                // Move up
                move(board, x-1, y);
                break;
            case "D":
                // Move down
                move(board, x+1, y);
                break;
            case "L":
                // Move left
                move(board, x, y-1);
                break;
            case "R":
                // Move right
                move(board, x, y+1);
                break;
            default:
                System.out.println("Invalid direction entered. Please use 'U', 'D', 'L', or 'R' for up, down, left, or right.");
                break;
        }
    }

    public void long_jump(Object[][] board, String direction) {
        switch (direction.toUpperCase()) {
            case "U":
                // long jump to up
                move(board, x-2, y);
                break;
            case "D":
                // long jump to down
                move(board, x+2, y);
                break;
            case "L":
                // long jump to left
                move(board, x, y-2);
                break;
            case "R":
                // long jump tp right
                move(board, x, y+2);
                break;
            default:
                System.out.println("Invalid direction entered. Please use 'U', 'D', 'L', or 'R' for up, down, left, or right.");
                break;
        }
        set_long_jumps(this.long_jumps - 1 );
    }
    
    private void move(Object[][] board, int newX, int newY) {
        try {
            if (board[newX][newY] == null) {
                board[newX][newY] = this;
                board[x][y] = null;
                x = newX;
                y = newY;
                str_setter(previous_act, this + " moved. Previous location: [" + x + "][" + y + "] Current location: [" + newX + "][" + newY + "].");
            } else if (board[newX][newY] instanceof treasure) {
                point += 10;
                ((treasure) board[newX][newY]).respawn(board);
                board[newX][newY] = this;
                board[x][y] = null;
                x = newX;
                y = newY;
                str_setter(previous_act, this + " moved. Previous location: [" + x + "][" + y + "] Current location: [" + newX + "][" + newY + "]. Earned 10 points.");
            } else if (board[newX][newY] instanceof traps) {
                handleTrapMove(board, newX, newY);
            }
            else {
                handleWallMove(board, newX, newY);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("ERROR: Outside of game board");
        }
    }

    private void handleTrapMove(Object[][] board, int newX, int newY) {
        if (tag.equals("BMB")) {
            board[newX][newY] = this;
            point -= 10;
            hp -= 2;
            board[x][y] = null;
            x = newX;
            y = newY;
            str_setter(previous_act, this + " moved through a bomb square. Previous location: [" + (x+1) + "][" + y + "] Current location: [" + x + "][" + y + "]. Lost 10 points and 2 HPs.");
        } else if (tag.equals("TNT")) {
            board[newX][newY] = this;
            point -= 15;
            hp -= 3;
            board[x][y] = null;
            x = newX;
            y = newY;
            str_setter(previous_act, this + " moved through a bomb square. Previous location: [" + (x+1) + "][" + y + "] Current location: [" + x + "][" + y + "]. Lost 15 points and 3 HPs.");
        } else {
            board[newX][newY] = this;
            point -= 5;
            hp -= 1;
            board[x][y] = null;
            x = newX;
            y = newY;
            str_setter(previous_act, this + " moved through a bomb square. Previous location: [" + (x+1) + "][" + y + "] Current location: [" + x + "][" + y + "]. Lost 5 points and 1 HP.");
        }
    }

    private void handleWallMove(Object[][] board, int newX, int newY) {
        if(tag.equals("BRW")) {
            System.out.println("this square is blocked by a breakable wall .");
        }
        else    System.out.println("this squared is blocked by an unbreakable wall . ");
    }

    public void trap(Object[][] board, int x, int y) {
        if (board[x][y] == null) {
            board[x][y] = new traps(x, y, "MST", true);
            set_spawn_traps((this.spawn_traps) - 1);
            str_setter(previous_act, "placed a trap in [ "+ x +" ]"+"[ "+y+" ]" );
            System.out.println("trap successfully placed .");
        }
        else    System.out.println("the square wasn't null so you couldn't place a trap in it .");
    }

    public void destruct(Object[][] board, int x, int y) {
        try {
            if (board[x][y] instanceof player) {
                System.out.println("you can't destruct the block which a player is curently in it");
                set_destructions((this.destructions) - 1);
            }
            else if(board[x][y] instanceof treasure) {
                System.out.println("you targeted the treasure block");
                set_destructions((this.destructions) - 1);
            }
            else if(board[x][y] instanceof traps) {
                if(((traps)board[x][y]).toString().equals("TNT")){
                        System.out.println("this block is filled by a TNT");
                        set_destructions((this.destructions) - 1);
                }
                else {
                    System.out.println("the block you targeted was successfully destructed");
                    board[x][y]=null;
                    set_destructions((this.destructions) - 1);
                }
            }
            else if(board[x][y] instanceof walls) {
                if(((walls)board[x][y]).toString().equals("UBW")){
                    System.out.println("you targeted an unbreakable wall .");
                    set_destructions((this.destructions) - 1);
                }
                else {
                    System.out.println("the block you tareted was successfullt destroyed");
                    board[x][y]=null;
                    set_destructions((this.destructions) - 1);
                }
            }
            else {
                    System.out.println("you can not destroy an empty block");
                    set_destructions((this.destructions) - 1);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("the block you targeted was out of the board");
        }
    }
}