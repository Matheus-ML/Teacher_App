package com.senai.teacherapp.Controllers;

import com.senai.teacherapp.Models.SchoolClass;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.Arrays;

//Classe da tela principal do professor.
public class PrincipalViewController {
    @FXML
    private Button btnExit;

    @FXML
    private TableView<SchoolClass> tableListStudent;

    @FXML
    private TableColumn<SchoolClass, Integer> tableID;

    @FXML
    private TableColumn<SchoolClass, String> tableName;

    @FXML
    private TableColumn<SchoolClass, Void> tableAction;

    @FXML
    public void initialize(){
        //Configura as colunas de nome e id da classe SchoolClass na tabela estática principal do professor.
        tableID.setCellValueFactory(new PropertyValueFactory<>("idClass"));
        tableName.setCellValueFactory(new PropertyValueFactory<>("nameClass"));

        //Adicionar coluna de ação com botão "Excluir" na coluna de Ação da tabela estática principal do professor.
        Callback<TableColumn<SchoolClass, Void>, TableCell<SchoolClass, Void>> cellFactory = new Callback<TableColumn<SchoolClass, Void>, TableCell<SchoolClass, Void>>() {
            @Override
            public  TableCell<SchoolClass, Void> call(final TableColumn<SchoolClass, Void> param) {
                final TableCell<SchoolClass, Void> cell = new TableCell<>(){
                    private final Button btn = new Button("Excluir");

                    {
                        btn.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                SchoolClass schoolClass = getTableView().getItems().get(getIndex());
                                getTableView().getItems().remove(schoolClass);
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
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

        tableAction.setCellFactory(cellFactory);

        SchoolClass schoolClass1 = new SchoolClass(1,"1° Ano A",30);
        SchoolClass schoolClass2 = new SchoolClass(2,"2° Ano B",25);
        tableListStudent.getItems().addAll(Arrays.asList(schoolClass1,schoolClass2));
    }
}
