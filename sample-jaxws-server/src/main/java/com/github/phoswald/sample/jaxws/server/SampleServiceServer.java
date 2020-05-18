package com.github.phoswald.sample.jaxws.server;

import java.io.IOException;
import java.time.Instant;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.github.phoswald.sample.jaxws.api.SampleRequest;
import com.github.phoswald.sample.jaxws.api.SampleResponse;
import com.github.phoswald.sample.jaxws.api.SampleService;

@WebService(name="Sample", targetNamespace = "com.github.phoswald.sample.jaxws", serviceName = "SampleService", portName = "SampleServicePort")
public class SampleServiceServer implements SampleService {

    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Syntax: SampleServiceServer <port>");
            System.exit(1);
        }

        int port = Integer.parseInt(args[0]);
        String url = "http://localhost:" + port + "/sample";
        System.out.println("Going to listen on " + url);
        Endpoint ep = Endpoint.publish(url, new SampleServiceServer());

        System.out.println("Press enter to quit");
        System.in.read();
        ep.stop();
    }

    @Override
    public SampleResponse sampleMethod(SampleRequest request) {
        System.out.println("Received SampleRequest with name=" + request.getName());
        SampleResponse response = new SampleResponse();
        response.setMessage("Hello, " + (request.getName() != null ? request.getName() : "stranger") + "!");
        response.setTimestamp(Instant.now().toString());
        return response;
    }
}
