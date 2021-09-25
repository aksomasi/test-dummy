package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.example.demo.modal.Devices;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Builder;
import com.opencsv.bean.CsvToBeanBuilder;

@SpringBootApplication
public class BootJarApplication {

	static List<InputData> beans;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		SpringApplication.run(BootJarApplication.class, args);
		String fileName = "input.csv";

		beans = new CsvToBeanBuilder<InputData>(new FileReader(fileName)).withType(InputData.class).build().parse();

		/*
		 * beans.forEach(val -> { System.out.println(val.getIpAddress() + " , " +
		 * val.getHostName()); });
		 */

		String file = "devices.json";
		String json = "";
		try {
			System.out.println("read method starts");

			readDevices();
			System.out.println("read method ends");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);

			e.printStackTrace();
		}
		// resp = objectMapper.readValue(response, Student.class);

	}

	// testClass t = new testClass();
	// t.print(args);
	public static String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	@SuppressWarnings("unused")
	public static void readDevices() throws JsonProcessingException, IOException {
		List<JsonNode> nodes = new ArrayList<JsonNode>();
		List<List<String>> headersList = new ArrayList<List<String>>();

		JsonNode jsonTree = new ObjectMapper().readTree(new File("devices.json"));
		Builder csvSchemaBuilder = CsvSchema.builder();
		JsonNode firstObject = jsonTree.elements().next();
		JsonNode data = jsonTree.get("data");
		String appUrl = "http:www.camcost.com/apps/";
		List<Devices> devices = new ArrayList<Devices>();
		data.forEach(val -> {
			String deviceType = val.get("type").textValue();
			if (val != null && deviceType.equalsIgnoreCase("devices")) {
				Devices device = new Devices();
				device.setId(val.get("id").textValue());
				device.setDeviceType(deviceType);
				device.setIpAddress(val.get("attributes").get("ip-address").textValue());
				device.setHostName(val.get("attributes").get("hostname").textValue());
				device.setAppsLink(appUrl + device.getId());
				devices.add(device);
			}

		});
		List<Devices> finalDevices = devices.stream()
				.filter(filterDevice -> findGivenInpputDevice(filterDevice.getIpAddress(), filterDevice.getHostName()))
				.collect(Collectors.toList());
		try {
			fetchDataFromApps(finalDevices);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static boolean findGivenInpputDevice(String ipAddress, String hostName) {
		return beans.stream().filter(input -> input.getIpAddress().equalsIgnoreCase(ipAddress)
				|| input.getHostName().equalsIgnoreCase(hostName)).collect(Collectors.toList()).size() > 0;

	}

	public static void fetchDataFromApps(List<Devices> list) throws Exception {
		String appUrl = "http://localhost:8081/api/apps/";
		RestTemplate restTemplate = new RestTemplate();
		list.forEach(data -> {
			String response = restTemplate.getForObject(appUrl + data.getId(), String.class);
			JsonNode jsonTree;
			try {
				if (response != null) {
					jsonTree = new ObjectMapper().readTree(response);
					JsonNode jsonApp = jsonTree.get("data");
					jsonApp.forEach(val -> {
						data.setAppId(val.get("id").textValue());
						data.setAppName(val.get("attributes").get("name").textValue());
					});

				}

			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		fetchDataFromOwners(list);

	}

	public static void fetchDataFromOwners(List<Devices> list) throws Exception {
		String appUrl = "http://localhost:8081/api/owners/";
		RestTemplate restTemplate = new RestTemplate();
		list.forEach(data -> {
			if (data.getAppId() != null) {
				String response = restTemplate.getForObject(appUrl + data.getAppId(), String.class);
				JsonNode jsonTree;
				try {
					if (response != null) {
						jsonTree = new ObjectMapper().readTree(response);
						JsonNode jsonOwner = jsonTree.get("data");
						data.setOwnerId(jsonOwner.get("id").textValue());
						data.setFirstName(jsonOwner.get("attributes").get("first-name").textValue());
						data.setLastName(jsonOwner.get("attributes").get("last-name").textValue());
						data.setDepartment(jsonOwner.get("attributes").get("department").textValue());
						data.setEmail(jsonOwner.get("attributes").get("email").textValue());
						data.setMobile(jsonOwner.get("attributes").get("mobile-phone").textValue());
						data.setJobDescription(jsonOwner.get("attributes").get("job-descr").textValue());
					}

				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
		list.forEach(dev -> {
			System.out.println(dev.getId() + " , " + dev.getAppName() + " , " + dev.getIpAddress() + " , "
					+ dev.getHostName() + " , " + dev.getOwnerId() + " , " + dev.getFirstName());
		});
		dwonloadCsv(list);
	}
	
	public static void dwonloadCsv(List<Devices> list) throws Exception{
		File csvOutputFile = new File("user_output.csv");

       


        CsvMapper mapper = new CsvMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);

        CsvSchema schema = CsvSchema.builder().setUseHeader(true)
                .addColumn("id")
                .addColumn("ipAddress")
                .addColumn("deviceType")
                .addColumn("hostName")
                .addColumn("appsLink")
                .addColumn("appId")
                .addColumn("appName")
                .addColumn("ownerId")
                .addColumn("department")
                .addColumn("firstName")
                .addColumn("lastName")
                .addColumn("email")
                .addColumn("mobile")
                .addColumn("jobDescription")
                .build();

        ObjectWriter writer = mapper.writerFor(Devices.class).with(schema);

        writer.writeValues(csvOutputFile).writeAll(list);

        System.out.println("Users saved to csv file under path: ");
        System.out.println(csvOutputFile);
	}
}
