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
    private PasswordField txtSenha;

    @FXML
    private TextField txtPesquisaUsuario;

    @FXML
    private Tab tabConsultaUsuario;

    @FXML
    private TabPane paneUsuario;

    @FXML
    private TextField txtTelefone;

    @FXML
    private Button btnPesquisa;

    @FXML
    private TextField txtCPF;

    @FXML
    private Button btnCadastrarUsuario;

    @FXML
    private TableView<?> tableListaUsuario;

    @FXML
    private Tab tabCadastraUsuario;

    @FXML
    private TextField txtNomeUsuario;

    @FXML
    private Button btnAtualizaUsuario;

    @FXML
    private TextField txtEnderecoUsuario;

    @FXML
    private Button btnExcluirUsuario;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtLogin;

    @FXML
    private AnchorPane paneConsultaUsuario;

    @FXML
    void handleClickCadastraUsuario(ActionEvent event) {
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
    void handleClickExcluiUsuario(ActionEvent event) {

    }

    @FXML
    void handleClickAtualizaUsuario(ActionEvent event) {

    }

    @FXML
    void handleClickPesquisarUsuario(ActionEvent event) {

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
