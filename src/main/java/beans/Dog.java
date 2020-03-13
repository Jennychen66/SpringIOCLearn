package beans;

public class Dog {

    public Dog(){
       System.out.println("dog");
    }

    private int age;
    private String name;
    @Override
    public String toString(){
        return "Dog's age is " + this.age;
    }
}
