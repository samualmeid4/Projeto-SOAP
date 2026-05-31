from zeep import Client
from zeep.exceptions import Fault


"""
Cliente SOAP em Python para consumir o servico Calculadora.

A biblioteca Zeep le o WSDL publicado pelo servidor Java, descobre
as operacoes disponiveis e monta as mensagens SOAP/XML automaticamente.
"""

WSDL_URL = "http://localhost:8080/calculadora?wsdl"


def main():
    client = Client(wsdl=WSDL_URL)

    print("Consumindo o servico SOAP da Calculadora")
    print("WSDL:", WSDL_URL)
    print()

    try:
        resultado_soma = client.service.somar(10.0, 5.0)
        print("Resultado de somar(10.0, 5.0):", resultado_soma)

        resultado_divisao = client.service.dividir(10.0, 2.0)
        print("Resultado de dividir(10.0, 2.0):", resultado_divisao)

        print("Tentando dividir por zero...")
        client.service.dividir(10.0, 0.0)

    except Fault as fault:
        print("SOAP Fault recebido do servidor:")
        print(fault)
    except Exception as erro:
        print("Erro ao consumir o servico SOAP:")
        print(erro)


if __name__ == "__main__":
    main()
