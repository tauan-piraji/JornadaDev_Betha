package main;

import org.junit.*;

public class AfterBefore{

	@Before
	public void before() {
		System.out.println("@Before");
	}
	
	@After
	public void after() {
		System.out.println("@after");
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("@BeforeClass");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("@afterClass");
	}
	
	@Test
	public void test1() {
		System.out.println("teste1");
	}

	@Test
	public void test2() {
		System.out.println("teste2");
	}
	
	@Test
	public void test3() {
		System.out.println("teste3");
	}
}