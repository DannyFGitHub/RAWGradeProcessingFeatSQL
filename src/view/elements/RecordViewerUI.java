package view.elements;

import enums.ColumnNames;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class RecordViewerUI extends TableView {

    public RecordViewerUI() {

        TableColumn<String, SearchResultEntry> idColumn = new TableColumn<>(ColumnNames.ID.getDisplayName());
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<String, SearchResultEntry> nameColumn = new TableColumn<>(ColumnNames.NAME.getDisplayName());
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<String, SearchResultEntry> quizColumn = new TableColumn<>(ColumnNames.QUIZ.getDisplayName());
        quizColumn.setCellValueFactory(new PropertyValueFactory<>("quiz"));

        TableColumn<String, SearchResultEntry> a1Column = new TableColumn<>(ColumnNames.A1.getDisplayName());
        a1Column.setCellValueFactory(new PropertyValueFactory<>("a1"));

        TableColumn<String, SearchResultEntry> a2Column = new TableColumn<>(ColumnNames.A2.getDisplayName());
        a2Column.setCellValueFactory(new PropertyValueFactory<>("a2"));

        TableColumn<String, SearchResultEntry> examColumn = new TableColumn<>(ColumnNames.EXAM.getDisplayName());
        examColumn.setCellValueFactory(new PropertyValueFactory<>("exam"));

        TableColumn<String, SearchResultEntry> cumulativemarkColumn = new TableColumn<>(ColumnNames.CUMULATIVEMARK.getDisplayName());
        cumulativemarkColumn.setCellValueFactory(new PropertyValueFactory<>("cumulativeMark"));

        TableColumn<String, SearchResultEntry> gradeColumn = new TableColumn<>(ColumnNames.GRADE.getDisplayName());
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        this.getColumns().add(idColumn);
        this.getColumns().add(nameColumn);
        this.getColumns().add(quizColumn);
        this.getColumns().add(a1Column);
        this.getColumns().add(a2Column);
        this.getColumns().add(examColumn);
        this.getColumns().add(cumulativemarkColumn);
        this.getColumns().add(gradeColumn);
    }

}
