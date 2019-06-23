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
import model.Alimento;
import model.Beneficiado;
import model.Cesta;
import model.ItemCesta;
import org.controlsfx.control.textfield.TextFields;
import service.AlimentoService;
import util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MontarCestaController implements Initializable {
    private List<ItemCesta> listaItemCesta = new ArrayList<>();
    private AlimentoService alimentoService = new AlimentoService();
    private ItemCesta itemCestaAdicionar = new ItemCesta();
    private ObservableList<ItemCesta> obsItemCesta;

    @FXML
    private TableView<?> tableListaCesta;

    @FXML
    private TableView<ItemCesta> tableItemCesta;

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
    private Button btnPesquisar;

    @FXML
    private AnchorPane paneConsultaCesta;

    @FXML
    private Button btnResetLista;

    @FXML
    private TextField txtNomeBeneficiado;

    @FXML
    private Tab tabConsultarCesta;

    @FXML
    private Button btnVoltar;

    @FXML
    private DatePicker dataEntrega;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnSalvarCesta;

    @FXML
    private TextField txtQtdEstoque;

    @FXML
    private DatePicker dataValidade;

    @FXML
    private TabPane paneCesta;

    @FXML
    private TextField txtPesquisaCestaPorBeneficiado;

    @FXML
    void handleClickAdicionarNaCesta(ActionEvent event) {
        Alimento alimentoAdicionar = itemCestaAdicionar.getAlimento();

        if (alimentoAdicionar != null) {
            final Integer quantidade = txtQuantidade.getText() != "" ? Integer.parseInt(txtQuantidade.getText()) : 1;


            if (quantidade <= alimentoAdicionar.getQtdEstoque() && quantidade > 0) {
                itemCestaAdicionar.setQuantidade(quantidade);
                listaItemCesta.add(itemCestaAdicionar);
                atualizarListaItemCesta(listaItemCesta);
                setPaneAlimentos(new Alimento(), "");
                itemCestaAdicionar = new ItemCesta();
            } else {
                Alerta.abrirAlert("Erro", "Quantidade indisponível.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    void handleClickCancelar(ActionEvent event) {

    }

    @FXML
    void handleClickExcluirDaCesta(ActionEvent event) {

    }

    @FXML
    void handleClickSalvarCesta(ActionEvent event) {

    }

    @FXML
    void handleClickPesquisarBeneficiado(ActionEvent event) {

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

    private void configurarTableViewAdicionados() {
        obsItemCesta = FXCollections.observableArrayList();

        TableColumn<ItemCesta, Alimento> nome = new TableColumn<>("Nome");
        nome.setMinWidth(140);

        TableColumn<ItemCesta, String> tipo = new TableColumn<>("Quantidade");
        tipo.setMinWidth(50);

        tableItemCesta.getColumns().addAll(nome, tipo);

        nome.setCellValueFactory(new PropertyValueFactory<>("alimento"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        tableItemCesta.setItems(obsItemCesta);
    }

    private void configurarAutoCompleteAlimentos() {
        final List<Alimento> listaAlimentos = alimentoService.listar();
        final List<String> nomesAlimentos = new ArrayList<>();

        for (Alimento alimento : listaAlimentos) {
            nomesAlimentos.add(alimento.getNomeAlimento());
        }

        TextFields.bindAutoCompletion(txtNomeAlimento, nomesAlimentos);
    }

    private void handleChangeNomeAlimento(String txtNomeAlimento) {
        Alimento alimentoAdicionar = alimentoService.buscarPorNome(txtNomeAlimento);

        if (alimentoAdicionar != null) {
            itemCestaAdicionar.setAlimento(alimentoAdicionar);
            setPaneAlimentos(alimentoAdicionar, txtNomeAlimento);
        } else {
            setPaneAlimentos(new Alimento(), txtNomeAlimento);
        }
    }

    private void setPaneAlimentos(Alimento alimento, String newTxtNomeAlimento) {
        txtNomeAlimento.setText(newTxtNomeAlimento);
        txtQuantidade.setText("");
        txtQtdEstoque.setText(String.valueOf(alimento.getQtdEstoque()));
        dataValidade.setValue(alimento.getDataValidade());
    }

    private void atualizarListaItemCesta(List<ItemCesta> listaItemCesta) {
        obsItemCesta.clear();

        for (ItemCesta itemCesta : listaItemCesta) {
            obsItemCesta.add(itemCesta);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTableViewAdicionados();
        configurarAutoCompleteAlimentos();

        txtNomeAlimento.textProperty().addListener((obs, oldText, newText) -> {
            handleChangeNomeAlimento(newText);
        });
    }
}
