package br.com.winecompany.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.winecompany.util.Mappers;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(Mappers.SWAGGER_BASE_PACKAGE))
				.paths(regex(Mappers.SWAGGER_API_PHISIC_STORES + ".*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {
		
		ApiInfo apiInfo = new ApiInfo(
				Mappers.SWAGGER_API_INFO_TITLE,
				Mappers.SWAGGER_API_INFO_DESCRIPTION,
				Mappers.SWAGGER_API_INFO_VERSION,
				Mappers.SWAGGER_API_INFO_TERM,
				new Contact(Mappers.SWAGGER_API_INFO_CONTACT_NAME, Mappers.SWAGGER_API_INFO_CONTACT_URL, Mappers.SWAGGER_API_INFO_CONTACT_EMAIL),
				Mappers.SWAGGER_API_INFO_LICENSE,
				Mappers.SWAGGER_API_INFO_LICENSE_URL, new ArrayList<VendorExtension>() 
		);  
		
		return apiInfo;
	}
	
	
}