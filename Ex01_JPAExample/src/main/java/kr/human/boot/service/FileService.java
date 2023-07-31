package kr.human.boot.service;

import kr.human.boot.vo.FileVO;

public interface FileService {
	FileVO selectByIdx(int idx);
	void insert(FileVO vo);
	void update(FileVO vo);
	void delete(FileVO vo);
}
