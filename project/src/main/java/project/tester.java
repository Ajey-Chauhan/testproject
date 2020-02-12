package project;

import static org.junit.Assert.*;

import org.junit.Test;

public class tester {
	String jsonData;
	String actualData;
	
	tester(String data, String result){
		jsonData=data;	
		actualData=result;
	}
	
	public String parser() {
	
		return jsonData;
	}
	
	@Test
	public void test() {

        String result = parser();
        

        assertEquals(actualData, result);
	}
	

}
