package kr.human.boot.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.human.boot.dao.BoardDAO;
import kr.human.boot.vo.BoardVO;
import kr.human.boot.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("boardService")
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private final BoardDAO boardDAO;
	@Override
	public PagingVO<BoardVO> select(int start,int size,int length){
		log.info("selectCount : {}",boardDAO.count());
		PagingVO<BoardVO> pv = new PagingVO<>((int)boardDAO.count(), start, size, length);
		Pageable pageable = PageRequest.of(start-1, size,Sort.by("idx").descending());
		Page<BoardVO> vo = boardDAO.findAll(pageable);
		pv.setContent(vo.getContent());
		return pv;
	}

	@Override
	public BoardVO selectByIdx(int idx) {
		Optional<BoardVO> option = boardDAO.findById(idx);
		if(option.isEmpty())return null;
		BoardVO vo = option.get();
		return vo;
	}

	@Override
	public void insert(BoardVO vo) {
		boardDAO.insert(vo);
	}

	@Override
	public void update(BoardVO vo) {
		log.info("service update : {}",vo);
		Optional<BoardVO> option = boardDAO.findById(vo.getIdx());
		if(option.isEmpty())return ;
		BoardVO vo2 = option.get();
		vo2.setSubject(vo.getSubject());
		vo2.setContent(vo.getContent());
		boardDAO.save(vo2);
	}

	@Override
	public void delete(BoardVO vo) {
		Optional<BoardVO> option = boardDAO.findById(vo.getIdx());
		if(option.isEmpty())return ;
		BoardVO vo2 = option.get();
		boardDAO.delete(vo2);
	}

}
