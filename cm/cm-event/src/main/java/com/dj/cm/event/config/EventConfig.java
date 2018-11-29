package com.dj.cm.event.config;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("event.properties")
public class EventConfig {

	//
	// Constants
	//
	public static final String ECHO_EXCHANGE_NAME = "com.dj.cm.event.echo";
	public static final String ECHO_BIZ_QUEUE_NAME = ECHO_EXCHANGE_NAME + ".biz";
	public static final String ECHO_CLIENT_QUEUE_NAME = ECHO_EXCHANGE_NAME + ".client";


	@Value("${event.enabled:false}") // From application.properties
	private boolean eventEnabled;

	@Value("${event.rabbitmq.hostname}")
	private String eventHostname;

	public boolean isEventEnabled() {
		return eventEnabled;
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		CachingConnectionFactory connectionFactory =
				new CachingConnectionFactory(eventHostname);
		return connectionFactory;
	}

	@Bean
	public AmqpAdmin amqpAdmin() {
		return new RabbitAdmin(connectionFactory());
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}


	@Bean
	public FanoutExchange echoExchange() {
		return new FanoutExchange(ECHO_EXCHANGE_NAME);
	}

	@Bean
	public Queue echoBizQueue() {
		return new Queue(ECHO_BIZ_QUEUE_NAME);
	}

	@Bean
	public Queue echoClientQueue() {
		return new Queue(ECHO_CLIENT_QUEUE_NAME);
	}

	@Bean
	public Binding echoBizBinding() {
		return BindingBuilder.bind(echoBizQueue()).to(echoExchange());
	}

	@Bean
	public Binding echoClientBinding() {
		return BindingBuilder.bind(echoClientQueue()).to(echoExchange());
	}
}
