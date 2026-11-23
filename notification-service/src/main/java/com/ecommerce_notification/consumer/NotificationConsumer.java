package com.ecommerce_notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.ecommerce_notification.dto.NotificationEvent;
import com.ecommerce_notification.service.EmailService;
import com.ecommerce_notification.service.SmsService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationConsumer {

	
	private final EmailService emailService;
	private final SmsService smsService;
	
	@KafkaListener(topics = "Notification-topic", groupId= "notification-group")
	public void consume(NotificationEvent event) {
		
		if("EMAIL".equals(event.getType())) {
		  emailService.sendMail(event.getEmail(), event.getMessage(), event.getSubject());
		}
		
		if("SMS".equals(event.getType())) {
			smsService.sendSms(event.getMobile(), event.getMessage());
		}
	}
}
