package com.example.vlad.database;

/**
 * Created by Vlad on 1/6/2018.
 */

public class Person
{
int    id;
String firstName;
String lastName;
int    age;

public Person(){}
public Person(int id, String firstName, String lastName, int age) {
this.id        = id;
this.firstName = firstName;
this.lastName  = lastName;
this.age       = age;
}
// GET AND SET ID
public int getId(){return id;}
public void setId(int id){this.id = id;}


// GET AND SET FIRSTNAME
public String getFirstName(){return firstName;}
public void setFirstName(String firstName) {this.firstName = firstName;}


// GET AND SET LASTNAME
public String getLastName(){return lastName;}
public void setLastName(String lastName) {this.lastName = lastName;}


// GET AND SET AGE
public int getAge(){return age;}
public void setAge(int age) {this.age = age;}

@Override
public String toString(){
    return "Person(" +
            "id ="          + id +
            ", firstname =" + firstName + "\"" +
            ", lastname ="  + lastName + "\"" +
            " ,age =" + age + ")";
}
}
