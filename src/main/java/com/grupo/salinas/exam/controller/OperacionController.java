package com.grupo.salinas.exam.controller;

import com.grupo.salinas.exam.model.ResponseOperation;
import com.grupo.salinas.exam.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


//Trabajamos con el estándar de REST , los response y request llegan en formato JSON por default.
@RestController

//Indicamos el recurso general
@RequestMapping("/operacion")
public class OperacionController {
    @Autowired
    OperationService operationService;
    
    
    //Con este metodo calculamos la serie de Fibonacci
    
    //Recursos dependiendo la operacion y recibe una variable por medio de la anotacion @PathVariable
    //Retornará en el body una respuesta en formato Json
    @GetMapping(value="/fibonacci/{limite}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getFibonacci(@PathVariable("limite") int limite) {
    	
    	//Creamos un objeto de tipo ResponseOperation y lo inicializamos
        ResponseOperation response = new ResponseOperation();
        response.setNombre("fibonacci");
        
        //Por medio de operationService llamamos al metodo getFibonacciOperation
        ArrayList result = operationService.getFibonacciOperation(limite);
        if (!result.isEmpty()) {
            response.setResultList(result);
            response.setDescripcion("Operacion Exitosa");
            response.setCode(1);
        } else {
            response.setDescripcion("No cumple con las validaciones");
            response.setCode(0);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Creamos un metodo en donde mandamos a traer todas las peticiones que se generaron con el metodo getFibonacci 
    //Generamos un nuevo recurso en el cual llevará "/operacion" y posteriormente recivirá una variable "name" en este caso sería el nombre de la operacion
    @GetMapping("/{name}")
    public ResponseEntity getListPeticones(@PathVariable("name") String name) {
    	
    	//Aplicamos el metodo getListRequest de la clase operationService y lo almacenamos en un ArrayList con el nombre de result
        ArrayList result = operationService.getListRequest(name);
        //Con ResponseEntity 
        return new ResponseEntity<>(new ResponseOperation(name, result, (!result.isEmpty()) ? "Operacion Existosa" : "No se encontraron datos"
                , (!result.isEmpty()) ? 1 : 0), HttpStatus.OK);
    }

    //Generamos un nuevo recurso para eliminar alguna operacion en cual recibimos la operacion y el valor a eliminar
    @DeleteMapping("/{name}/{val}")
    public ResponseEntity deleteOperacion(@PathVariable("name") String name, @PathVariable("val") int val) {
        return new ResponseEntity<>(operationService.deleteOperation(name, val),HttpStatus.ACCEPTED);
    }

}
