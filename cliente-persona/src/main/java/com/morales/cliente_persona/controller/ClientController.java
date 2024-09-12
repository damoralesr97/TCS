package com.morales.cliente_persona.controller;

import com.morales.cliente_persona.dto.ClientRequestDTO;
import com.morales.cliente_persona.dto.ClientResponseDTO;
import com.morales.cliente_persona.service.interfaces.IClientService;
import com.morales.cliente_persona.utils.ResponseHandler;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @GetMapping
    public ResponseEntity<ResponseHandler<List<ClientResponseDTO>>> findAll() {
        ResponseHandler<List<ClientResponseDTO>> responseHandler;
        try {
            responseHandler = ResponseHandler.<List<ClientResponseDTO>>builder().code(HttpStatus.OK.value())
                    .value(this.clientService.findAll()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        } catch (Exception e) {
            responseHandler = ResponseHandler.<List<ClientResponseDTO>>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(null).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{dni}")
    public ResponseEntity<ResponseHandler<Object>> findByDni(@PathVariable("dni") String dni) {
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.OK.value())
                    .value(this.clientService.findByDni(dni)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseHandler<Object>> save(@Valid @RequestBody() ClientRequestDTO clientRequestDTO) {
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(this.clientService.save(clientRequestDTO)).build();
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
                message = "El cliente ingresado ya existe. Por favor, verifica los datos.";
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

    @PatchMapping("/{dni}")
    public ResponseEntity<ResponseHandler<Object>> updateByDni(@PathVariable("dni") String dni, @RequestBody() ClientRequestDTO clientRequestDTO) {
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(this.clientService.updateByDni(dni, clientRequestDTO)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.CREATED);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{dni}")
    public ResponseEntity<ResponseHandler<Boolean>> deleteByDni(@PathVariable("dni") String dni) {
        ResponseHandler<Boolean> responseHandler;
        try {
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.OK.value())
                    .value(this.clientService.deleteByDni(dni)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        } catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

}
