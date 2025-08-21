# Gerador de QR Code em JavaFX

Este projeto é um gerador de QR Code desenvolvido em Java, utilizando Maven para gerenciamento de dependências e a biblioteca ZXing para a geração dos códigos. Ele oferece uma interface gráfica simples com JavaFX e também um modo de execução via linha de comando.

## Requisitos

Para compilar e executar este projeto, você precisará ter instalado:

*   **Java Development Kit (JDK) 11 ou superior**
*   **Apache Maven 3.6.3 ou superior**

## Estrutura do Projeto

O projeto está estruturado da seguinte forma:

```
qrcode-generator/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── qrcode/
│   │   │           ├── App.java (Interface Gráfica JavaFX)
│   │   │           ├── CliMain.java (Modo de Linha de Comando)
│   │   │           ├── module-info.java (Configuração de Módulos Java)
│   │   │           └── service/
│   │   │               └── QRCodeService.java (Lógica de Geração de QR Code)
│   │   └── resources/
│   └── test/
├── qrcodes/ (Pasta para salvar os QR Codes gerados)
└── README.md
```

## Como Compilar e Executar

### 1. Clonar o Repositório (se aplicável)

```bash
git clone <URL_DO_SEU_REPOSITORIO>
cd qrcode-generator
```

### 2. Compilar o Projeto

Navegue até o diretório raiz do projeto (`qrcode-generator`) e execute o seguinte comando Maven:

```bash
mvn clean install
```

Este comando irá baixar as dependências, compilar o código e empacotar o projeto em um arquivo JAR executável com todas as dependências (`qrcode-generator-1.0-SNAPSHOT-jar-with-dependencies.jar`) na pasta `target/`.

### 3. Executar a Aplicação

#### Modo Interface Gráfica (JavaFX)

Para executar a aplicação com a interface gráfica, utilize o plugin JavaFX Maven:

```bash
mvn javafx:run
```

**Nota:** Este modo requer um ambiente gráfico disponível. Se você estiver em um ambiente sem interface gráfica (como um servidor), esta opção não funcionará.

#### Modo Linha de Comando (CLI)

Para gerar QR Codes via linha de comando, execute o JAR gerado e passe o argumento `--cli`:

```bash
java -jar target/qrcode-generator-1.0-SNAPSHOT-jar-with-dependencies.jar --cli
```

O programa solicitará o texto/URL e o nome do arquivo de saída. Os QR Codes serão salvos na pasta `qrcodes/`.

## Funcionalidades

*   **Geração de QR Code:** Converte texto ou URL em uma imagem QR Code.
*   **Formato de Saída:** Salva o QR Code como arquivo PNG.
*   **Pasta de Saída:** Os arquivos são salvos automaticamente na pasta `qrcodes/`.
*   **Nome de Arquivo Personalizável:** Opção de definir o nome do arquivo de saída (no modo GUI e CLI).
*   **Interface Gráfica (JavaFX):** Interface intuitiva para interação do usuário.
*   **Modo CLI:** Geração de QR Code via terminal para automação ou ambientes sem GUI.

## Bibliotecas Utilizadas

*   **ZXing (Zebra Crossing):** Biblioteca de código aberto para processamento de códigos de barras 1D/2D. Utilizada para a geração do QR Code.
*   **JavaFX:** Toolkit de interface gráfica para aplicações Java.

## Estrutura de Camadas

O projeto segue uma estrutura de camadas básica para organização do código:

*   `com.qrcode.service`: Contém a lógica de negócio principal para a geração do QR Code (`QRCodeService`).
*   `com.qrcode`: Contém as classes principais da aplicação (`App` para a GUI e `CliMain` para o CLI).
