package com.grupo.salinas.exam.repository;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;

@Repository
public class OperationRepository {
	//Se genera el archivo txt en la siguente reuta
    private String fileName = "C:\\File\\requestOperations.txt";
    
    //Calculo de la operacion
    public int fibonacci(int n) {
        if (n > 1) {
        	//si se cumple, se aplica la siguiente formula de fibonaacci
            return fibonacci(n - 1) + fibonacci(n - 2);
            //en caso de que n sea igual a 1 me retorne un 1
        } else if (n == 1) {
            return 1;
          //si n es igual a 0 que me retorne un 0
        } else if (n == 0) {
            return 0;
            
        } else {
            System.out.println("Error el numero debe ser mayor a 0");
            return -1;
        }
    }

    public void guardarRequest(String operacion, Object valor) {
        try {
        	//Creamos un nuevo archivo que será el archivo "principal"
            File actualFile = new File(fileName);
            //Y se crea otro archivo temporal
            File tempFile = new File(actualFile.getAbsolutePath() + ".tmp");
            
            //generamos un archivo para escribir en el y persistir la informacion
            PrintWriter write = new PrintWriter(tempFile, "UTF-8");
            if(actualFile.exists()) {
            //Leemos un archivo linea por linea
            BufferedReader br = new BufferedReader(new FileReader(actualFile));
            //almacenamos en line la linea leida 
            String line = br.readLine();
          //lo continuaremos haciendo hasta que line sea igual a null
            while (line != null) {
                write.println(line);
                line = br.readLine();
            }
            br.close();
            }
            //despues la escribimos en el archivo temporal que contendrá el tipo de operacion y su respectivo valor o respuesta
            write.println(operacion+"|"+valor);
            
            //cerramos ambos archivos
            write.close();
           
            //Eliminamos el archivo principal y en caso de no ser posible nos arrojará el sigiente mensaje
            if (!actualFile.delete()) {
                System.out.println("No se elimino el fichero principal");
            }
            //Renombramos el archivo temporal por el actual para que "ocupe su lugar" y en caso de no ser posible arrojá el siguiente mensaje
            if (!tempFile.renameTo(actualFile)) {
                System.out.println("No se pudo renombrar el fichero tem");
            }
            
            //Cachamos las excepciones y que nos muestre toda la traza de porque fue el error y donde se generó
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList getRequest(String operacion) {
        ArrayList listOperacion = new ArrayList<>();
        try {
        	//leemos linea por linea el archivo txt que se haya generado en la direccion proporcionada  a fileName
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            //Almacenamos las lineas leidas en line
            String line = reader.readLine();
            //y mientras que line sea diferente de null, seguirá leyendo la linea que contiene la operacion
            while (line != null) {
                if (line.contains(operacion)) {
                    String[] val = line.split("\\|");
                    listOperacion.add(val[1]);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOperacion;
    }

    public boolean deleteRequest(String operacion, int value) {
        File actualFile = new File(fileName);
        File tempFile = new File(actualFile.getAbsolutePath() + ".tmp");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(actualFile));
            String line = reader.readLine();
            PrintWriter write = new PrintWriter(tempFile, "UTF-8");
            while (line != null) {
                if (!(operacion + "|" + value).trim().equals(line)) {
                    write.println(line);
                    write.flush();
                }
                line = reader.readLine();
            }
            write.close();
            reader.close();
            if (!actualFile.delete()) {
                return false;
            }
            if (!tempFile.renameTo(actualFile)) {
                return false;
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
