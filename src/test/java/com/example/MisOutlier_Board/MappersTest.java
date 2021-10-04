package com.example.MisOutlier_Board;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.MisOutlier_Board.domain.BoardDTO;
import com.example.MisOutlier_Board.mapper.BoardMapper;

@SpringBootTest
class MappersTests {

	@Autowired
	private BoardMapper boardMapper;

	@Test
	public void testOfInsert() {
		
		BoardDTO params = new BoardDTO();
		params.setIdx((long)3);
		params.setTitle("3대 중량 공개합니다.");
		params.setContent("Squat 180kg, Dealift 200kg, BenchPress 120kg 3대 500kg입니다.");
		params.setWriter("홍찬의");
		params.setNoticeYn("Y");
		params.setSecretYn("N");
		params.setDeleteYn("N");
		LocalDateTime currentDateTime = LocalDateTime.now();
		params.setInsertTime(currentDateTime);
		
		int result = boardMapper.insertBoard(params);
		System.out.println("결과 " + result + "입니다.");
		System.out.println("id " + params.getIdx() + "입니다.");
		System.out.println("title " + params.getTitle() + "입니다.");
		System.out.println("content " + params.getContent() + "입니다.");
		System.out.println("Writer " + params.getWriter() + "입니다.");
		System.out.println("게시시간 " + params.getInsertTime() + "입니다.");
		
	}

}