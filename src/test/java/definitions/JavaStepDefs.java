package definitions;

import cucumber.api.java.en.Given;
import pages.Animal;
import pages.Cat;
import pages.Dog;

public class JavaStepDefs {
    @Given("I work with classes")
    public void iWorkWithClasses() {

        Animal tom = new Cat("Tom");

        System.out.println(tom.getName());
        tom.walk();
        tom.eat("fish");
        tom.speak();

        Animal charlie = new Dog();
        System.out.println(charlie.getName());
        charlie.walk();
        charlie.eat("bone");
        charlie.speak();


    }
}
