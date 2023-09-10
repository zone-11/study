package study.enums;

public class Enumeration {
	public static void main(String[] args) {
		TemperatureProduct product = TemperatureProduct.HOT;
		for(ITemperatureProduct iProduct : product.getValues()) {
		}
	}
}

enum TemperatureProduct {
	HOT(ITemperatureProduct.HotProduct.class),
	COLD(ITemperatureProduct.ColdProduct.class);
	
	private ITemperatureProduct[] values;
	private TemperatureProduct(Class<? extends ITemperatureProduct> kind) {
		values = kind.getEnumConstants();
	}
	public ITemperatureProduct[] getValues() { return values; }
}
interface ITemperatureProduct {
	enum HotProduct implements ITemperatureProduct { SOAP, TEA }
	enum ColdProduct implements ITemperatureProduct { ICECREAM, JUICE }
}