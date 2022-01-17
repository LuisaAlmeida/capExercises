package singleton;

public class Main {
	
	public static void main(String[] args) {
		
		ShopList instance = ShopList.getInstance();
		String item1 = "Food-Atum";
		String item2 = "Other-Papel";
		String item3 = "Food-Maçã";
		String item4 = "Other-Sabão";
		String item5 = "Food-Atum";
		
		instance.add(item1);
		instance.add(item2);
		instance.add(item3);
		instance.add(item4);
		instance.add(item5);
		
		System.out.println();
		instance.downloadFood();
		System.out.println();
		instance.downloadOther();
		System.out.println();
	
		
		
		
		
		
	}

}
