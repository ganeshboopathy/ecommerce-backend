package com.order.order_service_api.dto.member;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequest {
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private Integer age;
}
