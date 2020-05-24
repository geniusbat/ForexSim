package aiss.model.repository;

public class Purchase {
	
	private String id;
	private String ticker;
	private Double quantity;
	private String date;
	
	public Purchase() {}

	public Purchase(String id, String ticker, Double quantity, String date) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.quantity = quantity;
		this.date = date;
	}
	
	public Purchase(String ticker, Double quantity, String date) {
		super();
		this.ticker = ticker;
		this.quantity = quantity;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


}
