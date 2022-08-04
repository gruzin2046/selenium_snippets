package p02_pom.heroku.dygresjaMonada;

public class Person {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.withName("adi")
                .withSurname("dadi")
                .withAge(30);
    }

    private String name, surname;
    private Integer age;

    public Person withName(String name) {
        this.name = name;
        return this;
    }

    public Person withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Person withAge(Integer age) {
        this.age = age;
        return this;
    }
}
