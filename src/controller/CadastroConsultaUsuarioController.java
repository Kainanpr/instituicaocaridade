package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Permissao;
import model.Usuario;
import service.UsuarioService;

import java.io.IOException;

public class CadastroConsultaUsuarioController {
    private UsuarioService usuarioService = new UsuarioService();

    @FXML
    private Tab tabCadastrarUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtPesquisaUsuario;

    @FXML
    private TabPane paneUsuario;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Tab tabConsultarUsuario;

    @FXML
    private TextField txtCPF;

    @FXML
    private Button btnCadastrarUsuario;

    @FXML
    private Button btnPesquisar;

    @FXML
    private TextField txtLogin;

    @FXML
    private TableView<?> tableListaUsuario;

    @FXML
    private TextField txtNomeUsuario;

    @FXML
    private Button btnAtualizarUsuario;

    @FXML
    private TextField txtEnderecoUsuario;

    @FXML
    private Button btnExcluirUsuario;

    @FXML
    private Button btnVoltar;

    @FXML
    private AnchorPane paneConsultaUsuario;

    @FXML
    void handleClickCadastrarUsuario(ActionEvent event) {
        final String nome = txtNomeUsuario.getText();
        final String endereco = txtEnderecoUsuario.getText();
        final String cpf = txtCPF.getText();
        final String telefone = txtTelefone.getText();
        final String login = txtLogin.getText();
        final String senha = txtSenha.getText();

        final Usuario usuario = new Usuario(nome, endereco, cpf, telefone, login, senha, Permissao.ADMINISTRADOR);

        usuarioService.inserir(usuario);
    }

    @FXML
    void handleClickExcluirUsuario(ActionEvent event) {

    }

    @FXML
    void handleClickAtualizarUsuario(ActionEvent event) {

    }

    @FXML
    void handleClickPesquisarUsuario(ActionEvent event) {

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
