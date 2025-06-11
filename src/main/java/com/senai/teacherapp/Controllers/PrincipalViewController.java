package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.DAO.ActivityDAO;
import com.senai.teacherapp.DAO.SchoolClassDAO;
import com.senai.teacherapp.Models.Notification;
import com.senai.teacherapp.Models.SchoolClass;
import com.senai.teacherapp.Models.UserSession;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

//Classe da tela principal do professor.
public class PrincipalViewController extends Notification {
    @FXML
    private TableView<SchoolClass> tableListStudent;
    @FXML
    private TableColumn<SchoolClass, Integer> tableID;
    @FXML
    private TableColumn<SchoolClass, String> tableName;
    @FXML
    private TableColumn<SchoolClass, Void> tableDelete;
    @FXML
    private TableColumn<SchoolClass, Void> tableView;
    @FXML
    private Text txtNameProfessor;
    @FXML
    private TableColumn<SchoolClass, Integer> tableStudents;

    private UserSession session;

    public void setSession(UserSession session) {
        this.session = session;
        if (txtNameProfessor != null) {
            txtNameProfessor.setText(session.getName());
        }

        loadTableCell();
        loadSchoolClassTable();
    }

    @FXML
    void btnExit() {
        InformationAlert("Mensagem", "Você deseja sair do aplicativo?");
    }

    public void loadTableCell() {
        tableID.setCellValueFactory(new PropertyValueFactory<>("idSchoolClass"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("nameSchoolClass"));
        tableStudents.setCellValueFactory(new PropertyValueFactory<>("quantityStudent"));
        adjustmentTable();
    }

    public void adjustmentTable() {
        tableID.setSortable(false);
        tableID.setReorderable(false);
        tableID.setResizable(false);

        tableName.setSortable(false);
        tableName.setReorderable(false);
        tableName.setResizable(false);

        tableStudents.setSortable(false);
        tableStudents.setReorderable(false);
        tableStudents.setResizable(false);
    }

    public void loadSchoolClassTable() {
        try {
            SchoolClassDAO dao = new SchoolClassDAO();
            ObservableList<SchoolClass> list = FXCollections.observableArrayList(dao.listSchoolClass(session.getId()));
            tableListStudent.setItems(list);
        } catch (SQLException e) {
            System.out.println("Deu zebra na lista: " + e);
        }
        addBtnDelete();
        addBtnView();
    }

    @FXML
    void btnOnRegister(ActionEvent event) throws IOException {
        FXMLLoader fxml = new FXMLLoader(getClass().getResource("/com/senai/teacherapp/views/register-schoolclass-view.fxml"));
        Parent root = fxml.load();

        RegisterSchoolClassController rac = fxml.getController();
        rac.setSession(session);
        rac.setOnSchoolClassRegister(() -> loadSchoolClassTable());

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Tela de Cadastrar Turmas");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void InformationAlert(String title, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(text);

        ButtonType yes = new ButtonType("Sim");
        ButtonType no = new ButtonType("Não");

        alert.getButtonTypes().setAll(yes, no);

        Optional<ButtonType> confirmButtons = alert.showAndWait();

        if (confirmButtons.isPresent() && confirmButtons.get() == yes) {
            Platform.exit();
            System.exit(0);
        }
    }

    private void addBtnDelete() {
        Callback<TableColumn<SchoolClass, Void>, TableCell<SchoolClass, Void>> cellFactory =
                new Callback<TableColumn<SchoolClass, Void>, TableCell<SchoolClass, Void>>() {

                    @Override
                    public TableCell<SchoolClass, Void> call(final TableColumn<SchoolClass, Void> param) {

                        TableCell<SchoolClass, Void> cell = new TableCell<SchoolClass, Void>() {

                            private final Button btn = new Button("Excluir");

                            {
                                btn.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        SchoolClass sc = getTableView().getItems().get(getIndex());

                                        Alert confirmationMessage = new Alert(Alert.AlertType.CONFIRMATION);
                                        confirmationMessage.setTitle("Confirmação");
                                        confirmationMessage.setHeaderText("Tem certeza que deseja excluir?");
                                        confirmationMessage.setContentText("Está ação não poderá ser desfeita.");

                                        Optional<ButtonType> resultado = confirmationMessage.showAndWait();
                                        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                                            int id = sc.getIdSchoolClass();
                                            try {
                                                ActivityDAO actDAO = new ActivityDAO();
                                                boolean hasActivities = actDAO.hasActivities(id);
                                                if (hasActivities) {
                                                    new Notification().ErrorAlert("Exclusão Bloqueada", "Não é possível excluir uma turma que tem atividades pendentes.");
                                                    return;
                                                }
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }

                                            try {
                                                new SchoolClassDAO().deleteSchoolClass(id);
                                            } catch (SQLException e) {
                                                throw new RuntimeException(e);
                                            }
                                            getTableView().getItems().remove(sc);
                                        }
                                    }
                                });
                            }

                            @Override
                            protected void updateItem(Void sc, boolean empty) {
                                super.updateItem(sc, empty);

                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
                };

        tableDelete.setCellFactory(cellFactory);
    }

    private void addBtnView() {
        Callback<TableColumn<SchoolClass, Void>, TableCell<SchoolClass, Void>> cellFactory =
                new Callback<TableColumn<SchoolClass, Void>, TableCell<SchoolClass, Void>>() {

                    @Override
                    public TableCell<SchoolClass, Void> call(final TableColumn<SchoolClass, Void> param) {

                        TableCell<SchoolClass, Void> cell = new TableCell<SchoolClass, Void>() {

                            private final Button btn = new Button("Visualizar");

                            {
                                btn.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        SchoolClass sc = getTableView().getItems().get(getIndex());

                                        try {
                                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/senai/teacherapp/views/activity-view.fxml"));
                                            Parent root = loader.load();

                                            loader.getController();
                                            ActivityViewController avc = loader.getController();
                                            avc.setSchoolClassId(sc.getIdSchoolClass());

                                            Scene scene = new Scene(root);
                                            Stage stage = new Stage();
                                            stage.setResizable(false);
                                            stage.setTitle("Atividades da Turma: " + sc.getNameSchoolClass());
                                            stage.setScene(scene);
                                            stage.show();

                                        } catch (IOException e) {
                                            new Notification().ErrorAlert("Erro", "Erro ao abrir tela de atividades!");
                                        }
                                    }
                                });
                            }

                            @Override
                            protected void updateItem(Void sc, boolean empty) {
                                super.updateItem(sc, empty);

                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
                };

        tableView.setCellFactory(cellFactory);
    }
}
