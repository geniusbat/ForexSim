
package pack_forexSim.model.finantialModellingPrep;

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
    "high",
    "low",
    "close",
    "adjClose",
    "volume",
    "unadjustedVolume",
    "change",
    "changePercent",
    "vwap",
    "label",
    "changeOverTime"
})
public class Historical {

    @JsonProperty("date")
    private String date;
    @JsonProperty("open")
    private Double open;
    @JsonProperty("high")
    private Double high;
    @JsonProperty("low")
    private Double low;
    @JsonProperty("close")
    private Double close;
    @JsonProperty("adjClose")
    private Double adjClose;
    @JsonProperty("volume")
    private Double volume;
    @JsonProperty("unadjustedVolume")
    private Double unadjustedVolume;
    @JsonProperty("change")
    private Double change;
    @JsonProperty("changePercent")
    private Double changePercent;
    @JsonProperty("vwap")
    private Double vwap;
    @JsonProperty("label")
    private String label;
    @JsonProperty("changeOverTime")
    private Double changeOverTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("open")
    public Double getOpen() {
        return open;
    }

    @JsonProperty("open")
    public void setOpen(Double open) {
        this.open = open;
    }

    @JsonProperty("high")
    public Double getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Double high) {
        this.high = high;
    }

    @JsonProperty("low")
    public Double getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Double low) {
        this.low = low;
    }

    @JsonProperty("close")
    public Double getClose() {
        return close;
    }

    @JsonProperty("close")
    public void setClose(Double close) {
        this.close = close;
    }

    @JsonProperty("adjClose")
    public Double getAdjClose() {
        return adjClose;
    }

    @JsonProperty("adjClose")
    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    @JsonProperty("volume")
    public Double getVolume() {
        return volume;
    }

    @JsonProperty("volume")
    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @JsonProperty("unadjustedVolume")
    public Double getUnadjustedVolume() {
        return unadjustedVolume;
    }

    @JsonProperty("unadjustedVolume")
    public void setUnadjustedVolume(Double unadjustedVolume) {
        this.unadjustedVolume = unadjustedVolume;
    }

    @JsonProperty("change")
    public Double getChange() {
        return change;
    }

    @JsonProperty("change")
    public void setChange(Double change) {
        this.change = change;
    }

    @JsonProperty("changePercent")
    public Double getChangePercent() {
        return changePercent;
    }

    @JsonProperty("changePercent")
    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    @JsonProperty("vwap")
    public Double getVwap() {
        return vwap;
    }

    @JsonProperty("vwap")
    public void setVwap(Double vwap) {
        this.vwap = vwap;
    }

    @JsonProperty("label")
    public String getLabel() {
        return label;
    }

    @JsonProperty("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @JsonProperty("changeOverTime")
    public Double getChangeOverTime() {
        return changeOverTime;
    }

    @JsonProperty("changeOverTime")
    public void setChangeOverTime(Double changeOverTime) {
        this.changeOverTime = changeOverTime;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
