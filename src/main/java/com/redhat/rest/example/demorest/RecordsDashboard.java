package com.redhat.rest.example.demorest;


import com.google.gson.Gson;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;

@SpringBootApplication
@RestController
public class RecordsDashboard {

	public static void main(String[] args) {

		SpringApplication.run(RecordsDashboard.class, args);
	}

	@Value("${businessCentralUserName}")
	private String businessCentralUserName;

	@Value("${businessCentralPassword}")
	private String businessCentralPassword;

	@Value("${businessCentralUrl}")
	private String businessCentralUrl;

	@Value("${fileLocation}")
	private String fileLocation;

	@Value("${soapEndPoint}")
	private String soapEndPoint;

	@Value("${soapWsdlURL}")
	private String soapWsdlURL;

	@Value("${soapOperation}")
	private String soapOperation;


	public static final String BODY = "${body}";


	@Bean
	public RouteBuilder routeBuilder() {
		return new RouteBuilder() {


			private String host = "host="+businessCentralUserName+":"+businessCentralPassword+"@"+businessCentralUrl;


			@Override
			public void configure() throws Exception {

				File file = new File("/Users/sadhananandakumar/Documents/Developer/DM/transaction-analysis-spark/src/main/resources/inputTransactions.xls");

				FileInputStream fileInputStream = new FileInputStream(file);
				ExcelConverter excelConverter = new ExcelConverter();
                File alertFile = new File("/Users/sadhananandakumar/Documents/Developer/DM/transaction-analysis-spark/src/main/resources/alert.xls");


                ExcelConverterAlerts excelConverterAlerts  =new ExcelConverterAlerts();
                FileInputStream fileInputStream1 = new FileInputStream(alertFile);


				String json = new Gson().toJson(excelConverter.process(fileInputStream));


				rest("/records/transaction-analysis")
						.get().route().setBody().constant(json);

				String alertJson = new Gson().toJson(excelConverterAlerts.process(fileInputStream1));

                rest("/records/alerts")
                        .get().route().setBody().constant(alertJson);


				restConfiguration()
						.component("servlet")
						.bindingMode(RestBindingMode.auto)
						.producerComponent("http4").host("localhost:8097");


		}


	};


}}
