package com.learncamel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import com.learncamel.routes.XMLToJSONRoute;

public class XMLToJSONRouteTest extends CamelTestSupport {

	@Override
	public RouteBuilder createRouteBuilder() {
		return new XMLToJSONRoute();
	}
	
	@Test
	public void marshalEmployeeToJsonXML() {
		
		String expected = "{\"id\":\"123\",\"name\":\"dilip\",\"joinDate\":\"12JAN2017\"}";
		String input = "<?xml version='1.0' encoding='UTF-8'?><employee><id>123</id><name>dilip</name><joinDate>12JAN2017</joinDate></employee>";

		String response = template.requestBody("direct:marshalEmployeexml2json", input, String.class);
	
		
		assertEquals(expected, response);
	}
	
	@Test
	public void unmarshalEmployeeToXMLJson() {
		
		String input =  "{\"id\":\"123\",\"name\":\"dilip\",\"joinDate\":\"12JAN2017\"}";
		String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<o><id>123</id><joinDate>12JAN2017</joinDate><name>dilip</name></o>\r\n";

		String response = template.requestBody("direct:unMarshalEmployeejson2xml", input, String.class);
	
		assertEquals(expected, response);
	}
}
