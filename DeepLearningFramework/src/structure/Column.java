
package structure;

import java.io.Serializable;

public class Column implements Serializable {
    
    public enum Type {feature, label};
    public enum Content {text, enumerated, int_number, float_number, boolean_value};
    public enum NullFill {none, medium, discard};
    
    private Type type;
    private String name;
    private Content content;
    private int min_value;
    private int max_value;
    private boolean irrelevant;
    private NullFill null_fill;
    private String[] enumerated_element_set;
    boolean join; // only for : type = label

    public Column(String type_str, String name, String content_str, String min_value_str,
        String max_value_str, String irrelevant_str, String null_fill_str,
        String enumerated_elements_str, String join_str) {
        this.type = toType (type_str);
        this.name = name;
        this.content = toContent(content_str);
        if (min_value_str != null) this.min_value = Integer.parseInt(min_value_str);
        else this.min_value = -1;
        if (max_value_str != null) this.max_value = Integer.parseInt(max_value_str);
        else this.max_value = -1;
        if (irrelevant_str != null) this.irrelevant = Boolean.parseBoolean(irrelevant_str);
        else this.irrelevant = false;
        if (null_fill_str != null) this.null_fill = toNullFill (null_fill_str);
        else this.null_fill = null;
        if (enumerated_elements_str != null)
            this.enumerated_element_set = enumerated_elements_str.split(" | ");
        else this.enumerated_element_set = null;
        if (join_str != null) this.join = Boolean.parseBoolean(join_str);
        else this.join = false;
    }

    public Type getType() { return type; }
    public String getName() { return name; }
    public Content getContent() { return content; }
    public boolean isIrrelevant() { return irrelevant; }
    public NullFill getNullFill() { return null_fill; }
    public String[] getEnumeratedElements() { return enumerated_element_set; }
    public boolean isJoin() { return join; }
    
    public Type toType (String type_str) {
        switch(type_str) {
            case "feature": return Type.feature;
            case "label": return Type.label;
            default: return null;
        }
    }    
    
    public Content toContent (String content_str) {
        switch(content_str) {
            case "text": return Content.text;
            case "enumerated": return Content.enumerated;
            case "int": return Content.int_number;
            case "float": return Content.float_number;
            case "boolean": return Content.boolean_value;
            default: return null;
        }
    }
    
    public NullFill toNullFill (String null_fill_str) {
        switch(null_fill_str) {
            case "none": return NullFill.none;
            case "medium": return NullFill.medium;
            case "discard": return NullFill.discard;
            default: return null;
        }
    }
    
    public String toString() {
        String column_str = "Feature " + name + " : ";
        if (type == Type.label) column_str = "Label " + name + " : ";
        if (irrelevant) column_str += "irrelevant - content = " + content;
        else column_str += "content = " + content;
        if ((content == Content.int_number) || (content == Content.float_number)) {
            column_str += " [" + min_value + ", " + max_value + "] = " + null_fill;
        } else if (content == Content.enumerated) {
            column_str += " { ";
            for (String enumerated_element : enumerated_element_set) {
                column_str += enumerated_element + " ";
            }
            column_str += "}";
        }
        if (type == Type.label) column_str += " - join";
        return column_str;
    }
    
}
