package api.resources.comparators;

import java.time.LocalDate;
import java.util.Comparator;

import aiss.model.repository.Purchase;

public class ComparatorDatePurchase implements Comparator<Purchase> {

	@Override
	public int compare(Purchase o1, Purchase o2) {
		// TODO Auto-generated method stub
		
		/*String s1 = o1.getDate().substring(8, 10);
		if( s1.startsWith("0")){
			s1 = s1.substring(1);
		}
		int day1 = Integer.valueOf(s1);
		
		String s2 = o2.getDate().substring(8, 10);
		if( s2.startsWith("0")){
			s2 = s2.substring(1);
		}
		int day2 = Integer.valueOf(s1);*/
		
		return LocalDate.of(Integer.valueOf(o2.getDate().substring(0, 4)), Integer.valueOf(o2.getDate().substring(5, 7)), Integer.valueOf(o2.getDate().substring(8, 10))).compareTo(LocalDate.of(Integer.valueOf(o1.getDate().substring(0, 4)), Integer.valueOf(o1.getDate().substring(5, 7)), Integer.valueOf(o1.getDate().substring(8, 10))));
	}

}
