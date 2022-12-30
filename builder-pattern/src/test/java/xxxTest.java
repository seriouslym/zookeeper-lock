import comj.zjj.design.Student;
import org.junit.Test;

public class xxxTest {

    @Test
    public void t(){
        Student student = Student.builder().name("zjj").address("shanghai").age(11).sex("男").build();
        System.out.println(student);
    }
}
