import java.util.Date;
import java.util.Random;

public class traps extends entity{

    int x, y;
    Random rand=new Random(new Date().getTime());

    public traps(int x, int y, String tag, boolean destroyable) {
        super(tag, destroyable);
        this.x = x;
        this.y = y;
    }

    // public void BMB(Object[][] board) {

    //     while (true) 
    //     {
    //         int newX=rand.nextInt(0, 10);
    //         int newY=rand.nextInt(0, 10);

    //         if(board[newX][newY]==null)
    //         {
    //             board[newX][newY]=new traps(newX, newY, "BMB", true);
    //             board[x][y]=null;
    //             this.x=newX;
    //             this.y=newY;
    //             break;
    //         }
    //     }
    // }

    // public void TNT(Object[][] board) {

    //     while (true) 
    //     {
    //         int newX=rand.nextInt(0, 10);
    //         int newY=rand.nextInt(0, 10);

    //         if(board[newX][newY]==null)
    //         {
    //             board[newX][newY]=new traps(newX, newY, "TNT", false);
    //             board[x][y]=null;
    //             this.x=newX;
    //             this.y=newY;
    //             break;
    //         }
    //     }
    // }
}
