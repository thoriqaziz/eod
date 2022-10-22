package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eod {
    private String id;
    private String name;
    private String age;
    private float balanced;
    private Long thread2b;
    private Long thread3;
    private float prevBalanced;
    private float avgBalanced;
    private Long thread1;
    private float freeTransfer;
    private Long thread2a;
}
