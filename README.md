# Projeto SOAP Academico - Calculadora

Este projeto demonstra um Servico Web SOAP funcional com servidor em Java usando JAX-WS e cliente em Python usando Zeep. O objetivo e mostrar a interoperabilidade entre linguagens diferentes por meio de mensagens SOAP/XML.

## Estrutura

```text
soap/src/soap/
|
├── InterfaceCalculadora.java
├── ServicoCalculadora.java
└── PublicadorCalculadora.java

cliente-python/
|
└── cliente_calculadora.py
```

## Operacoes do Servico

- `somar(double numero1, double numero2)`: retorna a soma dos dois numeros.
- `dividir(double dividendo, double divisor)`: retorna o resultado da divisao.
- Caso o divisor seja zero, o servidor lanca a excecao: `Não é permitido dividir por zero.`

## Como Executar o Servidor Java

Este exemplo usa JAX-WS classico, disponivel diretamente no JDK 8.

1. Acesse a pasta do projeto:

```bash
cd soap
```

2. Compile as classes Java:

```bash
mkdir bin
javac -d bin src/soap/*.java
```

No Windows, caso o terminal nao esteja usando UTF-8, prefira:

```bash
javac -encoding UTF-8 -d bin src/soap/*.java
```

Se a pasta `bin` ainda nao existir, crie antes:

```bash
mkdir bin
javac -encoding UTF-8 -d bin src/soap/*.java
```

Se o comando `javac` nao for reconhecido, instale o JDK 8 e reabra o terminal.

3. Execute o publicador do servico:

```bash
java -cp bin soap.PublicadorCalculadora
```

4. Acesse o WSDL no navegador:

```text
http://localhost:8080/calculadora?wsdl
```

O endpoint local do servico sera:

```text
http://localhost:8080/calculadora
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
python cliente_calculadora.py
```

O cliente realiza tres chamadas:

- chamada valida para `somar(10.0, 5.0)`;
- chamada valida para `dividir(10.0, 2.0)`;
- chamada invalida para `dividir(10.0, 0.0)`, capturando o SOAP Fault.

## WSDL e SOAP/XML

O WSDL gerado automaticamente pelo JAX-WS descreve:

- o endpoint do servico;
- as operacoes disponiveis;
- os tipos XML usados nas mensagens;
- os namespaces;
- a estrutura das mensagens SOAP com `Envelope` e `Body`.

Esse contrato permite que o cliente Python entenda como chamar o servico Java sem conhecer sua implementacao interna.

## Exemplo de Requisicao SOAP para somar

```xml
<?xml version="1.0" encoding="UTF-8"?>
<soapenv:Envelope
    xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
    xmlns:soap="http://soap/">
    <soapenv:Header/>
    <soapenv:Body>
        <soap:somar>
            <arg0>10.0</arg0>
            <arg1>5.0</arg1>
        </soap:somar>
    </soapenv:Body>
</soapenv:Envelope>
```

## Exemplo de Resposta SOAP

```xml
<?xml version="1.0" encoding="UTF-8"?>
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <ns2:somarResponse xmlns:ns2="http://soap/">
            <return>15.0</return>
        </ns2:somarResponse>
    </S:Body>
</S:Envelope>
```

## Exemplo de SOAP Fault na Divisao por Zero

```xml
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
    <S:Body>
        <S:Fault>
            <faultcode>S:Server</faultcode>
            <faultstring>Não é permitido dividir por zero.</faultstring>
        </S:Fault>
    </S:Body>
</S:Envelope>
```

## Testando no SoapUI

1. Abra o SoapUI.
2. Crie um novo projeto SOAP.
3. Informe o WSDL:

```text
http://localhost:8080/calculadora?wsdl
```

4. O SoapUI ira listar as operacoes `somar` e `dividir`.
5. Abra a requisicao da operacao `somar`, informe valores como `10.0` e `5.0`, e execute.
6. Para testar erro, abra `dividir`, informe divisor `0.0` e verifique o SOAP Fault retornado.

## Observacao Sobre JDK 11 ou Superior

O JAX-WS foi removido do JDK a partir do Java 11. Para manter o projeto simples e adequado a uma apresentacao academica curta, recomenda-se usar JDK 8. Em ambientes com Java 11 ou superior, e necessario adicionar dependencias externas do JAX-WS.
