package br.inatel.dm112.client.billet.stub;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.5
 * 2016-03-31T15:24:03.501-03:00
 * Generated source version: 3.1.5
 * 
 */
@WebService(targetNamespace = "dm112", name = "BilletImpl")
@XmlSeeAlso({ObjectFactory.class})
public interface BilletImpl {

    @WebMethod
    @RequestWrapper(localName = "generateBillet", targetNamespace = "dm112", className = "dm112.GenerateBillet")
    @ResponseWrapper(localName = "generateBilletResponse", targetNamespace = "dm112", className = "dm112.GenerateBilletResponse")
    @WebResult(name = "return", targetNamespace = "")
    public br.inatel.dm112.client.billet.stub.BilletGenData generateBillet(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );
}