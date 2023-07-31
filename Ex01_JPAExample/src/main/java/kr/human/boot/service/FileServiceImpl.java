package kr.human.boot.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.human.boot.dao.FileDAO;
import kr.human.boot.vo.FileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("FileService")
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
	
	private final FileDAO fileDAO;

	@Override
	public FileVO selectByIdx(int idx) {
		FileVO vo = fileDAO.selectByRef(idx);
		return vo;
	}

	@Override
	public void insert(FileVO vo) {
		fileDAO.insert(vo);
	}

	@Override
	public void update(FileVO vo) {
		log.info("service update : {}",vo);
		Optional<FileVO> option = fileDAO.findById(vo.getIdx());
		if(option.isEmpty())return ;
		FileVO vo2 = option.get();
		fileDAO.save(vo2);
	}

	@Override
	public void delete(FileVO vo) {
		Optional<FileVO> option = fileDAO.findById(vo.getIdx());
		if(option.isEmpty())return ;
		FileVO vo2 = option.get();
		fileDAO.delete(vo2);
	}

}
