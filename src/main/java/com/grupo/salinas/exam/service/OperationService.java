package com.grupo.salinas.exam.service;

import com.grupo.salinas.exam.model.RespValues;
import com.grupo.salinas.exam.model.ResponseOperation;
import com.grupo.salinas.exam.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
                       
@Service
public class OperationService {
    @Autowired
    OperationRepository operationRepository;

    public ArrayList getFibonacciOperation(int n) {

        ArrayList listResults = new ArrayList<>();
        
       //si no cumple con la validacion, nos retorna la lista vacia 
        if (validaRequest(n)) {
        	//En caso de aprobar la validacion que me continue con lo siguiente:
        	//llamamos el metodo guardarRequest de la clase operationRepository para guardar el fichero en la ruta especificada
            operationRepository.guardarRequest("fibonacci", n);
            
            //Despues de validar el metodo guardarRequest se procede a recorrer con un for con el indice
            //i que inicie de 0, sea menor que el limite"n" y aunmente su valor de 1 en 1.
            for (int i = 0; i < n; i++) {
            	//se almacenan los resultados de la serie de fibonacci 
                listResults.add(operationRepository.fibonacci(i));
            }
        }
        return listResults;
    }
    //Hacemos validacion del request
    private boolean validaRequest(int limite) {
        if (limite > 100 && limite <= 0)
            return false;
        else
            return true;
    }
    
    public ArrayList getListRequest(String name){
        return operationRepository.getRequest(name);
    }
    public RespValues deleteOperation(String name, int val){
        RespValues resp = new RespValues();
        boolean eliminado = operationRepository.deleteRequest(name,val);
        if(eliminado){
            resp.setDescripcion("Se ha eliminado el registro exitosamente");
            resp.setCode(1);
        }else {
            resp.setDescripcion("No se ha eliminado el registro");
            resp.setCode(0);
        }

        return resp;
    }

}
