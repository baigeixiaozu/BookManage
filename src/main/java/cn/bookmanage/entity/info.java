package cn.bookmanage.entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class info {
    private Long info_id;
    private String content;
    private Date time;
    private String receiver;
    private String sender;
    private int read_status;
}
