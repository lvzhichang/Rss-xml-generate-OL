package onem.cjq.web.test;

import org.junit.Test;

import onem.cjq.web.database.DatabaseInit;

public class testcase {
	@Test
    public void getConnection(){
		DatabaseInit.getInstance();
	}

}
