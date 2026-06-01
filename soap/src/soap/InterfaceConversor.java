package soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface InterfaceConversor {

    @WebMethod
    double bits_for_byte(Double valor);

    @WebMethod
    double byte_for_bits(Double valor);
}
