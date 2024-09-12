package com.morales.cuenta_movimientos.controller;

import com.morales.cuenta_movimientos.dto.ReportDTO;
import com.morales.cuenta_movimientos.service.interfaces.IMovementService;
import com.morales.cuenta_movimientos.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReportController {

    @Autowired
    private IMovementService movementService;

    @GetMapping()
    public ResponseEntity<ResponseHandler<List<ReportDTO>>> findByAccountNumberAccount(@RequestParam List<String> fecha,
                                                                                       @RequestParam String cliente){
        ResponseHandler<List<ReportDTO>> responseHandler;
        try{
            responseHandler = ResponseHandler.<List<ReportDTO>>builder().code(HttpStatus.OK.value())
                    .value(movementService.findMoventsByClientDniBetweenDate(cliente,fecha.get(0),fecha.get(1))).build();

            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<List<ReportDTO>>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(null).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

}
