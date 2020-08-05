package br.com.projeto

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.log4j.{Logger, Level}
import org.apache.spark.sql.SparkSession

object AppProducer {

  val kafkaBrokers = "localhost:9092"
  val topicName = "TesteAndre"

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.OFF)

    val spark = SparkSession.builder.appName("TestKafka")
      .config("spark.master", "local").getOrCreate

    val df = spark.read.format("csv").option("header", "true")
      .option("multiline", true)
      .option("sep", ";")
      .load("C:/dados/DadosDrinks.csv")

    val dfJson = df.toJSON

    dfJson.collect().foreach(x => {
      sendEvent(x)
    })
  }

  def sendEvent(message: String) = {

    val props = new Properties()
    props.put("bootstrap.servers", kafkaBrokers)
    props.put("acks", "all")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val key = java.util.UUID.randomUUID().toString()
    producer.send(new ProducerRecord[String, String](topicName, key, message))
    println("Sent event with key: '" + key + "' and message: '" + message + "'\n")
  }
}
