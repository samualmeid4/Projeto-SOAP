# Projeto SOAP Academico - Conversor

Este projeto demonstra um Servico Web SOAP funcional com servidor em Java usando JAX-WS e cliente em Python usando Zeep. O servico converte valores entre bits e bytes usando mensagens SOAP/XML.

## Estrutura

```text
soap/src/soap/
|
├── InterfaceConversor.java
├── ServicoConversor.java
└── PublicadorConversor.java

cliente-python/
|
└── cliente_conversor.py
```

## Operacoes do Servico

- `bits_for_byte(String bits)`: converte bits em bytes.
- `byte_for_bits(String bytes)`: converte bytes em bits.
- Caso o valor seja negativo, o servidor lanca uma excecao informando que o numero nao pode ser negativo.
- Caso o valor nao seja numerico, o servidor lanca a excecao: `Digite apenas números.`

## Como Executar o Servidor Java

Este exemplo usa JAX-WS classico, disponivel diretamente no JDK 8.

1. Acesse a pasta do servidor:

```bash
cd soap
```

2. Compile as classes Java:

```bash
mkdir bin
javac -encoding UTF-8 -d bin src/soap/*.java
```

Se o comando `javac` nao for reconhecido, instale o JDK 8 e reabra o terminal.

3. Execute o publicador do servico:

```bash
java -cp bin soap.PublicadorConversor
```

4. Acesse o WSDL no navegador:

```text
http://localhost:8080/conversor?wsdl
```

O endpoint local do servico sera:

```text
http://localhost:8080/conversor
```

## Instalacao do Cliente Python

Instale a biblioteca Zeep:

```bash
pip install zeep
```

## Como Executar o Cliente Python

Com o servidor Java em execucao, rode:

```bash
cd cliente-python
python cliente_conversor.py
```

O cliente realiza duas chamadas:

- chamada valida para `bits_for_byte("16")`;
- chamada valida para `byte_for_bits("2")`.

## WSDL e SOAP/XML

O WSDL gerado automaticamente pelo JAX-WS descreve o endpoint, as operacoes disponiveis, os tipos XML, os namespaces e a estrutura das mensagens SOAP com `Envelope` e `Body`.

## Exemplo de Requisicao SOAP para bits_for_byte

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:soap="http://soap/">
    <soapenv:Header/>
    <soapenv:Body>
        <soap:bits_for_byte>
            <arg0>16</arg0>
        </soap:bits_for_byte>
    </soapenv:Body>
</soapenv:Envelope>
```

## Exemplo de Resposta SOAP

```xml
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <ns2:bits_for_byteResponse xmlns:ns2="http://soap/">
            <return>2.0</return>
        </ns2:bits_for_byteResponse>
    </S:Body>
</S:Envelope>
```

## Testando no SoapUI

1. Abra o SoapUI.
2. Crie um novo projeto SOAP.
3. Informe o WSDL:

```text
http://localhost:8080/conversor?wsdl
```

4. O SoapUI ira listar as operacoes `bits_for_byte` e `byte_for_bits`.
5. Abra uma requisicao, informe valores como `16` ou `2`, e execute.
6. Para testar erro, envie um valor negativo ou um texto como `abc`.

## Observacao Sobre JDK 11 ou Superior

O JAX-WS foi removido do JDK a partir do Java 11. Para manter o projeto simples e adequado a uma apresentacao academica curta, recomenda-se usar JDK 8. Em ambientes com Java 11 ou superior, e necessario adicionar dependencias externas do JAX-WS.
