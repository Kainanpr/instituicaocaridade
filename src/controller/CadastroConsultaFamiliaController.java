package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CadastroConsultaFamiliaController {

    @FXML
    private TextField txtPesquisaBeneficiado;

    @FXML
    private TextField txtEndereco;

    @FXML
    private TabPane paneBeneficiado;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Button btnPesquisa;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextField txtProfissao;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtNomeBeneficiado;

    @FXML
    private DatePicker dataNasc;

    @FXML
    private Button btnSalvarBeneficiado;

    @FXML
    private Button btnExcluirBeneficiado;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableView<?> tableListaBeneficiado;

    @FXML
    private TextField txtCidade;

    @FXML
    private Button btnAtualizaBeneficiado;

    @FXML
    private Tab tabCadastraBeneficiado;

    @FXML
    private Button btnHistoricoBeneficiado;

    @FXML
    private TextField txtBairro;

    @FXML
    private Tab tabConsultaBeneficiado;


    @FXML
    void handleClickSalvarBeneficiado(ActionEvent event) {

    }


    @FXML
    void handleClickExcluiBeneficiado(ActionEvent event) {

    }

    @FXML
    void handleClickAtualizaBeneficiado(ActionEvent event) {

    }

    @FXML
    void handleClickPesquisaBeneficiado(ActionEvent event) {

    }

    @FXML
    void handleClickHistoricoBeneficiado(ActionEvent event) {

    }

    @FXML
    void handleClickVoltaMenu(ActionEvent event) {
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
