
package pack_forexSim.model.telegram;

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
    "id",
    "title",
    "type",
    "all_members_are_administrators"
})
public class Chat {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("type")
    private String type;
    @JsonProperty("all_members_are_administrators")
    private Boolean allMembersAreAdministrators;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Chat() {
    }

    /**
     * 
     * @param allMembersAreAdministrators
     * @param id
     * @param title
     * @param type
     */
    public Chat(Integer id, String title, String type, Boolean allMembersAreAdministrators) {
        super();
        this.id = id;
        this.title = title;
        this.type = type;
        this.allMembersAreAdministrators = allMembersAreAdministrators;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("all_members_are_administrators")
    public Boolean getAllMembersAreAdministrators() {
        return allMembersAreAdministrators;
    }

    @JsonProperty("all_members_are_administrators")
    public void setAllMembersAreAdministrators(Boolean allMembersAreAdministrators) {
        this.allMembersAreAdministrators = allMembersAreAdministrators;
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
