package aiss.model.repository;

public class Compra {
	
	private String id;
	private String ticker;
	private Double cantidad;
	
	public Compra() {}

	public Compra(String id, String ticker, Double cantidad) {
		super();
		this.id = id;
		this.ticker = ticker;
		this.cantidad = cantidad;
	}
	
	public Compra(String ticker, Double cantidad) {
		super();
		this.ticker = ticker;
		this.cantidad = cantidad;
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

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

}
