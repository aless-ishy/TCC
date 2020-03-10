
package structure;

import java.io.Serializable;

public class Project implements Serializable {
    
    public enum Metric {accuracy, precision, recall, F1};
    
    int project_file_index;
        
    private String title;
    private int epochs_n;
    private int batch_size;
    private Metric[] metric_set;
    private Data data;
    private Network network;

    public Project(String title, String epochs_n_str, String batch_size_str, String metrics_str,
        Data data, Network network) {
        this.title = title;
        this.epochs_n = Integer.parseInt(epochs_n_str);
        this.batch_size = Integer.parseInt(batch_size_str);
        this.metric_set = toMetricSet(metrics_str.split(" - "));
        this.data = data;
        this.network = network;
    }

    public String getTitle() { return title; }
    public int getEpochs_n() { return epochs_n; }
    public int getBatchSize() { return batch_size; }
    public Metric[] getMetricSet() { return metric_set; }
    public Data getData() { return data; }
    public Network getNetwork() { return network; }
    
    public Metric[] toMetricSet (String[] metric_str_set) {
        Metric[] metric_set = new Metric[metric_str_set.length];
        for (int n = 0; n < metric_str_set.length; n++) {
            switch(metric_str_set[n]) {
                case "accuracy": metric_set[n] = Metric.accuracy; break;
                case "precision": metric_set[n] = Metric.precision; break; 
                case "recall": metric_set[n] = Metric.recall; break;
                case "F1": metric_set[n] = Metric.F1; break;
            }
        }
        return metric_set;
    }
    
    public String populateExamplesCSV(int examples_total, float prediction_error_rate) {
        String CSV_examples = "";
        // TO DO
        return CSV_examples;
    }
    
    public void configureExecuteNetwork(String CVS_file_path) {
        // TO DO
    }
    
    public String toString() {
        String project_str = "Projet : " + title
            + "\n - epochs = " + epochs_n + " - batch size = " + batch_size
            + "\n - metrics = {" + metric_set[0];
        for (int n = 1; n < metric_set.length; n++) { project_str += " - " + metric_set[n]; }
        project_str += "}\n" + data.toString() + "\n" + network.toString();
        return project_str;
    }
    
}
