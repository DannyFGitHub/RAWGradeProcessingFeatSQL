package view.elements;

/**
 * Objects used by the table view to convert from object to tableview row.
 */
public class SearchResultEntry {

    private String id = null;
    private String name = null;
    private String quiz = null;
    private String a1 = null;
    private String a2 = null;
    private String exam = null;
    private String cumulativeMark = null;
    private String grade = null;

    public SearchResultEntry() {
    }

    public SearchResultEntry(String id, String name, String quiz, String a1, String a2, String exam, String cumulativeMark, String grade) {
        this.id = id;
        this.name = name;
        this.quiz = quiz;
        this.a1 = a1;
        this.a2 = a2;
        this.exam = exam;
        this.cumulativeMark = cumulativeMark;
        this.grade = grade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public String getA1() {
        return a1;
    }

    public void setA1(String a1) {
        this.a1 = a1;
    }

    public String getA2() {
        return a2;
    }

    public void setA2(String a2) {
        this.a2 = a2;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    public String getCumulativeMark() {
        return cumulativeMark;
    }

    public void setCumulativeMark(String cumulativeMark) {
        this.cumulativeMark = cumulativeMark;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
