Análise de Padrões de Projeto na Camada de Persistência do Sistema feedback.edu
Projeto: feedback.edu
Equipa: Felipe Pereira da Silva, Samuel Horta de Faria, Rikerson Antonio Freitas Silva
Data: 11 de setembro de 2025

1. Introdução
Este documento descreve os principais padrões de projeto (Design Patterns) aplicados na implementação da camada de persistência do sistema feedback.edu. A escolha destes padrões foi fundamental para criar uma arquitetura robusta, manutenível e desacoplada, seguindo as melhores práticas de engenharia de software.

Analisaremos dois padrões centrais que foram utilizados em conjunto, graças ao framework Spring Data JPA: o padrão Repository e o padrão DAO (Data Access Object).

2. Padrão Repository
2.1. Nome do Padrão
Repository

2.2. Trecho de Código Onde o Padrão é Aplicado
O padrão Repository é aplicado em todas as interfaces dentro do pacote com.feedbackedu.repository. Um exemplo claro é a interface UsuarioRepository.

Ficheiro: src/main/java/com/feedbackedu/repository/UsuarioRepository.java

package com.feedbackedu.repository;

import com.feedbackedu.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Spring Data JPA cria a query automaticamente pelo nome do método
    Optional<Usuario> findByMatricula(String matricula);
    Optional<Usuario> findByEmail(String email);
}

2.3. Explicação Sucinta do Padrão e Benefícios
O padrão Repository atua como uma "coleção" de objetos de domínio em memória. A sua principal função é mediar o acesso aos dados entre a camada de domínio (os nossos Services) e a camada de mapeamento de dados (o Hibernate/JPA), abstraindo a complexidade do armazenamento.

Como o padrão foi aplicado no contexto do projeto e quais benefícios ele trouxe:

Abstração e Desacoplamento: A camada de serviço (UsuarioService, por exemplo) não precisa de saber como os dados dos utilizadores são guardados. Ela não sabe se estamos a usar um banco de dados MySQL, PostgreSQL ou até mesmo um ficheiro de texto. Ela simplesmente pede ao UsuarioRepository: "dê-me o utilizador com esta matrícula" ou "guarde este novo utilizador". Isto resulta num baixo acoplamento, tornando o sistema muito mais flexível a futuras mudanças.

Linguagem do Domínio: O Repository permite-nos definir métodos de busca que usam a linguagem do nosso negócio, como findByMatricula(). Isto torna a camada de serviço mais limpa e legível, pois a sua lógica fica focada nas regras de negócio, e não em como construir consultas ao banco de dados.

Reusabilidade e Testabilidade: Ao centralizar o acesso aos dados num único ponto (o repositório), evitamos a duplicação de código de consulta. Além disso, torna-se muito mais fácil testar a camada de serviço, pois podemos "simular" (mock) o comportamento do repositório, testando a lógica de negócio de forma isolada, sem a necessidade de uma base de dados real.

3. Padrão DAO (Data Access Object)
3.1. Nome do Padrão
DAO (Data Access Object)

3.2. Trecho de Código Onde o Padrão é Aplicado
Embora muitas vezes usados como sinónimos, o padrão DAO é um conceito mais granular que o Repository. O DAO foca-se em abstrair o acesso a uma única fonte de dados (uma tabela, por exemplo). No contexto do Spring Data JPA, a implementação concreta que o Spring cria para as nossas interfaces de Repository é, na sua essência, um DAO.

O Spring Data JPA gera automaticamente uma implementação para a interface JpaRepository que atua como o nosso DAO. O código abaixo mostra como ele é utilizado pela camada de serviço, que interage com o DAO através da interface do Repository.

Ficheiro: src/main/java/com/feedbackedu/service/UsuarioService.java

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository; // Injeção do nosso DAO/Repository

    public Usuario criarNovoUsuario(UsuarioCreateDTO dto) {
        // ... (lógica de validação) ...

        // Utilização do método save() fornecido pelo DAO subjacente
        return usuarioRepository.save(novoUsuario); 
    }
}

3.3. Explicação Sucinta do Padrão e Benefícios
O padrão DAO tem como único objetivo fornecer uma interface abstrata para alguma fonte de dados, escondendo todos os detalhes de como os dados são obtidos e persistidos.

Como o padrão foi aplicado no contexto do projeto e quais benefícios ele trouxe:

Separação de Responsabilidades: O benefício mais claro é a separação de responsabilidades. A nossa lógica de negócio (UsuarioService) não está misturada com o código de persistência (comandos SQL, gestão de conexões com o JDBC, etc.). O DAO (implementado pelo Spring) cuida de toda a "conversa" de baixo nível com o banco de dados.

Transparência da Fonte de Dados: O DAO torna a fonte de dados transparente para o resto da aplicação. A camada de serviço não se importa se os dados vêm de um banco MySQL, Oracle ou de um serviço externo. Se precisássemos de mudar de MySQL para PostgreSQL, apenas a configuração da fonte de dados (application.properties) e, possivelmente, a implementação do DAO mudariam, sem qualquer impacto na lógica de negócio.

Simplificação do Código: Ao utilizar o Spring Data JPA, aproveitámos ao máximo este padrão sem precisar de escrever a implementação repetitiva (o "boilerplate code") de um DAO. Nós apenas definimos a interface do nosso Repository, e o Spring gerou um DAO poderoso e otimizado para nós, com todas as operações CRUD já implementadas.