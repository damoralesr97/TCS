package com.morales.cuenta_movimientos.controller;

import com.morales.cuenta_movimientos.dto.MovementDTO;
import com.morales.cuenta_movimientos.dto.MovementRequestDTO;
import com.morales.cuenta_movimientos.service.interfaces.IMovementService;
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
@RequestMapping("/movimientos")
public class MovementController {

    @Autowired
    private IMovementService movementService;

    @GetMapping()
    public ResponseEntity<ResponseHandler<List<MovementDTO>>> findAll(){
        ResponseHandler<List<MovementDTO>> responseHandler;
        try {
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(HttpStatus.OK.value())
                    .value(this.movementService.findAll()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        } catch (Exception e) {
            responseHandler = ResponseHandler.<List<MovementDTO>>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(null).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseHandler<Object>> findById(@PathVariable("id") Integer id){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.OK.value())
                    .value(this.movementService.findById(id)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ResponseHandler<Object>> save(@RequestBody() MovementRequestDTO request){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(this.movementService.save(request)).build();
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
                message = "El movimiento ya existe. Por favor, verifica los datos.";
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

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseHandler<Object>> updateById(@PathVariable("id") Integer id, @RequestBody() MovementDTO movementDTO){
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(this.movementService.updateById(id, movementDTO)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.CREATED);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseHandler<Boolean>> deleteById(@PathVariable("id") Integer id){
        ResponseHandler<Boolean> responseHandler;
        try {
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.OK.value())
                    .value(this.movementService.deleteById(id)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        } catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

}
