# ScheduEase
Projeto desenvolvido como avaliação final do módulo de Spring Boot do curso +Dev2Blu 2023. A ScheduEase é um BackEnd de uma aplicação
para salão de beleza que permite que cada profissional do salão se cadastre e gerencie sua agenda de horas marcadas de seus
respectivos clientes.

# Diagrama de Classes

<p align="center">
   <img alt="digrama-de-classes" src="https://github.com/NeoJhonn/ScheduEase/blob/main/Documentation/Class_Diagram.png" 
  width="80%" border-radius= 10px>
</p>

# Requsitos Funcionais
Funcionário
- Cadastramento de funcionários.
- Exclusão de um cadastro de funcionário.
- Atualizar o cadastro de um funcionário.
- Listar funcionários cadastrados.
- Pesquisar um cadastro de funcionário pelo nome do mesmo.

Agenda
- Cadastramento de horários.
- Exclusão de um horário.
- Atualizar um horário
- Listar horários cadastrados.
- Pesquisar um horário pelo nome cliente agendado.
  
# Requsitos Não Funcionais
- Desenvolvido em Java.
- Spring Boot Framework.
- Cobertura mínima de código de 50%(testes unitários).
- Banco de Dados em Postgres.
- Documentação das API’s no Swagger.
- Maven para gerenciamento das dependências do projeto.
- Flyway para gerenciamento dos migrations do projeto.

# Regras de Negócio
- A aplicação deve permitir o agendamento de um horário para todo o ano das 8 às 22 horas.
- A aplicação deve listar os horários agendados e disponíveis do dia selecionado de seu respectivo funcionário/profissional.
