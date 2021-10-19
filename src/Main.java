
public class Main {
	
	private Champ ch;

	Main() {
		System.out.println("main");
		Client Client1 = new Client();
		Client1.connectToServer();
		System.out.println("after Client");
		System.out.println("main");
		Client Client2 = new Client();
		Client2.connectToServer();
		System.out.println("after Client");
		System.out.println("main");
		Client Client3= new Client();
		Client3.connectToServer();
		System.out.println("after Client");
	}

	public static void main(String[] args) {
		new Main();
	}
}
