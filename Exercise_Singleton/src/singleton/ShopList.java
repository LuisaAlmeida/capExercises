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

	public Boolean verify(String item) {
		if(!shopList.contains(item) && (item.startsWith("Food") || item.startsWith("Other"))) {
			return true;
		}
		System.out.println("This item cannot be added");
		return false;
	}

	public boolean add(String item) {
		if(verify(item) == true) {
			shopList.add(item);
			return true;
		}
		return false;
	}

	public void downloadFood() {
		shopList.stream()
		.filter(item -> item.startsWith("Food"))
		.forEach(System.out::println);
	}

	public void downloadOther() {
		shopList.stream()
		.filter(item -> item.startsWith("Other"))
		.forEach(System.out::println);
	}

}
