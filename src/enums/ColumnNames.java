package enums;

/**
 * Enum created for column names and column name display name strings.
 */
public enum ColumnNames {

    ID("ID"),
    NAME("Name"),
    QUIZ("Quiz"),
    A1("A1"),
    A2("A2"),
    EXAM("Exam"),
    CUMULATIVEMARK("CumulativeMark"),
    GRADE("Grade");

    //Store the displayName
    private final String displayName;

    //Constructor to prepare the display name based on enum
    ColumnNames(String displayName) {
        this.displayName = displayName;
    }

    //Get display name function
    public String getDisplayName() {
        return displayName;
    }
}
