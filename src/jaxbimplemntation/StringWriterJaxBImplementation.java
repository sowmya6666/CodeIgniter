/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxbimplemntation;

import comm.studentdetails.StudentRegistartionRq;
import java.io.StringWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author sowmya
 */
public class StringWriterJaxBImplementation {

    public static void main(String[] args) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance("com.student");
            StringWriter sw = new StringWriter();
            String marshallObject = null;
            marshallObject = getJaxBMarshallObject(jaxbContext, sw);
            System.out.println("Marshalled Object" + marshallObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getJaxBMarshallObject(JAXBContext jaxbContext, StringWriter sw) throws NumberFormatException, JAXBException {
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        StudentRegistartionRq stReq = new StudentRegistartionRq();
        StudentRegistartionRq.Students.Student student = new StudentRegistartionRq.Students.Student();
        List<StudentRegistartionRq.Students.Student.Address> addList = new ArrayList<StudentRegistartionRq.Students.Student.Address>();
        StudentRegistartionRq.Students.Student.Address address1 = new StudentRegistartionRq.Students.Student.Address();
        address1.setCity("Visakhapatnam");
        address1.setHNo("3 Ekata");
        address1.setPincode(BigInteger.valueOf(Long.valueOf("530014")));
        address1.setState("Andhra Pradesh");
        address1.setStreet("Navala Base");
        addList.add(address1);
        StudentRegistartionRq.Students.Student.Address address2 = new StudentRegistartionRq.Students.Student.Address();
        address2.setCity("Visakhapatnam");
        address2.setHNo("2G");
        address2.setPincode(BigInteger.valueOf(Long.valueOf("530013")));
        address2.setState("Andhra Pradesh");
        address2.setStreet("Seethammadhara");
        addList.add(address1);
        addList.add(address2);
        student.setId(BigInteger.ONE);
        student.setAddress(address2);
        student.setAge(BigInteger.valueOf(26));
        student.setName("Sowmya");
        student.setMobilenumber(BigInteger.valueOf(Long.valueOf("8220568318")));
        StudentRegistartionRq.Students ss = new StudentRegistartionRq.Students();
        ss.getStudent().add(student);
        stReq.setStudents(ss);
        jaxbMarshaller.marshal(stReq, sw);

        return sw.toString();
    }
}
