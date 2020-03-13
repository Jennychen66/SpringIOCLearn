package beans;

import org.springframework.stereotype.Component;

@Component
public class Bird {
    public Bird(){
        System.out.println("bird is running");
    }

    private int age;
    @Override
    public String toString(){
        return "Bird's age is " + this.age;
    }

}
