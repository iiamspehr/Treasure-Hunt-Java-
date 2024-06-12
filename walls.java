import java.util.Random;
import java.util.Date;

public class walls extends entity {

    int x, y;
    Random rand=new Random(new Date().getTime());

    public walls(int x, int y, String tag, Boolean destroyable) {
        super(tag, destroyable);
        this.x=x;
        this.y=y;
    }
    
}
