# 📊 SonarQube SGM

Bem-vindo ao projeto **SonarQube SGM**! Este projeto é um microserviço desenvolvido em Java utilizando Spring Boot, que interage com as APIs do SonarQube para gerenciar métricas e quality gates.

## 📋 Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [Endpoints](#endpoints)
- [Contribuição](#contribuição)
- [Licença](#licença)

## 📖 Sobre o Projeto

O **SonarQube SGM** foi desenvolvido para ajudar o time de TestOps a gerenciar métricas e quality gates de seus projetos utilizando o SonarQube. O sistema permite recuperar históricos de projetos, realocar projetos em novos gates e ajustar gates de repositórios que não alcançam as métricas estabelecidas.

## ✨ Funcionalidades

- **Recuperar Histórico de Projetos**: Utilize a API do SonarQube para recuperar o histórico de todos os projetos e armazenar em um banco de dados.
- **Realocar Projetos em Novos Gates**: Identifique projetos do gate atual e realoque-os nos novos gates com base nas métricas identificadas.
- **Ajustar Gates de Repositórios**: Altere o gate de um repositório que não alcance as métricas estabelecidas para um gate de menor qualidade.

## 🛠️ Pré-requisitos

- Java 11 ou superior
- Maven 3.6.3 ou superior
- SonarQube Server
- Banco de dados H2 (ou outro banco de dados de sua escolha)

## 🚀 Instalação

1. Clone o repositório:
    ```sh
    git clone https://github.com/seu-usuario/sonarqube-sgm.git
    cd sonarqube-sgm
    ```

2. Compile o projeto usando Maven:
    ```sh
    mvn clean install
    ```

## ⚙️ Configuração

1. Configure as propriedades do SonarQube no arquivo `application.yml`:
    ```yaml
    sonarqube:
      url: http://seu-servidor-sonarqube:9000
      token: seu_token_de_acesso
    ```

2. Configure a porta do servidor (opcional):
    ```yaml
    server:
      port: 8080
    ```

## 📚 Uso

Inicie a aplicação:
```sh
mvn spring-boot:run