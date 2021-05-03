package main;

public class Principal{
	
	public static void main(String[] args) {
		a(100);
		System.out.println("terminando execu��o de main()");
	}
	
	public static void a(int i){
		System.out.println("executar a() com "+i);
		try {
			b(100);
		}catch(Exception e) {
			System.out.println("tratando exce��o: "+ e.getMessage());
		}
		
		System.out.println("terminando execu��o de a()");
	}
	
	public static void b(int i) throws Exception{
		System.out.println("executar b() com " + i);
		throw new Exception("mensagem");
	}
	
}
