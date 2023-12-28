import insurance.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class InsuranceApp {
    public static void main(String[] args) {
        try {
            Properties properties = loadProperties("insurance.properties");

            Автострахування автострахування = createAutoInsurance(properties);
            Життєве_страхування життєве_страхування = createLifeInsurance(properties);

            Зобовязання[] зобовязання = {автострахування, життєве_страхування};
            Дериватив дериватив = new Дериватив("Пакет страхування", зобовязання);

            System.out.println(дериватив.toString());

            double загальнаВартість = дериватив.розрахуватиВартість();
            System.out.println("Загальна вартість деривативу: " + загальнаВартість);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties loadProperties(String filename) throws IOException {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filename)) {
            properties.load(input);
        }
        return properties;
    }

    private static Автострахування createAutoInsurance(Properties properties) {
        String назваАвтострахування = properties.getProperty("назваАвтострахування");
        double вартістьАвтострахування = Double.parseDouble(properties.getProperty("вартістьАвтострахування"));
        double ризикАвтострахування = Double.parseDouble(properties.getProperty("ризикАвтострахування"));
        int рікВипуску = Integer.parseInt(properties.getProperty("рікВипуску"));

        return new Автострахування(назваАвтострахування, вартістьАвтострахування, ризикАвтострахування, рікВипуску);
    }

    private static Життєве_страхування createLifeInsurance(Properties properties) {
        String назваЖиттєвогоСтрахування = properties.getProperty("назваЖиттєвогоСтрахування");
        double вартістьЖиттєвогоСтрахування = Double.parseDouble(properties.getProperty("вартістьЖиттєвогоСтрахування"));
        double ризикЖиттєвогоСтрахування = Double.parseDouble(properties.getProperty("ризикЖиттєвогоСтрахування"));
        int термінДії = Integer.parseInt(properties.getProperty("термінДії"));

        return new Життєве_страхування(назваЖиттєвогоСтрахування, вартістьЖиттєвогоСтрахування, ризикЖиттєвогоСтрахування, термінДії);
    }
}