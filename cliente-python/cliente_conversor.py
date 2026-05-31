from zeep import Client
from zeep.exceptions import Fault

WSDL_URL = "http://localhost:8080/conversor?wsdl"


def main():
    client = Client(wsdl=WSDL_URL)

    print("Consumindo o servico SOAP do Conversor")
    print("WSDL:", WSDL_URL)
    print()

    try:
        resultado_bits_para_bytes = client.service.bits_for_byte("16")
        print("Resultado de bits_for_byte(\"16\"):", resultado_bits_para_bytes)

        resultado_bytes_para_bits = client.service.byte_for_bits("2")
        print("Resultado de byte_for_bits(\"2\"):", resultado_bytes_para_bits)

    except Fault as fault:
        print("SOAP Fault recebido do servidor:")
        print(fault)
    except Exception as erro:
        print("Erro ao consumir o servico SOAP:")
        print(erro)


if __name__ == "__main__":
    main()
