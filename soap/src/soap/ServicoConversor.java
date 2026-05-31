package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "soap.InterfaceConversor")
public class ServicoConversor implements InterfaceConversor {


    @WebMethod
    @Override
    public double bits_for_byte(String bits) {
        try {
            double valor = Double.parseDouble(bits);

            if (valor < 0) {
                throw new IllegalArgumentException("O número de bits não pode ser negativo.");
            }

            return valor / 8;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Digite apenas números.");
        }
    }

    @WebMethod
    @Override
    public double byte_for_bits(String bytes) {
        try {
            double valor = Double.parseDouble(bytes);

            if (valor < 0) {
                throw new IllegalArgumentException("O número de bytes não pode ser negativo.");
            }

            return valor * 8;
        } catch (NumberFormatException e) {
            throw new RuntimeException("Digite apenas números.");
        }
    }
}
