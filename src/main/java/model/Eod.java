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
    private long thread2b;
    private long thread3;
    private float prevBalanced;
    private float avgBalanced;
    private long thread1;
    private float freeTransfer;
    private long thread2a;
}
