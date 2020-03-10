
package structure;

import java.io.Serializable;

public class Layer implements Serializable {
    
    String type;
    String random_initialization;
    String activation_function;
    int input_nodes_n;
    int output_nodes_n;

    public Layer(String type, String random_initialization, String activation_function,
        String input_nodes_n_str, String output_nodes_n_str) {
        this.type = type;
        this.random_initialization = random_initialization;
        this.activation_function = activation_function;
        this.input_nodes_n = Integer.parseInt(input_nodes_n_str);
        this.output_nodes_n = Integer.parseInt(output_nodes_n_str);
    }

    public String getType() { return type; }
    public String getRandomInitialization() { return random_initialization; }
    public String getActivationFunction() { return activation_function; }
    public int getInputNodesN() { return input_nodes_n; }
    public int getOutputNodesN() { return output_nodes_n; }
    
    public String toString() {
        String layer_str = "Layer " + type
            + " : nodes [" + input_nodes_n + ", " + output_nodes_n + "] - weight random initialization = "
            + random_initialization + " - activation_function = " + activation_function;
        return layer_str;
    }
    
}
