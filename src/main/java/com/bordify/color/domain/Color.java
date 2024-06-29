package com.bordify.color.domain;

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
