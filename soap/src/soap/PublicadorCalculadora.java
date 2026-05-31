package soap;

import javax.xml.ws.Endpoint;

/**
 * Classe responsavel por iniciar e publicar o servico SOAP.
 *
 * Ao executar o metodo main, o JAX-WS disponibiliza o endpoint local
 * e gera automaticamente o WSDL em http://localhost:8080/calculadora?wsdl.
 */
public class PublicadorCalculadora {

    public static void main(String[] args) {
        Endpoint.publish(
            "http://localhost:8080/calculadora",
            new ServicoCalculadora()
        );

        System.out.println("Servico SOAP da calculadora iniciado.");
        System.out.println("Endpoint: http://localhost:8080/calculadora");
        System.out.println("WSDL: http://localhost:8080/calculadora?wsdl");
    }
}
