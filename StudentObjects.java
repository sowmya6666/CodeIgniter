package com.webservices;


import comm.studentdetails.StudentRegistartionRq;
import comm.studentdetails.StudentRegistartionRs;
import java.io.File;
import java.io.StringReader;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sowmya
 */
public class StudentObjects {
    
    
     public String getJaxBObjects(String inputXml) {
        StudentRegistartionRs rs = null;
        File file = new File("/home/sowmya/Desktop/StudentFinal.xml");
        BigInteger studentId = BigInteger.ZERO;
        try {
           JAXBContext jaxbContext = JAXBContext.newInstance("comm.studentdetails");
            Unmarshaller createUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader= new StringReader(inputXml);
           StudentRegistartionRq stReq=(StudentRegistartionRq)createUnmarshaller.unmarshal(reader);
            
            String studentName = null;
            int stAge = 0;
            String stCity = null;
            String stHNo = null;
            String stState = null;
            String stStreet = null;
            int stPinCode = 0;
            Long mNo;
            String email = null;
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/StudentDb", "root", "123456");

            for (StudentRegistartionRq.Students.Student st : stReq.getStudents().getStudent()) {
                studentId = st.getId();
                studentName = st.getName();
                stAge = st.getAge().intValue();
                stCity = st.getAddress().getCity();
                stHNo = st.getAddress().getHNo();
                stState = st.getAddress().getState();
                stStreet = st.getAddress().getStreet();
                stPinCode = st.getAddress().getPincode().intValue();
                mNo = st.getMobilenumber().longValue();
                StringBuffer sb = new StringBuffer();
                sb.append(stHNo + "," + stStreet + "," + stCity + "," + stState + "," + stPinCode);

                System.out.println("StudentID :: " + studentId + "StudentName:: " + studentName + "Student Age:: " + stAge + "StudentCity :: " + stCity
                        + "StudentHno ::" + stHNo + "Student State :: " + stState + " Student Street :: " + stStreet + "StudentPincode ::" + stPinCode);

                String query = ("INSERT INTO `StudentDb`.`studentdetails` (`sno`, `sname`, `sage`, `smobileno`, `saddress`) VALUES (?,?,?,?,?)");

                //BigDecimal bd = new BigDecimal(mNo);
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, studentId.intValue());
                preparedStmt.setString(2, studentName);
                preparedStmt.setInt(3, stAge);
                preparedStmt.setLong(4, mNo);
                preparedStmt.setString(5, sb.toString());

                // execute the preparedstatement
                preparedStmt.execute();

            }
            rs= new StudentRegistartionRs();
            rs.setId(studentId);
            rs.setSuccess("Registration is successfully completed");
            Marshaller marshall=jaxbContext.createMarshaller();
            marshall.marshal(rs, file);
            System.out.println(rs.toString());
           
            }catch(Exception e){
                rs.setId(studentId);
                rs.setFailure("Registartion is failed");
            e.printStackTrace();
        }
     
        return rs.toString() ;      
        
     }
}
