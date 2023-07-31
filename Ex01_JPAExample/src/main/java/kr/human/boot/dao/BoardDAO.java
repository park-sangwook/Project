package kr.human.boot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import kr.human.boot.vo.BoardVO;

public interface BoardDAO extends JpaRepository<BoardVO, Integer>{
	public static final String insert = "insert into board values(board_idx_seq.nextval,:#{#vo.name},:#{#vo.password}"
			+ ",:#{#vo.subject},:#{#vo.content},sysdate)";
	@Transactional
	@Modifying
	@Query(value = insert,nativeQuery = true)
	public void insert(@Param("vo") BoardVO vo);
	List<BoardVO> findAllByOrderByIdxDesc();
}