# Livraria-JavaFx
Aplicativo JavaFX que consome uma **API RESTful Spring Boot** criada no **Trabalho 1**, realizando operações de CRUD sobre usuários.

## Funcionalidades

- Listagem de usuários (GET)
- Cadastro de novos usuários (POST)
- Edição de usuários existentes (PUT)
- Exclusão de usuários (DELETE)
- Comunicação via JSON usando `HttpClient`
- Interface gráfica com JavaFX + FXML (Scene Builder)
- Código estruturado em camadas (modelo, serviço, controle)

## Tecnologias Utilizadas

- Java 21
- JavaFX 21
- Maven
- Spring Boot (para a API)
- Jackson (para JSON)
- Scene Builder (para a interface gráfica)

### Pré-requisitos

- Java 21 instalado
- IntelliJ IDEA (recomendado)
- Scene Builder (opcional para editar FXML)
- API REST rodando localmente em `http://localhost:8080`

## Estrutura do Projeto

```
javafx-client-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/javafxapp/
│   │   │   ├── MainApp.java
│   │   │   ├── model/Usuario.java
│   │   │   ├── service/UsuarioService.java
│   │   │   └── controller/TelaMainController.java
│   │   └── resources/com/example/javafxapp/fxml/
│   │       └── TelaMain.fxml
├── pom.xml
├── README.md
```

## Endpoints utilizados da API

- `GET /api/usuarios`
- `POST /api/usuarios`
- `PUT /api/usuarios/{id}`
- `DELETE /api/usuarios/{id}`

