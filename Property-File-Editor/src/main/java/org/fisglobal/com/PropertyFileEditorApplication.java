package org.fisglobal.com;


import org.fisglobal.com.controller.ListFileController;
import org.fisglobal.com.controller.PropertiesController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PropertiesController.class, ListFileController.class})
public class PropertyFileEditorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyFileEditorApplication.class, args);
	}
	 
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")
	            .allowedOrigins("http://localhost:4200")
	            .allowedMethods("PUT", "DELETE","GET", "POST");
////	            .allowedHeaders("header1", "header2", "header3")
////	            .exposedHeaders("header1", "header2")
//	            .allowCredentials(false).maxAge(3600);
	    }
}
