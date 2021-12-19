package board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.model.dao.BoardDao;
import board.model.dto.Board;
import common.db.JDBCTemplate;
import common.exception.DataAccessException;
import common.file.FileDTO;
import common.util.Paging;

public class BoardService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private BoardDao boardDao = new BoardDao();

	public Map<String,Object> selectBoardList(int page) {
		
		List<Board> board = null;
		Paging pageUtil = null;
		Connection conn = template.getConnection();
		int cntPerPage = 15;
		try {
			board = boardDao.selectBoardList(conn,1+(page-1)*cntPerPage,page*cntPerPage);
			
			pageUtil = Paging.builder()
					.url("/board/board-list")
					.total(boardDao.selectBoardCount(conn))
					.cntPerPage(cntPerPage)
					.blockCnt(3)
					.curPage(page)
					.build();
		} finally {
			template.close(conn);
		}
		return Map.of("board",board,"paging",pageUtil);
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

	public void updateViewCount(int bdIdx) {
		
		Connection conn = template.getConnection();
		try {
			boardDao.updateViewCount(bdIdx, conn);
			template.commit(conn);
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	public Map<String, Object> selectBoardDetail(int bdIdx) {
		Connection conn = template.getConnection();
		Map<String, Object> res = new HashMap<String, Object>();
		
		try {
			Board board = boardDao.selectBoardDetail(bdIdx, conn);
			List<FileDTO> files = boardDao.selectFileDTOs(bdIdx, conn);
			res.put("board", board);
			res.put("files", files);
		} finally {
			template.close(conn);
		}
		return res;
	}

	public void updateLikeCount(int bdIdx) {
		Connection conn = template.getConnection();
		try {
			boardDao.updateLikeCount(bdIdx, conn);
			template.commit(conn);
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	public Board selectBoardByBdIdx(int bdIdx) {
		Connection conn = template.getConnection();
		Board board = null;
		try {
			board = boardDao.selectBoardDetail(bdIdx, conn);
		} finally {
			template.close(conn);
		}
		return board;
	}

	public void deleteBoardByBdIdx(int bdIdx) {
		Connection conn = template.getConnection();
		List<FileDTO> fileDTO = new ArrayList<FileDTO>();
		try {
			fileDTO = boardDao.selectFileDTOs(bdIdx, conn);
			if(fileDTO != null) {
				boardDao.deleteBoardByBdIdx(bdIdx, conn);
				boardDao.deleteFileByBdIdx(bdIdx,conn);
			}else {
				boardDao.deleteBoardByBdIdx(bdIdx, conn);
			}
			template.commit(conn);
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}

	public void updateBoardByBdIdx(Board board, List<FileDTO> fileDTOs) {
		Connection conn = template.getConnection();
		List<FileDTO> files = new ArrayList<FileDTO>(); 
		try {
			files = boardDao.selectFileDTOs(board.getBdIdx(), conn);
			if(files != null) {
				boardDao.deleteFileByBdIdx(board.getBdIdx(), conn);
			}
			for (FileDTO fileDTO : fileDTOs) {
				if(fileDTO.getOriginFileName() != null) {
					boardDao.insertFile(fileDTO, conn, board.getBdIdx());
				}
			}
			boardDao.updateBoard(board,conn);
			template.commit(conn);
		} catch (DataAccessException e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}


}
