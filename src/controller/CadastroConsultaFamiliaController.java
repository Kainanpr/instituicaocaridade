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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Beneficiado;
import model.Usuario;
import service.BeneficiadoService;
import util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CadastroConsultaFamiliaController implements Initializable {
    private Beneficiado beneficiadoSelecionado;
    private BeneficiadoService beneficiadoService = new BeneficiadoService();
    private ObservableList<Beneficiado> obsBeneficiados;

    @FXML
    private TextField txtPesquisaBeneficiado;

    @FXML
    private TextField txtEndereco;

    @FXML
    private Tab tabConsultarBeneficiado;

    @FXML
    private TabPane paneBeneficiado;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private TextField txtProfissao;

    @FXML
    private TextField txtNumero;

    @FXML
    private Button btnAtualizarBeneficiado;

    @FXML
    private Button btnPesquisar;

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
    private Button btnResetLista;

    @FXML
    private TableView<Beneficiado> tableListaBeneficiado;

    @FXML
    private Tab tabHistoricoCestas;

    @FXML
    private TextField txtCidade;

    @FXML
    private Tab tabCadastrarBeneficiado;

    @FXML
    private TextField txtBairro;

    @FXML
    void handleClickSalvarBeneficiado(ActionEvent event) {
        final String nome = txtNomeBeneficiado.getText();
        final String proficao = txtProfissao.getText();
        final String telefone = txtTelefone.getText();
        final LocalDate dataNascimento = dataNasc.getValue();
        final String endereco = txtEndereco.getText();
        final int numero = Integer.parseInt(txtNumero.getText());
        final String bairro = txtBairro.getText();
        final String cidade = txtCidade.getText();
        final String descricao = txtDescricao.getText();

        final Beneficiado beneficiario = new Beneficiado(nome, proficao, telefone,
                dataNascimento, endereco, numero, bairro, cidade, descricao, LoginController.usuarioLogado);

        if (beneficiadoSelecionado == null) {
            beneficiadoService.inserir(beneficiario);
            Alerta.abrirAlert("Beneficiado cadastrado", "Beneficiado cadastrado com sucesso.", Alert.AlertType.INFORMATION);
        } else {
            beneficiario.setId(beneficiadoSelecionado.getId());
            beneficiadoService.atualizar(beneficiario);
            Alerta.abrirAlert("Beneficiado atualizado", "Beneficiado atualizado com sucesso.", Alert.AlertType.INFORMATION);
            beneficiadoSelecionado = null;
        }

        setPaneBeneficiado(new Beneficiado());
        atualizarLista(beneficiadoService.listar());
    }


    @FXML
    void handleClickExcluirBeneficiado(ActionEvent event) {
        beneficiadoSelecionado = tableListaBeneficiado.getSelectionModel().getSelectedItem();

        if (beneficiadoSelecionado == null) {
            Alerta.abrirAlert("Erro", "Selecione um beneficiado para excluir.", Alert.AlertType.ERROR);
        } else {
            Alert alert = Alerta.confirmarAlert("Deseja excluir o beneficiado?", "Você tem certeza que deseja excluir este beneficiado?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                beneficiadoService.deletar(beneficiadoSelecionado.getId());
                Alerta.abrirAlert("Beneficiado excluído", "Beneficiado excluído com sucesso.", Alert.AlertType.INFORMATION);
                beneficiadoSelecionado = null;
            }
        }

        atualizarLista(beneficiadoService.listar());
    }

    @FXML
    void handleClickAtualizarBeneficiado(ActionEvent event) {
        beneficiadoSelecionado = tableListaBeneficiado.getSelectionModel().getSelectedItem();

        if (beneficiadoSelecionado == null) {
            Alerta.abrirAlert("Erro", "Selecione um beneficiado para ser atualizado.", Alert.AlertType.ERROR);
        } else {
            paneBeneficiado.getSelectionModel().select(tabCadastrarBeneficiado);
            setPaneBeneficiado(beneficiadoSelecionado);
        }
    }

    @FXML
    void handleClickPesquisarBeneficiado(ActionEvent event) {
        final String nome = txtPesquisaBeneficiado.getText();

        List<Beneficiado> listBeneficiados = beneficiadoService.buscarPorNome(nome);

        atualizarLista(listBeneficiados);
    }

    @FXML
    void handleClickHistoricoBeneficiado(ActionEvent event) {

    }

    @FXML
    void handleClickResetLista(ActionEvent event) {
        txtPesquisaBeneficiado.setText("");
        atualizarLista(beneficiadoService.listar());
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

    private void setPaneBeneficiado(Beneficiado beneficiadoSelecionado) {
        txtNomeBeneficiado.setText(beneficiadoSelecionado.getNome());
        txtProfissao.setText(beneficiadoSelecionado.getProfissao());
        txtTelefone.setText(beneficiadoSelecionado.getTelefone());
        dataNasc.setValue(beneficiadoSelecionado.getDataNascimento());
        txtEndereco.setText(beneficiadoSelecionado.getEndereco());
        txtNumero.setText(String.valueOf(beneficiadoSelecionado.getNumero()));
        txtBairro.setText(beneficiadoSelecionado.getBairro());
        txtCidade.setText(beneficiadoSelecionado.getCidade());
        txtDescricao.setText(beneficiadoSelecionado.getDescricao());
    }

    private void configurarTableView() {
        obsBeneficiados = FXCollections.observableArrayList();

        TableColumn<Beneficiado, Integer> id = new TableColumn<>("Id");
        id.setMinWidth(50);

        TableColumn<Beneficiado, String> nome = new TableColumn<>("Nome");
        nome.setMinWidth(178);

        TableColumn<Beneficiado, String> profissao = new TableColumn<>("Profissão");
        profissao.setMinWidth(122);

        TableColumn<Beneficiado, String> telefone = new TableColumn<>("Telefone");
        telefone.setMinWidth(122);

        TableColumn<Beneficiado, LocalDate> dataNascimento = new TableColumn<>("Data de nascimento");
        dataNascimento.setMinWidth(122);

        TableColumn<Beneficiado, String> endereco = new TableColumn<>("Endereço");
        endereco.setMinWidth(122);

        TableColumn<Beneficiado, Integer> numero = new TableColumn<>("Número");
        numero.setMinWidth(122);

        TableColumn<Beneficiado, String> bairro = new TableColumn<>("Bairro");
        bairro.setMinWidth(122);

        TableColumn<Beneficiado, String> cidade = new TableColumn<>("Cidade");
        cidade.setMinWidth(122);

        TableColumn<Beneficiado, String> descricao = new TableColumn<>("Descrição");
        descricao.setMinWidth(122);

        TableColumn<Beneficiado, Usuario> usuario = new TableColumn<>("Usuario");
        usuario.setMinWidth(122);

        tableListaBeneficiado.getColumns().addAll(id, nome, profissao,
                telefone, dataNascimento, endereco, numero, bairro, cidade,
                descricao, usuario);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        profissao.setCellValueFactory(new PropertyValueFactory<>("profissao"));
        telefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        dataNascimento.setCellValueFactory(new PropertyValueFactory<>("dataNascimento"));
        endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        numero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        bairro.setCellValueFactory(new PropertyValueFactory<>("bairro"));
        cidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        descricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        tableListaBeneficiado.setItems(obsBeneficiados);
    }

    private void atualizarLista(List<Beneficiado> listaBeneficiados) {
        obsBeneficiados.clear();

        for (Beneficiado beneficiado : listaBeneficiados) {
            obsBeneficiados.add(beneficiado);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTableView();
        atualizarLista(beneficiadoService.listar());
    }
}
