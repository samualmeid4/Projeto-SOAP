package soap;

import javax.jws.WebService;

/**
 * Implementacao concreta do servico SOAP da calculadora.
 *
 * A anotacao @WebService associa esta classe a interface do servico.
 * O JAX-WS usa essa informacao para publicar as operacoes no endpoint
 * e gerar automaticamente o WSDL em formato XML.
 */
@WebService(endpointInterface = "soap.InterfaceCalculadora")
public class ServicoCalculadora implements InterfaceCalculadora {

    /**
     * Soma dois valores recebidos pelo cliente SOAP.
     */
    @Override
    public double somar(double numero1, double numero2) {
        return numero1 + numero2;
    }

    /**
     * Divide o dividendo pelo divisor.
     *
     * Quando o divisor e zero, uma excecao e lancada. O JAX-WS converte
     * essa excecao em um SOAP Fault para o cliente.
     */
    @Override
    public double dividir(double dividendo, double divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Não é permitido dividir por zero.");
        }

        return dividendo / divisor;
    }
}
