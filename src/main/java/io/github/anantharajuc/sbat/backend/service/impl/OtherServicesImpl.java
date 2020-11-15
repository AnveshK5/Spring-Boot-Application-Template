package io.github.anantharajuc.sbat.backend.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.sbat.backend.persistence.model.ApplicationSetings;
import io.github.anantharajuc.sbat.backend.persistence.repositories.ApplicationSettingsRepository;
import io.github.anantharajuc.sbat.backend.service.OtherServices;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Service
public class OtherServicesImpl implements OtherServices
{
	//Application Settings
	private String applicationName;
	private String applicationVersion;
	
	//Postman URL
	private String postmanEchoBaseUrl;
	private String postmanEchoGETurl;
	private String postmanEchoPOSTpath;
	
	private String keystoreFileName;
	private String keystoreAlias;
	private String keystorePassword;
	
	private Long verificationTokenValidity;
	private Long jwtExpirationTime;
	
	private String mailFrom;
	private String mailSubject;

	private int springMailPort;
	private String springMailProtocol;
	private String springMailUsername;
	private String springMailPassword;
	
	private String springMailHost;
	
	@Autowired
	private ApplicationSettingsRepository applicationSettingsRepository;
		
	@Override
	public void loadApplicationSettings() 
	{
		log.info("-----> Loading Application Settings Value");
		
		List<ApplicationSetings> applicationSettingsList = applicationSettingsRepository.findAll();
		
		HashMap<String, String> applicationSettingsHashMap = new HashMap<>(); 
		
		for(int i = 0; i< applicationSettingsList.size(); i++)
		{
			applicationSettingsHashMap.put(applicationSettingsList.get(i).getAppKey(), applicationSettingsList.get(i).getAppValue());
		}
		
		setApplicationName(applicationSettingsHashMap.get("applicationName"));
		setApplicationVersion(applicationSettingsHashMap.get("applicationVersion"));
		
		setPostmanEchoBaseUrl(applicationSettingsHashMap.get("postmanEchoBaseUrl"));
		setPostmanEchoGETurl(applicationSettingsHashMap.get("postmanEchoGETurl")); 
		setPostmanEchoPOSTpath(applicationSettingsHashMap.get("postmanEchoPOSTpath"));
		
		setKeystoreFileName(applicationSettingsHashMap.get("keystoreFileName"));
		setKeystoreAlias(applicationSettingsHashMap.get("keystoreAlias"));
		setKeystorePassword(applicationSettingsHashMap.get("keystorePassword"));
		setVerificationTokenValidity(Long.parseLong(applicationSettingsHashMap.get("verificationTokenValidity")));
		setJwtExpirationTime(Long.parseLong(applicationSettingsHashMap.get("jwtExpirationTime")));
		
		setMailFrom(applicationSettingsHashMap.get("mailFrom"));
		setMailSubject(applicationSettingsHashMap.get("mailSubject"));

		setSpringMailPort(Integer.parseInt(applicationSettingsHashMap.get("springMailPort")));
		setSpringMailProtocol(applicationSettingsHashMap.get("springMailProtocol"));
		setSpringMailUsername(applicationSettingsHashMap.get("springMailUsername"));
		setSpringMailPassword(applicationSettingsHashMap.get("springMailPassword"));
		
		setSpringMailHost(applicationSettingsHashMap.get("springMailHost"));
	}
}
