package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Permissao;
import model.Usuario;
import service.UsuarioService;
import util.Alerta;

import java.io.IOException;

public class LoginController {
    protected static Usuario usuarioLogado;
    private UsuarioService usuarioService = new UsuarioService();

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private Button btnLogin;

    @FXML
    void HandleClickLogin(ActionEvent event) {
        final String login = txtLogin.getText();
        final String senha = txtSenha.getText();

        usuarioLogado = usuarioService.verificarAcesso(login, senha);

        if (usuarioLogado == null) {
            if (login.equals("admin") && senha.equals("admin")) {
                final Usuario usuario = new Usuario("Administrador", "", "", "", "admin", "admin", Permissao.ADMINISTRADOR);
                usuarioLogado = usuarioService.inserir(usuario);
                Alerta.abrirAlert("Primeiro acesso", "Usuário administrador cadastrado com sucesso.", Alert.AlertType.INFORMATION);
                abrirTelaMenuPrincipal();
            } else {
                Alerta.abrirAlert("Erro", "Usuário ou senha inválidos.", Alert.AlertType.ERROR);
            }
        } else {
            abrirTelaMenuPrincipal();
        }
    }

    private void abrirTelaMenuPrincipal() {
        try {
            Pane menuPrincipal = FXMLLoader.load(getClass().getResource("../view/TelaMenuPrincipal.fxml"));
            // Fecha janela anterior
            ((Stage) btnLogin.getScene().getWindow()).hide();
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
