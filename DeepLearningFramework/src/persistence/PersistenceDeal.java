
package persistence;

import java.io.File;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import persistence.FilePersistence;
import structure.Column;
import structure.Data;
import structure.Layer;
import structure.Network;
import structure.Project;

public class PersistenceDeal {
    
    private int project_file_index;

    public PersistenceDeal(int project_file_index) { this.project_file_index = project_file_index; }
        
    public void convertXML_SaveDescription() {
        File description_file = getSelectedFile("corpus/xml", project_file_index);
        String description_file_name = getFileName(description_file, "xml");
        Project project = extractProject(description_file);
        FilePersistence.save(project, "corpus/description/" + description_file_name + ".desc");
    }
    
    public Project retrieveDescription() {
        String description_file_name = getFilePath("description", "desc");
        return (Project) FilePersistence.load("corpus/description/" + description_file_name + ".desc");
    }
    
    public void saveData(String project_examples) {
        String description_file_name = getFilePath("description", "desc");
        FilePersistence.save(project_examples, "corpus/data/" + description_file_name + ".csv");
    }
        
    public Project extractProject(File description_file) {
        Document document = null;
        SAXBuilder builder = new SAXBuilder();
        try { document = builder.build(description_file); }
        catch (JDOMException exception) { System.out.println("Erro de JDOM " + exception.getMessage()); }
        catch (IOException exception) { System.out.println("Erro de IO " + exception.getMessage()); }
        Element project_element = (Element) document.getRootElement();
        String title = project_element.getAttributeValue("title");
        Element data_element = project_element.getChild("data");
        Data data = extractData(data_element);
        Element network_element = project_element.getChild("network");
        Network network = extractNetwork(network_element);
        Element hyper_parameters_element = project_element.getChild("hyper_parameters");
        String epochs_n = hyper_parameters_element.getAttributeValue("epochs_n");
        String batch_size = hyper_parameters_element.getAttributeValue("batch_size");
        Element metrics_element = project_element.getChild("metrics");
        String metrics_str = metrics_element.getValue();
        return new Project(title, epochs_n, batch_size, metrics_str, data, network);
    }
    
    private Data extractData(Element data_element) {
        Element input_element = data_element.getChild("input");
        String type_str = input_element.getAttributeValue("type");
        String split_percentage_str = input_element.getAttributeValue("split_percentage");
        Data data = new Data(type_str, split_percentage_str);
        for (Element feature_element : data_element.getChildren("feature")) {
            data.addColumn(extractColumn(feature_element, true));
        }
        Element label_element = data_element.getChild("label");
        data.addColumn(extractColumn(label_element, false));
        return data; 
    }
    
    private Column extractColumn(Element column_element, boolean feature) {
        String name = column_element.getAttributeValue("name");
        String content_str = column_element.getAttributeValue("content");
        String enumerated_elements_str = null;
        if (content_str.indexOf("|") > 0) {
            enumerated_elements_str = content_str;
            content_str = "enumerated";
        }
        String type_str;
        String min_value_str = column_element.getAttributeValue("min");
        String max_value_str = column_element.getAttributeValue("max");
        String irrelevant_str = null;
        String null_fill_str = null;
        String join_str = null;
        if (feature) {
            type_str = "feature";
            irrelevant_str = column_element.getAttributeValue("irrelevant");
            null_fill_str = column_element.getAttributeValue("null_fill");
        } else {
            type_str = "label";
            join_str = column_element.getAttributeValue("join");
        }
        return new Column(type_str, name, content_str, min_value_str, max_value_str,
            irrelevant_str, null_fill_str, enumerated_elements_str, join_str);
    }
    
    private Network extractNetwork(Element network_element) {
        String seed_str = network_element.getAttributeValue("seed");
        String loss_funtion = network_element.getAttributeValue("loss_funtion");
        String optimization = network_element.getAttributeValue("optimization");
        String updater = network_element.getAttributeValue("updater");
        String learning_rate_str = network_element.getAttributeValue("learning_rate");
        String pretrain_str = network_element.getAttributeValue("pretrain");
        String back_propagation_str = network_element.getAttributeValue("back_propagation");
        Network network = new Network(seed_str, loss_funtion, optimization, updater,
            learning_rate_str, pretrain_str, back_propagation_str);
        for (Element layer_element : network_element.getChildren("layer")) {
            network.addLayer(extractLayer(layer_element));
        }        
        return network;
    }
    
    private Layer extractLayer(Element layer_element) {
        String type = layer_element.getAttributeValue("type");
        String random_initialization = layer_element.getAttributeValue("random");
        String activation_function = layer_element.getAttributeValue("activation");
        String input_nodes_n_str = layer_element.getAttributeValue("input");
        String output_nodes_n_str = layer_element.getAttributeValue("output");
        return new Layer(type, random_initialization, activation_function, input_nodes_n_str,
            output_nodes_n_str);
    }
    
    public String getFilePath(String sub_directory, String extension) {
        File file = getSelectedFile("corpus/" + sub_directory, project_file_index);
        String file_name = getFileName(file, extension);
        return file_name;
    }
    
    private File getSelectedFile(String file_path, int file_index) {
        return new File(file_path).listFiles()[file_index - 1];      
    }
    
    private String getFileName(File file, String file_extension) {
        return file.getName().split("." + file_extension)[0];
    }
    
}
