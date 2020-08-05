<h1>TUTORIAL - KAFKA</h1>

<h3>Iniciar zookeper</h3>
c:\kafka\bin\windows\zookeeper-server-start.bat c:\kafka\config\zookeeper.properties

<h3>Iniciar kafka</h3>
c:\kafka\bin\windows\kafka-server-start.bat c:\kafka\config\server.properties

<h3>Criar tópico kafka</h3>
c:\kafka\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic TesteAndre

<h3>Listar tópicos</h3>
c:\kafka\bin\windows\kafka-topics.bat --list --zookeeper localhost:2181

<h3>Inserir dados na fila</h3>
c:\kafka\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic TesteAndre

<h3>Ler os dados na fila</h3>
c:\kafka\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic TesteAndre --from-beginning

<h3>Excluir topico</h3>
c:\kafka\bin\windows\kafka-topics.bat --zookeeper localhost:2181 --delete --topic TesteAndre
