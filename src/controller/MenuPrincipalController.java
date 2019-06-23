package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Permissao;
import util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MenuPrincipalController implements Initializable {

    @FXML
    private Button btnMenuFamilias;

    @FXML
    private Button btnSair;

    @FXML
    private Button btnMenuAlimento;

    @FXML
    private Button btnMenuUsuarios;

    @FXML
    void HandleClickNovoAlimento(ActionEvent event) {
        try {
            Pane menuPrincipal = FXMLLoader.load(getClass().getResource("../view/TelaCadastroConsultaAlimento.fxml"));
            // Fecha janela anterior
            ((Stage) btnMenuAlimento.getScene().getWindow()).hide();
            Scene scene = new Scene(menuPrincipal);
            Stage stage = new Stage();
            //Desabilita o redimensionamento
            stage.setResizable(false);
            stage.setTitle("Cadastro de Alimentos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void HandleClickNovoUsuario(ActionEvent event) {
        try {
            Pane menuPrincipal = FXMLLoader.load(getClass().getResource("../view/TelaCadastroConsultaUsuario.fxml"));
            // Fecha janela anterior
            ((Stage) btnMenuUsuarios.getScene().getWindow()).hide();
            Scene scene = new Scene(menuPrincipal);
            Stage stage = new Stage();
            //Desabilita o redimensionamento
            stage.setResizable(false);
            stage.setTitle("Cadastro de Usuários");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void HandleClickNovaFamilia(ActionEvent event) {
        try {
            Pane menuPrincipal = FXMLLoader.load(getClass().getResource("../view/TelaCadastroConsultaFamilia.fxml"));
            // Fecha janela anterior
            ((Stage) btnMenuFamilias.getScene().getWindow()).hide();
            Scene scene = new Scene(menuPrincipal);
            Stage stage = new Stage();
            //Desabilita o redimensionamento
            stage.setResizable(false);
            stage.setTitle("Cadastro de Familias");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void HandleClickSair(ActionEvent event) {
        Alert alert = Alerta.confirmarAlert("Deseja sair do sistema?", "Você tem certeza que deseja sair do sistema?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            try {
                LoginController.usuarioLogado = null;
                Pane telaLogin = FXMLLoader.load(getClass().getResource("../view/TelaLogin.fxml"));
                // Fecha janela anterior
                ((Stage) btnSair.getScene().getWindow()).hide();
                Scene scene = new Scene(telaLogin, 335, 461);
                Stage stage = new Stage();
                //Desabilita o redimensionamento
                stage.setResizable(false);
                stage.setTitle("Tela de Login");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (LoginController.usuarioLogado.getPermissao() == Permissao.DOADOR) {
            btnMenuUsuarios.setDisable(true);
            btnMenuFamilias.setDisable(true);
        }
    }
}
