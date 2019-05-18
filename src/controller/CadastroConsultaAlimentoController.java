package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroConsultaAlimentoController {

    @FXML
    private TableView<?> tableAlimento;

    @FXML
    private TableView<?> tableEstoqueAlimentos;

    @FXML
    private Button btnAdicionarAlimento;

    @FXML
    private TextField txtNomeAlimento;

    @FXML
    private TextField txtTipo;

    @FXML
    private Tab tabEstoque;

    @FXML
    private TableView<?> tableValidade;

    @FXML
    private TextField txtPesquisaUsuario1;

    @FXML
    private DatePicker dataValidade;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnPesquisarEstoque;

    @FXML
    private Tab tabValidadesProximas;

    @FXML
    private Tab tabCadastrarAlimento;

    @FXML
    void handleClickAdicionarAlimento(ActionEvent event) {

    }

    @FXML
    void handleClickPesquisarEstoque(ActionEvent event) {

    }

    @FXML
    void handleClickVoltarMenu(ActionEvent event) {
        try {
            Pane menuPrincipal = FXMLLoader.load(getClass().getResource("../view/TelaMenuPrincipal.fxml"));
            // Fecha janela anterior
            ((Stage) btnVoltar.getScene().getWindow()).hide();
            Scene scene = new Scene(menuPrincipal);
            Stage stage = new Stage();
            //Desabilita o redimensionamento
            stage.setResizable(false);
            stage.setTitle("Menu Principal");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
