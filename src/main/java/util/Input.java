package util;

import exceptions.AutomationException;

import java.io.FileInputStream;
import java.util.Properties;

public class Input {
    private static Properties inputProperties;
    private static final String FILE_PATH = "src\\main\\java\\config\\input.properties";

    public Input() {
        loadProperties();
    }

    public void loadProperties() {
        try {
            inputProperties = new Properties();
            inputProperties.load(new FileInputStream(FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomationException("Could not load Input");
        }
    }

    public String getProductCategory() {
        return inputProperties.getProperty("product.category");
    }

    public String getBudget() {
        return inputProperties.getProperty("budget");
    }

    public String getEMagUrl() {
        return inputProperties.getProperty("emag.url");
    }

    public String getPriceRunnerUrl() {
        return inputProperties.getProperty("pricerunner.url");
    }

    public String getCompariUrl() {
        return inputProperties.getProperty("compari.url");
    }

}
