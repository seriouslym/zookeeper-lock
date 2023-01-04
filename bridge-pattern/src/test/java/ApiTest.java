import com.zjj.design.abstractP.Circle;
import com.zjj.design.instance.GreenCircle;
import com.zjj.design.instance.RedCircle;
import org.junit.Test;

public class ApiTest {

    @Test
    public void t(){
        Circle circle = new Circle(2,2,3, new RedCircle());
        circle.draw();
    }
}
