# Introdução ao Clean Architecture

## Necessidade:
Com o aumento do code base dos projetos, da complexidade e das equipes de trabalho, fica cada vez mais difícil recomendar a utilização de monólitos: blocos massivos de código com alto nível de dependência interna e baixa manutenibilidade. Isto ocorre devido a uma característica evidenciada nos modelos de arquitetura mais modernos (clean, hexagonal, dci, bce), a segregação das responsabilidades do código através de camadas.

## Conceito:
O Clean Architecture, proposto por Robert C. Martin, trata-se de um modelo arquitetural de separação de camadas/preocupações. Possuindo, ao menos, uma camada para regras de negócio, interfaces de usuário e sistema. Na qual, evidenciam-se algumas características:

- Independência de linguagem: a arquitetura não depende da linguagem, bem como a plataforma, isto é, a arquitetura pode ser aplicada independente de linguagem, seja ela voltada para backend, frontend, etc.
- Independência de frameworks: a arquitetura não depende da existência de nenhuma biblioteca de software carregada de recursos.
- Testabilidade: as regras de negócio podem ser testadas sem a UI, banco de dados ou qualquer elemento externo.
- Independência da UI: a UI pode mudar facilmente, sem alterar o restante do sistema.
- Independência do Banco de Dados: a solução pode mudar o banco de dados, independente do seu tipo, visto o banco de dados e principalmente esta camada, não carregam a lógica de negócio.
- Independência externa: as regras de negócio não sabem nada sobre as interfaces externas.

Com base nessas características, é interessante salientar que basicamente qualquer camada pode ser substituída sem maiores complicações. No qual, aplica-se como base a centralização no domínio, ou seja, tudo o que não é, ou faz parte, do domínio da aplicação, são considerados **detalhes**. O foco é separar e priorizar as regras de negócio.

## Conhecendo as Camadas:
É proposto um modelo de 4 camadas originalmente, sendo elas: entidades, casos de uso, adaptadores de interface e interfaces externa. Contudo, não existe uma regra que empeça uma divisão ainda maior das responsabilidades, desde de que o fluxo de dependência seja respeitado, no qual camadas externas dependem das camadas internas, e não o contrário, sob hipótese alguma.

### Entidades
As entidades reúnem as regras de negócio mais cruciais e de nível mais alto do projeto. São as representações dos objetos de negócio e dos relacionamentos. Possui grande capacidade de reutilização pelas aplicações, não possuindo dependência alguma com as demais camadas, bem como nenhuma mudança operacional deve a influenciar.

### Casos de Uso
Os casos de uso representam as regras de negócio da aplicação. São os orquestradores do fluxos de dados, para e a partir das entidades. Reúnem e implementam todos os casos de uso do sistema, garantido que a regras de negócio sejam respeitadas.

### Adaptadores de Interface
Os adaptadores de interface, também conhecidos como mappers ou controllers, realizam a ponte entre as entidades e suas representações, no nível da aplicação. São responsáveis por realizarem as conversões dos dados nos formatos mais convenientes para os casos de uso, entidades, banco de dados ou agentes externos.

### Interfaces Externas
As interfaces externas compreendem os frameworks, drivers, banco de dados, etc. Fazem parte da camada mais externa do modelo de arquitetura, sendo esta a mais sujeita a mudanças. Concentram os recursos mais específicos da solução, sejam eles aplicações, serviços ou interfaces gráficas. Nenhum problema nesta camada influencia as camadas anteriores (interiores).

