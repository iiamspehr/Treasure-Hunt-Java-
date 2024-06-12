import java.util.Date;
import java.util.Random;

public class treasure extends entity {
    int x, y;
    Random rand=new Random(new Date().getTime());
    public treasure(int x, int y, String tag, boolean destroyable) {
        super(tag, destroyable);
        this.x = x;
        this.y = y;
    }
    public void respawn(Object[][] board)
    {
        while (true) 
        {
            int newX=rand.nextInt(0, 10);
            int newY=rand.nextInt(0, 10);
            if(board[newX][newY]==null)
            {
                board[newX][newY]=new treasure(newX, newY, "TSR", false);
                board[x][y]=null;
                this.x=newX;
                this.y=newY;
                break;
            }
        }
    }
}