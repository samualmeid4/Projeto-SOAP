package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface InterfaceConversor {

    /**
     * Converte bits em bytes.
     *
     * @param bits quantidade de bits em formato de texto
     * @return quantidade equivalente em bytes
     */
    @WebMethod
    double bits_for_byte(String bits);

    /**
     * Converte bytes em bits.
     *
     * @param bytes quantidade de bytes em formato de texto
     * @return quantidade equivalente em bits
     */
    @WebMethod
    double byte_for_bits(String bytes);
}
