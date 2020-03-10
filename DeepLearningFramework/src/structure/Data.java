
package structure;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {
    
    public enum Type {features_label, examples, train_test};
    
    private Type type;
    private int split_percentage; // only for : type IN {features_label | examples}
    private ArrayList<Column> column_set = new ArrayList();

    public Data(String type_str, String split_percentage_str) {
        this.type = toType (type_str);
        if (split_percentage_str != null)
            this.split_percentage = Integer.parseInt(split_percentage_str);
        else this.split_percentage = -1;
    }
    public void addColumn(Column column) { column_set.add(column); }

    public Type getType() { return type; }
    public int getSplitPercentage() { return split_percentage; }
    public ArrayList<Column> getColumnSet() { return column_set; }
    
    public Type toType (String type_str) {
        switch(type_str) {
            case "features_label": return Type.features_label;
            case "examples": return Type.examples; 
            case "recall": return Type.train_test;
            default: return null;
        }
    }
    
    public String toString() {
        String data_str = "Data\n - Input : type = " + type
            + " - train/test split percentage = " + split_percentage + "%";
        for (Column column : column_set) { data_str += "\n - " + column.toString(); }
        return data_str;
    }
    
}
