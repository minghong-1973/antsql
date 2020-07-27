package ant.sql.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ant.sql.AntSqlService;

class JunitAntSqlTest {

	@Test
	void testWhere() {
		
		String[] rows= {"class,price,productname,sum","水果 ,10,苹果,20","水果 ,12,香蕉,18","肉类,12,猪肉,18"};
		AntSqlService service=new AntSqlService();
		System.out.println(service.antSqlService(rows, "(class=='水果' or class=='肉类') and price>10", null,0));
		
	}
	@Test
	void testWhere1() {
		
		String[] rows= {"class,price,productname,sum","水果 ,10,苹果,20","水果 ,12,香蕉,18","肉类,12,猪肉,18"};
		AntSqlService service=new AntSqlService();
		System.out.println(service.antSqlService(rows, "(class=='水果' or class=='肉类') and price!=10", null,0));
		
	}
	@Test
	void testWhere2() {
		
		String[] rows= {"class,price,productname,sum","水果 ,10,苹果,20","水果 ,12,香蕉,18","肉类,12,猪肉,18"};
		AntSqlService service=new AntSqlService();
		System.out.println(service.antSqlService(rows, "(class=='水果' or (class=='肉类' and price>5 )) and price!=10", null,0));
		
	}
	
	@Test
	void testGroup() {
		String[] rows= {"class,price,productname,sum","水果 ,10,苹果,20","水果 ,10,香蕉,18","肉类,12,猪肉,18"};
		AntSqlService service=new AntSqlService();
		System.out.println(service.antSqlService(rows, null, "class,price",1));
	}
	@Test
	void testLimit() {
		String[] rows= {"class,price,productname,sum","水果 ,10,苹果,20","水果 ,10,香蕉,18","肉类,12,猪肉,18"};
		AntSqlService service=new AntSqlService();
		System.out.println(service.antSqlService(rows, "class!='肉类'", null,2));
	}


}
