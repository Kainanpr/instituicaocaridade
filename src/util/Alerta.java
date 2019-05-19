package util;

import javafx.scene.control.Alert;

public class Alerta {

    public static void abrirAlert(String titulo, String conteudo, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

    public static Alert confirmarAlert(String titulo, String conteudo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(conteudo);

        return alert;
    }
}
