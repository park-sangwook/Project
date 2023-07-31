package kr.human.boot.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exfile")
public class FileVO {
	@Id
	@GeneratedValue(generator = "generator",strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "generator",allocationSize = 1,sequenceName = "exfile_idx_seq")
	private int idx;
	private int ref;
	private String uuid;
	private String name;
	public FileVO(String uuid, String name) {
		super();
		this.uuid = uuid;
		this.name = name;
	}
	
}