![A Arquitetura Limpa](https://miro.medium.com/max/700/1*qE2iD96K6zlACY6m8BqtqQ.png "A arquitetura limpa")

## Fluxo de dependência:

**Características:** 

- Os círculos mais externos são mecanismos, os mais internos são políticas.
- As dependências de código apontam apenas para dentro, nas direção das regras de negócio.
- Camadas internas não podem saber nada sobre os elementos mais externos.
- Os formatos de dados declarados em um círculo externo não devem ser usados em um círculo interno, especialmente se esses formatos forem gerados por um framework.
- De forma alguma um elemento do círculo externo pode ter impacto sobre os círculos internos.

## Comunicação entre as camadas:

A comunicação entre a camada de domínio e as camadas externas, como ui e banco de dados, ocorre através de um modelo interface-implementação, no qual o domínio expõe um contrato (interface) contendo um conjunto de regras que definem como a classe implementante deverá ser criada. Isto garante, dentro do limite do contrato, liberdade para que a interface seja implementada e posteriormente modificada, de com acordo com a necessidade da classe (implementação), proporcionando autonomia entre as camadas.

Primeiramente temos uma interface (contrato), e como a definição da palavra sugere, é um meio de interação entre coisas diversas, uma interface gráfica é um meio de comunicação entre o usuário e o sistema, por exemplo. No nosso caso, é uma interação entre as camadas internas e externas.

![Modelo interface-implementação](https://images2.imgbox.com/e5/3c/xvvFWxRn_o.png "Modelo interface-implementação")

A partir da figura, observa-se o fluxo "implementação-representação", no qual a classe **implementa** a interface, e a mesma, **representa** uma abstração da classe. Por sua vez, métodos abstratos representam os métodos concretos, que os implementam dentro de seu escopo.
Em outras palavras, a interface define quais métodos a classe terá, por sua vez, os métodos abstratos definem o tipo de entrada (parâmetros do construtor) e saída (retorno) dos métodos concretos.

## FAQ:
- Devo abandonar o MVC, MVP para utilizar Clean Architecture?
- R: Não, ambos bebem da mesma fonte no quesito divisão de responsabilidade, mas são coisas distintas e complementares, o Clean Architecture é um modelo de arquitetura de software e como dito anteriormente, é independente de linguagem e framework. Os itens citados (MVC, MVP, MVVM, etc.) são patterns (padrões) de desenvolvimento utilizados no nível da aplicação, podendo ou não, ter dependência do framework. Uma analogia simples seria olharmos a construção de uma casa, o clean é a arquitetura proposta em uma planta e determina as cadeia de construção, o telhado precisa (depende) de paredes/colunas, as mesmas necessitam do piso e assim sucessivamente. O mvc é a técnica de como as paredes serão erguidas, visando aumentar o nível de acabamento e qualidade.

# Aplicando Clean Architecture em aplicações Android

## Divisão de Camadas
Existem formas diferentes de implementar o Clean Architecture e dividir as responsabilidades entre as camadas, no qual varia-se muito, caso haja grande dependência com o framework. Independente de plataforma, os objetivos principais são: permitir a modularização, aumentar a performance (diminuir tempo de build) e atingir independência entre as camadas, visando agilidade de desenvolvimento e distribuição de trabalho.
Para esta abordagem, utilizando o Android, exemplifica-se um modelo de separação em 3 principais camadas, que podem possuir ou não, subcamadas.

- Camada de domínio: camada responsável pelas regras de negócio, este é o coração da aplicação.
- Camada de dados: camada responsável por gerenciar as fontes de dados e padroniza-las para o formato requisitado.
- Camada de interface do usuário: camada responsável pela apresentação do aplicativo e gerenciamento das ações do usuário.

Deve-se salientar que o domínio é o centro da aplicação, e as demais camadas são "detalhes", logo, podem ser substituídas sem maiores complicações.

## Domain
A camada de domínio, contém as camadas/subcamadas mais internas da arquitetura, como entidade e casos de uso, onde é disponibilizado o contrato para acesso dos dados nos repositórios da aplicação.

### Responsabilidades:

- Fornecer as classes de dados, ou objetos de negócio, que representam as entidades.
- Fornecer os casos de uso.
- Fornecer os contratos de repositório, para a implementação na camada da dados.

### Dependências:

- A camada de domínio não possui dependências de bibliotecas ou frameworks, caso deseje modularizar esta camada, aconselha-se o uso de módulos Kotlin. 

## Data

- A camada de dados é responsável pela implementação do contrato de domínio e prover dados à aplicação, seu papel é gerenciar subcamadas, tanto para dados externos (remote), quanto dados locais (cache), através de um datasource (interface). Além de realizar a conversão dos modelos de dados, para os objetos de negócio (entidades).

### Resposabilidades:

- Fornecer modelos de dados, que posteriormente serão mapeados para entidades.
- Fornecer os contratos de datasource, para a implementação nas camadas remotas ou locais.
- Implementar o contrato da camada de domínio, a fim de prover dados.

### Dependências:

- A camada de dados não possui dependências de bibliotecas ou frameworks, caso deseje modularizar esta camada, aconselha-se o uso de módulos Kotlin. Vale ressaltar que suas subcamadas, muito provavelmente, terão dependências, logo, recomenda-se a divisão em camadas diferentes, uma camada de modelo de dados, uma camada de dados remoto e uma camada de dados locais, por exemplo.

### Subcamadas: Local e Remota
Estas subcamadas são responsáveis pela implementação do contrato de dados da camada de data, além de estruturar objetos, para transporte ou ações do banco, que posteriormente serão convertidos em modelos de dados.

As subcamadas dependem diretamente de bibliotecas para obtenção dos dados, seja através do Retrofit (biblioteca de cliente HTTP) para dados remotos, ou do Room (biblioteca de persistência) para dados locais. Logo, cabe ao desenvolvedor, isolar em uma subcamada diferente ou manter junto, o código referente a estas bibliotecas.

Como observação, para a criação de módulos com dependência de bibliotecas ou frameworks, aconselha-se o uso de módulos Android. 

## Ui: Interface do Usuário
A camada de interface do usuário é responsável pela apresentação e interação do usuário com a aplicação, seu papel é definir ações mediantes a entrada e saída de dados, além de cuidar do estado da tela.

### Responsabilidades:

- Fornecer interface visual ao usuário.
- Controlar estado da tela
- Determinar ações mediante as interações.

### Dependências:
Como a camada de ui necessita muito do framework e de bibliotecas, caso seja do interesse modularizar, recomenda-se fortemente a criação de um módulo do tipo Aplicação.

### Recomendações:
Recomenda-se a utilização de patterns (padrões de projeto) que façam a separação das responsabilidades. De forma grosseira, todos os padrões como MVC, MVP, MVVM e MVI, compartilham do mesmo princípio, separar a parte lógica (viewmodel/controller) da view, garantindo mais qualidade, legibilidade e testabilidade, à aplicação.

## Fluxo de interação entre as camadas
A principal função de um aplicativo é permitir interação com usuário, seja com entrada ou saída de dados. Para isto, as camadas devem se comunicar, visto que apesar da sua independência, não entregam valor sozinhas. A seguir observa-se um fluxo de entrada de dados, solicitando uma resposta ao servidor:

![Fluxo de entrada](https://images2.imgbox.com/36/f0/O2sqebmd_o.jpeg "Fluxo de entrada")

Em ordem de execução, o fluxo de entrada é disposto através dos seguintes passos:

- O usuário realiza uma interação com a tela, solicitando uma resposta da mesma.
- A tela é "burra", portanto não possui lógica alguma, sua responsabilidade é delegar a ação a um controller ou viewmodel.
- O controller/viewmodel verifica a ação ou mudança de estado da tela, solicitando um caso de uso específico.
- O caso de uso está vinculado a uma entidade e um repositório (interface), o contrato é implementado na camada externa.
- A camada de dados implementa o contrato, e nesse caso, dispõe de um contrato que deve ser implementado na subcamada de dados remotos.
- A camada de dados remotos implementa o contrato de dados, realizando a chamada com a API. No qual fica a critério do desenvolvedor, a separação ou não, das classes com maior dependência com bibliotecas externas.

Após a solicitação dos dados, o fluxo de saída realiza exatamente o caminho contrario para devolver os dados, realizando alguns mapeamentos ou adaptações entre os objetos durante o caminho. Entende-se que o fluxo de dependências proposto pelo Clean Architecture é muito claro ao dizer que existe um fluxo de dependência das camadas externas para com as camadas internas, porem durante a comunicação o fluxo de dados pode ser invertido.

![Implementação x Instanciação](https://images2.imgbox.com/aa/1f/VyXzhvyo_o.png "Implementação x Instanciação")

A camada externa (UI) solicita os dados através da **instância** do caso de uso, o caso de uso solicita os dados por meio de seu contrato de repositório, porém, este contrato é apenas interface (meio de interação), portanto, para obtenção real dos dados, faz-se necessário a **implementação** do contrato. Em resumo, o fluxo de dados no sentido das camadas internas é feito por instanciação, e o fluxo de dados no sentido das camadas externas é feito por implementação.

### Recomendações:
Seguindo as boas práticas de desenvolvimento, a classe não deve se preocupar com a instância dos objetos, portanto, recomenda-se a utilização de um injetor de dependências que será responsável por guardar todas as instâncias da aplicação. De forma complementar, como as instâncias são utilizadas por boa parte das camadas, e desejamos a independência entre elas, devemos separar a injeção de dependências numa camada a parte.

