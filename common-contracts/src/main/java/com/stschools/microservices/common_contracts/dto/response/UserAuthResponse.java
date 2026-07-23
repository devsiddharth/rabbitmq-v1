package com.stschools.microservices.common_contracts.dto.response;

import com.stschools.microservices.common_contracts.enums.Role;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponse {

    private Long id;

    private String email;

    private String password;

    private Role role;

}