
package structure;

import java.io.Serializable;
import java.util.ArrayList;

public class Network implements Serializable {
    
    private int seed;
    private String loss_funtion;
    private String optimization;
    private String updater;
    private float learning_rate;
    private boolean pretrain;
    private boolean back_propagation;
    private ArrayList<Layer> layer_set = new ArrayList();

    public Network(String seed_str, String loss_funtion, String optimization, String updater,
        String learning_rate_str, String pretrain_str, String back_propagation_str) {
        this.seed = Integer.parseInt(seed_str);
        this.loss_funtion = loss_funtion;
        this.optimization = optimization;
        this.updater = updater;
        this.learning_rate = Float.parseFloat(learning_rate_str);
        this.pretrain =  Boolean.parseBoolean(pretrain_str);
        this.back_propagation = Boolean.parseBoolean(back_propagation_str);
    }
    public void addLayer(Layer layer) { layer_set.add(layer); }

    public int getSeed() { return seed; }
    public String getLossFuntion() { return loss_funtion; }
    public String getOptimization() { return optimization; }
    public String getUpdater() { return updater; }
    public float getLearningRate() { return learning_rate; }
    public boolean isPretrain() { return pretrain; }
    public boolean isBackPropagation() { return back_propagation; }
    public ArrayList<Layer> getLayerSet() { return layer_set; }
    
    public String toString() {
        String network_str = "Network Configuration" 
            + "\n - learning_rate = " + learning_rate+ " - seed = " + seed 
            + "\n   loss funtion = " + loss_funtion + " - optimization = " + optimization
            + " - updater = " + updater
            + "\n   pretrain = " + pretrain + " - back_propagation = " + back_propagation;
        for (Layer layer : layer_set) { network_str += "\n - " + layer.toString(); }
        return network_str;
    }    
    
}
