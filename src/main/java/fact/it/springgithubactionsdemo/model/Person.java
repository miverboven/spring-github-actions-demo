package fact.it.springgithubactionsdemo.model;

public class Person {

    private String givenName, surname;

    public Person() {
    }

    public Person(String givenName, String surname) {
        this.givenName = givenName;
        this.surname = surname;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return surname.toUpperCase() + " " + givenName.toLowerCase();
    }
}
