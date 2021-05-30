package util;

import util.Student;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student stud1, Student stud2) {
        if (stud1 == null && stud2 == null) return 0;
        if (stud1 == null) return 1;
        if (stud2 == null) return -1;

        // compare on their last names
        int lastNameCompare = stud1.getLastName().compareTo(stud2.getLastName());

        // if last names are identical, compare on first names
        if (lastNameCompare == 0) {
            return stud1.getFirstName().compareTo(stud2.getFirstName());
        }
        else {
            return lastNameCompare;
        }
    }
}
