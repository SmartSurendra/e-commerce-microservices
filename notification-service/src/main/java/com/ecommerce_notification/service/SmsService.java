package com.ecommerce_notification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.twilio.rest.conversations.v1.conversation.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmsService {
	
	@Value("${twilio.from-number}")
	private String fromNumber;
	
	public void sendSms(String to, String message) {
		Message.creator(message).create();
	}
}