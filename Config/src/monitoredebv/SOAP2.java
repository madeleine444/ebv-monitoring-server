package monitoredebv;

public class SOAP2 {
	
	public class HttpResponseHandler implements SOAPHandler<SOAPMessageContext> {
    private Logger log = Logger.create(HttpResponseHandler.class);

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        boolean outboundProperty = (boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY); // Response
        if(!outboundProperty) {
            int status = (int)context.get(MessageContext.HTTP_RESPONSE_CODE);
            log.debug("HTTP status code = " + status);
        }
        return true;
    }
}

// Usage : building your service

List<Handler> soapHandlers = new ArrayList();
soapHandlers.add(new HttpResponseHandler());

URL wsdlDocumentLocation = this.getClass().getResource("some_url");
Service service = Service.create(wsdlDocumentLocation, new QName("namespace", "servicename"));
service.setHandlerResolver(new HandlerResolver() {
    public List<Handler> getHandlerChain(PortInfo portInfo) {
        return soapHandlers;
    }
});
BindingProvider provider = (BindingProvider)service.getPort(new QName("namespace", "portname"), serviceInterface);
provider.getRequestContext().put("javax.xml.ws.service.endpoint.address", this.endpointAddress);
provider.getRequestContext().put("com.sun.xml.ws.connect.timeout", connectTimeout);
provider.getRequestContext().put("com.sun.xml.ws.request.timeout", requestTimeout);
return provider;



}
