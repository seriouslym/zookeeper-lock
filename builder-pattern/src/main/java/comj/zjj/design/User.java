package comj.zjj.design;

public class User implements Cloneable{
    private String name;
    private int age;
    public User(){}
    public User(String name, int age){
        this.age = age;
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
