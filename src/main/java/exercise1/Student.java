package exercise1;

import com.sun.org.apache.bcel.internal.generic.ExceptionThrower;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a student.
 * A Student is identified by its registration number.
 * A student gets scored in different courses.
 * These scores are expressed as integers on a scale from 0 to 20.
 */
public class Student {

    private String name;
    private String registrationNumber;
    //private String course;
    //private int score;
    HashMap<String, Integer> scoreByCourse = new HashMap<String, Integer>();


    /**
     * Creates a new Student.
     *
     * @throws NullPointerException if one of the parameter is null.
     */
    public Student(String name, String registrationNumber) {
        this.name = name;
        this.registrationNumber=registrationNumber;
        if(name == null)
            throw new NullPointerException();

        if (registrationNumber == null)
            throw new NullPointerException();
    }

    /**
     * Sets the score of this student for the given course.
     * If the score is set twice for the same course, the new score replaces the previous one.
     *
     * @throws NullPointerException if the course name is null.
     * @throws IllegalArgumentException if the score is less than 0 or greater than 20.
     */
    public void setScore(String course, int score) {
        scoreByCourse.put(course, score);

        if (course ==null)
            throw new NullPointerException();

        if (score < 0 || score > 20)
            throw new IllegalArgumentException();

    }

    /**
     * Returns the score for the given course.
     *
     * @return the score if found, <code>OptionalInt#empty()</code> otherwise.
     */
    public OptionalInt getScore(String course) {
        //integer est une classe du coup doit la metre car contient série d'opérations
        Integer nullableScore = scoreByCourse.get(course);

        return nullableScore != null ? OptionalInt.of(nullableScore): OptionalInt.empty();
    }
    //correspond à un if then else, le if est après point d'intérrogation, le alors équivaut au : et le dernier terme le else

    /**
     * Returns the average score.
     *
     * @return the average score or 0 if there is none.
     */
    public double averageScore() {

        int count = 0;
        double totalScore = 0.0;
        for (Integer score : scoreByCourse.values()){
            count ++;
            totalScore += score;
        }
        return totalScore /count;

        /* Autre méthode
        return scoreByCourse.values().stream()
        .mapToInt(Integer::intValue)
        .average()
        .orElse(0.0);
         */
    }

    /**
     * Returns the course with the highest score.
     *
     * @return the best scored course or <code>Optional#empty()</code> if there is none.
     */
    public Optional<String> bestCourse() {

        return scoreByCourse.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .findFirst();
        //recupère valeurs (le cours et la note)
        //trie les cours en fonction des notes par ordre croissant
        //ne garde plus que les cours puisqu'ils sont triés
        //.map on aurait pu le distribuer sur plein de machines
        //renvoie celui d'en haut puisque c'est la plus haute note
    }

    /**
     * Returns the highest score.
     *
     * @return the highest score or 0 if there is none.
     */
    public int bestScore() {
        return scoreByCourse.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .mapToInt(Map.Entry::getValue)
                .findFirst()
                .orElse(0);

    }

    /**
     * Returns the set of failed courses sorted by decreasing score.
     * A course is considered as passed if its score is higher than 12.
     */
    public Set<String> failedCourses() {

        return scoreByCourse.entrySet().stream()
        .filter(entry -> entry.getValue() < 12)
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toCollection(LinkedHashSet::new));
        /*

        return scoreByCourse.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .map(Map.Entry::getValue)
                .findFirst();
        */
    }

    /**
     * Returns <code>true</code> if the student has an average score greater than or equal to 12.0 and has less than 3 failed courses.
     */
    public boolean isSuccessful() {
        return false;
    }

    /**
     * Returns the set of courses for which the student has received a score, sorted by course name.
     */
    public Set<String> attendedCourses() { return null; }

    public String getName() {
        return null;
    }

    public String getRegistrationNumber() {
        return null;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(getName());
        sb.append(" (").append(getRegistrationNumber()).append(")");
        return sb.toString();
    }
}
