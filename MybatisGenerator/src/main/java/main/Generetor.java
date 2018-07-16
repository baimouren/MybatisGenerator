package main;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generetor {

    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {

        String configFilePath = System.getProperty("user.dir").concat("/src/main/resources/generatorConfig.xml");
        System.out.println("加载配置文件===" + configFilePath);
        boolean overwrite = true;
        File configFile = new File(configFilePath);

        List<String> warnings = new ArrayList<String>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        ProgressCallback progressCallback = new VerboseProgressCallback();
        // myBatisGenerator.generate(null);
        myBatisGenerator.generate(progressCallback);

        for (String warning : warnings) {
            System.out.println(warning);
        }
    }

}
