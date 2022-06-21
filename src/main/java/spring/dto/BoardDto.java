package spring.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {

    private int bno;
    private String btitle;
    private String bcontent;

}
