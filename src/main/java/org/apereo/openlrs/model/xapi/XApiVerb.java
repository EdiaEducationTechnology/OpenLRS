package org.apereo.openlrs.model.xapi;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Holds a representation of a statement verb
 *
 * @author Robert E. Long (rlong @ unicon.net)
 */
@JsonInclude(Include.NON_NULL)
public class XApiVerb implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1;

	/**
     * Corresponds to a Verb definition. Each Verb definition corresponds to the meaning of a Verb, not the word. 
     * The IRI should be human-readable and contain the Verb meaning.
     */
	@Field(type=FieldType.String,index=FieldIndex.not_analyzed) 
    @NotNull private String id;

    /**
     * The human readable representation of the Verb in one or more languages. This does not have any impact on 
     * the meaning of the Statement, but serves to give a human-readable display of the meaning already determined 
     * by the chosen Verb.
     * 
     * e.g.
     * "display":{
     *   "en-US":"ran",
     *   "es" : "corrió" 
     * }
     */
    private Map<String, String> display;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getDisplay() {
        return display;
    }

    public void setDisplay(Map<String, String> display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "Verb[id: " + id + ", display: " + display + "]";
    }
}
