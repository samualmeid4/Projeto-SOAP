package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "soap.InterfaceConversor")
public class ServicoConversor implements InterfaceConversor {


    @WebMethod
    @Override
    public double bits_for_byte(Double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O número de bits não pode ser negativo.");
        }

        return valor / 8;
        
    }

    @WebMethod
    @Override
    public double byte_for_bits(Double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O número de bytes não pode ser negativo.");
        }

        return valor * 8;
        
    }
}
