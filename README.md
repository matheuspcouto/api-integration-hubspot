# API Integration HubSpot

## Descrição do Projeto

Este projeto é uma integração com a API do HubSpot, permitindo que os usuários interajam com os dados do HubSpot de forma simplificada. O projeto foi desenvolvido utilizando as seguintes tecnologias e frameworks:

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para construção de aplicações Java.
- **Maven**: Gerenciador de dependências e construção do projeto.
- **Swagger**: Documentação da API e interface para testes.

## 1 - Montagem e Configuração

Para configurar o projeto em sua máquina local, siga os passos abaixo:

### Requisitos

- Java JDK 17
- Maven
- IntelliJ IDEA ou outro IDE de sua preferência

### Clonando o Projeto

1. Abra o terminal.
2. Navegue até o diretório onde deseja clonar o projeto.
3. Execute o seguinte comando:

```bash
git clone https://github.com/matheuspcouto/api-integration-hubspot.git
```

### Importando o Projeto no IntelliJ

1. Abra o IntelliJ IDEA.
2. Selecione "Open" e navegue até o diretório onde você clonou o projeto.
3. Selecione a pasta do projeto e clique em "OK".
4. O IntelliJ irá detectar automaticamente o projeto Maven e baixar as dependências necessárias.

## 2 - Como Executar o Projeto

1. **Criar Conta no HubSpot**:
    - Acesse [HubSpot](https://www.hubspot.com/) e crie uma conta gratuita.
    - Após criar a conta, crie um aplicativo novo e defina os escopos necessários
    - Defina o `redirectUri` como http://localhost:8080/oauth-callback.
    - Obtenha as informações: `clientId`, `clientSecret`, `redirectUri` e `scope`.
    - Substitua essas informações no arquivo `application.yml` no diretório `src/main/resources`

   
2. **Configurar o Arquivo application.yml**:
    - Abra o arquivo `application.yml` no diretório `src/main/resources`.
    - Substitua os valores de `clientId`, `clientSecret` e `redirectUri` pelas informações obtidas anteriormente.


3. **Executar o Projeto**:
    - No IntelliJ, localize a classe principal no diretório `src/main/java/Application.java`.
    - Clique com o botão direito na classe e selecione "Run".


4. **Acessar a API**:
    - Após a execução, abra seu navegador e acesse a seguinte URL para visualizar a documentação da API:
   ```
   http://localhost:8080/swagger-ui/index.html
   ```

Agora você está pronto para interagir com a API do HubSpot através deste projeto!

## Links de Ajuda
- [Criar uma conta de desenvolvedor no HubSpot:](https://developers.hubspot.com)
