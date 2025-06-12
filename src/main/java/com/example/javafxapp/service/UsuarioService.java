package com.example.javafxapp.service;

import com.example.javafxapp.model.Usuario;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.*;
import java.util.List;

public class UsuarioService {
    private static final String BASE_URL = "http://localhost:8080/api/usuarios";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Usuario> listar() throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(BASE_URL)).GET().build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(res.body(), new TypeReference<List<Usuario>>() {});
    }

    public void salvar(Usuario u) throws Exception {
        String json = mapper.writeValueAsString(u);
        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();
        client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    public void atualizar(Usuario u) throws Exception {
        String json = mapper.writeValueAsString(u);
        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/" + u.getId()))
            .header("Content-Type", "application/json")
            .PUT(HttpRequest.BodyPublishers.ofString(json))
            .build();
        client.send(req, HttpResponse.BodyHandlers.ofString());
    }

    public void deletar(Long id) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
            .uri(URI.create(BASE_URL + "/" + id))
            .DELETE()
            .build();
        client.send(req, HttpResponse.BodyHandlers.ofString());
    }
}
