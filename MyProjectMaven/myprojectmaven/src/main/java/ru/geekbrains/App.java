package ru.geekbrains;

import com.google.gson.Gson;
/**
 *
 */
public class App {
    public static void main(String[] args) {
        Person person = new Person("Yuliya", "Malysheva", 40);

        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println(json);

        Person deserializedPerson = gson.fromJson(json, Person.class);
        System.out.println(deserializedPerson.toString());
    }
}
