package com.session04.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstructorCreateRequest {
    private String name;
    private String email;
}
