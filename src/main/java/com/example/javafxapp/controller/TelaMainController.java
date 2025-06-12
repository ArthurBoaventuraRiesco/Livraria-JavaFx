package com.example.javafxapp.controller;

import com.example.javafxapp.model.Usuario;
import com.example.javafxapp.service.UsuarioService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TelaMainController {

    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, Long> colunaId;
    @FXML private TableColumn<Usuario, String> colunaNome;
    @FXML private TableColumn<Usuario, String> colunaEmail;
    @FXML private TextField campoNome;
    @FXML private TextField campoEmail;

    private final UsuarioService service = new UsuarioService();
    private final ObservableList<Usuario> usuarios = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colunaId.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        colunaNome.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNome()));
        colunaEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        tabelaUsuarios.setItems(usuarios);
        carregarUsuarios();
    }

    public void carregarUsuarios() {
        try {
            usuarios.setAll(service.listar());
        } catch (Exception e) {
            mostrarErro("Erro ao carregar usuários: " + e.getMessage());
        }
    }

    public void salvarUsuario() {
        try {
            Usuario u = new Usuario(null, campoNome.getText(), campoEmail.getText());
            service.salvar(u);
            carregarUsuarios();
        } catch (Exception e) {
            mostrarErro("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public void atualizarUsuario() {
        Usuario selecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                selecionado.setNome(campoNome.getText());
                selecionado.setEmail(campoEmail.getText());
                service.atualizar(selecionado);
                carregarUsuarios();
            } catch (Exception e) {
                mostrarErro("Erro ao atualizar usuário: " + e.getMessage());
            }
        }
    }

    public void deletarUsuario() {
        Usuario selecionado = tabelaUsuarios.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            try {
                service.deletar(selecionado.getId());
                carregarUsuarios();
            } catch (Exception e) {
                mostrarErro("Erro ao deletar usuário: " + e.getMessage());
            }
        }
    }

    private void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Erro");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
