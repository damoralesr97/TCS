package com.morales.cuenta_movimientos.controller;

import com.morales.cuenta_movimientos.dto.AccountDTO;
import com.morales.cuenta_movimientos.service.interfaces.IAccountService;
import com.morales.cuenta_movimientos.utils.ResponseHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping()
    public ResponseEntity<ResponseHandler<List<AccountDTO>>> findAll(){
        ResponseHandler<List<AccountDTO>> responseHandler;
        try {
            responseHandler = ResponseHandler.<List<AccountDTO>>builder().code(HttpStatus.OK.value())
                    .value(this.accountService.findAll()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        } catch (Exception e) {
            responseHandler = ResponseHandler.<List<AccountDTO>>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(null).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<ResponseHandler<Object>> findByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.OK.value())
                    .value(this.accountService.findByAccountNumber(accountNumber)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseHandler<Object>> save(@RequestBody() AccountDTO accountDTO){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(this.accountService.save(accountDTO)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.CREATED);
        } catch (ConstraintViolationException ev){
            List<String> messages = ev.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .toList();
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(messages).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        } catch (DataIntegrityViolationException ev){
            String message;
            if (ev.getMessage().contains("unique constraint")) {
                message = "La cuenta ya existe. Por favor, verifica los datos.";
            } else {
                message = "Ocurrió un error al procesar la solicitud. Por favor, verifica los datos e inténtalo de nuevo.";
            }
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(message).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{accountNumber}")
    public ResponseEntity<ResponseHandler<Object>> updateByAccountNumber(@PathVariable("accountNumber") String accountNumber, @RequestBody() AccountDTO accountDTO){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(this.accountService.updateByAccountNumber(accountNumber, accountDTO)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.CREATED);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<ResponseHandler<Boolean>> deleteByAccountNumber(@PathVariable("accountNumber") String accountNumber){
        ResponseHandler<Boolean> responseHandler;
        try {
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.OK.value())
                    .value(this.accountService.deleteByAccountNumber(accountNumber)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        } catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

}
