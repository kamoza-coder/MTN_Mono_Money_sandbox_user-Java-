package com.mtn;

import com.mtn.dto.APIUser;
import com.mtn.responses.APIUserResponse;
import com.mtn.responses.GetApiKeyResponse;
import com.mtn.responses.GetApiUserResponse;
import com.mtn.services.MTNServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MtnApplicationTests {

	private MTNServices mtnServices;

	@Autowired
	public void setMtnServices(MTNServices mtnServices) {
		this.mtnServices = mtnServices;
	}
	@Test
	void contextLoads() {
	}

	@Test
	void testCreateApiUser() {
		APIUser apiUser = new APIUser();
		apiUser.setProviderCallbackHost("TestingMtnZimasweas");
		APIUserResponse response = mtnServices.createApiUser(apiUser);
		assertThat(response).isNull();
	}

	@Test
	void testGetApiUer() {
		GetApiUserResponse response = mtnServices.getApiUer("83b15281-91f9-47be-8833-2c2c04579eb9");
		assertThat(response.getTargetEnvironment()).isEqualTo("sandbox");
	}

	@Test
	void testGetApiKey() {
		GetApiKeyResponse response = mtnServices.getApiKey("83b15281-91f9-47be-8833-2c2c04579eb9");
		assertThat(response.getApiKey()).isNotBlank();
	}

}
