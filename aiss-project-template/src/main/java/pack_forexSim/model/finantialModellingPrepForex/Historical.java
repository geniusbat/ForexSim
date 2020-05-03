package pack_forexSim.model.finantialModellingPrepForex;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"date",
	"open",
	"low",
	"high",
	"close",
	"volume"
})
public class Historical {

	@JsonProperty("date")
	private String date;
	@JsonProperty("open")
	private Double open;
	@JsonProperty("low")
	private Double low;
	@JsonProperty("high")
	private Double high;
	@JsonProperty("close")
	private Double close;
	@JsonProperty("volume")
	private Integer volume;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public Historical() {
	}

	/**
	 *
	 * @param date
	 * @param volume
	 * @param high
	 * @param low
	 * @param close
	 * @param open
	 */
	public Historical(String date, Double open, Double low, Double high, Double close, Integer volume) {
		super();
		this.date = date;
		this.open = open;
		this.low = low;
		this.high = high;
		this.close = close;
		this.volume = volume;
	}

	@JsonProperty("date")
	public String getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
	}

	public Historical withDate(String date) {
		this.date = date;
		return this;
	}

	@JsonProperty("open")
	public Double getOpen() {
		return open;
	}

	@JsonProperty("open")
	public void setOpen(Double open) {
		this.open = open;
	}

	public Historical withOpen(Double open) {
		this.open = open;
		return this;
	}

	@JsonProperty("low")
	public Double getLow() {
		return low;
	}

	@JsonProperty("low")
	public void setLow(Double low) {
		this.low = low;
	}

	public Historical withLow(Double low) {
		this.low = low;
		return this;
	}

	@JsonProperty("high")
	public Double getHigh() {
		return high;
	}

	@JsonProperty("high")
	public void setHigh(Double high) {
		this.high = high;
	}

	public Historical withHigh(Double high) {
		this.high = high;
		return this;
	}

	@JsonProperty("close")
	public Double getClose() {
		return close;
	}

	@JsonProperty("close")
	public void setClose(Double close) {
		this.close = close;
	}

	public Historical withClose(Double close) {
		this.close = close;
		return this;
	}

	@JsonProperty("volume")
	public Integer getVolume() {
		return volume;
	}

	@JsonProperty("volume")
	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public Historical withVolume(Integer volume) {
		this.volume = volume;
		return this;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public Historical withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	

}