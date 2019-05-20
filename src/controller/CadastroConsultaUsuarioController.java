package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Permissao;
import model.Usuario;
import service.UsuarioService;
import util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CadastroConsultaUsuarioController implements Initializable {
    private Usuario usuarioSelecionado;
    private UsuarioService usuarioService = new UsuarioService();
    private ObservableList<Permissao> obsPermissao;
    private ObservableList<Usuario> obsUsuarios;

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
    private TableView<Usuario> tableListaUsuario;

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
    private Button btnResetLista;

    @FXML
    private AnchorPane paneConsultaUsuario;

    @FXML
    private ComboBox<Permissao> cbTiposUsuarios;

    @FXML
    void handleClickCadastrarUsuario(ActionEvent event) {
        final String nome = txtNomeUsuario.getText();
        final String endereco = txtEnderecoUsuario.getText();
        final String cpf = txtCPF.getText();
        final String telefone = txtTelefone.getText();
        final String login = txtLogin.getText();
        final String senha = txtSenha.getText();
        final Permissao tipoUsuario = cbTiposUsuarios.getValue();

        final Usuario usuario = new Usuario(nome, endereco, cpf, telefone, login, senha, tipoUsuario);

        if (usuarioSelecionado == null) {
            usuarioService.inserir(usuario);
            Alerta.abrirAlert("Usuário cadastrado", "Usuário cadastrado com sucesso.", Alert.AlertType.INFORMATION);
        } else {
            usuario.setId(usuarioSelecionado.getId());
            usuarioService.atualizar(usuario);
            Alerta.abrirAlert("Usuário atualizado", "Usuário atualizado com sucesso.", Alert.AlertType.INFORMATION);
            usuarioSelecionado = null;
        }

        setPaneUsuario(new Usuario());
        atualizarLista(usuarioService.listar());
    }

    @FXML
    void handleClickExcluirUsuario(ActionEvent event) {
        usuarioSelecionado = tableListaUsuario.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado == null) {
            Alerta.abrirAlert("Erro", "Selecione um usuário para excluir.", Alert.AlertType.ERROR);
        } else {
            Alert alert = Alerta.confirmarAlert("Deseja excluir o usuário?", "Você tem certeza que deseja excluir este Usuário?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                usuarioService.deletar(usuarioSelecionado.getId());
                Alerta.abrirAlert("Usuário excluído", "Usuário excluído com sucesso.", Alert.AlertType.INFORMATION);
                usuarioSelecionado = null;
            }
        }

        atualizarLista(usuarioService.listar());
    }

    @FXML
    void handleClickAtualizarUsuario(ActionEvent event) {
        usuarioSelecionado = tableListaUsuario.getSelectionModel().getSelectedItem();

        if (usuarioSelecionado == null) {
            Alerta.abrirAlert("Erro", "Selecione um usuário para ser atualizado.", Alert.AlertType.ERROR);
        } else {
            paneUsuario.getSelectionModel().select(tabCadastrarUsuario);
            setPaneUsuario(usuarioSelecionado);
        }
    }

    @FXML
    void handleClickPesquisarUsuario(ActionEvent event) {
        final String nome = txtPesquisaUsuario.getText();

        List<Usuario> listaUsuarios = usuarioService.buscarPorNome(nome);

        atualizarLista(listaUsuarios);
    }

    @FXML
    void handleClickResetLista(ActionEvent event) {
        txtPesquisaUsuario.setText("");
        atualizarLista(usuarioService.listar());
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

    private void setPaneUsuario(Usuario usuarioSelecionado) {
        txtNomeUsuario.setText(usuarioSelecionado.getNome());
        txtEnderecoUsuario.setText(usuarioSelecionado.getEndereco());
        txtCPF.setText(usuarioSelecionado.getCpf());
        txtTelefone.setText(usuarioSelecionado.getTelefone());
        txtLogin.setText(usuarioSelecionado.getLogin());
        txtSenha.setText(usuarioSelecionado.getSenha());
        cbTiposUsuarios.setValue(usuarioSelecionado.getPermissao());
    }

    private void carregarTiposUsuarios() {
        obsPermissao = FXCollections.observableArrayList(Permissao.values());
        cbTiposUsuarios.setItems(obsPermissao);
    }

    private void configurarTableView() {
        obsUsuarios = FXCollections.observableArrayList();

        TableColumn<Usuario, Integer> id = new TableColumn<>("Id");
        id.setMinWidth(50);

        TableColumn<Usuario, String> nome = new TableColumn<>("Nome");
        nome.setMinWidth(178);

        TableColumn<Usuario, String> cpf = new TableColumn<>("CPF");
        cpf.setMinWidth(122);

        TableColumn<Usuario, Permissao> tipoUsuario = new TableColumn<>("Tipo");
        cpf.setMinWidth(122);

        tableListaUsuario.getColumns().addAll(id, nome, cpf, tipoUsuario);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tipoUsuario.setCellValueFactory(new PropertyValueFactory<>("permissao"));

        tableListaUsuario.setItems(obsUsuarios);
    }

    private void atualizarLista(List<Usuario> listaUsuarios) {
        obsUsuarios.clear();

        for (Usuario usuario : listaUsuarios) {
            obsUsuarios.add(usuario);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarTiposUsuarios();
        configurarTableView();
        atualizarLista(usuarioService.listar());
    }
}
