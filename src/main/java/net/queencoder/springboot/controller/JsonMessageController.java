package net.queencoder.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.queencoder.springboot.kafka.JsonKafkaProducer;
import net.queencoder.springboot.payload.User;

/**
 * @author julietnkwor
 *
 */
@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {
	
	private JsonKafkaProducer jsonKafkaProducer;

	public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
		this.jsonKafkaProducer = jsonKafkaProducer;
	}
	
	//http://localhost:8080/api/v1/kafka/publish?message=Hello world
	/*
	 * In this realtime project, we will how to use apache kafka broker to read the
	 * real time stream data from wikimedia
	 * https://stream.wikimedia.org/v2/stream/recentchange and then write to the
	 * database. We created two Microservices, one that sends message to the topic and another 
	 * that consumes the data.
	 */		@PostMapping("/publish")
		public ResponseEntity<String> publish(@RequestBody User user){
			jsonKafkaProducer.sendMessage(user);
			
			return ResponseEntity.ok("JSON Message sent to kafka topic");
		}

}
