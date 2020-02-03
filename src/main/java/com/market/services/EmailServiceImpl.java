package com.market.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.entities.AddressEntity;
import com.market.entities.BasketEntity;
import com.market.entities.UserEntity;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void sendTemplateMessage(UserEntity user, BasketEntity basket, AddressEntity address, String subject)
			throws Exception {
		MimeMessage mail = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);

		helper.setTo("vanjadolsina@gmail.com");
		helper.setSubject(subject);
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(basket.getBasket_products());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(formatter.format(date));
		String text = "<html><head><style> table, th, td { border: 1px solid black; border-collapse: collapse;} th, td { padding: 15px;} </style> </head> <body>"
				+ "<tr><th>Time</th><th>User</th><th>Email</th><th>Street</th><th>City</th><th>Basket</th><th>Sum</th><th>Products</th></tr>"
				+ "<td>" + date + "</td>" + "<td>" + user.getName() + " " + user.getLastname() + "</td>" + "<td>" + user.getEmail() + "</td>" + "<td>"
				+ address.getStreet() + "</td>" + "<td>" + address.getCity() + "</td>" + "<td>" + basket.getId()
				+ "</td>" + "<td>" + basket.getSuma() + "</td>" + "<td>" + jsonString
				+ "</td></tr></table></body></html>";

		helper.setText(text, true);

		emailSender.send(mail);
	}
}
