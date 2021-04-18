package com.disertatie.rent.car.controller;

import com.disertatie.rent.car.exceptions.ExceptionAlreadyRent;
import com.disertatie.rent.car.exceptions.ExceptionNotFound;
import com.disertatie.rent.car.model.OrderDetailModel;
import com.disertatie.rent.car.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.logging.Logger;

@RestController
@RequestMapping(path = "/receipts")
public class ReceiptController {

    private final static Logger LOGGER = Logger.getLogger(ReceiptController.class.getName());


    @Resource(name = "receiptService")
    private ReceiptService receiptService;

    @PostMapping(path = "/add", produces = "application/json")
    public ResponseEntity addReceipt(@RequestBody OrderDetailModel orderDetailModel) throws ExceptionAlreadyRent, ExceptionNotFound {
        LOGGER.info("ReceiptController : addCard()" + orderDetailModel);
        receiptService.addReceipt(orderDetailModel);
        return ResponseEntity.ok().build();
    }
}
