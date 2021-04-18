package com.disertatie.rent.car.service.impl;


import com.disertatie.rent.car.entities.Receipt;
import com.disertatie.rent.car.exceptions.ExceptionAlreadyRent;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.OrderDetailModel;
import com.disertatie.rent.car.repository.ReceiptRepository;
import com.disertatie.rent.car.service.CardService;
import com.disertatie.rent.car.service.ReceiptService;
import com.disertatie.rent.car.service.RentDetailService;
import com.disertatie.rent.car.service.UserService;
import com.disertatie.rent.car.service.mailSender.MailSenderApp;
import com.disertatie.rent.car.transformers.Transformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "receiptService")
@Transactional
public class ReceiptServiceImpl implements ReceiptService {


    @Resource(name = "receiptRepository")
    private ReceiptRepository receiptRepository;

    @Resource(name = "rentDetailService")
    private RentDetailService rentDetailService;

    @Resource(name = "cardService")
    private CardService cardService;

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "mailSenderApp")
    private MailSenderApp mailSender;

    @Resource(name = "transformer")
    private Transformer transformer;


    @Override
    public void addReceipt(OrderDetailModel orderDetailModel) throws ExceptionAlreadyRent, ExceptionNotFound {
        if (orderDetailModel.getPayMethod().toLowerCase().contains("my")) {
            cardService.checkCard(orderDetailModel.getCardNumber(), orderDetailModel.getCvvCode(), orderDetailModel.getUserId());
        }
        orderDetailModel.setRentDetailId(rentDetailService.addRentDetail(orderDetailModel));
        Receipt receipt = receiptRepository.save(transformer.transformModelToEntity(orderDetailModel));
        mailSender.sendEmail(userService.getUserById(orderDetailModel.getUserId()), receipt);
    }
}
