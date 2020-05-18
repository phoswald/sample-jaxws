package com.github.phoswald.sample.jaxws.api;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(name="Sample", targetNamespace = "com.github.phoswald.sample.jaxws", serviceName = "SampleService", portName = "SampleServicePort")
public interface SampleService {

    @WebMethod
    public SampleResponse sampleMethod(SampleRequest request);
}
