package com.mtn.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetApiUserResponse {

      private String  providerCallbackHost;
      private String targetEnvironment;

}
