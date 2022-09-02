package sample;

import javafx.beans.property.SimpleStringProperty;

public class Person {

        private SimpleStringProperty firstName;
        private SimpleStringProperty lastName;

        public Person () {
        }

        public Person (String s1, String s2) {

            firstName = new SimpleStringProperty(s1);
            lastName = new SimpleStringProperty(s2);
        }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    @Override
    public String toString() {
        return (firstName.get() + ", " + lastName.get());
    }
}
