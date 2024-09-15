# Sistema de Reserva de Salas

## 📚 Descrição do Projeto

O **Sistema de Reserva de Salas** é uma aplicação desenvolvida em **Spring Boot** para o gerenciamento de salas de reunião. A API permite criar, listar, e gerenciar salas de reunião e suas reservas. Ideal para ambientes corporativos ou eventos que requerem uma solução prática e eficiente para reserva de espaços.

### 🎯 Funcionalidades

1. **Gerenciamento de Salas de Reunião**
   - **Criar Sala de Reunião:** Adicione novas salas ao sistema.
   - **Listar Salas de Reunião:** Visualize todas as salas disponíveis.
   - **Buscar Sala por ID:** Consulte uma sala específica pelo seu ID.

2. **Gerenciamento de Reservas**
   - **Criar Reserva:** Faça uma reserva para uma sala de reunião.
   - **Listar Reservas:** Veja todas as reservas existentes.
   - **Consultar Reservas Entre Datas:** Filtre reservas com base em um intervalo de datas.
   - **Atualizar Reserva:** Modifique detalhes de uma reserva existente.
   - **Cancelar Reserva:** Exclua uma reserva existente.

3. **Consultas Avançadas**
   - **Listar Reservas Ordenadas:** Ordene as reservas por data e hora.
   - **Buscar Reserva por Data:** Consulte reservas para uma data específica.

## 🚀 Tecnologias Utilizadas

- **Spring Boot:** Framework principal para desenvolvimento da API REST.
- **Java 17:** Linguagem de programação utilizada.
- **JUnit 5:** Para criação e execução de testes.
- **Postman:** Ferramenta para teste dos endpoints da API.

## 📋 Endpoints

### 1. **Criar Sala de Reunião**

- **Método:** `POST`
- **URL:** `/fiap/salas`
- **Request Body:**

    ```json
    {
        "tipo": "Sala de Conferência"
    }
    ```

- **Respostas:**
  - **201 Created:** Sala criada com sucesso.
  - **400 Bad Request:** Dados inválidos.

### 2. **Listar Salas de Reunião**

- **Método:** `GET`
- **URL:** `/fiap/salas`
- **Respostas:**
  - **200 OK:** Lista de salas com sucesso.
  - **404 Not Found:** Nenhuma sala encontrada.

### 3. **Buscar Sala por ID**

- **Método:** `GET`
- **URL:** `/fiap/salas/{id}`
- **Respostas:**
  - **200 OK:** Sala encontrada com sucesso.
  - **404 Not Found:** Sala não encontrada.

### 4. **Criar Reserva**

- **Método:** `POST`
- **URL:** `/fiap/salas/{id}/reservas`
- **Request Body:**

    ```json
    {
        "dataReuniao": "2024-09-20 14:00",
        "participantes": ["user1@example.com", "user2@example.com"]
    }
    ```

- **Respostas:**
  - **200 OK:** Reserva criada com sucesso.
  - **400 Bad Request:** Dados inválidos.
  - **409 Conflict:** Conflito na reserva.

### 5. **Listar Reservas**

- **Método:** `GET`
- **URL:** `/fiap/salas/{id}/reservas`
- **Parâmetros de Query:**
  - `tipo` (opcional): "ordenado" para listar reservas ordenadas.
  - `dataInicio` (opcional): Data de início no formato `yyyy-MM-dd HH:mm`.
  - `dataFim` (opcional): Data de fim no formato `yyyy-MM-dd HH:mm`.
- **Respostas:**
  - **200 OK:** Lista de reservas com sucesso.
  - **404 Not Found:** Sala não encontrada.

### 6. **Consultar Reserva por Data**

- **Método:** `GET`
- **URL:** `/fiap/salas/{id}/reservas/{dataReserva}`
- **Respostas:**
  - **200 OK:** Reserva encontrada com sucesso.
  - **404 Not Found:** Reserva não encontrada.

### 7. **Atualizar Reserva**

- **Método:** `PUT`
- **URL:** `/fiap/salas/{id}/reservas/{dataReserva}`
- **Request Body:**

    ```json
    {
        "dataReuniao": "2024-09-20 15:00",
        "participantes": ["user1@example.com", "user3@example.com"]
    }
    ```

- **Respostas:**
  - **200 OK:** Reserva atualizada com sucesso.
  - **404 Not Found:** Reserva não encontrada.

### 8. **Cancelar Reserva**

- **Método:** `DELETE`
- **URL:** `/fiap/salas/{id}/reservas/{dataReserva}`
- **Respostas:**
  - **200 OK:** Reserva cancelada com sucesso.
  - **404 Not Found:** Reserva não encontrada.

## 🔧 Execução da Aplicação

1. **Clone o Repositório:**

    ```bash
    git clone https://github.com/seuusuario/ApiReservadeSalas
    ```

2. **Navegue até o Diretório do Projeto:**

    ```bash
    cd sistema-reserva-salas
    ```

3. **Compile e Execute a Aplicação:**

    ```bash
    ./mvnw spring-boot:run
    ```

4. **Teste a API com Postman:**
   - Utilize o Postman para enviar requisições aos endpoints e verificar o funcionamento da API.

## 💡 Contribuição

Contribuições são bem-vindas! Para contribuir:

1. Fork o repositório.
2. Crie uma nova branch (`git checkout -b feature/nova-funcionalidade`).
3. Faça suas alterações e commit (`git commit -am 'Adiciona nova funcionalidade'`).
4. Push para a branch (`git push origin feature/nova-funcionalidade`).
5. Abra um Pull Request.

