package com.wtw.springcloud.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Payment {
    private Long id;
    private String serial;

}
