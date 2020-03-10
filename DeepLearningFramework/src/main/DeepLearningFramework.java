
package main;

import persistence.PersistenceDeal;
import structure.Project;

public class DeepLearningFramework {

    public static void main(String[] args) {
        int project_file_index = 1;
        PersistenceDeal persistence_deal = new PersistenceDeal(project_file_index);
        Project project;
        int option = 2;
        switch (option) {

            case 1: // Converts XML - Saves Description Project 
                    persistence_deal.convertXML_SaveDescription();
                    break;

            case 2: // Retrieves Description Project - Populates Project Examples
                    project = persistence_deal.retrieveDescription();
                    System.out.println(project.toString());
                    int examples_total = 10000;
                    float prediction_error_rate = 0;                    
//                    String project_examples = project.populateExamplesCSV
//                        (examples_total, prediction_error_rate);
//                    persistence_deal.saveData(project_examples);
                    break;
                    
            case 3: // Retrieves Description Project - Executes Project Network
                    project = persistence_deal.retrieveDescription();
                    String CVS_file_path = "corpus/data/"
                        + persistence_deal.getFilePath("data", "csv") + ".csv";
//                    project.configureExecuteNetwork(CVS_file_path);
                    break;

            default: break;
        }
    }

    
}
