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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Alimento;
import model.Beneficiado;
import model.Cesta;
import model.ItemCesta;
import org.controlsfx.control.textfield.TextFields;
import service.AlimentoService;
import service.BeneficiadoService;
import util.Alerta;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MontarCestaController implements Initializable {
    private Cesta cesta = new Cesta();
    private ItemCesta itemCestaSelecionado;
    private List<ItemCesta> listaItemCesta = new ArrayList<>();
    private AlimentoService alimentoService = new AlimentoService();
    private BeneficiadoService beneficiadoService = new BeneficiadoService();
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
            final Integer quantidade = txtQuantidade.getText().isEmpty() ? 1 : Integer.parseInt(txtQuantidade.getText());


            if (quantidade <= alimentoAdicionar.getQtdEstoque() && quantidade > 0) {
                itemCestaAdicionar.setQuantidade(quantidade);
                listaItemCesta.add(itemCestaAdicionar);
                atualizarListaItemCesta(listaItemCesta);
                resetPaneAlimentos();
            } else {
                Alerta.abrirAlert("Erro", "Quantidade indisponível.", Alert.AlertType.ERROR);
            }
        } else {
            Alerta.abrirAlert("Erro", "Selecione um alimento.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void handleClickCancelar(ActionEvent event) {
        resetPaneAlimentos();
    }

    @FXML
    void handleClickExcluirDaCesta(ActionEvent event) {
        itemCestaSelecionado = tableItemCesta.getSelectionModel().getSelectedItem();

        if (itemCestaSelecionado == null) {
            Alerta.abrirAlert("Erro", "Selecione um item para excluir.", Alert.AlertType.ERROR);
        } else {
            Alert alert = Alerta.confirmarAlert("Deseja excluir o item?", "Você tem certeza que deseja excluir este item?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                listaItemCesta.remove(itemCestaSelecionado);
                resetPaneAlimentos();
            }
        }

        atualizarListaItemCesta(listaItemCesta);
    }

    @FXML
    void handleClickLinha(MouseEvent event) {
        itemCestaSelecionado = tableItemCesta.getSelectionModel().getSelectedItem();

        if (itemCestaSelecionado != null) {
            final Alimento alimentoSelecionado = itemCestaSelecionado.getAlimento();
            final String quantidade = String.valueOf(itemCestaSelecionado.getQuantidade());

            setPaneAlimentos(alimentoSelecionado, alimentoSelecionado.getNomeAlimento(), quantidade);
        }
    }

    @FXML
    void handleClickSalvarCesta(ActionEvent event) {
        final LocalDate dataDoacao = dataEntrega.getValue();

        cesta.setDataDoacao(dataDoacao);

        if (listaItemCesta.size() == 0) {
            Alerta.abrirAlert("Erro", "Adicione um alimento para montar a cesta.", Alert.AlertType.ERROR);
        } else if (cesta.getBeneficiado() == null){
            Alerta.abrirAlert("Erro", "Adicione um beneficiado para montar a cesta.", Alert.AlertType.ERROR);
        } else if (cesta.getDataDoacao() == null){
            Alerta.abrirAlert("Erro", "Adicione a data de entrega para montar a cesta.", Alert.AlertType.ERROR);
        } else {
            System.out.println("Salvando...");
        }
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
        tipo.setMinWidth(100);

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

    private void configurarAutoCompleteBeneciado() {
        final List<Beneficiado> listaBeneficiados = beneficiadoService.listar();
        final List<String> nomesBeneficiados = new ArrayList<>();

        for (Beneficiado beneficiado : listaBeneficiados) {
            nomesBeneficiados.add(beneficiado.getNome());
        }

        TextFields.bindAutoCompletion(txtNomeBeneficiado, nomesBeneficiados);
    }

    private void handleChangeNomeAlimento(String txtNomeAlimento) {
        Alimento alimentoAdicionar = alimentoService.buscarPorNome(txtNomeAlimento);

        if (alimentoAdicionar != null) {
            itemCestaAdicionar.setAlimento(alimentoAdicionar);
            setPaneAlimentos(alimentoAdicionar, txtNomeAlimento, "");
        } else {
            setPaneAlimentos(new Alimento(), txtNomeAlimento, "");
        }
    }

    private void handleChangeNomeBeneficiado(String txtNomeBeneficiado) {
        Beneficiado beneficiadoAdicionar = beneficiadoService.buscarPorNomeCompleto(txtNomeBeneficiado);

        if (beneficiadoAdicionar != null) {
            cesta.setBeneficiado(beneficiadoAdicionar);
        }

        setPaneBeneficiado(txtNomeBeneficiado);
    }

    private void setPaneAlimentos(Alimento alimento, String newTxtNomeAlimento, String quantidade) {
        txtNomeAlimento.setText(newTxtNomeAlimento);
        txtQuantidade.setText(quantidade);
        txtQtdEstoque.setText(String.valueOf(alimento.getQtdEstoque()));
        dataValidade.setValue(alimento.getDataValidade());
    }

    private void setPaneBeneficiado(String nome) {
        txtNomeBeneficiado.setText(nome);
    }

    private void atualizarListaItemCesta(List<ItemCesta> listaItemCesta) {
        obsItemCesta.clear();

        for (ItemCesta itemCesta : listaItemCesta) {
            obsItemCesta.add(itemCesta);
        }
    }

    private void resetPaneAlimentos() {
        setPaneAlimentos(new Alimento(), "", "");
        itemCestaAdicionar = new ItemCesta();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTableViewAdicionados();
        configurarAutoCompleteAlimentos();
        configurarAutoCompleteBeneciado();

        txtNomeAlimento.textProperty().addListener((obs, oldText, newText) -> {
            handleChangeNomeAlimento(newText);
        });

        txtNomeBeneficiado.textProperty().addListener((obs, oldText, newText) -> {
            handleChangeNomeBeneficiado(newText);
        });
    }
}
