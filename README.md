# sample-jaxws

Experiments with minimalistic JAX-WS

## Run Server

~~~
$ mvn clean install
$ cd sample-jaxws-server
$ java -cp 'target/*:target/lib/*' \
  com.github.phoswald.sample.jaxws.server.SampleServiceServer \
  8080
~~~

Query WSDL: `http://localhost:8080/sample?wsdl`

Update WSDL: `curl http://localhost:8080/sample?wsdl  > sample-jaxws-api/src/main/resources/sample.wsdl`

## Run Client

~~~
$ mvn clean install
$ cd sample-jaxws-client
$ java -cp 'target/*:target/lib/*' \
  com.github.phoswald.sample.jaxws.client.SampleServiceClient \
  'http://localhost:8080/sample' hallo
~~~
