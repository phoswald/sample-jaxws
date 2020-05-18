package com.github.phoswald.sample.jaxws.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

import com.github.phoswald.sample.jaxws.api.SampleRequest;
import com.github.phoswald.sample.jaxws.api.SampleResponse;
import com.github.phoswald.sample.jaxws.api.SampleService;

public class SampleServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        if(args.length != 2) {
            System.out.println("Syntax: SampleServiceClient <url> <name>");
            System.exit(1);
        }

        String url = args[0];
        URL wsdl = SampleService.class.getResource("/sample.wsdl");
        QName qname = new QName("com.github.phoswald.sample.jaxws", "SampleService");
        Service service = Service.create(wsdl, qname);
        SampleService port = service.getPort(SampleService.class);
        ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);

        SampleRequest request = new SampleRequest();
        request.setName(args[1]);
        System.out.println("Going to call " + url);
        SampleResponse response = port.sampleMethod(request);
        System.out.println("Received SampleResponse with message=" + response.getMessage() + ", timestamp=" + response.getTimestamp());
    }
}
