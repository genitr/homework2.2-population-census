package src;

import java.util.*;
import java.util.stream.Collectors;

public class Main<names> {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        personCollection(persons, names, families);

        separator("Количество несовершеннолетних:");
        Long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

        separator("Список призывников:");
        List<String> recruit = persons.stream()
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruit);

        separator("Список работоспособных женщин и мужчин с высшим образованием:");
        List<Person> workable = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() > 17)
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() < 65 ||
                                  person.getSex() == Sex.WOMAN && person.getAge() < 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(workable);

    }

    public static void personCollection (Collection<Person> persons, List<String> names, List<String> families) {
        for (int i = 0; i < 10_000_000; i++) {
            persons.add( new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
    }

    public static void separator (String title) {
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println(title);
        System.out.println("------------------------------------------------------");
    }
}
