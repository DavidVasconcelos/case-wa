### Readme Case de entrevista

- Kotlin
- Spring boot
- Gradle

### Base de dados em memória

- url: http://localhost:8080/h2-console/login.do
- database: jdbc:h2:mem:testdb
- user: sa
- pwd:

### Desafio

- Realizar um crud do Usuário
    - Salvar
    - Atualizar
    - Recuperar o usuário pelo id
- Campos que devem ser persistidos:
    - Identificador
    - Nome
    - Documento
    - Data de criação
    - Data de atualização

### Docker 

```
$ docker image build -t wa/case .
```
```
$ docker container run -d --name case -p 8080:8080 wa/case
```

 ### Swagger
http://localhost:8080/swagger-ui/index.html
  