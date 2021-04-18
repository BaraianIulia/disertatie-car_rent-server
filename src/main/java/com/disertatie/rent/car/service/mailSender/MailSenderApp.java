package com.disertatie.rent.car.service.mailSender;

import com.disertatie.rent.car.entities.Receipt;
import com.disertatie.rent.car.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.Period;

import static java.time.temporal.ChronoUnit.DAYS;

@Component(value = "mailSenderApp")
public class MailSenderApp {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(UserModel user, Receipt receipt) {


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("<html> <body> ")
                .append("<h1> Thank you for using our services, ")
                .append(user.getName())
                .append(" </h1> ")
                .append(" <br>")
                .append(" <h2> ")
                .append(receipt.getRentDate())
                .append(" </h2>")
                .append(" <p> Pick up and drop off your car from/to ")
                .append(receipt.getRentDetail().getPickupDropoffLocation())
                .append("</p>")
                .append("<p> Your car is rented from ")
                .append(receipt.getRentDetail().getStartDate())
                .append(" to ")
                .append(receipt.getRentDetail().getEndDate())
                .append("</p>")
                .append("<p> Pay method ")
                .append(receipt.getPayMethod().toString())
                .append("</p>")
                .append("<p> Your total price is: ")
                .append(receipt.getTotalPrice())
                .append("€     (number of days (")
                .append(DAYS.between(receipt.getRentDetail().getStartDate(), receipt.getRentDetail().getEndDate()) + 1)
                .append(") * car price per day (")
                .append(receipt.getRentDetail().getCar().getPricePerDay())
                .append(") + insurance (").append(receipt.getRentDetail().getCar().getInsurance())
                .append("))")
                .append("</p>")
                .append("<p> Car rented: brand- ")
                .append(receipt.getRentDetail().getCar().getBrand())
                .append(", model- ")
                .append(receipt.getRentDetail().getCar().getModel())
                .append(", vin- ")
                .append(receipt.getRentDetail().getCar().getVehicleIdentificationNumber())
                .append(".  </p>")
                .append("</body> </html>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper msg = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessage.setSubject("Rent car");
            msg.setTo("iulia.baraian97@gmail.com");
            msg.setText(stringBuilder.toString(), true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
