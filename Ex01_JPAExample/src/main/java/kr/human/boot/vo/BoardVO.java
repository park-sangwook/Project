package kr.human.boot.vo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "board")
public class BoardVO {
	@Id
	@GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "generator",allocationSize = 1,sequenceName = "board_idx_seq")
	private int idx;
	private String name;
	private String password;
	private String subject;
	private String content;
	@Column(name = "REGDATE")
	private Date regDate;
}
