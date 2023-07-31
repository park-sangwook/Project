package kr.human.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import kr.human.boot.vo.FileVO;

public interface FileDAO extends JpaRepository<FileVO, Integer>{
	public static final String insert="insert into exfile values(exfile_idx_seq.nextval,board_idx_seq.currval,:#{#vo.uuid},:#{#vo.name})";
	@Transactional
	@Modifying
	@Query(value = insert,nativeQuery = true)
	public void insert(@Param("vo") FileVO vo);
	@Query(value = "select * from exfile where ref=:idx",nativeQuery = true)
	public FileVO selectByRef(@Param("idx") int idx);
}
