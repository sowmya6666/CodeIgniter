/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sowmya
 */
@WebService(serviceName = "StudentDetails")
public class StudentDetails {

    StudentObjects stObj = new StudentObjects();

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        String response = stObj.getJaxBObjects(txt);
        return response;
    }
}
