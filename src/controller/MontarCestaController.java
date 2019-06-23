package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MontarCestaController {

    @FXML
    private TableView<?> tableListaUsuario1;

    @FXML
    private Button btnAdicionarNaCesta;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TextField txtNomeAlimento;

    @FXML
    private Button btnExcluirDaCesta;

    @FXML
    private Tab tabMontarCesta;

    @FXML
    private Button btnCadastrarUsuario;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TableView<?> tableListaUsuario;

    @FXML
    private Button btnResetLista;

    @FXML
    private TextField txtNomeBeneficiado;

    @FXML
    private Tab tabConsultarCesta;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnCancelar;

    @FXML
    private AnchorPane paneConsultaUsuario;

    @FXML
    private TabPane paneCesta;

    @FXML
    private TextField txtPesquisaCestaPorBeneficiado;

    @FXML
    void handleClickAdicionarNaCesta(ActionEvent event) {

    }

    @FXML
    void handleClickCancelar(ActionEvent event) {

    }

    @FXML
    void handleClickExcluirDaCesta(ActionEvent event) {

    }

    @FXML
    void handleClickCadastrarUsuario(ActionEvent event) {

    }

    @FXML
    void handleClickPesquisarUsuario(ActionEvent event) {

    }

    @FXML
    void handleClickResetLista(ActionEvent event) {

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
