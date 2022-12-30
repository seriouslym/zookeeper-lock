import comj.zjj.design.Student;
import comj.zjj.design.User;
import org.junit.Test;

public class xxxTest {

    @Test
    public void t(){
        Student student = Student.builder().name("zjj").address("shanghai").age(11).sex("男").build();
        System.out.println(student);
    }

    @Test
    public void cloneTest() throws CloneNotSupportedException {
        User user = new User("zjj",23);
        User clone = (User)user.clone();
        System.out.println(clone == user);
        System.out.println(user);
        System.out.println(clone);

    }
}
