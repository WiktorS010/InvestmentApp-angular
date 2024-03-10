package pl.stepien.investmentappangular;

import jakarta.annotation.PostConstruct;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
@Component
public class ExecuteSQLScript {
    @PostConstruct
    public void run() throws Exception {
        String url = "jdbc:mysql://localhost:3306/investmentappdb";

        Class.forName("com.mysql.cj.jdbc.Driver");

        String username = "Wiktor";
        String password = "bmxforever96";
        Connection connection = DriverManager.getConnection(url, username, password);

        File file = new File("C:\\Users\\stepi\\MyJavaProjects\\InvestmentApp-backend\\src\\main\\resources\\investmentappDataFile.sql");
        Resource resource = new FileSystemResource(file);
        EncodedResource encodedResource = new EncodedResource(resource);

        connection.setAutoCommit(false);

        ScriptUtils.executeSqlScript(connection, encodedResource);
        connection.setAutoCommit(true);

        connection.close();
    }

    public static void main(String[] args) {
        ExecuteSQLScript executeSQLScript = new ExecuteSQLScript();
        try {
            executeSQLScript.run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
