package soap;

import javax.xml.ws.Endpoint;

public class PublicadorConversor {

    public static void main(String[] args) {
        Endpoint.publish(
            "http://localhost:8080/conversor",
            new ServicoConversor()
        );

        System.out.println("Servico SOAP do conversor iniciado.");
        System.out.println("Endpoint: http://localhost:8080/conversor");
        System.out.println("WSDL: http://localhost:8080/conversor?wsdl");
    }
}
