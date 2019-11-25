# Instalação
* Baixe o codigo (https://entrevistandodados.wordpress.com/2017/09/28/baixando-arquivos-do-github/)
* Abra com Eclipse (https://www.guj.com.br/t/como-abrir-um-projeto-ja-existente-em-no-eclipse/106539)

# Funcionalidades
* Cadastro de produtos
* * permite criar um produto
* * lista os produtos existentes
* * permite selecionar um produto existente e editar
* * permite deletar o produto
* Venda de produtos
* * filtro de produtos por categoria
* * selecao de multiplos produtos
* * venda de ate 10 produtos por vez
* Arquivo Externo
* * salva seus produtos em um arquivo
* * permite abrir de um arquivo também
* * possivel manter varias versoes dos produtos (ex. por mes)

# Codigo

## Interface

### GUI
* Controla as telas
* Cria o menu e o frame principal
* Guarda a lista de produtos
* Chama o seletor de arquivo pra ler/salvar
* JFrame, JMenu, JMenuBar, JMenuItem, ActionListener

### GuiProductScreen
* Mostra o formulario e a tabela
* Gerencia os produtos
* Ponto principal de componentização
* GuiListener - ActionListener customizado

### GuiFormProduct
* Campos de dados do produto
* Sabe se é edicao ou criação
* Deleta
* JLabel, JTextField, JComboBox, JButton

### GuiTableProduct
* Evento de clique seleciona produto pra editar
* JTable

### GuiFormVendas
* Permite vender
* Venda altera qtd do produto
* Filtro de tipo
* JCheckBox

### GuiNumero
* Componente com + e - para selecionar quantidade de venda
* Se selecao for minimo, troca imagem pra cinza e tira evento (top)
* Canvas com imagem, MouseListener

## Dados

### Product
* Classe de estrutura de dados que mantém um produto
* enum 
* getters e setters

### ProductManager
* Gerencia os produtos
* HashMap fodelao
* sabe atualizar um produto

## Persistencia

### ProductParser
* transforma um produto numa linha de csv
* transforma uma linha de csv em um produto

### ProductStorage
* le um csv e da uma lista de produtos
* recebe uma lista de produtos e salva um csv

mais detalhes em comentarios nos arquivos
