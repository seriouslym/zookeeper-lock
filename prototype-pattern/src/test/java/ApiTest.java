import com.zjj.design.QuestionBankController;
import com.zjj.design.util.Topic;
import com.zjj.design.util.TopicRandomUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ApiTest {



    @Test
    public void test() throws CloneNotSupportedException {
        QuestionBankController questionBankController = new QuestionBankController();
        String paper1 = questionBankController.createPaper("zjj", "1111");
        String paper2 = questionBankController.createPaper("wjh", "1234");
        System.out.println(paper1);
        System.out.println(paper2);

    }
}
