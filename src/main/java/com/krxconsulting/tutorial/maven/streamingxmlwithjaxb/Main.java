/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krxconsulting.tutorial.maven.streamingxmlwithjaxb;

import java.io.FileOutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import com.krxconsulting.tutorial.maven.streamingxmlwithjaxb.MarketOrders;

/**
 *
 * @author regis
 */
public class Main {
	
  public static void main(String[] args) throws Exception {

             Main instance  = new Main();
             String outPutFile = "C:/Users/regis/Documents/projets/java/netbeans/masteringXmlDate/streamingXmlWithJaxb/target/marketcac.xml" ; 
	     System.out.println(" running streaming with jaxb !\n");
             try {
             JAXBContext jContext = JAXBContext.newInstance(MarketOrders.class);
             Marshaller marshallObj = jContext.createMarshaller();
             marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
             
             MarketOrders marketCac = instance.getDataToMArshall();
             marshallObj.marshal(marketCac, new FileOutputStream(outPutFile));

             System.out.println("Succesfully load context !\n");
             } catch (Exception ex ) {
                 System.err.println(" A Exception occurs on instancing context  !\n");
             }
      String csvPath = "C:/Users/regis/Documents/projets/java/netbeans/masteringXmlDate/streamingXmlWithJaxb/src/main/resources/cotations.csv";

      CsvFileAsStream iterOnCsv = new CsvFileAsStream(csvPath);
      while (iterOnCsv.hasNext()) {
          System.out.println(iterOnCsv.next());
      }

  }
  
  public MarketOrders getDataToMArshall() {

      MarketOrders  marketCac = new ObjectFactory().createMarketOrders();

      return marketCac;
  }
  
}
