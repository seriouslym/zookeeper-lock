import com.zjj.design.BlueGemDecorator;
import com.zjj.design.IEquip;
import com.zjj.design.RedGemDecorator;
import com.zjj.design.SwordEquip;
import org.junit.Test;

public class ApiTest {
    @Test
    public void t(){
        IEquip equip = new RedGemDecorator(new BlueGemDecorator(new SwordEquip()));
        System.out.println(equip.calculateAttack());
        System.out.println(equip.description());

    }
}
