package singleton;

import java.util.ArrayList;
import java.util.List;

public class ShopList {

	private static volatile ShopList instance;
	private final List<String> shopList;

	private ShopList() {
		if(instance != null) {
			throw new RuntimeException("Use getInstance() method to create");
		}
		shopList = new ArrayList<>();
	}

	public static ShopList getInstance() {
		if(instance == null) {
			synchronized(ShopList.class) {
				if(instance == null) {
					instance = new ShopList();
				}
			}
		}
		return instance;
	}

	public boolean add(String item) {
		if(shopList.isEmpty()) {
			if(item.startsWith("Food") || item.startsWith("Other")) {
				shopList.add(item);
				return true;
			}
		}
		if(verify(item) == true) {
			shopList.add(item);
			return true;
		} else {	
			return false;
		}
	}

	public Boolean verify(String item) {
		for(String i : shopList) {
			if(item.startsWith("Food") || item.startsWith("Other")) {
				System.out.println("Need to check if this item is already on the List");
				if(item.equals(i)) {
					System.out.println("This item already exist so it cannot be added");
					return false;
				}
			}
		}
		return true;
	}

	public void downloadFood() {
		for(String i : shopList) {
			if(i.startsWith("Food")) {
				System.out.println(i);
			}
		}
	}

	public void downloadOther() {
		for(String i : shopList) {
			if(i.startsWith("Other")) {
				System.out.println(i);
			}
		}
	}

}
