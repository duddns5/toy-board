package board.model.service;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDao;
import board.model.dto.Board;
import common.db.JDBCTemplate;
import common.exception.DataAccessException;
import common.file.FileDTO;

public class BoardService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();

	public List<Board> selectBoardList() {
		
		List<Board> board = null;
		Connection conn = template.getConnection();
		try {
			board = boardDao.selectBoardList(conn);
		} finally {
			template.close(conn);
		}
		return board;
	}

	public void insertBoard(Board board, List<FileDTO> fileDTOs) {
		Connection conn = template.getConnection();

		try {
			boardDao.insertBoard(board, conn);

			for (FileDTO fileDTO : fileDTOs) {
				if (fileDTO.getOriginFileName() != null) {
					boardDao.insertFile(fileDTO, conn);
				}
			}

			template.commit(conn);
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}

	}

}
