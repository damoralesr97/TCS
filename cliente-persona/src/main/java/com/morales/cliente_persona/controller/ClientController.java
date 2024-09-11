package com.morales.cliente_persona.controller;

import com.morales.cliente_persona.dto.ClientDTO;
import com.morales.cliente_persona.service.interfaces.IClientService;
import com.morales.cliente_persona.utils.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ResponseHandler<List<ClientDTO>>> findAll() {
        ResponseHandler<List<ClientDTO>> responseHandler;
        try {
            responseHandler = ResponseHandler.<List<ClientDTO>>builder().code(HttpStatus.OK.value())
                    .value(this.clientService.findAll()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        } catch (Exception e) {
            responseHandler = ResponseHandler.<List<ClientDTO>>builder().code(HttpStatus.BAD_REQUEST.value())
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
    public ResponseEntity<ResponseHandler<Object>> save(@RequestBody() ClientDTO clientDTO) {
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(clientService.save(clientDTO)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.CREATED);
        } catch (Exception e){
            responseHandler = ResponseHandler.builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(e.getMessage()).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{dni}")
    public ResponseEntity<ResponseHandler<Object>> updateByDni(@PathVariable("dni") String dni, @RequestBody() ClientDTO clientDTO) {
        ResponseHandler<Object> responseHandler;
        try{
            responseHandler = ResponseHandler.builder().code(HttpStatus.CREATED.value())
                    .value(this.clientService.updateByDni(dni, clientDTO)).build();
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
        try{
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.OK.value())
                    .value(this.clientService.deleteByDni(dni)).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.OK);
        }catch (Exception e){
            responseHandler = ResponseHandler.<Boolean>builder().code(HttpStatus.BAD_REQUEST.value())
                    .value(Boolean.FALSE).build();
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
    }

}
