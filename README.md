# sample-jaxws

Experiments with minimalistic JAX-WS

## Run Server

~~~
mvn clean install
~~~

Query WSDL: `http://localhost:8080/sample?wsdl`

Update WSDL: `curl http://localhost:8080/sample?wsdl  > sample-jaxws-api/src/main/resources/sample.wsdl`

## Run Client

~~~
mvn clean install
~~~
