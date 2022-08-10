<h4>Products and Order</h4>
Api para cadastrar,Atualizar,Deletar e Buscar produtos, Pedidos e itens de pedidos

<h4>Instalação</h4>
$ git clone https://github.com/paulomiron/ProcuctsAndOrders

Tendo instalado o Java 11, selecione a IDE de sua preferência e import Existing Maven Project. Por padrão, o projeto está rodando em http://localhost:8080/
Através deste link http://localhost:8080//swagger-ui.html#/ você poderá visualizar o swagger onde há uma documentação atualizada, além de auxiliar na visualização dos endpoints e testes.

<h4>Dependências</h4>
<li>Java 11</li>
<li>Maven</li>
<li>Springs: Data Jpa, Web, MockMVC e Swagger</li>
<br>
Postgres Database: Necessária instalação do postgres e Dbeaver e também necessário criar o database, após a instalação e criação do banco é preciso alterar no application.properties os campos:<br>
Obs: Dbeaver opcional para visualização da estrutura dos dados.<br><br>

spring.datasource.url= Ex: jdbc:postgresql://localhost:5432/nomedodatabase<br>
spring.datasource.username= Ex: username criado na instalação do banco<br>
spring.datasource.password= Ex: senha configurada na instalação do banco<br>

Ao configurar os campos acima, e rodar o projeto serão criadas as tabelas. 
Dependências estão configuradas nos arquivos pom.xml e application.properties

<h4>Arquitetura do Projeto<h4>
 |--config <br>
 |--entities <br>
 <li> |--dto<br> </li>
 <li> |--enums<br> </li>
 |--repository <br>
 |--resourcer <br>
 <li>  |--exceptions </li>
 |--service <br>
 <li>  |--exceptions </li>

