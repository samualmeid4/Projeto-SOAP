# Projeto SOAP Academico - Conversor

Este projeto demonstra um Servico Web SOAP funcional com servidor em Java usando JAX-WS e cliente consumidor em Python usando Zeep. O servico realiza conversoes simples entre bits e bytes por meio de mensagens SOAP/XML.

## Estrutura

```text
soap/src/soap/
|-- InterfaceConversor.java
|-- ServicoConversor.java
`-- PublicadorConversor.java

cliente-python/
`-- cliente_conversor.py
```

## Tecnologias Utilizadas

- Java 8
- JAX-WS
- Python
- Zeep
- SoapUI

## Operacoes do Servico

- `bits_for_byte(Double valor)`: converte uma quantidade de bits para bytes.
- `byte_for_bits(Double valor)`: converte uma quantidade de bytes para bits.

O servico tambem possui tratamento basico de erro: quando o valor informado e negativo, o servidor lanca uma excecao que e retornada ao consumidor como SOAP Fault.

## Endpoint e WSDL

Endpoint local do servico:

```text
http://localhost:8080/conversor
```

WSDL gerado automaticamente pelo JAX-WS:

```text
http://localhost:8080/conversor?wsdl
```

O WSDL descreve o contrato do servico, as operacoes disponiveis, os tipos XML, os namespaces e a estrutura das mensagens SOAP.

## Como Executar o Servidor Java

Este projeto usa JAX-WS classico, disponivel diretamente no JDK 8.

1. Acesse a pasta do projeto servidor:

```bash
cd soap
```

2. Compile as classes Java:

```bash
javac -encoding UTF-8 -d src/bin src/soap/*.java
```

3. Execute o publicador do servico:

```bash
java -cp src/bin soap.PublicadorConversor
```

4. Com o servidor em execucao, acesse o WSDL:

```text
http://localhost:8080/conversor?wsdl
```

## Como Executar o Cliente Python

Instale a dependencia do cliente:

```bash
pip install zeep
```

Com o servidor Java em execucao, rode:

```bash
cd cliente-python
python cliente_conversor.py
```

O cliente apresenta um menu para consumir as duas operacoes SOAP:

- converter bits para bytes;
- converter bytes para bits.

## Exemplos de Chamadas

Chamada valida:

```text
bits_for_byte(16) = 2.0
byte_for_bits(2) = 16.0
```

Chamada invalida:

```text
bits_for_byte(-5)
```

Resultado esperado: SOAP Fault informando que o numero de bits nao pode ser negativo.

## Exemplo de Requisicao SOAP

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

## Testes no SoapUI

Antes de abrir o SoapUI, o servidor Java precisa estar em execucao. No terminal, execute:

```bash
cd soap
java -cp src/bin soap.PublicadorConversor
```

Depois disso, siga os passos abaixo no SoapUI.

### 1. Criar o projeto SOAP

1. Abra o SoapUI.
2. Clique em `File > New SOAP Project`.
3. No campo `Project Name`, digite:

```text
ConversorSOAP
```

4. No campo `Initial WSDL`, informe:

```text
http://localhost:8080/conversor?wsdl
```

5. Clique em `OK`.

Se o servidor estiver funcionando, o SoapUI vai carregar o contrato WSDL e mostrar as operacoes `bits_for_byte` e `byte_for_bits`.

### 2. Testar a operacao bits_for_byte

1. No painel esquerdo, abra:

```text
ConversorSOAP > InterfaceConversorServiceSoapBinding > bits_for_byte > Request 1
```

2. O SoapUI vai mostrar uma requisicao XML. Procure a tag `arg0`.
3. Substitua o valor de `arg0` por `16`, deixando parecido com este exemplo:

```xml
<soap:bits_for_byte>
   <arg0>16</arg0>
</soap:bits_for_byte>
```

4. Clique no botao verde de executar a requisicao.
5. A resposta esperada deve conter:

```xml
<return>2.0</return>
```

### 3. Testar a operacao byte_for_bits

1. No painel esquerdo, abra:

```text
ConversorSOAP > InterfaceConversorServiceSoapBinding > byte_for_bits > Request 1
```

2. Substitua o valor de `arg0` por `2`, deixando parecido com este exemplo:

```xml
<soap:byte_for_bits>
   <arg0>2</arg0>
</soap:byte_for_bits>
```

3. Clique no botao verde de executar a requisicao.
4. A resposta esperada deve conter:

```xml
<return>16.0</return>
```

### 4. Testar uma chamada invalida

Para validar o tratamento basico de erro, execute uma chamada com valor negativo.

Exemplo para `bits_for_byte`:

```xml
<soap:bits_for_byte>
   <arg0>-5</arg0>
</soap:bits_for_byte>
```

Ao executar, a resposta esperada deve ser um SOAP Fault contendo a mensagem de erro informando que o numero de bits nao pode ser negativo.

### 5. Prints para a entrega

Para atender ao requisito dos testes completos no SoapUI, tire prints de:

- projeto carregado com as operacoes `bits_for_byte` e `byte_for_bits`;
- chamada valida de `bits_for_byte` retornando `2.0`;
- chamada valida de `byte_for_bits` retornando `16.0`;
- chamada invalida retornando SOAP Fault.

Esses prints devem ser colocados no PDF de evidencias.

## Evidencias Recomendadas para Entrega

O PDF de evidencias pode conter:

- print do servidor Java em execucao;
- print do cliente Python consumindo uma chamada valida;
- print do SoapUI executando uma chamada valida;
- print do SoapUI executando uma chamada invalida;
- print da documentacao do servico gerada pelo SoapUI;
- breve justificativa tecnica.

## Justificativa Tecnica

SOAP foi adequado para este projeto porque a atividade exigia um contrato formal de servico, interoperabilidade entre tecnologias distintas e validacao das mensagens por meio de WSDL. O uso de Java no servidor e Python no cliente demonstrou que o consumidor nao depende da mesma linguagem do provedor, desde que ambos respeitem o contrato SOAP/XML. A principal dificuldade tecnica foi a compilacao do servidor: o JAX-WS faz parte do JDK 8, mas foi removido das versoes mais recentes do Java. Por isso, foi necessario ajustar o ambiente para usar Java 8 e compilar o projeto corretamente com `javac`.

## Observacao Sobre Versao do Java

O projeto foi feito para execucao com JDK 8. Em Java 11 ou superior, o JAX-WS nao vem mais incluido por padrao, sendo necessario adicionar dependencias externas para publicar o endpoint SOAP.
