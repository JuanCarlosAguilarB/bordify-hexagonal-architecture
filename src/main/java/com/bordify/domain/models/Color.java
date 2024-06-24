package com.bordify.domain.models;

import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
public class Color {

    private Integer id;
    private String  name;
    private String  hex;

}
