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
import model.Alimento;
import model.Usuario;
import service.AlimentoService;
import util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CadastroConsultaAlimentoController implements Initializable {
    private Alimento alimentoSelecionado;
    private AlimentoService alimentoService = new AlimentoService();
    private ObservableList<Alimento> obsAlimentos;
    private ObservableList<Alimento> obsAlimentosVencidos;
    private ObservableList<Alimento> obsAlimentosPorTipo;

    @FXML
    private TableView<Alimento> tableAlimento;

    @FXML
    private TableView<Alimento> tableEstoqueAlimentos;

    @FXML
    private Button btnAdicionarAlimento;

    @FXML
    private TextField txtNomeAlimento;

    @FXML
    private TextField txtTipo;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private Tab tabEstoque;

    @FXML
    private TableView<Alimento> tableValidade;

    @FXML
    private TextField txtTipoPesquisar;

    @FXML
    private DatePicker dataValidade;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnPesquisarEstoque;

    @FXML
    private Button btnResetLista;

    @FXML
    private Tab tabVencidos;

    @FXML
    private Tab tabSalvarAlimento;

    @FXML
    void handleClickSalvarAlimento(ActionEvent event) {
        final String nome = txtNomeAlimento.getText();
        final LocalDate dataValidadeAlimento = dataValidade.getValue();
        final int qtdEstoque = Integer.parseInt(txtQuantidade.getText());
        final String tipo = txtTipo.getText();

        final Alimento alimento = new Alimento(nome, dataValidadeAlimento, qtdEstoque, tipo, LoginController.usuarioLogado);

        alimentoService.inserir(alimento);
        Alerta.abrirAlert("Alimento cadastrado", "Alimento cadastrado com sucesso.", Alert.AlertType.INFORMATION);

        setPaneAlimento(new Alimento());
        atualizarListaCadastro(alimentoService.listar());
        atualizarListaVencidos(alimentoService.listarVencidos());
        atualizarListaPesquisaPorTipo(alimentoService.listar());
    }

    @FXML
    void handleClickPesquisarEstoque(ActionEvent event) {
        final String tipo = txtTipoPesquisar.getText();

        List<Alimento> listaAlimentos = alimentoService.buscarPorTipo(tipo);

        atualizarListaPesquisaPorTipo(listaAlimentos);
    }

    @FXML
    void handleClickResetLista(ActionEvent event) {
        txtTipoPesquisar.setText("");
        atualizarListaPesquisaPorTipo(alimentoService.listar());
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

    private void setPaneAlimento(Alimento alimento) {
        txtNomeAlimento.setText(alimento.getNomeAlimento());
        dataValidade.setValue(alimento.getDataValidade());
        txtQuantidade.setText(String.valueOf(alimento.getQtdEstoque()));
        txtTipo.setText(alimento.getTipo());
    }

    private void configurarTableViewCadastro() {
        obsAlimentos = FXCollections.observableArrayList();

        TableColumn<Alimento, Integer> id = new TableColumn<>("Id");
        id.setMinWidth(50);

        TableColumn<Alimento, String> nome = new TableColumn<>("Nome");
        nome.setMinWidth(178);

        TableColumn<Alimento, LocalDate> dataValidade = new TableColumn<>("Validade");
        dataValidade.setMinWidth(122);

        TableColumn<Alimento, String> tipo = new TableColumn<>("Tipo");
        tipo.setMinWidth(178);

        TableColumn<Alimento, Usuario> usuario = new TableColumn<>("Usuario");
        usuario.setMinWidth(122);

        tableAlimento.getColumns().addAll(id, nome, dataValidade, tipo, usuario);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nomeAlimento"));
        dataValidade.setCellValueFactory(new PropertyValueFactory<>("dataValidade"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        tableAlimento.setItems(obsAlimentos);
    }

    private void atualizarListaCadastro(List<Alimento> listAlimentos) {
        obsAlimentos.clear();

        for (Alimento alimento : listAlimentos) {
            obsAlimentos.add(alimento);
        }
    }

    private void configurarTableViewVencidos() {
        obsAlimentosVencidos = FXCollections.observableArrayList();

        TableColumn<Alimento, Integer> id = new TableColumn<>("Id");
        id.setMinWidth(50);

        TableColumn<Alimento, String> nome = new TableColumn<>("Nome");
        nome.setMinWidth(178);

        TableColumn<Alimento, LocalDate> dataValidade = new TableColumn<>("Validade");
        dataValidade.setMinWidth(122);

        TableColumn<Alimento, String> tipo = new TableColumn<>("Tipo");
        tipo.setMinWidth(178);

        TableColumn<Alimento, Usuario> usuario = new TableColumn<>("Usuario");
        usuario.setMinWidth(122);

        tableValidade.getColumns().addAll(id, nome, dataValidade, tipo, usuario);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nomeAlimento"));
        dataValidade.setCellValueFactory(new PropertyValueFactory<>("dataValidade"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        tableValidade.setItems(obsAlimentosVencidos);
    }

    private void atualizarListaVencidos(List<Alimento> listAlimentos) {
        obsAlimentosVencidos.clear();

        for (Alimento alimento : listAlimentos) {
            obsAlimentosVencidos.add(alimento);
        }
    }

    private void configurarTableViewPesquisaPorTipo() {
        obsAlimentosPorTipo = FXCollections.observableArrayList();

        TableColumn<Alimento, Integer> id = new TableColumn<>("Id");
        id.setMinWidth(50);

        TableColumn<Alimento, String> nome = new TableColumn<>("Nome");
        nome.setMinWidth(178);

        TableColumn<Alimento, LocalDate> qtdEstoque = new TableColumn<>("Estoque");
        qtdEstoque.setMinWidth(122);

        TableColumn<Alimento, String> tipo = new TableColumn<>("Tipo");
        tipo.setMinWidth(178);

        TableColumn<Alimento, LocalDate> dataValidade = new TableColumn<>("Validade");
        dataValidade.setMinWidth(122);

        TableColumn<Alimento, Usuario> usuario = new TableColumn<>("Usuario");
        usuario.setMinWidth(122);

        tableEstoqueAlimentos.getColumns().addAll(id, nome, qtdEstoque, tipo, dataValidade, usuario);

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome.setCellValueFactory(new PropertyValueFactory<>("nomeAlimento"));
        qtdEstoque.setCellValueFactory(new PropertyValueFactory<>("qtdEstoque"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        dataValidade.setCellValueFactory(new PropertyValueFactory<>("dataValidade"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));

        tableEstoqueAlimentos.setItems(obsAlimentosPorTipo);
    }

    private void atualizarListaPesquisaPorTipo(List<Alimento> listAlimentos) {
        obsAlimentosPorTipo.clear();

        for (Alimento alimento : listAlimentos) {
            obsAlimentosPorTipo.add(alimento);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTableViewCadastro();
        configurarTableViewVencidos();
        configurarTableViewPesquisaPorTipo();
        atualizarListaCadastro(alimentoService.listar());
        atualizarListaVencidos(alimentoService.listarVencidos());
        atualizarListaPesquisaPorTipo(alimentoService.listar());
    }
}
