package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface do servico SOAP da calculadora.
 *
 * A anotacao @WebService informa ao JAX-WS que esta interface define
 * as operacoes que serao expostas no WSDL do servico.
 */
@WebService
public interface InterfaceCalculadora {

    /**
     * Operacao SOAP para somar dois numeros.
     *
     * @param numero1 primeiro numero da soma
     * @param numero2 segundo numero da soma
     * @return resultado da soma
     */
    @WebMethod
    double somar(double numero1, double numero2);

    /**
     * Operacao SOAP para dividir dois numeros.
     *
     * @param dividendo numero que sera dividido
     * @param divisor numero pelo qual o dividendo sera dividido
     * @return resultado da divisao
     */
    @WebMethod
    double dividir(double dividendo, double divisor);
}
