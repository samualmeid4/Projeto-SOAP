from zeep import Client
from zeep.exceptions import Fault

WSDL_URL = "http://localhost:8080/conversor?wsdl"


def main():
    client = Client(wsdl=WSDL_URL)

    print("Consumindo o serviço SOAP do Conversor")
    print("WSDL:", WSDL_URL)

    while True:
        print("\n===== MENU =====")
        print("1 - Converter Bits para Bytes")
        print("2 - Converter Bytes para Bits")
        print("0 - Sair")

        opcao = input("Escolha uma opção: ").strip()

        if opcao == "0":
            print("Encerrando cliente...")
            break

        elif opcao == "1":
            valor = input("Digite a quantidade de bits: ").strip()

            try:
                resultado = client.service.bits_for_byte(valor)
                print(f"Resultado: {resultado}")

            except Fault as fault:
                print("SOAP Fault recebido do servidor:")
                print(fault)

            except Exception as erro:
                print("Erro ao consumir o serviço SOAP:")
                print(erro)

        elif opcao == "2":
            valor = input("Digite a quantidade de bytes: ").strip()

            try:
                resultado = client.service.byte_for_bits(valor)
                print(f"Resultado: {resultado}")

            except Fault as fault:
                print("SOAP Fault recebido do servidor:")
                print(fault)

            except Exception as erro:
                print("Erro ao consumir o serviço SOAP:")
                print(erro)

        else:
            print("Opção inválida. Tente novamente.")


if __name__ == "__main__":
    main()