# StudyFlow Backend

Backend academico do sistema StudyFlow, desenvolvido com Java 17 e Spring Boot para demonstrar API REST, MVC em camadas, JPA, Swagger/OpenAPI, DTOs, services, repositories e tratamento basico de excecoes.

## Integrantes

- Arthur Azevedo Costa de Paula
- Dandalia Luiza da Silva Teixeira
- Jose Heitor Felix Guimaraes
- Victoria Maria Beltrao de Andrade

## Estrutura inicial

```text
studyflow-backend/
├── pom.xml
├── README.md
├── .env.example
└── src/
    ├── main/
    │   ├── java/com/studyflow/
    │   │   ├── StudyFlowApplication.java
    │   │   ├── config/
    │   │   ├── controller/
    │   │   ├── dto/
    │   │   ├── entity/
    │   │   ├── exception/
    │   │   ├── repository/
    │   │   └── service/
    │   │       ├── factory/
    │   │       └── strategy/
    │   └── resources/application.properties
    └── test/java/com/studyflow/
```

## Funcao das camadas

- `controller`: recebe requisicoes HTTP e retorna respostas JSON.
- `service`: concentra regras de negocio e coordenacao dos casos de uso.
- `repository`: interfaces Spring Data JPA para acesso ao banco.
- `entity`: entidades JPA mapeadas para tabelas do PostgreSQL/Supabase.
- `dto`: objetos para entrada e saida de dados da API, separando contrato externo das entidades.
- `config`: configuracoes globais da aplicacao, incluindo Swagger/OpenAPI.
- `exception`: excecoes e tratamento padronizado de erros.
- `service/factory`: espaco reservado para o Factory Method relacionado a criacao de tarefas.
- `service/strategy`: espaco reservado para Strategy, especialmente regras de ordenacao/priorizacao de tarefas.

## Entidades planejadas

- `Estudante`
- `Materia`
- `Categoria`
- `Tarefa`
- `EstudanteMateria`

Nesta primeira etapa, as entidades e repositories ainda nao foram criados para permitir que outros membros do grupo facam commits proprios com essas partes.

## Banco de dados

O Supabase sera utilizado como PostgreSQL. Configure as variaveis de ambiente:

```bash
SUPABASE_DB_URL=jdbc:postgresql://SEU_HOST_SUPABASE:5432/postgres?sslmode=require
SUPABASE_DB_USERNAME=postgres
SUPABASE_DB_PASSWORD=sua_senha
```

## Execucao

```bash
mvn spring-boot:run
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

## Plano sugerido de commits

1. Commit inicial: estrutura, Maven, configuracao e README.
2. Membro 1: `Estudante` + `EstudanteRepository`.
3. Membro 2: `Materia` + `MateriaRepository`.
4. Membro 3: `Categoria` + `CategoriaRepository`.
5. Membro 4: `Tarefa`, `EstudanteMateria` e respectivos repositories.

Depois desses commits, o grupo pode completar services, controllers, DTOs, excecoes e implementacoes dos padroes Singleton, Factory Method e Strategy.
