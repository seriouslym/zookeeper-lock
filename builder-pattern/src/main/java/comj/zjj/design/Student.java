package comj.zjj.design;

/**
 * lombok @Builder 模拟
 * 静态方法builder 返回一个内部静态类xxxBuilder 通过链式调用构造对象 ，最终通过调用xxxBuilder.build new 一个外部类对象返回
 */
public class Student {
    private String name;
    private String sex;
    private int age;
    private String address;

    public Student(){}

    public Student(String name, String sex, int age, String address){
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public static StudentBuilder builder(){
        return new StudentBuilder();
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }

    public static class StudentBuilder{
        private String name;
        private String sex;
        private int age;
        private String address;
        public StudentBuilder name(String name){
            this.name = name;
            return this;
        }
        public StudentBuilder sex(String sex){
            this.sex = sex;
            return this;
        }
        public StudentBuilder age(int age){
            this.age = age;
            return this;
        }
        public StudentBuilder address(String address){
            this.address = address;
            return this;
        }
        public Student build(){
            return new Student(this.name, this.sex, this.age, this.address);
        }

        @Override
        public String toString() {
            return "Student.StudentBuilder(name = " + this.name + ", sex = " + this.sex + ", age = " + this.age + ", address = "+ this.address + ")";
        }
    }
}
