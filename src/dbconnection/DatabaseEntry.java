package dbconnection;


import enums.Grades;

public class DatabaseEntry {

    private String id;
    private String name;
    private int quiz;
    private int a1;
    private int a2;
    private int exam;
    private double cumulativeMark;
    private String grade;

    /**
     * Create a new database entry object with calculation logic on creation.
     *
     * @param id   String should be 8 character string of id
     * @param name String name, name of student of entry
     * @param quiz int quiz results 0 - 100
     * @param a1   int assignment 1 results 0 - 100
     * @param a2   int assignment 2 results 0 - 100
     * @param exam int exam results 0 - 100
     */
    public DatabaseEntry(String id, String name, int quiz, int a1, int a2, int exam) {
        this.id = id;
        this.name = name;
        this.quiz = quiz;
        this.a1 = a1;
        this.a2 = a2;
        this.exam = exam;

        //Calculate the cumulativeMark:
        this.cumulativeMark = calculateCumulativeMark();
        //Calculate the grade:
        this.grade = calculateGrade();
    }

    /**
     * Calculates the cumulative mark as per the assignment formula given.
     *
     * @return double cumulative mark
     */
    private double calculateCumulativeMark() {
        return (quiz * 0.05) + (a1 * 0.15) + (a2 * 0.2) + (exam * 0.6);
    }

    /**
     * Calculates the grade from the cumulative Mark as the assignemnt requirement
     *
     * @return String of two character digit, if somehow no grade matches the cumulative mark it returns empty string.
     */
    private String calculateGrade() {
        if (cumulativeMark >= 85) {
            return Grades.HD.toString();
        } else if (75 <= cumulativeMark && cumulativeMark < 85) {
            return Grades.DI.toString();
        } else if (65 <= cumulativeMark && cumulativeMark < 75) {
            return Grades.CR.toString();
        } else if (50 <= cumulativeMark && cumulativeMark < 65) {
            return Grades.PS.toString();
        } else if (cumulativeMark < 50) {
            return Grades.FL.toString();
        } else {
            return "";
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuiz() {
        return quiz;
    }

    public int getA1() {
        return a1;
    }

    public int getA2() {
        return a2;
    }

    public int getExam() {
        return exam;
    }

    public double getCumulativeMark() {
        return cumulativeMark;
    }

    public String getGrade() {
        return grade;
    }
}
